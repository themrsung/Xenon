package civitas.celestis.graphics.face;

import civitas.celestis.math.vector.Vector3;
import civitas.celestis.util.group.Tuple;
import jakarta.annotation.Nonnull;

import java.util.Iterator;
import java.util.function.UnaryOperator;

/**
 * <h2>Face</h2>
 *
 * <p>The {@code Face} interface represents a three-dimensional face in space. A face consists of three vertices
 * that define its shape. It is commonly used in geometry and rendering to represent surfaces and polygons.</p>
 */
public interface Face extends Iterable<Vector3> {
    /**
     * Returns the first vertex of the face.
     *
     * @return The first vertex.
     */
    @Nonnull
    Vector3 a();

    /**
     * Returns the second vertex of the face.
     *
     * @return The second vertex.
     */
    @Nonnull
    Vector3 b();

    /**
     * Returns the third vertex of the face.
     *
     * @return The third vertex.
     */
    @Nonnull
    Vector3 c();

    /**
     * Calculates and returns the centroid of the face. The centroid is the average position of the vertices
     * and is often used for various calculations involving the face.
     *
     * @return The centroid vector.
     */
    @Nonnull
    Vector3 centroid();

    /**
     * Calculates and returns the normal vector of the face. The normal vector is perpendicular to the face's
     * surface and is used for lighting and rendering calculations.
     *
     * @return The normal vector.
     */
    @Nonnull
    Vector3 normal();

    /**
     * Converts the face's vertices into a tuple of vertices. The tuple is often used for various algorithms
     * and calculations involving the face's vertices.
     *
     * @return The tuple of vertices.
     */
    @Nonnull
    Tuple<Vector3> toTuple();

    /**
     * Returns an iterator over the vertices of the face. This allows iterating through the vertices using
     * the enhanced for-loop and other iteration mechanisms.
     *
     * @return An iterator over the vertices.
     */
    @Override
    @Nonnull
    Iterator<Vector3> iterator();

    /**
     * Applies the given unary operator to each vertex of the face and returns a new face
     * with the transformed vertices.
     *
     * @param operator The unary operator to apply to each vertex of the face.
     * @return A new face with the transformed vertices.
     * @throws NullPointerException If the operator is null.
     */
    @Nonnull
    Face apply(@Nonnull UnaryOperator<Vector3> operator);
}
