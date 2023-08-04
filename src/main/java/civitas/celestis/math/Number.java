package civitas.celestis.math;

import jakarta.annotation.Nonnull;

/**
 * <h2>Number</h2>
 * <p>
 * The {@code Number} interface represents a mathematical entity that can be treated as a collection of numbers,
 * whether in a vector or matrix form. It provides methods to access properties such as array representation.
 * </p>
 */
public interface Number {
    /**
     * Returns a copy of the number as a 1D double array.
     *
     * @return A copy of the number as a 1D double array.
     */
    @Nonnull
    double[] toArray();
}