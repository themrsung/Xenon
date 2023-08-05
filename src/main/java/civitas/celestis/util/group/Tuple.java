package civitas.celestis.util.group;

import de.javagl.obj.Obj;
import jakarta.annotation.Nonnull;

import java.util.List;
import java.util.Objects;

/**
 * <h2>Tuple</h2>
 *
 * <p>The {@code Tuple} class represents a group of three objects. It implements the {@link Group} interface,
 * providing methods to work with a tuple of objects as a unit. This class is used to organize and manipulate
 * three related objects together.</p>
 *
 * @param <T> The type of objects held by the tuple.
 */
public class Tuple<T> implements Group<T> {
    /**
     * Constructs a {@code Tuple} instance with the provided objects.
     *
     * @param a The first object in the tuple.
     * @param b The second object in the tuple.
     * @param c The third object in the tuple.
     */
    public Tuple(@Nonnull T a, @Nonnull T b, @Nonnull T c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Nonnull
    private final T a;
    @Nonnull
    private final T b;
    @Nonnull
    private final T c;

    /**
     * Returns the first object in the tuple.
     *
     * @return The first object.
     */
    @Nonnull
    public T a() {
        return a;
    }

    /**
     * Returns the second object in the tuple.
     *
     * @return The second object.
     */
    @Nonnull
    public T b() {
        return b;
    }

    /**
     * Returns the third object in the tuple.
     *
     * @return The third object.
     */
    @Nonnull
    public T c() {
        return c;
    }

    /**
     * Returns a {@link Pair} instance that contains the other two elements in the tuple,
     * excluding the provided element.
     *
     * @param element The element for which to find the other two elements in the tuple.
     * @return A {@link Pair} instance containing the other two elements.
     * @throws IllegalArgumentException If the provided element is not a member of this tuple.
     */
    @Nonnull
    public Pair<T> other(@Nonnull T element) throws IllegalArgumentException {
        if (a.equals(element)) return new Pair<>(b, c);
        if (b.equals(element)) return new Pair<>(a, c);
        if (c.equals(element)) return new Pair<>(a, b);

        throw new IllegalArgumentException("Given element is not a member of this tuple.");
    }

    @Nonnull
    @Override
    public List<T> toList() {
        return List.of(a, b, c);
    }

    @Override
    public boolean contains(@Nonnull T object) {
        return a.equals(object) || b.equals(object) || c.equals(object);
    }

    @Override
    public boolean equalsIgnoreOrder(@Nonnull Group<T> other) {
        if (!(other instanceof Tuple<?> tuple)) return false;

        return (Objects.equals(tuple.a, a) || Objects.equals(tuple.a, b) || Objects.equals(tuple.a, c)) ||
                (Objects.equals(tuple.b, a) || Objects.equals(tuple.b, b) || Objects.equals(tuple.b, c)) ||
                (Objects.equals(tuple.c, a) || Objects.equals(tuple.c, b) || Objects.equals(tuple.c, c));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Tuple<?> tuple)) return false;

        return Objects.equals(a, tuple.a) && Objects.equals(b, tuple.b) && Objects.equals(c, tuple.c);
    }
}