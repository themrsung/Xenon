package civitas.celestis.physics.solid;

import civitas.celestis.math.quaternion.Quaternion;
import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;

/**
 * <h2>Solid</h2>
 * <p>
 * The {@code Solid} interface represents a physical object with properties related to mass, volume, density,
 * and geometric attributes. It provides methods to calculate various physical and geometric properties of the solid.
 * </p>
 */
public interface Solid {
    /**
     * Returns the centroid (geometric center) of the solid.
     *
     * @return The centroid of the solid.
     */
    @Nonnull
    Vector3 centroid();

    /**
     * Returns the rotation of the solid as a quaternion.
     *
     * @return The rotation of the solid.
     */
    @Nonnull
    Quaternion rotation();

    /**
     * Updates the solid's centroid and rotation to new values.
     *
     * @param newCentroid The new centroid to set.
     * @param newRotation The new rotation to set.
     * @return A new instance of the solid with updated centroid and rotation.
     */
    @Nonnull
    Solid update(@Nonnull Vector3 newCentroid, @Nonnull Quaternion newRotation);

    /**
     * Returns the mass of the solid.
     *
     * @return The mass of the solid.
     */
    double mass();

    /**
     * Returns the volume of the solid.
     *
     * @return The volume of the solid.
     */
    double volume();

    /**
     * Returns the density of the solid.
     *
     * @return The density of the solid.
     */
    double density();

    /**
     * Returns an array of corner points that define the shape of the solid.
     *
     * @return An array of corner points.
     */
    @Nonnull
    Vector3[] corners();

    /**
     * Returns the number of corners that define the shape of the solid.
     *
     * @return The number of corners.
     */
    int numCorners();

    /**
     * Calculates the drag coefficient of the solid in the given view direction.
     *
     * @param viewDirection The direction from which the solid is viewed.
     * @return The drag coefficient of the solid.
     */
    double dragCoefficient(@Nonnull Vector3 viewDirection);

    /**
     * Calculates the cross-sectional area of the solid in the given view direction.
     *
     * @param viewDirection The direction from which the solid is viewed.
     * @return The cross-sectional area of the solid.
     */
    double crossSection(@Nonnull Vector3 viewDirection);

    /**
     * Checks whether a given point is located inside the solid.
     *
     * @param point The point to check.
     * @return {@code true} if the point is inside the solid, {@code false} otherwise.
     */
    boolean contains(@Nonnull Vector3 point);

    /**
     * Checks whether the solid overlaps with another solid.
     *
     * @param other The other solid to check for overlap.
     * @return {@code true} if the solids overlap, {@code false} otherwise.
     */
    boolean overlaps(@Nonnull Solid other);
}
