package civitas.celestis.object;

import civitas.celestis.graphics.model.Model;
import jakarta.annotation.Nonnull;


/**
 * <h2>Renderable</h2>
 * <p>
 * The {@code Renderable} interface defines objects that can be rendered using a {@link Model}.
 * It provides a method to retrieve the model associated with the object for rendering purposes.
 * </p>
 */
public interface Renderable {
    /**
     * Retrieves the {@link Model} associated with the object for rendering.
     *
     * @return The model associated with the object.
     */
    @Nonnull
    Model getModel();

    /**
     * Sets the renderable model for the object.
     *
     * @param model The renderable model to set.
     */
    void setModel(@Nonnull Model model);
}
