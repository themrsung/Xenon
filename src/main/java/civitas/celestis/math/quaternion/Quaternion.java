package civitas.celestis.math.quaternion;

import civitas.celestis.math.util.Numbers;
import civitas.celestis.math.util.QuaternionBuilder;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.math.vector.Vector4;
import jakarta.annotation.Nonnull;

/**
 * Represents a quaternion with w, x, y, and z components. This class provides methods for quaternion arithmetic,
 * magnitude calculations, conversions, and other operations.
 */
public class Quaternion extends Vector4 {
    //
    // Builder
    //

    /**
     * Creates a new instance of {@link QuaternionBuilder} to start building a quaternion.
     *
     * @return A new {@link QuaternionBuilder} instance for constructing quaternions.
     */
    @Nonnull
    public static QuaternionBuilder builder() {
        return new QuaternionBuilder();
    }

    //
    // Constants
    //

    /**
     * The identity quaternion, representing no rotation.
     */
    public static final Quaternion IDENTITY = new Quaternion(1, 0, 0, 0);

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
     * Calculates the pitch angle (rotation around the X-axis) from this Quaternion.
     *
     * @return The pitch angle in radians.
     */
    public double pitch() {
        final double sinp = 2 * (w() * x() + y() * z());
        final double cosp = 1 - 2 * (x() * x() + y() * y());
        return Math.atan2(sinp, cosp);
    }

    /**
     * Calculates the yaw angle (rotation around the Y-axis) from this Quaternion.
     *
     * @return The yaw angle in radians.
     */
    public double yaw() {
        final double sinp = 2 * (w() * y() - z() * x());
        final double cosp = 1 - 2 * (y() * y() + z() * z());
        return Math.atan2(sinp, cosp);
    }

    /**
     * Calculates the roll angle (rotation around the Z-axis) from this Quaternion.
     *
     * @return The roll angle in radians.
     */
    public double roll() {
        final double sinr = 2 * (w() * z() + x() * y());
        final double cosr = 1 - 2 * (y() * y() + z() * z());
        return Math.atan2(sinr, cosr);
    }

    /**
     * Calculates the axis of rotation represented by this quaternion.
     *
     * @return The axis of rotation represented by this quaternion.
     * @throws IllegalStateException If the quaternion represents no rotation (identity quaternion).
     */
    @Nonnull
    public Vector3 axis() {
        if (Math.abs(w()) >= 1.0 - Numbers.MARGIN_OF_SIGNIFICANCE) {
            throw new IllegalStateException("Quaternion represents no rotation (identity quaternion).");
        }

        final double angle = 2 * Math.acos(w());
        final double factor = 1.0 / Math.sqrt(1 - w() * w());
        return new Vector3(x() * factor, y() * factor, z() * factor).normalize();
    }

    /**
     * Calculates the angle in radians of rotation represented by this quaternion.
     *
     * @return The angle in radians of rotation represented by this quaternion.
     * @throws IllegalStateException If the quaternion represents no rotation (identity quaternion).
     */
    public double angle() {
        if (Math.abs(w()) >= 1.0 - Numbers.MARGIN_OF_SIGNIFICANCE) {
            throw new IllegalStateException("Quaternion represents no rotation (identity quaternion).");
        }

        return 2 * Math.acos(w());
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

    /**
     * Returns a new instance of {@link QuaternionBuilder} initialized with the current state of this {@link QuaternionBuilder}.
     * This allows creating a new {@link QuaternionBuilder} that starts from the same quaternion state as the current builder.
     *
     * @return A new {@link QuaternionBuilder} initialized with the current state of this builder.
     */
    @Nonnull
    public QuaternionBuilder toBuilder() {
        return new QuaternionBuilder(this);
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