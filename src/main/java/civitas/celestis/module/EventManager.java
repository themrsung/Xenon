package civitas.celestis.module;

import civitas.celestis.event.Event;
import civitas.celestis.event.EventHandler;
import civitas.celestis.event.Listener;
import jakarta.annotation.Nonnull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * <h2>EventManager</h2>
 * <p>
 * The {@code EventManager} class handles event management and dispatching for registered listeners and handlers.
 * It provides methods for starting and stopping the event processing thread, as well as registering and unregistering
 * listeners. Events can be called through the {@link #call(Event)} method, and event handlers are invoked based on their
 * annotated priorities.
 * </p>
 */
public final class EventManager {
    /**
     * Starts the event processing thread.
     */
    public void start() {
        thread.start();
    }

    /**
     * Stops the event processing thread.
     */
    public void stop() {
        thread.interrupt();
    }

    /**
     * Calls an event, adding it to the event queue for processing.
     *
     * @param event The event to be called.
     */
    public <E extends Event> void call(@Nonnull E event) {
        queue.add(event);
    }

    /**
     * Registers a listener for event handling.
     *
     * @param listener The listener to be registered.
     */
    public void register(@Nonnull Listener listener) {
        listeners.add(listener);
    }

    /**
     * Registers a collection of listeners for event handling.
     *
     * @param listeners The collection of listeners to be registered.
     */
    public void register(@Nonnull Collection<Listener> listeners) {
        this.listeners.addAll(listeners);
    }

    /**
     * Unregisters a listener from event handling.
     *
     * @param listener The listener to be unregistered.
     */
    public void unregister(@Nonnull Listener listener) {
        listeners.remove(listener);
    }

    /**
     * Unregisters a collection of listeners from event handling.
     *
     * @param listeners The collection of listeners to be unregistered.
     */
    public void unregister(@Nonnull Collection<Listener> listeners) {
        this.listeners.removeAll(listeners);
    }

    private final Queue<Event> queue = new LinkedList<>();
    private final List<Listener> listeners = new ArrayList<>();
    private final Thread thread = new Thread(() -> {
        while (true) {
            if (Thread.interrupted()) return;

            // Retrieve the next event from the queue
            final Event event = queue.poll();
            if (event == null) continue;

            // Create a list to store handler references
            final List<HandlerReference> handlers = new ArrayList<>();

            // Iterate through copied list of listeners and their methods to find handlers for the event
            for (final Listener listener : List.copyOf(listeners)) {
                for (final Method method : listener.getClass().getDeclaredMethods()) {
                    // Check for the presence of the EventHandler annotation
                    if (!method.isAnnotationPresent(EventHandler.class)) continue;

                    // Check the parameter count and type compatibility
                    if (method.getParameterCount() != 1) continue;
                    if (!method.getParameterTypes()[0].isAssignableFrom(event.getClass())) continue;

                    // Add the handler reference to the list
                    handlers.add(new HandlerReference(listener, method));
                }
            }

            // Sort handlers based on priority from the EventHandler annotation
            handlers.sort(Comparator.comparing(h -> h.method().getAnnotation(EventHandler.class).priority()));

            // Invoke each handler for the event
            for (final HandlerReference handler : handlers) {
                try {
                    handler.method().invoke(handler.listener(), event);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    // Fail silently and move on to the next handler
                }
            }
        }
    }, "EventManager");

    /**
     * A record that holds a reference to a listener and a method that serves as an event handler.
     * This is used to store event handler information for later invocation.
     */
    private record HandlerReference(@Nonnull Listener listener, @Nonnull Method method) {}
}
