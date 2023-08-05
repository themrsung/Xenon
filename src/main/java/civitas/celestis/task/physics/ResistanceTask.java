package civitas.celestis.task.physics;

import civitas.celestis.Xenon;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.object.BaseObject;
import civitas.celestis.object.XenonObjects;
import civitas.celestis.task.Task;
import civitas.celestis.world.World;

/**
 * <h2>ResistanceTask</h2>
 * <p>
 * A task that applies air resistance to objects in a world based on their properties and current velocity.
 * </p>
 */
public final class ResistanceTask implements Task {
    /**
     * Executes the resistance task by calculating and applying air resistance to objects in all worlds.
     *
     * @param delta The time interval in milliseconds since the last execution of the task.
     */
    @Override
    public void execute(long delta) {
        final double seconds = delta / 1000d;

        for (final World world : Xenon.getWorldManager().getWorlds()) {
            final double density = world.getAirDensity();

            if (density <= 0) continue;

            for (final BaseObject object : world.getObjects()) {
                final Vector3 acceleration = object.getAcceleration();
                final double dragCoefficient = XenonObjects.dragCoefficient(object);
                final double crossSection = XenonObjects.crossSection(object);
                final double mass = object.getSolid().mass();

                // Calculate and apply air resistance
                final Vector3 velocity = acceleration.multiply(seconds);
                final Vector3 direction = velocity.normalize();
                final double speedSquared = velocity.magnitude2();
                final double dragForceMagnitude = 0.5 * density * dragCoefficient * crossSection * speedSquared;

                // Calculate the drag force and apply it as an acceleration in the opposite direction
                final Vector3 dragForce = direction.multiply(-dragForceMagnitude);
                final Vector3 dragAcceleration = dragForce.multiply(1.0 / mass);
                object.accelerate(dragAcceleration);
            }
        }
    }
}
