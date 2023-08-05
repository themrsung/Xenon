package civitas.celestis.task.object;

import civitas.celestis.Xenon;
import civitas.celestis.object.BaseObject;
import civitas.celestis.task.Task;
import civitas.celestis.world.World;

/**
 * Implementation of the {@link Task} interface that performs movement and rotation updates on objects
 * within different worlds based on the elapsed time.
 */
public final class MovementTask implements Task {
    /**
     * Executes the movement and rotation updates for objects within different worlds.
     *
     * @param delta The time interval between the current and previous execution in milliseconds.
     */
    @Override
    public void execute(long delta) {
        // Convert delta to seconds for consistent calculations
        final double seconds = delta / 1000d;

        // Iterate through all worlds
        for (final World world : Xenon.getWorldManager().getWorlds()) {
            // Update objects within the current world
            for (final BaseObject object : world.getObjects()) {
                // Update object's position based on its acceleration
                object.move(object.getAcceleration().multiply(seconds));

                // Update object's rotation based on its rotation rate
                object.rotate(object.getRotationRate().scale(seconds));
            }
        }
    }
}
