package civitas.celestis.task;

/**
 * <h2>Task</h2>
 * <p>
 * The {@code Task} interface represents a unit of work that can be executed repeatedly at specified intervals.
 * Implementing classes must provide an implementation for the {@link #execute(long)} method, which defines the task's behavior.
 * The interval between task executions can be customized by overriding the {@link #interval()} method.
 * All time values are denoted in milliseconds.
 * </p>
 */
public interface Task {
    /**
     * Executes the task's logic.
     *
     * @param delta The time elapsed since the last execution, in milliseconds.
     */
    void execute(long delta);

    /**
     * Returns the interval between task executions.
     *
     * @return The interval in milliseconds.
     */
    default long interval() {
        return 1;
    }
}
