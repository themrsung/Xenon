package civitas.celestis.util.group;

import jakarta.annotation.Nonnull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.UnaryOperator;

/**
 * <h2>Group</h2>
 * <p>
 * The {@code Group} interface represents a collection of objects, typically organized as a tuple or a pair.
 * It serves as a generic structure that can hold multiple objects together, allowing for logical grouping
 * and manipulation of related data elements.
 * </p>
 * <p>
 * Implementations of this interface define how the group behaves and interacts with its constituent objects.
 * It provides a foundation for creating and managing various types of groups that may have specific semantics
 * and operations.
 * </p>
 *
 * @param <T> The type of objects that this group can hold.
 */
public interface Group<T> extends Iterable<T> {
    /**
     * Returns a {@link List} representation of the objects in the group.
     *
     * @return A {@link List} containing the objects in the group.
     */
    @Nonnull
    List<T> toList();

    /**
     * Checks if the group contains the specified object.
     *
     * @param object The object to check for existence in the group.
     * @return {@code true} if the group contains the object, {@code false} otherwise.
     */
    boolean contains(@Nonnull T object);

    /**
     * Checks if the group is equal to another group, ignoring the order of objects.
     *
     * @param other The other {@link Group} to compare with.
     * @return {@code true} if the groups are equal, ignoring the order of objects, {@code false} otherwise.
     */
    boolean equalsIgnoreOrder(@Nonnull Group<T> other);

    /**
     * Applies the given unary operator to each element in the group and returns a new group
     * containing the transformed elements.
     *
     * @param operator The unary operator to apply to each element.
     * @return A new group with the transformed elements.
     */
    @Nonnull
    Group<T> apply(@Nonnull UnaryOperator<T> operator);

    /**
     * Returns an iterator over the elements in this group.
     *
     * @return An iterator that traverses the elements in this group.
     */
    @Override
    @Nonnull
    Iterator<T> iterator();

    /**
     * Generates a list of unique pairs from a given list of elements.
     *
     * @param list The list of elements from which to generate pairs.
     * @param <A>  The type of elements in the list.
     * @return A list of unique pairs.
     */
    @Nonnull
    static <A> List<Pair<A>> pairsOfList(@Nonnull List<A> list) {
        final List<Pair<A>> result = new ArrayList<>();
        for (final A a : list) {
            for (final A b : list) {
                if (a.equals(b)) continue;

                final Pair<A> pair = new Pair<>(a, b);
                if (result.stream().anyMatch(p -> p.equalsIgnoreOrder(pair))) continue;

                result.add(pair);
            }
        }
        return result;
    }
}
