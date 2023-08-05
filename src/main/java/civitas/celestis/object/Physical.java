package civitas.celestis.object;

import civitas.celestis.physics.solid.Solid;
import jakarta.annotation.Nonnull;

/**
 * <h2>Physical</h2>
 * <p>
 * The {@code Physical} interface represents an object with physical properties, defined by a solid shape.
 * </p>
 */
public interface Physical {
    /**
     * Retrieve the solid shape associated with this physical object.
     *
     * @return The solid shape.
     */
    @Nonnull
    Solid getSolid();

    /**
     * Sets the physical representation of the object.
     *
     * @param solid The physical representation to set.
     */
    void setSolid(@Nonnull Solid solid);
}
