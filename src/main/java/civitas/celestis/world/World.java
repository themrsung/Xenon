package civitas.celestis.world;

import civitas.celestis.object.BaseObject;
import civitas.celestis.object.Unique;
import civitas.celestis.util.group.Pair;
import jakarta.annotation.Nonnull;

import java.util.List;
import java.util.UUID;

/**
 * <h2>World</h2>
 * <p>
 * The {@code World} interface represents a virtual world containing various {@link BaseObject}s.
 * It defines methods to manage and interact with the objects within the world.
 * </p>
 */
public interface World extends Unique {

    /**
     * Returns the name of the world.
     *
     * @return The name of the world.
     */
    @Nonnull
    String getName();

    /**
     * Returns a list of all objects currently present in the world.
     *
     * @return A list of BaseObjects in the world.
     */
    @Nonnull
    List<BaseObject> getObjects();

    /**
     * Retrieves a specific object from the world using its unique identifier.
     *
     * @param uniqueId The unique identifier of the object.
     * @return The BaseObject with the provided unique identifier.
     * @throws NullPointerException If no object with the given uniqueId exists.
     */
    @Nonnull
    BaseObject getObject(@Nonnull UUID uniqueId) throws NullPointerException;

    /**
     * Returns a list of pairs of BaseObjects that overlap with each other within the world.
     *
     * @return A list of pairs representing overlapping BaseObjects.
     */
    @Nonnull
    List<Pair<BaseObject>> getOverlaps();

    /**
     * Adds a BaseObject to the world.
     *
     * @param object The BaseObject to add to the world.
     */
    void addObject(@Nonnull BaseObject object);

    /**
     * Removes a BaseObject from the world.
     *
     * @param object The BaseObject to remove from the world.
     */
    void removeObject(@Nonnull BaseObject object);
}
