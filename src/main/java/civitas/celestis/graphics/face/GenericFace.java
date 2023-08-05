package civitas.celestis.graphics.face;

import civitas.celestis.math.vector.Vector3;
import civitas.celestis.util.group.Tuple;
import jakarta.annotation.Nonnull;

import java.util.Iterator;
import java.util.List;

/**
 * <h2>GenericFace</h2>
 * <p>
 * A class implementing the {@link Face} interface to represent a three-dimensional face.
 * </p>
 */
public class GenericFace implements Face {
    /**
     * Constructs a GenericFace instance with the provided vertices.
     *
     * @param a The first vertex of the face.
     * @param b The second vertex of the face.
     * @param c The third vertex of the face.
     */
    public GenericFace(@Nonnull Vector3 a, @Nonnull Vector3 b, @Nonnull Vector3 c) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.centroid = calculateCentroid();
        this.normal = calculateNormal();
    }

    /**
     * Constructs a GenericFace instance using a tuple of vertices.
     *
     * @param tuple The tuple of vertices containing three elements.
     */
    public GenericFace(@Nonnull Tuple<Vector3> tuple) {
        this(tuple.a(), tuple.b(), tuple.c());
    }

    /**
     * Constructs a GenericFace instance by copying another GenericFace instance.
     *
     * @param other The other GenericFace instance to copy from.
     */
    public GenericFace(@Nonnull GenericFace other) {
        this.a = other.a;
        this.b = other.b;
        this.c = other.c;
        this.centroid = other.centroid;
        this.normal = other.normal;
    }

    /**
     * Calculates and returns the centroid of the face.
     *
     * @return The centroid vector.
     */
    @Nonnull
    private Vector3 calculateCentroid() {
        return a.add(b).add(c).divide(3);
    }

    /**
     * Calculates and returns the normal vector of the face.
     *
     * @return The normal vector.
     */
    @Nonnull
    private Vector3 calculateNormal() {
        return b.subtract(a).cross(c.subtract(a));
    }

    @Nonnull
    private final Vector3 a;
    @Nonnull
    private final Vector3 b;
    @Nonnull
    private final Vector3 c;
    @Nonnull
    private final Vector3 centroid;
    @Nonnull
    private final Vector3 normal;

    /**
     * Returns the first vertex of the face.
     *
     * @return The first vertex.
     */
    @Nonnull
    @Override
    public Vector3 a() {
        return a;
    }

    /**
     * Returns the second vertex of the face.
     *
     * @return The second vertex.
     */
    @Nonnull
    @Override
    public Vector3 b() {
        return b;
    }

    /**
     * Returns the third vertex of the face.
     *
     * @return The third vertex.
     */
    @Nonnull
    @Override
    public Vector3 c() {
        return c;
    }

    /**
     * Returns the centroid of the face.
     *
     * @return The centroid vector.
     */
    @Nonnull
    @Override
    public Vector3 centroid() {
        return centroid;
    }

    /**
     * Returns the normal vector of the face.
     *
     * @return The normal vector.
     */
    @Nonnull
    @Override
    public Vector3 normal() {
        return normal;
    }

    /**
     * Converts the face's vertices into a tuple of vertices.
     *
     * @return The tuple of vertices.
     */
    @Nonnull
    @Override
    public Tuple<Vector3> toTuple() {
        return new Tuple<>(a, b, c);
    }

    /**
     * Returns an iterator over the vertices of the face.
     *
     * @return An iterator over the vertices.
     */
    @Nonnull
    @Override
    public Iterator<Vector3> iterator() {
        return List.of(a, b, c).iterator();
    }

    @Override
    @Nonnull
    public String toString() {
        return "GenericFace{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", centroid=" + centroid +
                ", normal=" + normal +
                '}';
    }
}
