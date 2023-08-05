package civitas.celestis.task.physics;

import civitas.celestis.Xenon;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.object.BaseObject;
import civitas.celestis.task.Task;
import civitas.celestis.world.World;

/**
 * <h2>Gravity</h2>
 * <p>
 * A task that simulates the effect of gravity on objects in the worlds.
 * </p>
 */
public final class GravityTask implements Task {
    /**
     * Executes the gravity simulation task.
     *
     * @param delta The time elapsed since the last execution, in milliseconds.
     */
    @Override
    public void execute(long delta) {
        final double seconds = delta / 1000d;

        for (final World world : Xenon.getWorldManager().getWorlds()) {
            final Vector3 gravity = world.getGravity();

            // Skip objects if no gravity effect is present
            if (gravity.equals(Vector3.ZERO)) continue;

            // Apply gravity to all objects in the world
            for (final BaseObject object : world.getObjects()) {
                object.accelerate(gravity.multiply(seconds));
            }
        }
    }
}