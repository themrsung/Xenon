package civitas.celestis.graphics.ray;

import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;

/**
 * <h2>Ray</h2>
 *
 * <p>The {@code Ray} interface represents a ray in three-dimensional space. A ray consists of an origin point
 * and a direction vector. It is often used in various geometric and rendering calculations, such as ray tracing
 * and collision detection.</p>
 */
public interface Ray {
    /**
     * Returns the origin point of the ray.
     *
     * @return The origin point vector.
     */
    @Nonnull
    Vector3 origin();

    /**
     * Returns the direction vector of the ray.
     *
     * @return The direction vector.
     */
    @Nonnull
    Vector3 direction();

    /**
     * Calculates the destination point on the ray given a parameter value {@code t}. The parameter {@code t}
     * determines how far along the ray the destination point is located.
     *
     * @param t The parameter value to calculate the destination point.
     * @return The destination point vector at parameter value {@code t}.
     */
    @Nonnull
    Vector3 destination(double t);
}
