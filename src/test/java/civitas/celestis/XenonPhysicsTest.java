package civitas.celestis;

import civitas.celestis.graphics.model.ObjModel;
import civitas.celestis.math.quaternion.Quaternion;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.object.BaseObject;
import civitas.celestis.physics.solid.Sphere;
import civitas.celestis.task.Task;
import civitas.celestis.world.RealisticWorld;
import civitas.celestis.world.World;
import de.javagl.obj.Obj;
import de.javagl.obj.ObjReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;

public class XenonPhysicsTest {
    public static void main(String[] args) {
        // Get model
        final File file = new File("src/main/resources/assets/models/bc304/BC304Render.obj");
        final FileReader reader;
        try {
            reader = new FileReader(file);
        } catch (IOException e) {
            return;
        }

        final Obj obj;
        try {
            obj = ObjReader.read(reader);
        } catch (IOException e) {
            return;
        }

        final ObjModel model = new ObjModel(obj, 3);

        // Define world
        final World world = new RealisticWorld(UUID.randomUUID(), "TestWorld");
        Xenon.getWorldManager().addWorld(world);

        // Define object
        final BaseObject o1 = new BaseObject(
                UUID.randomUUID(),
                new Vector3(0, 555, 0),
                Quaternion.IDENTITY,
                new Sphere(new Vector3(0, 555, 0), Quaternion.IDENTITY, 250, 7387412),
                model
        );
        world.addObject(o1);

        // Start engine
        Xenon.start();

        Xenon.getScheduler().register(new Task() {
            @Override
            public void execute(long delta) {
                System.out.println(o1.getSolid().centroid());
            }

            @Override
            public long interval() {
                return 1000;
            }
        });
    }
}
