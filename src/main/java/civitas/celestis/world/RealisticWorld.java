package civitas.celestis.world;

import civitas.celestis.Xenon;
import civitas.celestis.event.object.ObjectsCollidedEvent;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.object.BaseObject;
import civitas.celestis.object.XenonObjects;
import civitas.celestis.util.group.Group;
import civitas.celestis.util.group.Pair;
import jakarta.annotation.Nonnull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <h2>RealisticWorld</h2>
 * <p>
 * Represents a realistic world with physics properties such as gravity and air density.
 * </p>
 */
public class RealisticWorld implements World {
    /**
     * Creates a new instance of the RealisticWorld class with the specified unique identifier and name.
     * This constructor initializes the world with empty lists of objects and overlaps.
     *
     * @param uniqueId The unique identifier of the world.
     * @param name The name of the world.
     */
    public RealisticWorld(@Nonnull UUID uniqueId, @Nonnull String name) {
        this(uniqueId, name, new ArrayList<>(), new ArrayList<>());
    }

    /**
     * Constructs a RealisticWorld with default gravity and air density.
     *
     * @param uniqueId  The unique identifier of the world.
     * @param name      The name of the world.
     * @param objects   The list of objects in the world.
     * @param overlaps  The list of overlapping pairs of objects.
     */
    public RealisticWorld(@Nonnull UUID uniqueId, @Nonnull String name, @Nonnull List<BaseObject> objects, @Nonnull List<Pair<BaseObject>> overlaps) {
        this(uniqueId, name, objects, overlaps, new Vector3(0, -9.807, 0), 1.225);
    }

    /**
     * Constructs a RealisticWorld with specified gravity and air density.
     *
     * @param uniqueId    The unique identifier of the world.
     * @param name        The name of the world.
     * @param objects     The list of objects in the world.
     * @param overlaps    The list of overlapping pairs of objects.
     * @param gravity     The gravity vector affecting objects in the world.
     * @param airDensity  The air density affecting objects in the world.
     */
    public RealisticWorld(@Nonnull UUID uniqueId, @Nonnull String name, @Nonnull List<BaseObject> objects, @Nonnull List<Pair<BaseObject>> overlaps, @Nonnull Vector3 gravity, double airDensity) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.objects = objects;
        this.overlaps = overlaps;
        this.gravity = gravity;
        this.airDensity = airDensity;
    }

    @Nonnull
    private final UUID uniqueId;
    @Nonnull
    private final String name;
    @Nonnull
    private final List<BaseObject> objects;
    @Nonnull
    private final List<Pair<BaseObject>> overlaps;
    @Nonnull
    private Vector3 gravity;
    private double airDensity;

    @Override
    @Nonnull
    public UUID getUniqueId() {
        return uniqueId;
    }

    @Override
    @Nonnull
    public String getName() {
        return name;
    }

    @Override
    @Nonnull
    public List<BaseObject> getObjects() {
        return new ArrayList<>(objects);
    }

    @Nonnull
    @Override
    public BaseObject getObject(@Nonnull UUID uniqueId) throws NullPointerException {
        for (final BaseObject object : getObjects()) {
            if (object.getUniqueId().equals(uniqueId)) return object;
        }

        throw new NullPointerException("Object of unique identifier " + uniqueId + " does not exist.");
    }

    @Override
    @Nonnull
    public List<Pair<BaseObject>> getOverlaps() {
        return new ArrayList<>(overlaps);
    }

    @Override
    public void updateOverlaps() {
        final List<BaseObject> objects = getObjects();
        final List<Pair<BaseObject>> possiblePairs = Group.pairsOfList(objects);

        overlaps.removeIf(p -> !possiblePairs.contains(p));

        for (final Pair<BaseObject> pair : possiblePairs) {
            if (XenonObjects.overlaps(pair.a(), pair.b())) {
                if (!overlaps.contains(pair)) {
                    overlaps.add(pair);
                    Xenon.getEventManager().call(new ObjectsCollidedEvent(pair));
                }
            } else {
                overlaps.remove(pair);
            }
        }
    }

    @Override
    public void addObject(@Nonnull BaseObject object) {
        objects.add(object);
    }

    @Override
    public void removeObject(@Nonnull BaseObject object) {
        objects.remove(object);
        overlaps.removeIf(p -> p.contains(object));
    }

    @Override
    @Nonnull
    public Vector3 getGravity() {
        return gravity;
    }

    @Override
    public double getAirDensity() {
        return airDensity;
    }

    @Override
    public void setGravity(@Nonnull Vector3 gravity) {
        this.gravity = gravity;
    }

    @Override
    public void setAirDensity(double airDensity) {
        this.airDensity = airDensity;
    }
}
