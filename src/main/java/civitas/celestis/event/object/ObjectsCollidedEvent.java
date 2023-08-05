package civitas.celestis.event.object;

import civitas.celestis.object.BaseObject;
import civitas.celestis.util.group.Pair;
import jakarta.annotation.Nonnull;

/**
 * <h2>ObjectsCollidedEvent</h2>
 * <p>
 * Represents an event indicating that two objects have collided.
 * </p>
 */
public final class ObjectsCollidedEvent extends ObjectPairEvent {
    /**
     * Constructs an ObjectsCollidedEvent with a given pair of collided objects.
     *
     * @param objects The pair of collided objects.
     */
    public ObjectsCollidedEvent(@Nonnull Pair<BaseObject> objects) {
        super(objects);
    }

    /**
     * Constructs an ObjectsCollidedEvent with two given collided objects.
     *
     * @param o1 The first collided object.
     * @param o2 The second collided object.
     */
    public ObjectsCollidedEvent(@Nonnull BaseObject o1, @Nonnull BaseObject o2) {
        super(o1, o2);
    }
}

