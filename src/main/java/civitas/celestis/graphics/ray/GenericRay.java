package civitas.celestis.graphics.ray;

import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;

/**
 * <h2>GenericRay</h2>
 * <p>
 * A class implementing the {@link Ray} interface to represent a ray in three-dimensional space.
 * </p>
 */
public class GenericRay implements Ray {
    /**
     * Constructs a GenericRay instance with the provided origin and direction.
     *
     * @param origin    The origin vector of the ray.
     * @param direction The direction vector of the ray.
     */
    public GenericRay(@Nonnull Vector3 origin, @Nonnull Vector3 direction) {
        this.origin = origin;
        this.direction = direction.normalize();
    }

    /**
     * Constructs a GenericRay instance by copying another GenericRay instance.
     *
     * @param other The other GenericRay instance to copy from.
     */
    public GenericRay(@Nonnull GenericRay other) {
        this.origin = other.origin;
        this.direction = other.direction;
    }

    private final Vector3 origin;
    private final Vector3 direction;

    /**
     * Returns the origin vector of the ray.
     *
     * @return The origin vector.
     */
    @Nonnull
    @Override
    public Vector3 origin() {
        return origin;
    }

    /**
     * Returns the direction vector of the ray.
     *
     * @return The direction vector.
     */
    @Nonnull
    @Override
    public Vector3 direction() {
        return direction;
    }

    /**
     * Returns the destination point of the ray at a given parameter value t.
     *
     * @param t The parameter value.
     * @return The destination vector.
     */
    @Nonnull
    @Override
    public Vector3 destination(double t) {
        return origin.add(direction.multiply(t));
    }

    @Override
    @Nonnull
    public String toString() {
        return "GenericRay{" +
                "origin=" + origin +
                ", direction=" + direction +
                '}';
    }
}
