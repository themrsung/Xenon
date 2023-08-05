package civitas.celestis.math.vector;

import civitas.celestis.math.util.Numbers;
import jakarta.annotation.Nonnull;

/**
 * <h2>Vector3</h2>
 * <p>
 * Represents a three-dimensional vector in a mathematical space.
 * Vectors in three dimensions have magnitude and direction in three-dimensional space.
 * </p>
 */
public class Vector3 implements Vector {
    //
    // Constructors
    //

    /**
     * Constructs a three-dimensional vector with the specified components.
     *
     * @param x The x-component of the vector.
     * @param y The y-component of the vector.
     * @param z The z-component of the vector.
     */
    public Vector3(double x, double y, double z) {
        this.x = Numbers.requireFinite(x);
        this.y = Numbers.requireFinite(y);
        this.z = Numbers.requireFinite(z);
    }

    /**
     * Constructs a three-dimensional vector by copying the components from another {@link Vector3} object.
     *
     * @param other The {@link Vector3} object from which to copy the components.
     */
    public Vector3(@Nonnull Vector3 other) {
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
    }

    //
    // Components
    //

    private final double x;
    private final double y;
    private final double z;

    //
    // Getters
    //

    /**
     * Returns the x-component of the three-dimensional vector.
     *
     * @return The x-component of the vector.
     */
    public double x() {
        return x;
    }

    /**
     * Returns the y-component of the three-dimensional vector.
     *
     * @return The y-component of the vector.
     */
    public double y() {
        return y;
    }

    /**
     * Returns the z-component of the three-dimensional vector.
     *
     * @return The z-component of the vector.
     */
    public double z() {
        return z;
    }

    //
    // Conversion
    //

    @Nonnull
    @Override
    public double[] toArray() {
        return new double[]{x, y, z};
    }

    @Override
    public int size() {
        return 3;
    }

    //
    // Derivatives
    //


    @Override
    public double magnitude() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    @Override
    public double magnitude2() {
        return x * x + y * y + z * z;
    }

    //
    // Arithmetic
    //

    @Nonnull
    public Vector3 add(double s) {
        return new Vector3(x + s, y + s, z + s);
    }

    @Nonnull
    public Vector3 subtract(double s) {
        return new Vector3(x - s, y - s, z - s);
    }

    @Nonnull
    public Vector3 multiply(double s) {
        return new Vector3(x * s, y * s, z * s);
    }

    @Nonnull
    public Vector3 divide(double s) {
        if (s == 0) {
            throw new IllegalArgumentException("Division by zero.");
        }
        return new Vector3(x / s, y / s, z / s);
    }

    /**
     * Adds another vector to this vector and returns the result as a new vector.
     *
     * @param v The vector to be added.
     * @return A new vector resulting from the addition.
     */
    @Nonnull
    public Vector3 add(@Nonnull Vector3 v) {
        return new Vector3(x + v.x, y + v.y, z + v.z);
    }

    /**
     * Subtracts another vector from this vector and returns the result as a new vector.
     *
     * @param v The vector to be subtracted.
     * @return A new vector resulting from the subtraction.
     */
    @Nonnull
    public Vector3 subtract(@Nonnull Vector3 v) {
        return new Vector3(x - v.x, y - v.y, z - v.z);
    }

    /**
     * Computes the dot product between this vector and another vector.
     *
     * @param v The vector to compute the dot product with.
     * @return The dot product of the two vectors.
     */
    public double dot(@Nonnull Vector3 v) {
        return x * v.x + y * v.y + z * v.z;
    }

    /**
     * Computes the cross product between this vector and another vector using left-multiplication.
     *
     * @param v The vector to compute the cross product with.
     * @return A new vector representing the cross product.
     */
    @Nonnull
    public Vector3 cross(@Nonnull Vector3 v) {
        return new Vector3(
                y * v.z - z * v.y,
                z * v.x - x * v.z,
                x * v.y - y * v.x
        );
    }

    //
    // Utility
    //

    @Nonnull
    @Override
    public Vector3 negate() {
        return new Vector3(-x, -y, -z);
    }

    /**
     * Converts the vector to a new pure quaternion derived from the vector's components.
     *
     * @return A new {@link Quaternion} instance representing a pure quaternion derived from the vector.
     */
    @Nonnull
    public Quaternion toQuaternion() {
        return new Quaternion(0, x(), y(), z());
    }

    /**
     * Rotates the vector using the provided rotation quaternion.
     *
     * @param rq The rotation quaternion to apply.
     * @return A new {@link Vector3} instance representing the rotated vector.
     */
    @Nonnull
    public Vector3 rotate(@Nonnull Quaternion rq) {
        final Quaternion pq = rq.multiply(toQuaternion()).multiply(rq.conjugate());
        return new Vector3(pq.x(), pq.y(), pq.z());
    }

    /**
     * Calculates the Euclidean distance between this {@code Vector3} and another vector.
     *
     * @param other The vector to calculate the distance to.
     * @return The Euclidean distance between this vector and the provided vector.
     */
    public double distance(@Nonnull Vector3 other) {
        return other.subtract(this).magnitude();
    }

    /**
     * Calculates the squared Euclidean distance between this {@code Vector3} and another vector.
     *
     * @param other The vector to calculate the squared distance to.
     * @return The squared Euclidean distance between this vector and the provided vector.
     */
    public double distance2(@Nonnull Vector3 other) {
        return other.subtract(this).magnitude2();
    }

    /**
     * Compares this {@code Vector3} with the specified object for equality.
     *
     * @param o The object to compare with.
     * @return {@code true} if the provided object is equal to this {@code Vector3}, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Vector3 v3)) return false;
        return x == v3.x && y == v3.y && z == v3.z;
    }

    //
    // Serialization
    //

    /**
     * Returns a string representation of the vector in the format: Vector3{x=value, y=value, z=value}.
     *
     * @return A string representation of the vector.
     */
    @Override
    @Nonnull
    public String toString() {
        return "Vector3{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    /**
     * Parses a string representation of a vector and returns a new Vector3 instance.
     *
     * @param input The string representation of the vector in the format: "x=value,y=value,z=value".
     * @return A new Vector3 instance with the parsed values.
     * @throws NumberFormatException If the input string is not formatted correctly.
     */
    @Nonnull
    public static Vector3 parseVector(@Nonnull String input) {
        final String cleanInput = input.replaceAll("Vector3\\{", "").replaceAll("}", "");
        final String[] components = cleanInput.split(",");

        if (components.length != 3) {
            throw new NumberFormatException("Invalid input format. Expected 'x=value,y=value,z=value'.");
        }

        try {
            final double x = Double.parseDouble(components[0].split("=")[1]);
            final double y = Double.parseDouble(components[1].split("=")[1]);
            final double z = Double.parseDouble(components[2].split("=")[1]);
            return new Vector3(x, y, z);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Error parsing vector components: " + e.getMessage());
        }
    }
}
