package civitas.celestis.math.util;

import civitas.celestis.math.matrix.Matrix;
import civitas.celestis.math.vector.Vector;
import civitas.celestis.math.vector.Vector2;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.math.vector.Vector4;
import jakarta.annotation.Nonnull;

/**
 * <h2>Numbers</h2>
 * <p>A numerical utility class.</p>
 */
public final class Numbers {
    //
    // Constraints
    //

    /**
     * The usage of this method is an explicit notation that a given input must be finite.
     * Being non-finite is defined as being the values of {@link Double#NaN},
     * {@link Double#POSITIVE_INFINITY}, or {@link Double#NEGATIVE_INFINITY}.
     *
     * @param in The input value to be checked for finiteness.
     * @return The input value if it is finite.
     * @throws IllegalArgumentException If the input value is not finite.
     */
    public static double requireFinite(double in) {
        if (!Double.isFinite(in)) {
            throw new IllegalArgumentException("This field requires a finite value as its input. The value provided was " + in + ".");
        }

        return in;
    }

    /**
     * The usage of this method is an explicit notation that a given input must be finite.
     * Being non-finite is defined as being the values of {@link Float#NaN},
     * {@link Float#POSITIVE_INFINITY}, or {@link Float#NEGATIVE_INFINITY}.
     *
     * @param in The input value to be checked for finiteness.
     * @return The input value if it is finite.
     * @throws IllegalArgumentException If the input value is not finite.
     */
    public static float requireFinite(float in) {
        if (!Float.isFinite(in)) {
            throw new IllegalArgumentException("This field requires a finite value as its input. The value provided was " + in + ".");
        }

        return in;
    }

    //
    // Factory
    //

    /**
     * Creates a vector from a double array.
     *
     * @param values The array of values for the vector.
     * @return A new vector instance based on the length of the input array.
     * @throws IllegalArgumentException If the array length is not valid for any vector type.
     */
    @Nonnull
    public static Vector createVector(@Nonnull double[] values) {
        int length = values.length;
        if (length == 2) {
            return new Vector2(values[0], values[1]);
        } else if (length == 3) {
            return new Vector3(values[0], values[1], values[2]);
        } else if (length == 4) {
            return new Vector4(values[0], values[1], values[2], values[3]);
        } else {
            throw new IllegalArgumentException("Invalid array length for vector.");
        }
    }

    //
    // Vector-Matrix Arithmetic
    //

    /**
     * Performs vector-matrix addition.
     *
     * @param vector The vector to be added.
     * @param matrix The matrix to be added.
     * @return A new vector representing the result of the addition.
     * @throws IllegalArgumentException If matrix columns are not compatible with vector length.
     */
    @Nonnull
    public static Vector add(@Nonnull Vector vector, @Nonnull Matrix matrix) {
        // Check for compatibility between matrix and vector dimensions
        int vectorLength = vector.size();
        if (matrix.getNumRows() != vectorLength || matrix.getNumCols() < 1) {
            throw new IllegalArgumentException("Matrix dimensions must match vector length.");
        }

        double[] vectorArray = vector.toArray();
        double[][] matrixArray = new double[matrix.getNumRows()][matrix.getNumCols()];
        for (int i = 0; i < matrix.getNumRows(); i++) {
            for (int j = 0; j < matrix.getNumCols(); j++) {
                matrixArray[i][j] = matrix.get(i, j);
            }
        }

        double[] resultArray = new double[vectorLength];
        for (int i = 0; i < vectorLength; i++) {
            double sum = vectorArray[i];
            for (int j = 0; j < matrix.getNumCols(); j++) {
                sum += matrixArray[i][j];
            }
            resultArray[i] = sum;
        }

        return createVector(resultArray);
    }

    /**
     * Performs vector-matrix subtraction.
     *
     * @param vector The vector to be subtracted.
     * @param matrix The matrix to be subtracted.
     * @return A new vector representing the result of the subtraction.
     * @throws IllegalArgumentException If matrix columns are not compatible with vector length.
     */
    @Nonnull
    public static Vector subtract(@Nonnull Vector vector, @Nonnull Matrix matrix) {
        // Check for compatibility between matrix and vector dimensions
        int vectorLength = vector.size();
        if (matrix.getNumRows() != vectorLength || matrix.getNumCols() < 1) {
            throw new IllegalArgumentException("Matrix dimensions must match vector length.");
        }

        double[] vectorArray = vector.toArray();
        double[][] matrixArray = new double[matrix.getNumRows()][matrix.getNumCols()];
        for (int i = 0; i < matrix.getNumRows(); i++) {
            for (int j = 0; j < matrix.getNumCols(); j++) {
                matrixArray[i][j] = matrix.get(i, j);
            }
        }

        double[] resultArray = new double[vectorLength];
        for (int i = 0; i < vectorLength; i++) {
            double diff = vectorArray[i];
            for (int j = 0; j < matrix.getNumCols(); j++) {
                diff -= matrixArray[i][j];
            }
            resultArray[i] = diff;
        }

        return createVector(resultArray);
    }

    /**
     * Performs vector-matrix multiplication.
     *
     * @param vector The vector to be multiplied.
     * @param matrix The matrix to be multiplied.
     * @return A new vector representing the result of the multiplication.
     * @throws IllegalArgumentException If matrix columns are not compatible with vector length.
     */
    @Nonnull
    public static Vector multiply(@Nonnull Vector vector, @Nonnull Matrix matrix) {
        double[] vectorArray = vector.toArray();
        double[][] matrixArray = new double[matrix.getNumRows()][matrix.getNumCols()];
        for (int i = 0; i < matrix.getNumRows(); i++) {
            for (int j = 0; j < matrix.getNumCols(); j++) {
                matrixArray[i][j] = matrix.get(i, j);
            }
        }

        if (vectorArray.length != matrix.getNumCols()) {
            throw new IllegalArgumentException("Matrix columns must match vector length.");
        }

        double[] resultArray = new double[matrix.getNumRows()];
        for (int i = 0; i < matrix.getNumRows(); i++) {
            double sum = 0;
            for (int j = 0; j < matrix.getNumCols(); j++) {
                sum += vectorArray[j] * matrixArray[i][j];
            }
            resultArray[i] = sum;
        }

        return createVector(resultArray);
    }
}
