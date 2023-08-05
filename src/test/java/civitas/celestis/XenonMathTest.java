package civitas.celestis;

import civitas.celestis.math.matrix.GenericMatrix;
import civitas.celestis.math.matrix.Matrix;
import civitas.celestis.util.group.Pair;
import civitas.celestis.util.group.Tuple;

public class XenonMathTest {
    public static void main(String[] args) {
        final Matrix m = new GenericMatrix(new double[][] {
                {1, 1, 1},
                {2, 2, 2},
                {3, 3, 3}
        });

        final Matrix n = new GenericMatrix(new double[][] {
                {3, 3, 3},
                {2, 2, 2},
                {1, 1, 1}
        });

        final Matrix o = m.multiply(n);
//        System.out.println(o);


        final Tuple<Float> tuple = new Tuple<>(3f, 2f, 1f);
        System.out.println(tuple.apply(f -> f * 3));
    }
}
