package civitas.celestis.test;

import civitas.celestis.event.EventHandler;
import civitas.celestis.event.HandlerPriority;
import civitas.celestis.event.Listener;
import jakarta.annotation.Nonnull;

public class TestListener implements Listener {
    @EventHandler(priority = HandlerPriority.PERMISSIVE)
    public void onTestPermissive(@Nonnull TestEvent e) {
        System.out.println("Permissive");
    }

    @EventHandler(priority = HandlerPriority.EARLY)
    public void onTestEarly(@Nonnull TestEvent e) {
        System.out.println("Early");
    }
}
