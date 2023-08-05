package civitas.celestis;

import civitas.celestis.module.EventManager;
import civitas.celestis.module.Scheduler;
import jakarta.annotation.Nonnull;

/**
 * <h2>Xenon</h2>
 *
 * <p>The {@code Xenon} class represents a 3D game engine that provides methods for starting and stopping the engine,
 * as well as accessing modules such as the event manager and scheduler. It serves as a central point for
 * managing game-related functionality and resources.</p>
 */
public final class Xenon {
    //
    // Lifecycle
    //

    /**
     * Starts the Xenon game engine by initializing modules.
     */
    public static void start() {
        eventManager.start();
        scheduler.start();
    }

    /**
     * Stops the Xenon game engine by stopping modules.
     */
    public static void stop() {
        eventManager.stop();
        scheduler.stop();
    }

    //
    // Getters
    //

    /**
     * Retrieves the event manager instance associated with the Xenon game engine.
     *
     * @return The event manager.
     */
    @Nonnull
    public static EventManager getEventManager() {
        return eventManager;
    }

    /**
     * Retrieves the scheduler instance associated with the Xenon game engine.
     *
     * @return The scheduler.
     */
    @Nonnull
    public static Scheduler getScheduler() {
        return scheduler;
    }

    //
    // Definitions
    //

    private static final EventManager eventManager = new EventManager();
    private static final Scheduler scheduler = new Scheduler();
}
