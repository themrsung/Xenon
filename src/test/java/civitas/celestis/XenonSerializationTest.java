package civitas.celestis;

import civitas.celestis.math.matrix.GenericMatrix;
import civitas.celestis.math.matrix.Matrix;
import civitas.celestis.math.vector.Vector4;

public class XenonSerializationTest {
    public static void main(String[] args) {
        final Vector4 v = new Vector4(1, 2, 3, 4);
        System.out.println(Vector4.parseVector(v.toString()));

        final Matrix m = new GenericMatrix(new double[][] {
                {1, 2, 3},
                {4, 5, 6}
        });

        final Matrix n = GenericMatrix.parseMatrix(m.toString());

        System.out.println(n);
        System.out.println(n.get(0, 1));
    }
}
