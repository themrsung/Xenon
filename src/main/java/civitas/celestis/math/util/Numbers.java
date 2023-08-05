package civitas.celestis.math.util;

import civitas.celestis.math.matrix.Matrix;
import civitas.celestis.math.vector.GenericVector;
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
    // Loose Zeroness
    //

    /**
     * Checks if a double value is considered zero within a certain margin of significance.
     *
     * @param d The double value to check.
     * @return {@code true} if the value is effectively zero, {@code false} otherwise.
     */
    public static boolean isZero(double d) {
        return equals(d, 0);
    }

    /**
     * Checks if a float value is considered zero within a certain margin of significance.
     *
     * @param f The float value to check.
     * @return {@code true} if the value is effectively zero, {@code false} otherwise.
     */
    public static boolean isZero(float f) {
        return equals(f, 0);
    }

    /**
     * Checks if a Vector's magnitude is effectively zero within a certain margin of significance.
     *
     * @param v The Vector to check.
     * @return {@code true} if the Vector's magnitude is effectively zero, {@code false} otherwise.
     */
    public static boolean isZero(@Nonnull Vector v) {
        final double[] array = new double[v.size()];
        return equals(v, new GenericVector(array));
    }

    /**
     * Checks if all elements in a Matrix are effectively zero within a certain margin of significance.
     *
     * @param m The Matrix to check.
     * @return {@code true} if all elements in the Matrix are effectively zero, {@code false} otherwise.
     */
    public static boolean isZero(@Nonnull Matrix m) {
        for (final Double d : m) {
            if (!equals(d, 0)) return false;
        }
        return true;
    }

    //
    // Loose Equality
    //

    /**
     * Margin of significance used for comparing floating-point values.
     */
    public static final double MARGIN_OF_SIGNIFICANCE = 1e-6;

    /**
     * Compares two double values with a margin of significance.
     *
     * @param d1 The first double value.
     * @param d2 The second double value.
     * @return {@code true} if the values are considered equal, within the margin of significance, {@code false} otherwise.
     */
    public static boolean equals(double d1, double d2) {
        return Math.abs(d1 - d2) < MARGIN_OF_SIGNIFICANCE;
    }

    /**
     * Compares two float values with a margin of significance.
     *
     * @param f1 The first float value.
     * @param f2 The second float value.
     * @return {@code true} if the values are considered equal, within the margin of significance, {@code false} otherwise.
     */
    public static boolean equals(float f1, float f2) {
        return Math.abs(f1 - f2) < MARGIN_OF_SIGNIFICANCE;
    }

    /**
     * Compares two {@link Vector} objects with a margin of significance.
     *
     * @param v1 The first Vector.
     * @param v2 The second Vector.
     * @return {@code true} if the vectors are considered equal, within the margin of significance, {@code false} otherwise.
     */
    public static boolean equals(@Nonnull Vector v1, @Nonnull Vector v2) {
        final double[] a1 = v1.toArray();
        final double[] a2 = v2.toArray();

        if (a1.length != a2.length) return false;
        final double margin = MARGIN_OF_SIGNIFICANCE / a1.length;

        for (int i = 0; i < a1.length; i++) {
            if (Math.abs(a1[i] - a2[i]) > margin) return false;
        }

        return true;
    }

    /**
     * Compares two {@link Matrix} objects with a margin of significance.
     *
     * @param m1 The first Matrix.
     * @param m2 The second Matrix.
     * @return {@code true} if the matrices are considered equal, within the margin of significance, {@code false} otherwise.
     */
    public static boolean equals(@Nonnull Matrix m1, @Nonnull Matrix m2) {
        if (m1.getNumRows() != m2.getNumRows() || m1.getNumCols() != m2.getNumCols()) return false;

        final double[] a1 = m1.toArray();
        final double[] a2 = m2.toArray();

        for (int i = 0; i < a1.length; i++) {
            if (!equals(a1[i], a2[i])) return false;
        }

        return true;
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
