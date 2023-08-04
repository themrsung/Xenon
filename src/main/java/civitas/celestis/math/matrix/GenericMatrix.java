package civitas.celestis.math.matrix;

import jakarta.annotation.Nonnull;

import java.util.Iterator;

/**
 * <h2>GenericMatrix</h2>
 * <p>
 * This class represents a generic matrix with double values. It provides various operations for matrix arithmetic,
 * including addition, subtraction, multiplication, and division. The matrix can be created from a 2D array or a
 * flat array of values. It supports element-wise operations and deep copying.
 * </p>
 * <p>
 * The class implements the {@link Matrix} interface, allowing compatibility with other matrix implementations.
 * </p>
 */
public class GenericMatrix implements Matrix {
    //
    // Constructors
    //

    /**
     * Constructs a generic matrix with the specified number of rows and columns.
     *
     * @param numRows The number of rows in the matrix.
     * @param numCols The number of columns in the matrix.
     * @throws IllegalArgumentException If the number of rows or columns is not positive.
     */
    public GenericMatrix(int numRows, int numCols) {
        if (numRows <= 0 || numCols <= 0) {
            throw new IllegalArgumentException("Number of rows and columns must be positive.");
        }

        this.numRows = numRows;
        this.numCols = numCols;
        this.values = new double[numRows * numCols];
    }

    /**
     * Constructs a new matrix from a provided 2D array of values.
     *
     * @param matrix The 2D array of values to initialize the matrix with.
     * @throws IllegalArgumentException If the matrix dimensions are not consistent.
     */
    public GenericMatrix(@Nonnull double[][] matrix) {
        int numRows = matrix.length;
        int numCols = (numRows > 0) ? matrix[0].length : 0;

        for (int i = 1; i < numRows; i++) {
            if (matrix[i].length != numCols) {
                throw new IllegalArgumentException("Matrix rows must have consistent lengths.");
            }
        }

        this.numRows = numRows;
        this.numCols = numCols;
        this.values = new double[numRows * numCols];

        for (int i = 0; i < numRows; i++) {
            System.arraycopy(matrix[i], 0, this.values, i * numCols, numCols);
        }
    }

    //
    // Variables
    //

    private final int numRows;
    private final int numCols;
    private final double[] values;

    //
    // Accessors
    //

    @Override
    public double get(int row, int col) {
        if (row < 0 || row >= numRows || col < 0 || col >= numCols) {
            throw new IndexOutOfBoundsException("Invalid row or column index.");
        }
        return values[row * numCols + col];
    }

    @Override
    @Nonnull
    public Matrix set(int row, int col, double value) {
        if (row < 0 || row >= numRows || col < 0 || col >= numCols) {
            throw new IndexOutOfBoundsException("Invalid row or column index.");
        }
        values[row * numCols + col] = value;

        return this;
    }

    @Override
    public int getNumRows() {
        return numRows;
    }

    @Override
    public int getNumCols() {
        return numCols;
    }

    //
    // Conversion
    //

    @Override
    @Nonnull
    public double[] toArray() {
        return values.clone();
    }

    //
    // Arithmetic
    //

    @Nonnull
    @Override
    public Matrix add(double s) {
        final GenericMatrix result = new GenericMatrix(numRows, numCols);
        for (int i = 0; i < values.length; i++) {
            result.values[i] = values[i] + s;
        }
        return result;
    }

    @Nonnull
    @Override
    public Matrix subtract(double s) {
        final GenericMatrix result = new GenericMatrix(numRows, numCols);
        for (int i = 0; i < values.length; i++) {
            result.values[i] = values[i] - s;
        }
        return result;
    }

    @Nonnull
    @Override
    public Matrix multiply(double s) {
        final GenericMatrix result = new GenericMatrix(numRows, numCols);
        for (int i = 0; i < values.length; i++) {
            result.values[i] = values[i] * s;
        }
        return result;
    }

    @Nonnull
    @Override
    public Matrix divide(double s) {
        if (s == 0) {
            throw new IllegalArgumentException("Division by zero.");
        }
        final GenericMatrix result = new GenericMatrix(numRows, numCols);
        for (int i = 0; i < values.length; i++) {
            result.values[i] = values[i] / s;
        }
        return result;
    }

    @Nonnull
    @Override
    public Matrix add(@Nonnull Matrix other) {
        if (other.getNumRows() != numRows || other.getNumCols() != numCols) {
            throw new IllegalArgumentException("Matrix dimensions must match for addition.");
        }
        final GenericMatrix result = new GenericMatrix(numRows, numCols);
        for (int i = 0; i < values.length; i++) {
            result.values[i] = values[i] + other.get(i / numCols, i % numCols);
        }
        return result;
    }

    @Nonnull
    @Override
    public Matrix subtract(@Nonnull Matrix other) {
        if (other.getNumRows() != numRows || other.getNumCols() != numCols) {
            throw new IllegalArgumentException("Matrix dimensions must match for subtraction.");
        }
        final GenericMatrix result = new GenericMatrix(numRows, numCols);
        for (int i = 0; i < values.length; i++) {
            result.values[i] = values[i] - other.get(i / numCols, i % numCols);
        }
        return result;
    }

    @Nonnull
    @Override
    public Matrix multiply(@Nonnull Matrix other) {
        if (numCols != other.getNumRows()) {
            throw new IllegalArgumentException("Matrix dimensions are not compatible for multiplication.");
        }
        final GenericMatrix result = new GenericMatrix(numRows, other.getNumCols());
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < other.getNumCols(); j++) {
                double sum = 0;
                for (int k = 0; k < numCols; k++) {
                    sum += get(i, k) * other.get(k, j);
                }
                result.set(i, j, sum);
            }
        }
        return result;
    }

    //
    // Utility
    //

    @Nonnull
    @Override
    public Matrix negate() {
        final GenericMatrix result = new GenericMatrix(numRows, numCols);
        for (int i = 0; i < values.length; i++) {
            result.values[i] = -values[i];
        }
        return result;
    }

    @Override
    @Nonnull
    public Iterator<Double> iterator() {
        return new Iterator<Double>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < values.length;
            }

            @Override
            public Double next() {
                if (!hasNext()) {
                    throw new IllegalStateException("No more elements in the matrix.");
                }
                return values[index++];
            }
        };
    }

    @Override
    @Nonnull
    public GenericMatrix resize(int numRows, int numColumns) {
        if (numRows < 0 || numColumns < 0) {
            throw new IllegalArgumentException("Dimensions must be non-negative.");
        }

        final GenericMatrix newMatrix = new GenericMatrix(numRows, numColumns);
        final int minRows = Math.min(this.numRows, numRows);
        final int minCols = Math.min(this.numCols, numColumns);

        for (int row = 0; row < minRows; row++) {
            for (int col = 0; col < minCols; col++) {
                try {
                    newMatrix.set(row, col, get(row, col));
                } catch (IndexOutOfBoundsException ignored) {
                    // Fail silently and move on
                }
            }
        }

        return newMatrix;
    }

    //
    // Cloning
    //

    @Override
    @Nonnull
    public Matrix copy() {
        final GenericMatrix copy = new GenericMatrix(numRows, numCols);
        System.arraycopy(values, 0, copy.values, 0, values.length);
        return copy;
    }
}
