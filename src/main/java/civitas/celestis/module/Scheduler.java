package civitas.celestis.module;

import civitas.celestis.task.Task;
import civitas.celestis.util.counter.Repeater;
import jakarta.annotation.Nonnull;

import java.util.*;

/**
 * <h2>Scheduler</h2>
 * <p>
 * The {@code Scheduler} class manages the scheduling of tasks using multiple {@link SchedulerCore} instances.
 * It provides methods to start and stop the scheduler, register and unregister tasks, and manage the distribution
 * of tasks among the scheduler cores.
 * </p>
 */
public final class Scheduler {
    /**
     * Starts all the scheduler cores, allowing registered tasks to be executed.
     */
    public void start() {
        for (final SchedulerCore core : cores) {
            core.start();
        }
    }

    /**
     * Stops all the scheduler cores, preventing further execution of tasks.
     */
    public void stop() {
        for (final SchedulerCore core : cores) {
            core.stop();
        }
    }

    /**
     * Registers a task to be managed by the scheduler. The task is added to one of the scheduler cores for execution.
     *
     * @param task The task to be registered.
     */
    public void register(@Nonnull Task task) {
        nextCore().register(task);
    }

    /**
     * Registers a collection of tasks to be managed by the scheduler. The tasks are added to a single scheduler core
     * for synchronous execution.
     *
     * @param tasks The collection of tasks to be registered.
     */
    public void registerSync(@Nonnull Collection<Task> tasks) {
        final SchedulerCore core = nextCore();

        for (final Task task : tasks) {
            core.register(task);
        }
    }

    /**
     * Registers a collection of tasks to be managed by the scheduler. The tasks are distributed among the scheduler
     * cores for asynchronous execution.
     *
     * @param tasks The collection of tasks to be registered.
     */
    public void registerAsync(@Nonnull Collection<Task> tasks) {
        for (final Task task : tasks) {
            register(task);
        }
    }

    /**
     * Unregisters a task from all the scheduler cores.
     *
     * @param task The task to be unregistered.
     */
    public void unregister(@Nonnull Task task) {
        for (final SchedulerCore core : cores) {
            core.unregister(task);
        }
    }

    /**
     * Unregisters a collection of tasks from all the scheduler cores.
     *
     * @param tasks The collection of tasks to be unregistered.
     */
    public void unregister(@Nonnull Collection<Task> tasks) {
        for (final Task task : tasks) {
            unregister(task);
        }
    }

    /**
     * Returns the next available scheduler core for task distribution.
     *
     * @return The next available scheduler core.
     */
    @Nonnull
    private SchedulerCore nextCore() {
        return cores[repeater.next()];
    }

    // An array of scheduler cores
    private final SchedulerCore[] cores = {
            new SchedulerCore("Scheduler-1"),
            new SchedulerCore("Scheduler-2"),
            new SchedulerCore("Scheduler-3"),
            new SchedulerCore("Scheduler-4"),
            new SchedulerCore("Scheduler-5"),
            new SchedulerCore("Scheduler-6"),
            new SchedulerCore("Scheduler-7"),
            new SchedulerCore("Scheduler-8")
    };

    // A repeater used to distribute tasks among the scheduler cores
    private final Repeater repeater = new Repeater(cores.length);

    /**
     * <h2>SchedulerCore</h2>
     * <p>
     * The {@code SchedulerCore} class manages the execution of tasks at specified intervals. It maintains a list of tasks
     * to be executed and their corresponding execution times. The execution of tasks is controlled by a dedicated thread.
     * Tasks are repeatedly executed at intervals defined by their {@link Task#interval()} values.
     * </p>
     */
    private static final class SchedulerCore {
        /**
         * Constructs a {@code SchedulerCore} instance with the specified name.
         *
         * @param name The name of the scheduler thread.
         */
        public SchedulerCore(@Nonnull String name) {
            this.thread = new Thread(() -> {
                while (true) {
                    if (Thread.interrupted()) return;

                    // Iterate through a copy of the tasks list to avoid concurrent modifications
                    for (final Task task : List.copyOf(tasks)) {
                        final long now = System.currentTimeMillis();
                        final long previous = times.getOrDefault(task, now);
                        final long delta = now - previous;

                        // Check if the time interval for the task has passed
                        if (delta < task.interval()) continue;

                        // Execute the task and update the execution time
                        task.execute(delta);
                        times.put(task, now);
                    }
                }
            }, name);
        }

        /**
         * Starts the scheduler thread.
         */
        public void start() {
            thread.start();
        }

        /**
         * Stops the scheduler thread.
         */
        public void stop() {
            thread.interrupt();
        }

        /**
         * Registers a {@link Task} to be managed by the scheduler. The task is added to the list of tasks to be executed
         * and its initial execution time is recorded.
         *
         * @param task The task to be registered.
         */
        public void register(@Nonnull Task task) {
            tasks.add(task);
            times.put(task, System.currentTimeMillis());
        }

        /**
         * Unregisters a {@link Task} from the scheduler. The task is removed from the list of tasks to be executed
         * and its execution time record is removed.
         *
         * @param task The task to be unregistered.
         */
        public void unregister(@Nonnull Task task) {
            tasks.remove(task);
            times.remove(task);
        }

        private final List<Task> tasks = new ArrayList<>();
        private final Map<Task, Long> times = new HashMap<>();
        private final Thread thread;
    }
}
