package civitas.celestis;

import java.util.function.UnaryOperator;

public class XenonDerivativeExample {
    public static void main(String[] args) {
        UnaryOperator<Double> function = x -> x * x; // Example function: f(x) = x^2

        double x = 2.0; // Point at which to find the derivative
        double h = 1e-6; // Small step size for numerical differentiation

        double derivative = approximateDerivative(function, x, h);
        System.out.println("Approximate derivative at x = " + x + " is " + derivative);
    }

    public static double approximateDerivative(UnaryOperator<Double> function, double x, double h) {
        double fPlusH = function.apply(x + h);
        double fMinusH = function.apply(x - h);

        return (fPlusH - fMinusH) / (2 * h); // Central difference formula
    }
}

