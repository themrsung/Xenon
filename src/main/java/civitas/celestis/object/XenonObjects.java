package civitas.celestis.object;

import civitas.celestis.math.quaternion.Quaternion;
import jakarta.annotation.Nonnull;

/**
 * <h2>XenonObjects</h2>
 * <p>
 * Utility class for XenonObjects, providing static methods and constants.
 * This class cannot be instantiated.
 * </p>
 */
public final class XenonObjects {
    //
    // Physics
    //

    /**
     * Checks if two BaseObjects overlap by comparing their Solid components.
     *
     * @param o1 The first BaseObject.
     * @param o2 The second BaseObject.
     * @return {@code true} if the solids of the two BaseObjects overlap, {@code false} otherwise.
     */
    public static boolean overlaps(@Nonnull BaseObject o1, @Nonnull BaseObject o2) {
        return o1.getSolid().overlaps(o2.getSolid());
    }

    /**
     * Checks if a Movable object is currently in motion by examining its acceleration.
     *
     * @param o The Movable object to check.
     * @return {@code true} if the magnitude of the acceleration is greater than zero, indicating motion, {@code false} otherwise.
     */
    public static boolean isMoving(@Nonnull Movable o) {
        return o.getAcceleration().magnitude2() > 0;
    }

    /**
     * Checks if a Movable object is currently rotating by comparing its rotation rate with the identity quaternion.
     *
     * @param o The Movable object to check.
     * @return {@code true} if the rotation rate is not equal to the identity quaternion, indicating rotation, {@code false} otherwise.
     */
    public static boolean isRotating(@Nonnull Movable o) {
        return !o.getRotationRate().equals(Quaternion.IDENTITY);
    }

    //
    // Instantiation Blocking
    //

    /**
     * Private constructor for the utility class XenonObjects.
     * Throws an exception to prevent instantiation of this class.
     *
     * @throws Exception Always throws an exception indicating that XenonObjects cannot be instantiated.
     */
    private XenonObjects() throws Exception {
        throw new Exception("XenonObjects cannot be instantiated.");
    }
}
