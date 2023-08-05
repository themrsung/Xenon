package civitas.celestis.task.world;

import civitas.celestis.Xenon;
import civitas.celestis.task.Task;
import civitas.celestis.world.World;

/**
 * <h2>UpdateOverlapTask</h2>
 * <p>
 * A task responsible for updating the overlapping object pairs in the worlds managed by Xenon.
 * This task iterates through all the worlds managed by Xenon's world manager and calls the
 * {@link World#updateOverlaps()} method for each world to refresh the list of overlapping objects.
 * </p>
 */
public final class UpdateOverlapTask implements Task {
    /**
     * Executes the task to update overlapping object pairs in the managed worlds.
     *
     * @param delta The time interval in milliseconds since the last execution.
     */
    @Override
    public void execute(long delta) {
        for (final World world : Xenon.getWorldManager().getWorlds()) {
            world.updateOverlaps();
        }
    }
}
