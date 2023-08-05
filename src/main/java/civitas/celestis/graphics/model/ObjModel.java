package civitas.celestis.graphics.model;

import civitas.celestis.graphics.face.Face;
import civitas.celestis.graphics.face.GenericFace;
import civitas.celestis.math.vector.Vector3;
import de.javagl.obj.FloatTuple;
import de.javagl.obj.Obj;
import de.javagl.obj.ObjFace;
import jakarta.annotation.Nonnull;

import java.util.Arrays;
import java.util.function.UnaryOperator;

/**
 * <h2>ObjModel</h2>
 * <p>
 * Implementation of the {@link Model} interface based on a Wavefront .obj file.
 * </p>
 */
public class ObjModel implements Model {
    /**
     * Creates a new {@code ObjModel} instance based on the given {@link Obj} object.
     *
     * @param object The {@link Obj} object containing vertex and face information.
     */
    public ObjModel(@Nonnull Obj object) {
        this.vertices = new Vector3[object.getNumVertices()];
        this.faces = new Face[object.getNumFaces()];

        for (int i = 0; i < object.getNumVertices(); i++) {
            final FloatTuple v = object.getVertex(i);

            // Coordinate system is translated to match that of Xenon's vectors
            vertices[i] = new Vector3(v.getZ(), v.getY(), v.getX());
        }

        for (int i = 0; i < object.getNumFaces(); i++) {
            final ObjFace f = object.getFace(i);
            faces[i] = new GenericFace(
                    vertices[f.getVertexIndex(0)],
                    vertices[f.getVertexIndex(1)],
                    vertices[f.getVertexIndex(2)]
            );
        }
    }

    /**
     * Constructs an ObjModel with the given vertices and faces.
     *
     * @param vertices The array of vertices for the model.
     * @param faces The array of faces for the model.
     * @throws IllegalArgumentException If either vertices or faces are null.
     */
    protected ObjModel(@Nonnull Vector3[] vertices, @Nonnull Face[] faces) {
        this.vertices = vertices.clone();
        this.faces = faces.clone();
    }

    @Nonnull
    private final Vector3[] vertices;
    @Nonnull
    private final Face[] faces;

    @Nonnull
    @Override
    public Vector3[] vertices() {
        return vertices.clone();
    }

    @Nonnull
    @Override
    public Vector3 vertex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= vertices.length) {
            throw new IndexOutOfBoundsException("Vertex index out of range");
        }
        return vertices[index];
    }

    @Override
    public int numVertices() {
        return vertices.length;
    }

    @Nonnull
    @Override
    public Face[] faces() {
        return faces.clone();
    }

    @Nonnull
    @Override
    public Face face(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= faces.length) {
            throw new IndexOutOfBoundsException("Face index out of range");
        }
        return faces[index];
    }

    @Override
    public int numFaces() {
        return faces.length;
    }

    @Nonnull
    @Override
    public ObjModel apply(@Nonnull UnaryOperator<Vector3> operator) {
        final Vector3[] newVertices = new Vector3[vertices.length];
        for (int i = 0; i < vertices.length; i++) {
            newVertices[i] = operator.apply(vertices[i]);
        }

        final Face[] newFaces = new Face[faces.length];
        for (int i = 0; i < faces.length; i++) {
            newFaces[i] = faces[i].apply(operator);
        }

        return new ObjModel(newVertices, newFaces);
    }

    @Override
    @Nonnull
    public String toString() {
        return "ObjModel{" +
                "vertices=" + Arrays.toString(vertices) +
                ", faces=" + Arrays.toString(faces) +
                '}';
    }
}
