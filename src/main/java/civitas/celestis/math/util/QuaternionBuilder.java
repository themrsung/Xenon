package civitas.celestis.math.util;

import civitas.celestis.math.quaternion.Quaternion;
import jakarta.annotation.Nonnull;

/**
 * <h2>QuaternionBuilder</h2>
 * <p>
 * A builder class for constructing quaternions by applying rotations.
 * Quaternions can be built up by adding rotations in different axes and angles.
 * Use this builder to create complex rotations in a more intuitive way.
 * </p>
 */
public final class QuaternionBuilder {
    //
    // Static Builders
    //

    /**
     * Creates a quaternion representing a pitch rotation.
     *
     * @param rads The angle in radians for the pitch rotation.
     * @return The quaternion representing the pitch rotation.
     */
    @Nonnull
    public static Quaternion pitch(double rads) {
        final double halfAngle = rads * 0.5;
        final double sinHalfAngle = Math.sin(halfAngle);
        final double cosHalfAngle = Math.cos(halfAngle);

        return new Quaternion(cosHalfAngle, sinHalfAngle, 0, 0);
    }

    /**
     * Creates a quaternion representing a yaw rotation.
     *
     * @param rads The angle in radians for the yaw rotation.
     * @return The quaternion representing the yaw rotation.
     */
    @Nonnull
    public static Quaternion yaw(double rads) {
        final double halfAngle = rads * 0.5;
        final double sinHalfAngle = Math.sin(halfAngle);
        final double cosHalfAngle = Math.cos(halfAngle);

        return new Quaternion(cosHalfAngle, 0, sinHalfAngle, 0);
    }

    /**
     * Creates a quaternion representing a roll rotation.
     *
     * @param rads The angle in radians for the roll rotation.
     * @return The quaternion representing the roll rotation.
     */
    @Nonnull
    public static Quaternion roll(double rads) {
        final double halfAngle = rads * 0.5;
        final double sinHalfAngle = Math.sin(halfAngle);
        final double cosHalfAngle = Math.cos(halfAngle);

        return new Quaternion(cosHalfAngle, 0, 0, sinHalfAngle);
    }

    //
    // Constructors
    //

    /**
     * Constructs a new QuaternionBuilder with the underlying quaternion
     * initialized to the identity quaternion.
     */
    public QuaternionBuilder() {
        this.quaternion = Quaternion.IDENTITY;
    }

    /**
     * Initializes a new instance of the {@code QuaternionBuilder} class with an existing {@link Quaternion} object.
     * The provided {@link Quaternion} object serves as the starting point for building a new quaternion through
     * subsequent method calls on the {@code QuaternionBuilder}.
     *
     * @param existing The existing {@link Quaternion} object to use as a starting point for building a new quaternion.
     */
    public QuaternionBuilder(@Nonnull Quaternion existing) {
        this.quaternion = existing;
    }

    //
    // Variables
    //

    private Quaternion quaternion;

    //
    // Methods
    //

    /**
     * Adds the provided rotation to the accumulated quaternion rotation.
     *
     * @param rotation The quaternion rotation to add.
     * @return This {@link QuaternionBuilder} instance after applying the rotation.
     */
    @Nonnull
    public QuaternionBuilder add(@Nonnull Quaternion rotation) {
        this.quaternion = rotation.multiply(quaternion);
        return this;
    }

    /**
     * Adds a pitch rotation to the quaternion being constructed.
     *
     * @param rads The angle in radians for the pitch rotation.
     * @return This QuaternionBuilder instance for method chaining.
     */
    @Nonnull
    public QuaternionBuilder addPitch(double rads) {
        this.quaternion = pitch(rads).multiply(quaternion);
        return this;
    }

    /**
     * Adds a yaw rotation to the quaternion being constructed.
     *
     * @param rads The angle in radians for the yaw rotation.
     * @return This QuaternionBuilder instance for method chaining.
     */
    @Nonnull
    public QuaternionBuilder addYaw(double rads) {
        this.quaternion = yaw(rads).multiply(quaternion);
        return this;
    }

    /**
     * Adds a roll rotation to the quaternion being constructed.
     *
     * @param rads The angle in radians for the roll rotation.
     * @return This QuaternionBuilder instance for method chaining.
     */
    @Nonnull
    public QuaternionBuilder addRoll(double rads) {
        this.quaternion = roll(rads).multiply(quaternion);
        return this;
    }

    /**
     * Adds a pitch rotation to the quaternion being constructed using an angle in degrees.
     *
     * @param degrees The angle in degrees for the pitch rotation.
     * @return This QuaternionBuilder instance for method chaining.
     */
    @Nonnull
    public QuaternionBuilder addPitchDegrees(double degrees) {
        return addPitch(Math.toRadians(degrees));
    }

    /**
     * Adds a yaw rotation to the quaternion being constructed using an angle in degrees.
     *
     * @param degrees The angle in degrees for the yaw rotation.
     * @return This QuaternionBuilder instance for method chaining.
     */
    @Nonnull
    public QuaternionBuilder addYawDegrees(double degrees) {
        return addYaw(Math.toRadians(degrees));
    }

    /**
     * Adds a roll rotation to the quaternion being constructed using an angle in degrees.
     *
     * @param degrees The angle in degrees for the roll rotation.
     * @return This QuaternionBuilder instance for method chaining.
     */
    @Nonnull
    public QuaternionBuilder addRollDegrees(double degrees) {
        return addRoll(Math.toRadians(degrees));
    }

    /**
     * Builds and returns the quaternion that has been constructed using this builder.
     *
     * @return The constructed quaternion.
     */
    @Nonnull
    public Quaternion build() {
        return quaternion;
    }
}
