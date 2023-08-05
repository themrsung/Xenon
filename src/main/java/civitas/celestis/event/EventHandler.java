package civitas.celestis.event;

import jakarta.annotation.Nonnull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <h2>EventHandler</h2>
 *
 * <p>The {@code EventHandler} annotation is used to mark methods as event handlers.
 * These methods will be invoked when corresponding events are triggered.</p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EventHandler {
    /**
     * Returns the priority level at which the event handler should be executed.
     *
     * @return The priority level of the event handler.
     * @implSpec The default priority is {@link HandlerPriority#NORMAL}.
     */
    @Nonnull
    HandlerPriority priority() default HandlerPriority.NORMAL;
}
