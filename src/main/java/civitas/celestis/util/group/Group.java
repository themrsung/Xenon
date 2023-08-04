package civitas.celestis.util.group;

import jakarta.annotation.Nonnull;

import java.util.List;

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
public interface Group<T> {
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
}
