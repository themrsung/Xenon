package civitas.celestis.event.object;

import civitas.celestis.event.Event;
import civitas.celestis.object.BaseObject;
import civitas.celestis.util.group.Pair;
import jakarta.annotation.Nonnull;

/**
 * <h2>ObjectPairEvent</h2>
 * <p>
 * Represents an event involving a pair of objects.
 * </p>
 */
public abstract class ObjectPairEvent implements Event {

    /**
     * Constructs an ObjectPairEvent with a given pair of objects.
     *
     * @param objects The pair of objects involved in the event.
     */
    public ObjectPairEvent(@Nonnull Pair<BaseObject> objects) {
        this.objects = objects;
    }

    /**
     * Constructs an ObjectPairEvent with two given objects.
     *
     * @param o1 The first object.
     * @param o2 The second object.
     */
    public ObjectPairEvent(@Nonnull BaseObject o1, @Nonnull BaseObject o2) {
        this(new Pair<>(o1, o2));
    }

    @Nonnull
    private final Pair<BaseObject> objects;

    /**
     * Retrieves the pair of objects involved in the event.
     *
     * @return The pair of objects.
     */
    @Nonnull
    public Pair<BaseObject> getObjects() {
        return objects;
    }
}
