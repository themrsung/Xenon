package civitas.celestis.math.vector;

import civitas.celestis.math.Number;
import jakarta.annotation.Nonnull;

/**
 * <h2>Vector</h2>
 * <p>
 * An array of scalars.
 * A vector is composed of two or more scalars, and can represent a direction and/or magnitude,
 * or a certain coordinate within an n-dimensional space.
 * </p>
 */
public interface Vector extends Number {
    //
    // Conversion
    //

    /**
     * Converts the vector to an array representation.
     *
     * @return An array containing the components of the vector.
     */
    @Nonnull
    double[] toArray();

    /**
     * Returns the number of components in this vector.
     *
     * @return The number of components in the vector.
     */
    int size();

    //
    // Derivatives
    //

    /**
     * Calculates and returns the magnitude (length) of this vector.
     *
     * @return The magnitude of the vector.
     */
    double magnitude();

    /**
     * Calculates and returns the squared magnitude of this vector.
     * The squared magnitude is often used for optimization purposes as it avoids the square root operation.
     *
     * @return The squared magnitude of the vector.
     */
    double magnitude2();

    //
    // Arithmetic
    //

    /**
     * Adds a scalar value to each component of the vector.
     *
     * @param s The scalar value to be added.
     * @return A new vector resulting from the addition.
     */
    @Nonnull
    Vector add(double s);

    /**
     * Subtracts a scalar value from each component of the vector.
     *
     * @param s The scalar value to be subtracted.
     * @return A new vector resulting from the subtraction.
     */
    @Nonnull
    Vector subtract(double s);

    /**
     * Multiplies each component of the vector by a scalar value.
     *
     * @param s The scalar value to be multiplied.
     * @return A new vector resulting from the multiplication.
     */
    @Nonnull
    Vector multiply(double s);

    /**
     * Divides each component of the vector by a scalar value.
     *
     * @param s The scalar value to be divided by.
     * @return A new vector resulting from the division.
     */
    @Nonnull
    Vector divide(double s);

    //
    // Utility
    //

    /**
     * Returns the negation of this vector.
     * The negation of a vector is a vector with the same magnitude but opposite direction.
     *
     * @return The negation of the vector.
     */
    @Nonnull
    Vector negate();
}
