package civitas.celestis.graphics.util;

import civitas.celestis.graphics.face.Face;
import civitas.celestis.graphics.ray.GenericRay;
import civitas.celestis.graphics.ray.Ray;
import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * <h2>Collisions</h2>
 * <p>
 * The {@code Collisions} class provides utility methods for handling collision detection and response.
 * This class cannot be instantiated and contains only static methods.
 * </p>
 */
public final class Collisions {
    //
    // Collisions
    //

    /**
     * Computes the intersection point between a ray and a face (triangle) using the Möller–Trumbore algorithm.
     *
     * @param ray  The ray for intersection testing.
     * @param face The face (triangle) for intersection testing.
     * @return The intersection point between the ray and the face, or null if no intersection.
     */
    @Nullable
    public static Vector3 intersection(@Nonnull Ray ray, @Nonnull Face face) {
        final Vector3 vertexA = face.a();
        final Vector3 vertexB = face.b();
        final Vector3 vertexC = face.c();

        final Vector3 edge1 = vertexB.subtract(vertexA);
        final Vector3 edge2 = vertexC.subtract(vertexA);

        final Vector3 h = ray.direction().cross(edge2);
        double a = edge1.dot(h);

        if (a > -1e-6 && a < 1e-6) {
            return null;  // Ray and triangle are parallel or nearly parallel
        }

        double f = 1.0 / a;
        final Vector3 s = ray.origin().subtract(vertexA);
        double u = f * s.dot(h);

        if (u < 0.0 || u > 1.0) {
            return null;  // Intersection point is outside the triangle
        }

        final Vector3 q = s.cross(edge1);
        double v = f * ray.direction().dot(q);

        if (v < 0.0 || u + v > 1.0) {
            return null;  // Intersection point is outside the triangle
        }

        double t = f * edge2.dot(q);
        if (t > 1e-6) {
            return ray.destination(t);  // Intersection point found
        }

        return null;  // Intersection point is behind the ray's origin
    }

    /**
     * Calculates the reflection ray of an incoming ray on a face.
     *
     * @param ray  The incoming ray.
     * @param face The face on which the reflection occurs.
     * @return The reflection ray, or {@code null} if no intersection occurs.
     */
    @Nullable
    public static GenericRay reflect(@Nonnull GenericRay ray, @Nonnull Face face) {
        final Vector3 intersection = intersection(ray, face);

        if (intersection == null) {
            return null; // No intersection, cannot compute reflection
        }

        final Vector3 normal = face.normal();
        final Vector3 incidentDirection = ray.direction();

        // Calculate the reflection direction
        final Vector3 reflectedDirection = incidentDirection.subtract(normal.multiply(2.0 * incidentDirection.dot(normal)));

        // Create and return the reflection ray
        return new GenericRay(intersection, reflectedDirection);
    }

    //
    // Instantiation Blocking
    //

    /**
     * Private constructor to prevent instantiation of the {@code Collisions} class.
     *
     * @throws Exception This constructor throws an exception to prevent instantiation.
     */
    private Collisions() throws Exception {
        throw new Exception("Collisions class cannot be instantiated.");
    }
}
