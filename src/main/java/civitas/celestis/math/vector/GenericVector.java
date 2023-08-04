package civitas.celestis.math.vector;

import jakarta.annotation.Nonnull;

/**
 * <h2>GenericVector</h2>
 * <p>
 * This class represents a generic vector with double values. It implements the {@link Vector} interface and provides
 * various operations for vector arithmetic, including addition, subtraction, multiplication, and division. The vector
 * can be created with an array of values. It supports element-wise operations and deep copying.
 * </p>
 */
public class GenericVector implements Vector {
    //
    // Constructors
    //

    /**
     * Constructs a generic vector with the specified values.
     *
     * @param values The array of values for the vector.
     */
    public GenericVector(@Nonnull double... values) {
        this.values = values.clone();
    }

    //
    // Variables
    //

    private final double[] values;

    //
    // Conversion
    //

    /**
     * {@inheritDoc}
     * <p>
     * This can also be used as a getter for {@link GenericVector#values}.
     *
     * @return An array containing the components of the vector.
     */
    @Nonnull
    @Override
    public double[] toArray() {
        return values.clone();
    }

    //
    // Derivatives
    //

    @Override
    public int size() {
        return values.length;
    }

    @Override
    public double magnitude() {
        double sumOfSquares = 0;
        for (double value : values) {
            sumOfSquares += value * value;
        }
        return Math.sqrt(sumOfSquares);
    }

    @Override
    public double magnitude2() {
        double sumOfSquares = 0;
        for (double value : values) {
            sumOfSquares += value * value;
        }
        return sumOfSquares;
    }

    //
    // Arithmetic
    //

    @Nonnull
    @Override
    public Vector add(double s) {
        final double[] resultValues = new double[values.length];
        for (int i = 0; i < values.length; i++) {
            resultValues[i] = values[i] + s;
        }
        return new GenericVector(resultValues);
    }

    @Nonnull
    @Override
    public Vector subtract(double s) {
        final double[] resultValues = new double[values.length];
        for (int i = 0; i < values.length; i++) {
            resultValues[i] = values[i] - s;
        }
        return new GenericVector(resultValues);
    }

    @Nonnull
    @Override
    public Vector multiply(double s) {
        final double[] resultValues = new double[values.length];
        for (int i = 0; i < values.length; i++) {
            resultValues[i] = values[i] * s;
        }
        return new GenericVector(resultValues);
    }

    @Nonnull
    @Override
    public Vector divide(double s) {
        if (s == 0) {
            throw new IllegalArgumentException("Division by zero.");
        }
        final double[] resultValues = new double[values.length];
        for (int i = 0; i < values.length; i++) {
            resultValues[i] = values[i] / s;
        }
        return new GenericVector(resultValues);
    }

    @Nonnull
    public Vector add(@Nonnull Vector other) {
        if (other.size() != values.length) {
            throw new IllegalArgumentException("Vector dimensions must match.");
        }
        final double[] otherValues = other.toArray();
        final double[] resultValues = new double[values.length];
        for (int i = 0; i < values.length; i++) {
            resultValues[i] = values[i] + otherValues[i];
        }
        return new GenericVector(resultValues);
    }

    @Nonnull
    public Vector subtract(@Nonnull Vector other) {
        if (other.size() != values.length) {
            throw new IllegalArgumentException("Vector dimensions must match.");
        }
        final double[] otherValues = other.toArray();
        final double[] resultValues = new double[values.length];
        for (int i = 0; i < values.length; i++) {
            resultValues[i] = values[i] - otherValues[i];
        }
        return new GenericVector(resultValues);
    }

    public double dot(@Nonnull Vector other) {
        if (other.size() != values.length) {
            throw new IllegalArgumentException("Vector dimensions must match.");
        }
        final double[] otherValues = other.toArray();
        double dotProduct = 0;
        for (int i = 0; i < values.length; i++) {
            dotProduct += values[i] * otherValues[i];
        }
        return dotProduct;
    }

    @Nonnull
    @Override
    public Vector negate() {
        final double[] resultValues = new double[values.length];
        for (int i = 0; i < values.length; i++) {
            resultValues[i] = -values[i];
        }
        return new GenericVector(resultValues);
    }

    //
    // Conversions
    //

    /**
     * Converts this vector to a {@link Vector2}.
     *
     * @return A new {@link Vector2} instance with the components from this vector.
     * @throws UnsupportedOperationException If the size of this vector is not 2.
     */
    @Nonnull
    public Vector2 toVector2() {
        if (size() != 2) {
            throw new UnsupportedOperationException("Cannot convert to Vector2: Incorrect size.");
        }
        return new Vector2(values[0], values[1]);
    }

    /**
     * Converts this vector to a {@link Vector3}.
     *
     * @return A new {@link Vector3} instance with the components from this vector.
     * @throws UnsupportedOperationException If the size of this vector is not 3.
     */
    @Nonnull
    public Vector3 toVector3() {
        if (size() != 3) {
            throw new UnsupportedOperationException("Cannot convert to Vector3: Incorrect size.");
        }
        return new Vector3(values[0], values[1], values[2]);
    }

    /**
     * Converts this vector to a {@link Vector4}.
     *
     * @return A new {@link Vector4} instance with the components from this vector.
     * @throws UnsupportedOperationException If the size of this vector is not 4.
     */
    @Nonnull
    public Vector4 toVector4() {
        if (size() != 4) {
            throw new UnsupportedOperationException("Cannot convert to Vector4: Incorrect size.");
        }
        return new Vector4(values[0], values[1], values[2], values[3]);
    }

    /**
     * Converts this vector to a {@link Quaternion}.
     *
     * @return A new {@link Quaternion} instance with the components from this vector.
     * @throws UnsupportedOperationException If the size of this vector is not 4.
     */
    @Nonnull
    public Quaternion toQuaternion() {
        if (size() != 4) {
            throw new UnsupportedOperationException("Cannot convert to Quaternion: Incorrect size.");
        }
        return new Quaternion(values[0], values[1], values[2], values[3]);
    }
}