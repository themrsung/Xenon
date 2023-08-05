package civitas.celestis.physics.util;

/**
 * <h2>Physics</h2>
 * <p>
 * The {@code Physics} class provides utility methods related to physics calculations.
 * This class is designed to be used as a static utility class and cannot be instantiated.
 * </p>
 */
public final class Physics {


    //
    // Instantiation Blocking
    //

    /**
     * Private constructor to prevent instantiation of the {@code Physics} class.
     * Throws an exception with a message indicating that instantiation is not allowed.
     *
     * @throws Exception Always throws an exception with a message.
     */
    private Physics() throws Exception {
        throw new Exception("Physics cannot be instantiated.");
    }
}
