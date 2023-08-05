package civitas.celestis.math.vector;

import civitas.celestis.math.util.Numbers;
import jakarta.annotation.Nonnull;

import java.util.function.UnaryOperator;

/**
 * <h2>Vector2</h2>
 * <p>
 * Represents a two-dimensional vector with x and y components. This class provides methods for vector arithmetic,
 * magnitude calculations, and conversions.
 * </p>
 */
public class Vector2 implements Vector {
    //
    // Constants
    //

    /**
     * Absolute zero. Represents origin.
     */
    public static final Vector2 ZERO = new Vector2(0, 0);

    public static final Vector2 POSITIVE_X = new Vector2(1, 0);
    public static final Vector2 POSITIVE_Y = new Vector2(0, 1);
    public static final Vector2 NEGATIVE_X = new Vector2(-1, 0);
    public static final Vector2 NEGATIVE_Y = new Vector2(0, -1);

    //
    // Constructors
    //

    /**
     * Constructs a two-dimensional vector with the specified components.
     *
     * @param x The x-component of the vector.
     * @param y The y-component of the vector.
     */
    public Vector2(double x, double y) {
        this.x = Numbers.requireFinite(x);
        this.y = Numbers.requireFinite(y);
    }

    /**
     * Constructs a two-dimensional vector by copying the components from another {@link Vector2} object.
     *
     * @param other The {@link Vector2} object from which to copy the components.
     */
    public Vector2(@Nonnull Vector2 other) {
        this.x = other.x;
        this.y = other.y;
    }

    //
    // Components
    //

    private final double x;
    private final double y;

    //
    // Getters
    //

    /**
     * Returns the x-component of the two-dimensional vector.
     *
     * @return The x-component of the vector.
     */
    public double x() {
        return x;
    }

    /**
     * Returns the y-component of the two-dimensional vector.
     *
     * @return The y-component of the vector.
     */
    public double y() {
        return y;
    }

    //
    // Conversion
    //

    @Nonnull
    @Override
    public double[] toArray() {
        return new double[]{x, y};
    }

    @Override
    public int size() {
        return 2;
    }

    //
    // Derivatives
    //

    @Override
    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    @Override
    public double magnitude2() {
        return x * x + y * y;
    }

    //
    // Arithmetic
    //

    @Nonnull
    public Vector2 add(double s) {
        return new Vector2(x + s, y + s);
    }

    @Nonnull
    public Vector2 subtract(double s) {
        return new Vector2(x - s, y - s);
    }

    @Nonnull
    public Vector2 multiply(double s) {
        return new Vector2(x * s, y * s);
    }

    @Nonnull
    public Vector2 divide(double s) {
        if (s == 0) {
            throw new IllegalArgumentException("Division by zero.");
        }
        return new Vector2(x / s, y / s);
    }

    /**
     * Adds another vector to this vector and returns the result as a new vector.
     *
     * @param v The vector to be added.
     * @return A new vector resulting from the addition.
     */
    @Nonnull
    public Vector2 add(@Nonnull Vector2 v) {
        return new Vector2(x + v.x, y + v.y);
    }

    /**
     * Multiplies this vector by another Vector2 using complex number multiplication.
     *
     * @param v The Vector2 to multiply by.
     * @return A new Vector2 resulting from the complex number multiplication.
     */
    @Nonnull
    public Vector2 multiply(@Nonnull Vector2 v) {
        return new Vector2(x * v.x - y * v.y, x * v.y + y * v.x);
    }

    //
    // Utility
    //


    @Nonnull
    @Override
    public Vector2 apply(@Nonnull UnaryOperator<Double> operator) {
        return new Vector2(operator.apply(x), operator.apply(y));
    }

    @Nonnull
    public Vector2 negate() {
        return new Vector2(-x, -y);
    }

    @Nonnull
    @Override
    public Vector2 normalize() {
        final double magnitude = magnitude();
        if (magnitude == 0) return ZERO;

        return new Vector2(x / magnitude, y / magnitude);
    }

    /**
     * Rotates the vector counter-clockwise by the specified angle in radians.
     *
     * @param angle The angle in radians by which to rotate the vector.
     * @return A new {@link Vector2} instance representing the rotated vector.
     */
    @Nonnull
    public Vector2 rotate(double angle) {
        final double cos = Math.cos(angle);
        final double sin = Math.sin(angle);

        return multiply(new Vector2(cos, -sin));
    }

    /**
     * Compares this {@code Vector2} with the specified object for equality.
     *
     * @param o The object to compare with.
     * @return {@code true} if the provided object is equal to this {@code Vector2}, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Vector2 v2)) return false;
        return x == v2.x && y == v2.y;
    }

    //
    // Serialization
    //

    /**
     * Returns a string representation of the vector in the format: Vector2{x=value, y=value}.
     *
     * @return A string representation of the vector.
     */
    @Override
    @Nonnull
    public String toString() {
        return "Vector2{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    /**
     * Parses a string representation of a vector and returns a new Vector2 instance.
     *
     * @param input The string representation of the vector in the format: "x=value,y=value".
     * @return A new Vector2 instance with the parsed values.
     * @throws NumberFormatException If the input string is not formatted correctly.
     */
    @Nonnull
    public static Vector2 parseVector(@Nonnull String input) {
        final String cleanInput = input.replaceAll("Vector2\\{", "").replaceAll("}", "");
        final String[] components = cleanInput.split(",");

        if (components.length != 2) {
            throw new NumberFormatException("Invalid input format. Expected 'x=value,y=value'.");
        }

        try {
            final double x = Double.parseDouble(components[0].split("=")[1]);
            final double y = Double.parseDouble(components[1].split("=")[1]);
            return new Vector2(x, y);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Error parsing vector components: " + e.getMessage());
        }
    }
}