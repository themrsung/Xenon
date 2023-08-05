package civitas.celestis.physics.solid;

import civitas.celestis.math.quaternion.Quaternion;
import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;

/**
 * <h2>Sphere</h2>
 * <p>
 * Represents a solid sphere in 3D space.
 * </p>
 */
public class Sphere implements Solid {
    /**
     * Constructs a sphere with the given centroid, rotation, and radius.
     *
     * @param centroid The centroid (geometric center) of the sphere.
     * @param rotation The rotation of the sphere as a quaternion.
     * @param radius   The radius of the sphere.
     * @param mass     The mass of this sphere.
     */
    public Sphere(@Nonnull Vector3 centroid, @Nonnull Quaternion rotation, double radius, double mass) {
        this.centroid = centroid;
        this.rotation = rotation;
        this.radius = radius;
        this.mass = mass;
    }

    /**
     * Constructs a new Sphere by copying the properties of another Sphere.
     *
     * @param other The Sphere to copy properties from.
     */
    public Sphere(@Nonnull Sphere other) {
        this.centroid = other.centroid;
        this.rotation = other.rotation;
        this.radius = other.radius;
        this.mass = other.mass;
    }

    @Nonnull
    private final Vector3 centroid;
    @Nonnull
    private final Quaternion rotation;
    private final double radius;
    private final double mass;

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

    /**
     * Returns the radius of the sphere.
     *
     * @return Radius of the sphere.
     */
    public double radius() {
        return radius;
    }

    @Nonnull
    @Override
    public Sphere update(@Nonnull Vector3 newCentroid, @Nonnull Quaternion newRotation) {
        return new Sphere(
                newCentroid,
                newRotation,
                radius,
                mass
        );
    }

    @Override
    public double mass() {
        return mass;
    }

    @Override
    public double volume() {
        return (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);
    }

    @Override
    public double density() {
        if (volume() == 0) {
            return 0;
        }

        return mass() / volume();
    }

    @Nonnull
    @Override
    public Vector3[] corners() {
        return new Vector3[0];
    }

    @Override
    public int numCorners() {
        return 0;
    }

    @Override
    public double dragCoefficient(@Nonnull Vector3 viewDirection) {
        return 0.5;
    }

    @Override
    public double crossSection(@Nonnull Vector3 viewDirection) {
        return Math.PI * radius * radius;
    }

    @Override
    public boolean contains(@Nonnull Vector3 point) {
        return centroid.distance2(point) <= radius * radius;
    }

    @Override
    public boolean overlaps(@Nonnull Solid other) {
        if (other instanceof Sphere sphere) {
            return centroid.distance2(sphere.centroid) <= Math.pow((radius + sphere.radius), 2);
        } else {
            for (Vector3 corner : other.corners()) {
                if (contains(corner)) {
                    return true;
                }
            }

            return false;
        }
    }
}
