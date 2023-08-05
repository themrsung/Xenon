package civitas.celestis.event;

/**
 * <h2>HandlerPriority</h2>
 *
 * <p>The {@code HandlerPriority} enum defines different priority levels for event handlers.
 * These priorities determine the order in which event handlers are executed.</p>
 */
public enum HandlerPriority {
    PREEMPTIVE,
    PRE_EARLY,
    EARLY,
    POST_EARLY,
    PRE_NORMAL,
    NORMAL,
    POST_NORMAL,
    PRE_LATE,
    LATE,
    POST_LATE,
    PERMISSIVE;
}
