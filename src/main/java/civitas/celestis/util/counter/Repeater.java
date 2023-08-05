package civitas.celestis.util.counter;

/**
 * <h2>Repeater</h2>
 *
 * <p>The {@code Repeater} class represents a simple utility for generating a sequence of numbers
 * in a repeating manner, accounting for an upper limit.</p>
 */
public final class Repeater {
    /**
     * Constructs a {@code Repeater} instance with the specified upper limit.
     *
     * @param limit The upper limit of the repeating sequence.
     */
    public Repeater(int limit) {
        this(limit, 0);
    }

    /**
     * Constructs a {@code Repeater} instance with the specified upper limit and initial value.
     *
     * @param limit The upper limit of the repeating sequence.
     * @param i     The initial value of the sequence.
     */
    public Repeater(int limit, int i) {
        this.limit = limit;
        this.i = i;
    }

    private final int limit;
    private int i;

    /**
     * Generates the next value in the repeating sequence.
     *
     * @return The next value in the sequence.
     */
    public int next() {
        return iterate();
    }

    /**
     * Generates the next value in the repeating sequence, accounting for the upper limit.
     *
     * @return The next value in the sequence.
     */
    private int iterate() {
        final int currentValue = i;
        i = (i + 1) % limit; // Increment and wrap around if necessary
        return currentValue;
    }
}
