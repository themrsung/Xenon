package civitas.celestis.module;

import civitas.celestis.world.World;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <h2>WorldManager</h2>
 * <p>
 * A class responsible for managing different worlds in Xenon engine.
 * </p>
 */
public class WorldManager {
    /**
     * Returns a list of all the worlds managed by this WorldManager.
     *
     * @return A list of worlds.
     */
    @Nonnull
    public List<World> getWorlds() {
        return new ArrayList<>(worlds);
    }

    /**
     * Retrieves a world by its unique identifier.
     *
     * @param uniqueId The unique identifier of the world.
     * @return The world with the given unique identifier.
     * @throws NullPointerException if a world with the specified unique identifier is not found.
     */
    @Nonnull
    public World getWorld(@Nonnull UUID uniqueId) throws NullPointerException {
        for (final World world : getWorlds()) {
            if (world.getUniqueId().equals(uniqueId)) {
                return world;
            }
        }

        throw new NullPointerException("World of unique identifier " + uniqueId + " does not exist.");
    }

    /**
     * Retrieves a world by its name.
     *
     * @param name The name of the world.
     * @return The world with the given name, or null if not found.
     */
    @Nullable
    public World getWorld(@Nonnull String name) {
        for (final World world : getWorlds()) {
            if (world.getName().equals(name)) {
                return world;
            }
        }

        return null;
    }

    /**
     * Adds a new world to the managed list of worlds.
     *
     * @param world The world to be added.
     */
    public void addWorld(@Nonnull World world) {
        worlds.add(world);
    }

    /**
     * Removes a world from the managed list of worlds.
     *
     * @param world The world to be removed.
     */
    public void removeWorld(@Nonnull World world) {
        worlds.remove(world);
    }

    private final List<World> worlds = new ArrayList<>();

}
