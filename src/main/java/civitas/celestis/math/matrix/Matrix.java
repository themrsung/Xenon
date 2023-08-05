package civitas.celestis.math.matrix;

import civitas.celestis.math.Number;
import jakarta.annotation.Nonnull;

import java.util.Iterator;
import java.util.function.UnaryOperator;

/**
 * <h2>Matrix</h2>
 * <p>
 * This interface defines the contract for a matrix representation.
 * </p>
 */
public interface Matrix extends Number, Iterable<Double> {
    //
    // Accessors
    //

    /**
     * Retrieves the element at the specified row and column in the matrix.
     *
     * @param row    The row index of the element.
     * @param column The column index of the element.
     * @return The value of the element at the specified row and column.
     * @throws IndexOutOfBoundsException If the provided row or column index is out of bounds.
     */
    double get(int row, int column) throws IndexOutOfBoundsException;

    /**
     * Sets the element at the specified row and column in the matrix to the given value and returns this matrix instance.
     *
     * @param row    The row index of the element to set.
     * @param column The column index of the element to set.
     * @param value  The value to set at the specified row and column.
     * @return This matrix instance after setting the element.
     * @throws IndexOutOfBoundsException If the provided row or column index is out of bounds.
     */
    @Nonnull
    Matrix set(int row, int column, double value) throws IndexOutOfBoundsException;

    /**
     * Returns the number of rows in the matrix.
     *
     * @return The number of rows in the matrix.
     */
    int getNumRows();

    /**
     * Returns the number of columns in the matrix.
     *
     * @return The number of columns in the matrix.
     */
    int getNumCols();

    //
    // Conversion
    //

    /**
     * Returns the elements of the matrix as a 1-dimensional array.
     *
     * @return The array representation of the matrix.
     */
    @Nonnull
    double[] toArray();

    //
    // Arithmetic
    //

    /**
     * Adds a scalar value to all elements of the matrix and returns a new matrix with the result.
     *
     * @param s The scalar value to add to the matrix elements.
     * @return A new matrix resulting from adding the scalar value to the elements.
     */
    @Nonnull
    Matrix add(double s);

    /**
     * Subtracts a scalar value from all elements of the matrix and returns a new matrix with the result.
     *
     * @param s The scalar value to subtract from the matrix elements.
     * @return A new matrix resulting from subtracting the scalar value from the elements.
     */
    @Nonnull
    Matrix subtract(double s);

    /**
     * Multiplies all elements of the matrix by a scalar value and returns a new matrix with the result.
     *
     * @param s The scalar value to multiply with the matrix elements.
     * @return A new matrix resulting from multiplying the scalar value with the elements.
     */
    @Nonnull
    Matrix multiply(double s);

    /**
     * Divides all elements of the matrix by a scalar value and returns a new matrix with the result.
     *
     * @param s The scalar value to divide the matrix elements by.
     * @return A new matrix resulting from dividing the elements by the scalar value.
     */
    @Nonnull
    Matrix divide(double s);

    /**
     * Adds another matrix element-wise to this matrix and returns a new matrix with the result.
     *
     * @param other The matrix to be added.
     * @return A new matrix resulting from element-wise addition.
     */
    @Nonnull
    Matrix add(@Nonnull Matrix other);

    /**
     * Subtracts another matrix element-wise from this matrix and returns a new matrix with the result.
     *
     * @param other The matrix to be subtracted.
     * @return A new matrix resulting from element-wise subtraction.
     */
    @Nonnull
    Matrix subtract(@Nonnull Matrix other);

    /**
     * Multiplies this matrix by another matrix and returns a new matrix with the result.
     *
     * @param other The matrix to be multiplied with.
     * @return A new matrix resulting from matrix multiplication.
     */
    @Nonnull
    Matrix multiply(@Nonnull Matrix other);

    //
    // Utility
    //

    /**
     * Applies the given unary operator element-wise to each element of the matrix.
     *
     * @param operator The unary operator to be applied.
     * @return A new matrix where the given operator has been applied element-wise.
     */
    @Nonnull
    Matrix apply(@Nonnull UnaryOperator<Double> operator);

    /**
     * Returns a new matrix where all elements are negated (multiplied by -1).
     *
     * @return A new matrix with negated elements.
     */
    @Nonnull
    Matrix negate();

    /**
     * Returns an iterator over the elements in the matrix. The iterator iterates through the elements
     * row by row and provides a view of the matrix elements as {@link Double} values.
     *
     * @return An iterator over the matrix elements.
     */
    @Override
    @Nonnull
    Iterator<Double> iterator();

    /**
     * Creates a new matrix with the specified dimensions by resizing the original matrix.
     *
     * @param numRows    The number of rows in the new matrix.
     * @param numColumns The number of columns in the new matrix.
     * @return A new {@link GenericMatrix} instance with the resized dimensions.
     * @throws IllegalArgumentException If the new dimensions are not valid (non-negative).
     */
    @Nonnull
    GenericMatrix resize(int numRows, int numColumns);

    //
    // Cloning
    //

    /**
     * Returns a new matrix that is a deep copy of the current matrix.
     *
     * @return A new matrix that is a deep copy of the current matrix.
     */
    @Nonnull
    Matrix copy();
}
