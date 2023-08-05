package civitas.celestis.object;

import civitas.celestis.math.quaternion.Quaternion;
import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;

/**
 * <h2>Movable</h2>
 * <p>
 * The {@code Movable} interface represents objects that can be moved, accelerated, and rotated in 3D space.
 * It provides methods for getting and setting the location, acceleration, rotation, and rotation rate
 * of the object, as well as methods for applying movements, accelerations, and rotations to the object.
 * </p>
 */
public interface Movable {
    /**
     * Retrieves the current location of the object in 3D space.
     *
     * @return The current location of the object.
     */
    @Nonnull
    Vector3 getLocation();

    /**
     * Retrieves the current acceleration of the object in 3D space.
     *
     * @return The current acceleration of the object.
     */
    @Nonnull
    Vector3 getAcceleration();

    /**
     * Retrieves the current rotation quaternion representing the orientation of the object.
     *
     * @return The current rotation quaternion of the object.
     */
    @Nonnull
    Quaternion getRotation();

    /**
     * Retrieves the current rotation rate quaternion representing the rotational speed of the object.
     *
     * @return The current rotation rate quaternion of the object.
     */
    @Nonnull
    Quaternion getRotationRate();

    /**
     * Sets the new location of the object in 3D space.
     *
     * @param location The new location of the object.
     */
    void setLocation(@Nonnull Vector3 location);

    /**
     * Sets the new acceleration of the object in 3D space.
     *
     * @param acceleration The new acceleration of the object.
     */
    void setAcceleration(@Nonnull Vector3 acceleration);

    /**
     * Sets the new rotation quaternion to orient the object.
     *
     * @param rotation The new rotation quaternion.
     */
    void setRotation(@Nonnull Quaternion rotation);

    /**
     * Sets the new rotation rate quaternion to control the rotational speed of the object.
     *
     * @param rotationRate The new rotation rate quaternion.
     */
    void setRotationRate(@Nonnull Quaternion rotationRate);

    /**
     * Moves the object by the specified amount in 3D space.
     *
     * @param amount The amount by which to move the object.
     */
    void move(@Nonnull Vector3 amount);

    /**
     * Accelerates the object by the specified amount in 3D space.
     *
     * @param amount The amount by which to accelerate the object.
     */
    void accelerate(@Nonnull Vector3 amount);

    /**
     * Rotates the object by the specified amount using a quaternion rotation.
     *
     * @param amount The rotation quaternion representing the rotation amount.
     */
    void rotate(@Nonnull Quaternion amount);

    /**
     * Adjusts the rotational speed of the object by applying a rotation rate quaternion.
     *
     * @param amount The rotation rate quaternion representing the change in rotational speed.
     */
    void rotateRate(@Nonnull Quaternion amount);
}
