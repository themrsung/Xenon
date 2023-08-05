package civitas.celestis.util.group;

import jakarta.annotation.Nonnull;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.UnaryOperator;

/**
 * <h2>Pair</h2>
 *
 * <p>The {@code Pair} class represents a simple group of two objects. It implements the {@link Group} interface,
 * providing methods to work with a pair of objects as a unit. This class is a basic building block for organizing
 * and manipulating two related objects together.</p>
 *
 * @param <T> The type of objects held by the pair.
 */
public class Pair<T> implements Group<T> {
    /**
     * Constructs a {@code Pair} instance with the provided objects.
     *
     * @param a The first object in the pair.
     * @param b The second object in the pair.
     */
    public Pair(@Nonnull T a, @Nonnull T b) {
        this.a = a;
        this.b = b;
    }

    @Nonnull
    private final T a;
    @Nonnull
    private final T b;

    /**
     * Returns the first object in the pair.
     *
     * @return The first object.
     */
    @Nonnull
    public T a() {
        return a;
    }

    /**
     * Returns the second object in the pair.
     *
     * @return The second object.
     */
    @Nonnull
    public T b() {
        return b;
    }

    /**
     * Returns the other element in the pair that is not equal to the provided element.
     *
     * @param element The element for which to find the other element in the pair.
     * @return The other element in the pair.
     * @throws IllegalArgumentException If the provided element is not a member of this pair.
     */
    @Nonnull
    public T other(@Nonnull T element) throws IllegalArgumentException {
        if (a.equals(element)) return b;
        if (b.equals(element)) return a;

        throw new IllegalArgumentException("Given element is not a member of this pair.");
    }

    @Nonnull
    @Override
    public List<T> toList() {
        return List.of(a, b);
    }

    @Override
    public boolean contains(@Nonnull T object) {
        return a.equals(object) || b.equals(object);
    }

    @Override
    public boolean equalsIgnoreOrder(@Nonnull Group<T> other) {
        if (!(other instanceof Pair<?> pair)) return false;

        return (Objects.equals(a, pair.a) && Objects.equals(b, pair.b)) ||
                (Objects.equals(a, pair.b) && Objects.equals(b, pair.a));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Pair<?> pair)) return false;

        return Objects.equals(a, pair.a) && Objects.equals(b, pair.b);
    }

    @Nonnull
    @Override
    public Pair<T> apply(@Nonnull UnaryOperator<T> operator) {
        return new Pair<>(operator.apply(a), operator.apply(b));
    }

    @Override
    @Nonnull
    public Iterator<T> iterator() {
        return List.of(a, b).iterator();
    }

    @Override
    @Nonnull
    public String toString() {
        return "Pair{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}
