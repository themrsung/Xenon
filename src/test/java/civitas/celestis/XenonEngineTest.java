package civitas.celestis;

import civitas.celestis.test.TestEvent;
import civitas.celestis.test.TestListener;

public class XenonEngineTest {
    public static void main(String[] args) {
        Xenon.start();

        Xenon.getEventManager().register(new TestListener());
        Xenon.getEventManager().call(new TestEvent());

        Xenon.stop();
    }
}
