package civitas.celestis.math.util;

import civitas.celestis.math.matrix.Matrix;
import civitas.celestis.math.vector.Vector;
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

        return Vector.createVector(resultArray);
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

        return Vector.createVector(resultArray);
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

        return Vector.createVector(resultArray);
    }

    //
    // Instantiation Blocking
    //

    /**
     * Private constructor to prevent instantiation of the utility class.
     * <p>
     * This constructor is intentionally marked as private to prevent the creation of instances
     * of the {@code Numbers} class. It throws an exception to indicate that instances of this class
     * cannot be created.
     *
     * @throws Exception Always throws an exception to prevent instantiation.
     */
    private Numbers() throws Exception {
        throw new Exception("Numbers class cannot be instantiated.");
    }
}
