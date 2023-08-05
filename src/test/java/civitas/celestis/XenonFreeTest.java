package civitas.celestis;

import civitas.celestis.graphics.model.ObjModel;
import de.javagl.obj.Obj;
import de.javagl.obj.ObjReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class XenonFreeTest {
    public static void main(String[] args) {
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

        final ObjModel model = new ObjModel(obj);

        System.out.println(model.face(0));

        final ObjModel m2 = model.apply(v -> v.apply(c -> c + 100000));
        System.out.println(m2.face(0));
    }
}
