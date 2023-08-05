package civitas.celestis.physics.solid;

import civitas.celestis.math.quaternion.Quaternion;
import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;


/**
 * <h2>Cuboid</h2>
 * <p>
 * Represents a solid cuboid in three-dimensional space.
 * </p>
 */
public class Cuboid implements Solid {
    //
    // Constructors
    //

    /**
     * Creates a cuboid with the specified properties.
     *
     * @param centroid   The centroid of the cuboid.
     * @param rotation   The rotation of the cuboid.
     * @param dimensions The dimensions (width, height, depth) of the cuboid.
     * @param mass       The mass of the cuboid.
     */
    public Cuboid(@Nonnull Vector3 centroid, @Nonnull Quaternion rotation, @Nonnull Vector3 dimensions, double mass) {
        this.centroid = centroid;
        this.rotation = rotation;
        this.dimensions = dimensions;
        this.mass = mass;

        final double halfX = Math.abs(dimensions.x() / 2);
        final double halfY = Math.abs(dimensions.y() / 2);
        final double halfZ = Math.abs(dimensions.z() / 2);

        this.a = centroid.add(new Vector3(-halfX, halfY, -halfZ));
        this.b = centroid.add(new Vector3(halfX, halfY, -halfZ));
        this.c = centroid.add(new Vector3(halfX, -halfY, -halfZ));
        this.d = centroid.add(new Vector3(halfX, halfY, -halfZ));
        this.e = centroid.add(new Vector3(halfX, halfY, halfZ));
        this.f = centroid.add(new Vector3(halfX, -halfY, halfZ));
        this.g = centroid.add(new Vector3(-halfX, -halfY, halfZ));
        this.h = centroid.add(new Vector3(-halfX, halfY, halfZ));
    }

    /**
     * Constructs a Cuboid with the specified properties.
     *
     * @param centroid   The centroid of the cuboid.
     * @param rotation   The rotation of the cuboid as a Quaternion.
     * @param dimensions The dimensions of the cuboid as a Vector3.
     * @param mass       The mass of the cuboid.
     * @param a          Vertex A of the cuboid.
     * @param b          Vertex B of the cuboid.
     * @param c          Vertex C of the cuboid.
     * @param d          Vertex D of the cuboid.
     * @param e          Vertex E of the cuboid.
     * @param f          Vertex F of the cuboid.
     * @param g          Vertex G of the cuboid.
     * @param h          Vertex H of the cuboid.
     */
    protected Cuboid(
            @Nonnull Vector3 centroid,
            @Nonnull Quaternion rotation,
            @Nonnull Vector3 dimensions,
            double mass,
            @Nonnull Vector3 a,
            @Nonnull Vector3 b,
            @Nonnull Vector3 c,
            @Nonnull Vector3 d,
            @Nonnull Vector3 e,
            @Nonnull Vector3 f,
            @Nonnull Vector3 g,
            @Nonnull Vector3 h
    ) {
        this.centroid = centroid;
        this.rotation = rotation;
        this.dimensions = dimensions;
        this.mass = mass;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
    }

    //
    // Defined Properties
    //
    @Nonnull
    private final Vector3 centroid;
    @Nonnull
    private final Quaternion rotation;
    @Nonnull
    private final Vector3 dimensions;
    private final double mass;

    //
    // Derived Properties
    //
    @Nonnull
    private final Vector3 a, b, c, d, e, f, g, h;

    //
    // Getters
    //

    @Nonnull
    @Override
    public Vector3 centroid() {
        return centroid;
    }

    @Nonnull
    @Override
    public Quaternion rotation() {
        return rotation;
    }

    @Nonnull
    @Override
    public Cuboid update(@Nonnull Vector3 newCentroid, @Nonnull Quaternion newRotation) {
        return new Cuboid(newCentroid, newRotation, dimensions, mass, a, b, c, d, e, f, g, h);
    }

    /**
     * Get the dimensions of the cuboid.
     *
     * @return The dimensions of the cuboid as a Vector3.
     */
    @Nonnull
    public Vector3 dimensions() {
        return dimensions;
    }

    @Override
    public double mass() {
        return mass;
    }

    @Override
    public double volume() {
        return dimensions.x() * dimensions.y() * dimensions.z();
    }

    @Override
    public double density() {
        if (volume() == 0) {
            return 0;
        }

        return mass() / volume();
    }

    /**
     * Get vertex A of the cuboid.
     *
     * @return Vertex A as a Vector3.
     */
    @Nonnull
    public Vector3 a() {
        return a;
    }

    /**
     * Get vertex B of the cuboid.
     *
     * @return Vertex B as a Vector3.
     */
    @Nonnull
    public Vector3 b() {
        return b;
    }

    /**
     * Get vertex C of the cuboid.
     *
     * @return Vertex C as a Vector3.
     */
    @Nonnull
    public Vector3 c() {
        return c;
    }

    /**
     * Get vertex D of the cuboid.
     *
     * @return Vertex D as a Vector3.
     */
    @Nonnull
    public Vector3 d() {
        return d;
    }

    /**
     * Get vertex E of the cuboid.
     *
     * @return Vertex E as a Vector3.
     */
    @Nonnull
    public Vector3 e() {
        return e;
    }

    /**
     * Get vertex F of the cuboid.
     *
     * @return Vertex F as a Vector3.
     */
    @Nonnull
    public Vector3 f() {
        return f;
    }

    /**
     * Get vertex G of the cuboid.
     *
     * @return Vertex G as a Vector3.
     */
    @Nonnull
    public Vector3 g() {
        return g;
    }

    /**
     * Get vertex H of the cuboid.
     *
     * @return Vertex H as a Vector3.
     */
    @Nonnull
    public Vector3 h() {
        return h;
    }

    @Nonnull
    @Override
    public Vector3[] corners() {
        return new Vector3[]{a, b, c, d, e, f, g, h};
    }

    @Override
    public int numCorners() {
        return 8;
    }

    @Override
    public double dragCoefficient(@Nonnull Vector3 viewDirection) {
        return 1; // FIXME
    }

    @Override
    public double crossSection(@Nonnull Vector3 viewDirection) {
        return Math.PI * (dimensions.magnitude2() / 4.0);
    }

    @Override
    public boolean contains(@Nonnull Vector3 point) {
        final double halfX = Math.abs(dimensions().x() / 2);
        final double halfY = Math.abs(dimensions().y() / 2);
        final double halfZ = Math.abs(dimensions().z() / 2);

        final double minX = centroid().x() - halfX;
        final double maxX = centroid().x() + halfX;
        final double minY = centroid().y() - halfY;
        final double maxY = centroid().y() + halfY;
        final double minZ = centroid().z() - halfZ;
        final double maxZ = centroid().z() + halfZ;

        return point.x() >= minX && point.x() <= maxX &&
                point.y() >= minY && point.y() <= maxY &&
                point.z() >= minZ && point.z() <= maxZ;
    }

    @Override
    public boolean overlaps(@Nonnull Solid other) {
        if (other instanceof Sphere sphere) {
            // This may not be accurate for elongated cuboids
            final double halfDiagonalSquared = dimensions.magnitude2() / 2;
            final double radiusSquared = sphere.radius();
            final double distanceSquared = centroid.distance2(sphere.centroid());

            return distanceSquared <= halfDiagonalSquared * radiusSquared;
        } else {
            for (final Vector3 corner : other.corners()) {
                if (contains(corner)) return true;
            }

            return false;
        }
    }
}
