package civitas.celestis;

import civitas.celestis.listener.object.ObjectCollisionListener;
import civitas.celestis.module.EventManager;
import civitas.celestis.module.Scheduler;
import civitas.celestis.module.WorldManager;
import civitas.celestis.task.object.MovementTask;
import civitas.celestis.task.physics.GravityTask;
import civitas.celestis.task.physics.ResistanceTask;
import civitas.celestis.task.world.UpdateOverlapTask;
import jakarta.annotation.Nonnull;

import java.util.List;

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
        registerTasks();
        registerListeners();

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
    // Internal Methods
    //

    /**
     * Registers tasks to the scheduler.
     */
    private static void registerTasks() {
        // Object tasks
        scheduler.register(new MovementTask());

        // Physics tasks
        scheduler.registerAsync(List.of(
                new GravityTask(),
                new ResistanceTask()
        ));

        // World tasks
        scheduler.register(new UpdateOverlapTask());
    }

    /**
     * Registers event listeners to the event manager.
     */
    private static void registerListeners() {
        eventManager.register(new ObjectCollisionListener());
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

    /**
     * Retrieves the world manager instance associated with the Xenon game engine.
     *
     * @return The world manager.
     */
    @Nonnull
    public static WorldManager getWorldManager() {
        return worldManager;
    }

    //
    // Definitions
    //

    private static final EventManager eventManager = new EventManager();
    private static final Scheduler scheduler = new Scheduler();
    private static final WorldManager worldManager = new WorldManager();
}
