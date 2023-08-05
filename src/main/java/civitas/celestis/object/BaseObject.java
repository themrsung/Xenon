package civitas.celestis.object;

import civitas.celestis.graphics.model.Model;
import civitas.celestis.math.quaternion.Quaternion;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.physics.solid.Solid;
import jakarta.annotation.Nonnull;

import java.util.UUID;

/**
 * <h2>BaseObject</h2>
 * <p>
 * The base implementation of an object that is unique, movable, has physical properties, and can be rendered.
 * </p>
 */
public class BaseObject implements Unique, Movable, Physical, Renderable {
    /**
     * Constructs a BaseObject with the provided properties.
     *
     * @param uniqueId The unique identifier of the object.
     * @param location The initial location of the object.
     * @param rotation The initial rotation of the object.
     * @param solid    The physical representation of the object.
     * @param model    The renderable model of the object.
     */
    public BaseObject(@Nonnull UUID uniqueId, @Nonnull Vector3 location, @Nonnull Quaternion rotation, @Nonnull Solid solid, @Nonnull Model model) {
        this(uniqueId, location, Vector3.ZERO, rotation, Quaternion.IDENTITY, solid, model);
    }

    /**
     * Constructs a BaseObject with the provided properties.
     *
     * @param uniqueId     The unique identifier of the object.
     * @param location     The initial location of the object.
     * @param acceleration The initial acceleration of the object.
     * @param rotation     The initial rotation of the object.
     * @param rotationRate The initial rotation rate of the object.
     * @param solid        The physical representation of the object.
     * @param model        The renderable model of the object.
     */
    public BaseObject(@Nonnull UUID uniqueId, @Nonnull Vector3 location, @Nonnull Vector3 acceleration, @Nonnull Quaternion rotation, @Nonnull Quaternion rotationRate, @Nonnull Solid solid, @Nonnull Model model) {
        this.uniqueId = uniqueId;
        this.location = location;
        this.acceleration = acceleration;
        this.rotation = rotation;
        this.rotationRate = rotationRate;
        this.solid = solid;
        this.model = model;
    }

    @Nonnull
    private final UUID uniqueId;
    @Nonnull
    private Vector3 location;
    @Nonnull
    private Vector3 acceleration;
    @Nonnull
    private Quaternion rotation;
    @Nonnull
    private Quaternion rotationRate;
    @Nonnull
    private Solid solid;
    @Nonnull
    private Model model;

    @Override
    @Nonnull
    public UUID getUniqueId() {
        return uniqueId;
    }

    @Override
    @Nonnull
    public Vector3 getLocation() {
        return location;
    }

    @Override
    @Nonnull
    public Vector3 getAcceleration() {
        return acceleration;
    }

    @Override
    @Nonnull
    public Quaternion getRotation() {
        return rotation;
    }

    @Override
    @Nonnull
    public Quaternion getRotationRate() {
        return rotationRate;
    }

    @Override
    @Nonnull
    public Solid getSolid() {
        return solid;
    }

    @Override
    @Nonnull
    public Model getModel() {
        return model;
    }

    @Override
    public void setLocation(@Nonnull Vector3 location) {
        this.location = location;
        this.solid = solid.update(location, rotation);
    }

    @Override
    public void setAcceleration(@Nonnull Vector3 acceleration) {
        this.acceleration = acceleration;
    }

    @Override
    public void setRotation(@Nonnull Quaternion rotation) {
        this.rotation = rotation;
        this.solid = solid.update(location, rotation);
    }

    @Override
    public void setRotationRate(@Nonnull Quaternion rotationRate) {
        this.rotationRate = rotationRate;
    }

    @Override
    public void setSolid(@Nonnull Solid solid) {
        this.solid = solid;
    }

    @Override
    public void setModel(@Nonnull Model model) {
        this.model = model;
    }

    @Override
    public void move(@Nonnull Vector3 amount) {
        this.location = location.add(amount);
        this.solid = solid.update(location, rotation);
    }

    @Override
    public void accelerate(@Nonnull Vector3 amount) {
        this.acceleration = acceleration.add(amount);
    }

    @Override
    public void rotate(@Nonnull Quaternion amount) {
        this.rotation = amount.multiply(rotation);
        this.solid = solid.update(location, rotation);
    }

    @Override
    public void rotateRate(@Nonnull Quaternion amount) {
        this.rotationRate = amount.multiply(rotationRate);
    }
}
