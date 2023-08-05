package civitas.celestis.graphics.model;

import civitas.celestis.graphics.face.Face;
import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;

/**
 * <h2>Model</h2>
 * <p>
 * The {@code Model} interface represents a 3D model composed of vertices and faces.
 * It provides methods to access information about the model's vertices and faces.
 * </p>
 */
public interface Model {
    /**
     * Returns an array of vertices that make up the model.
     *
     * @return An array of vertices.
     */
    @Nonnull
    Vector3[] vertices();

    /**
     * Returns the vertex at the specified index.
     *
     * @param index The index of the vertex to retrieve.
     * @return The vertex at the given index.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    @Nonnull
    Vector3 vertex(int index) throws IndexOutOfBoundsException;

    /**
     * Returns the number of vertices in the model.
     *
     * @return The number of vertices.
     */
    int numVertices();

    /**
     * Returns an array of faces that make up the model.
     *
     * @return An array of faces.
     */
    @Nonnull
    Face[] faces();

    /**
     * Returns the face at the specified index.
     *
     * @param index The index of the face to retrieve.
     * @return The face at the given index.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    @Nonnull
    Face face(int index) throws IndexOutOfBoundsException;

    /**
     * Returns the number of faces in the model.
     *
     * @return The number of faces.
     */
    int numFaces();
}
