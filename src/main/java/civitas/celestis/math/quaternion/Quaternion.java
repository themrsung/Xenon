package civitas.celestis.math.quaternion;

import civitas.celestis.math.vector.Vector3;
import civitas.celestis.math.vector.Vector4;
import jakarta.annotation.Nonnull;

/**
 * Represents a quaternion with w, x, y, and z components. This class provides methods for quaternion arithmetic,
 * magnitude calculations, conversions, and other operations.
 */
public class Quaternion extends Vector4 {
    //
    // Constructors
    //

    /**
     * Constructs a quaternion with the specified components.
     *
     * @param w The w-component of the quaternion.
     * @param x The x-component of the quaternion.
     * @param y The y-component of the quaternion.
     * @param z The z-component of the quaternion.
     */
    public Quaternion(double w, double x, double y, double z) {
        super(w, x, y, z);
    }

    /**
     * Constructs a quaternion from a vector by setting the scalar part to 0.
     *
     * @param v The vector representing the vector part of the quaternion.
     */
    public Quaternion(@Nonnull Vector4 v) {
        this(v.w(), v.x(), v.y(), v.z());
    }

    /**
     * Constructs a quaternion with specified scalar and vector parts.
     *
     * @param s The scalar part of the quaternion.
     * @param v The vector part of the quaternion.
     */
    public Quaternion(double s, @Nonnull Vector3 v) {
        this(s, v.x(), v.y(), v.z());
    }

    //
    // Methods
    //

    /**
     * Returns the vector part of the quaternion as a {@link Vector3}.
     *
     * @return The vector part of the quaternion.
     */
    @Nonnull
    public Vector3 getVectorPart() {
        return new Vector3(x(), y(), z());
    }

    /**
     * Performs left-multiplication of quaternions, with this being the left and q being the right.
     *
     * @param q The right-hand quaternion for the multiplication.
     * @return The left-product of the two quaternions.
     */
    @Nonnull
    public Quaternion multiply(@Nonnull Quaternion q) {
        final double w = this.w() * q.w() - this.getVectorPart().dot(q.getVectorPart());
        final Vector3 v = this.getVectorPart().cross(q.getVectorPart()).add(this.getVectorPart().multiply(q.w()))
                .add(q.getVectorPart().multiply(this.w()));
        return new Quaternion(w, v.x(), v.y(), v.z());
    }

    /**
     * Scales the quaternion by a rotation factor and returns a new rotated quaternion.
     *
     * @param s The scaling factor representing the rotation.
     * @return A new quaternion resulting from scaling by the rotation factor.
     */
    @Nonnull
    public Quaternion scale(double s) {
        final double angle = Math.acos(w()) * 2.0;
        final double newW = Math.cos(angle * s * 0.5);
        final Vector3 v = getVectorPart().multiply(Math.sin(angle * s * 0.5) / Math.sin(angle * 0.5));
        return new Quaternion(newW, v);
    }

    /**
     * Computes the conjugate of the quaternion.
     *
     * @return A new {@link Quaternion} instance representing the conjugate of the quaternion.
     */
    @Nonnull
    public Quaternion conjugate() {
        return new Quaternion(w(), -x(), -y(), -z());
    }

    /**
     * Computes the inverse of the quaternion.
     *
     * @return A new {@link Quaternion} instance representing the inverse of the quaternion.
     */
    @Nonnull
    public Quaternion inverse() {
        final double normSquared = magnitude2();
        if (normSquared == 0) {
            throw new ArithmeticException("Quaternion has zero magnitude, cannot compute inverse.");
        }

        final double invNormSquared = 1.0 / normSquared;
        return new Quaternion(w() * invNormSquared, -x() * invNormSquared,
                -y() * invNormSquared, -z() * invNormSquared);
    }

    /**
     * Compares this {@code Quaternion} with the specified object for equality.
     *
     * @param o The object to compare with.
     * @return {@code true} if the provided object is equal to this {@code Quaternion}, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Quaternion q)) return false;
        return super.equals(q);
    }

    //
    // Serialization
    //

    /**
     * Returns a string representation of the quaternion in the format:
     * Quaternion{w=value, x=value, y=value, z=value}.
     *
     * @return A string representation of the quaternion.
     */
    @Override
    @Nonnull
    public String toString() {
        return "Quaternion{" +
                "w=" + w() +
                ", x=" + x() +
                ", y=" + y() +
                ", z=" + z() +
                '}';
    }

    /**
     * Parses a string representation of a quaternion and returns a new Quaternion instance.
     *
     * @param input The string representation of the quaternion in the format: "w=value, x=value, y=value, z=value".
     * @return A new Quaternion instance with the parsed values.
     * @throws NumberFormatException If the input string is not formatted correctly.
     */
    @Nonnull
    public static Quaternion parseQuaternion(@Nonnull String input) {
        final String cleanInput = input.replaceAll("Quaternion\\{", "").replaceAll("}", "");
        final String[] components = cleanInput.split(",");

        if (components.length != 4) {
            throw new NumberFormatException("Invalid input format. Expected 'w=value, x=value, y=value, z=value'.");
        }

        try {
            final double w = Double.parseDouble(components[0].split("=")[1]);
            final double x = Double.parseDouble(components[1].split("=")[1]);
            final double y = Double.parseDouble(components[2].split("=")[1]);
            final double z = Double.parseDouble(components[3].split("=")[1]);
            return new Quaternion(w, x, y, z);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Error parsing quaternion components: " + e.getMessage());
        }
    }
}