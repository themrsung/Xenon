package civitas.celestis.listener.object;

import civitas.celestis.event.EventHandler;
import civitas.celestis.event.HandlerPriority;
import civitas.celestis.event.Listener;
import civitas.celestis.event.object.ObjectPairEvent;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.object.BaseObject;
import jakarta.annotation.Nonnull;

/**
 * <h2>ObjectCollisionListener</h2>
 * <p>
 * Represents a listener for object collision events.
 * </p>
 */
public final class ObjectCollisionListener implements Listener {
    /**
     * Handles the event when two objects have collided.
     *
     * @param event The object pair event indicating the collision.
     */
    @EventHandler(priority = HandlerPriority.PERMISSIVE)
    public void onObjectsCollided(@Nonnull ObjectPairEvent event) {
        final BaseObject o1 = event.getObjects().a();
        final BaseObject o2 = event.getObjects().b();

        final Vector3 u1 = o1.getAcceleration();
        final Vector3 u2 = o2.getAcceleration();

        final double m1 = o1.getSolid().mass();
        final double m2 = o2.getSolid().mass();

        final Vector3 v1; // Final velocity of o1
        final Vector3 v2; // Final velocity of o2

        final double denominator = m1 + m2;

        if (denominator != 0) {
            v1 = u1.subtract(u1.subtract(u2).multiply(m2 / denominator));
            v2 = u2.subtract(u2.subtract(u1).multiply(m1 / denominator));
        } else {
            v1 = Vector3.ZERO;
            v2 = Vector3.ZERO;
        }

        o1.setAcceleration(v1);
        o2.setAcceleration(v2);
    }
}
