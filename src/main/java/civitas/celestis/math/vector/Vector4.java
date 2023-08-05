package civitas.celestis.math.vector;


import civitas.celestis.math.util.Numbers;
import jakarta.annotation.Nonnull;

/**
 * <h2>Vector4</h2>
 * <p>
 * Represents a four-dimensional vector with w, x, y, and z components. This class provides methods for vector arithmetic,
 * magnitude calculations, conversions, and other operations.
 * </p>
 */
public class Vector4 implements Vector {
    //
    // Constructors
    //

    /**
     * Constructs a four-dimensional vector with the specified components.
     *
     * @param w The w-component of the vector.
     * @param x The x-component of the vector.
     * @param y The y-component of the vector.
     * @param z The z-component of the vector.
     */
    public Vector4(double w, double x, double y, double z) {
        this.w = Numbers.requireFinite(w);
        this.x = Numbers.requireFinite(x);
        this.y = Numbers.requireFinite(y);
        this.z = Numbers.requireFinite(z);
    }

    /**
     * Constructs a four-dimensional vector by copying the components from another {@link Vector4} object.
     *
     * @param other The {@link Vector4} object from which to copy the components.
     */
    public Vector4(@Nonnull Vector4 other) {
        this.w = other.w;
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
    }

    //
    // Components
    //

    private final double w;
    private final double x;
    private final double y;
    private final double z;

    //
    // Getters
    //

    /**
     * Returns the w-component of the four-dimensional vector.
     *
     * @return The w-component of the vector.
     */
    public double w() {
        return w;
    }

    /**
     * Returns the x-component of the four-dimensional vector.
     *
     * @return The x-component of the vector.
     */
    public double x() {
        return x;
    }

    /**
     * Returns the y-component of the four-dimensional vector.
     *
     * @return The y-component of the vector.
     */
    public double y() {
        return y;
    }

    /**
     * Returns the z-component of the four-dimensional vector.
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
        return new double[]{w, x, y, z};
    }

    @Override
    public int size() {
        return 4;
    }

    //
    // Derivatives
    //

    @Override
    public double magnitude() {
        return Math.sqrt(w * w + x * x + y * y + z * z);
    }

    @Override
    public double magnitude2() {
        return w * w + x * x + y * y + z * z;
    }

    //
    // Arithmetic
    //

    @Nonnull
    public Vector4 add(double s) {
        return new Vector4(w + s, x + s, y + s, z + s);
    }

    @Nonnull
    public Vector4 subtract(double s) {
        return new Vector4(w - s, x - s, y - s, z - s);
    }

    @Nonnull
    public Vector4 multiply(double s) {
        return new Vector4(w * s, x * s, y * s, z * s);
    }

    @Nonnull
    public Vector4 divide(double s) {
        if (s == 0) {
            throw new IllegalArgumentException("Division by zero.");
        }
        return new Vector4(w / s, x / s, y / s, z / s);
    }

    /**
     * Adds another vector to this vector and returns the result as a new vector.
     *
     * @param v The vector to be added.
     * @return A new vector resulting from the addition.
     */
    @Nonnull
    public Vector4 add(@Nonnull Vector4 v) {
        return new Vector4(w + v.w, x + v.x, y + v.y, z + v.z);
    }

    /**
     * Subtracts another vector from this vector and returns the result as a new vector.
     *
     * @param v The vector to be subtracted.
     * @return A new vector resulting from the subtraction.
     */
    @Nonnull
    public Vector4 subtract(@Nonnull Vector4 v) {
        return new Vector4(w - v.w, x - v.x, y - v.y, z - v.z);
    }

    //
    // Utility
    //

    @Nonnull
    @Override
    public Vector4 negate() {
        return new Vector4(-w, -x, -y, -z);
    }

    /**
     * Compares this {@code Vector4} with the specified object for equality.
     *
     * @param o The object to compare with.
     * @return {@code true} if the provided object is equal to this {@code Vector4}, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Vector4 v4)) return false;
        return w == v4.w && x == v4.x && y == v4.y && z == v4.z;
    }

    //
    // Serialization
    //

    @Override
    @Nonnull
    public String toString() {
        return "Vector4{" +
                "w=" + w +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    @Nonnull
    public static Vector4 parseVector(@Nonnull String input) {
        final String cleanInput = input.replaceAll("Vector4\\{", "").replaceAll("}", "");
        final String[] components = cleanInput.split(",");

        if (components.length != 4) {
            throw new NumberFormatException("Invalid input format. Expected 'w=value,x=value,y=value,z=value'.");
        }

        try {
            final double w = Double.parseDouble(components[0].split("=")[1]);
            final double x = Double.parseDouble(components[1].split("=")[1]);
            final double y = Double.parseDouble(components[2].split("=")[1]);
            final double z = Double.parseDouble(components[3].split("=")[1]);
            return new Vector4(w, x, y, z);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Error parsing vector components: " + e.getMessage());
        }
    }
}
