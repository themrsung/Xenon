package civitas.celestis.object;

import jakarta.annotation.Nonnull;

import java.util.UUID;

/**
 * <h2>Unique</h2>
 * <p>
 * The {@code Unique} interface represents objects that have a unique identifier.
 * It provides a method to retrieve the unique identifier of an object.
 * </p>
 */
public interface Unique {
    /**
     * Retrieves the unique identifier (UUID) associated with this object.
     *
     * @return The unique identifier of the object.
     */
    @Nonnull
    UUID getUniqueId();
}
