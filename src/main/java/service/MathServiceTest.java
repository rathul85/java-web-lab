package service;

/**
 * Simple console test for Task 09. Run the main method and screenshot the output.
 */
public class MathServiceTest {

    static int pass = 0, fail = 0;

    static void check(String name, boolean ok, Object actual) {
        System.out.println((ok ? "PASS  " : "FAIL  ") + name + " -> " + actual);
        if (ok) pass++; else fail++;
    }

    public static void main(String[] args) {
        check("isPrime(7) = 1", MathService.isPrime(7) == 1, MathService.isPrime(7));
        check("isPrime(8) = 0", MathService.isPrime(8) == 0, MathService.isPrime(8));
        check("isPrime(1) = 0", MathService.isPrime(1) == 0, MathService.isPrime(1));
        check("isPrime(2) = 1", MathService.isPrime(2) == 1, MathService.isPrime(2));
        check("isPrime(-5) = 0", MathService.isPrime(-5) == 0, MathService.isPrime(-5));
        check("isPrime(97) = 1", MathService.isPrime(97) == 1, MathService.isPrime(97));

        check("17 % 5 = 2", MathService.calculateMod(17, 5) == 2, MathService.calculateMod(17, 5));
        check("-17 % 5 = -2", MathService.calculateMod(-17, 5) == -2, MathService.calculateMod(-17, 5));
        check("17 % -5 = 2", MathService.calculateMod(17, -5) == 2, MathService.calculateMod(17, -5));
        check("0 % 7 = 0", MathService.calculateMod(0, 7) == 0, MathService.calculateMod(0, 7));

        try {
            MathService.calculateMod(5, 0);
            check("5 % 0 -> error", false, "no exception");
        } catch (IllegalArgumentException e) {
            check("5 % 0 -> error", true, e.getMessage());
        }

        check("sin(30) = 0.5", MathService.isClose(MathService.calculateSin(30), 0.5), MathService.calculateSin(30));
        check("cos(60) = 0.5", MathService.isClose(MathService.calculateCos(60), 0.5), MathService.calculateCos(60));
        check("tan(45) = 1.0", MathService.isClose(MathService.calculateTan(45), 1.0), MathService.calculateTan(45));

        try {
            MathService.calculateTan(90);
            check("tan(90) -> undefined", false, "no exception");
        } catch (ArithmeticException e) {
            check("tan(90) -> undefined", true, e.getMessage());
        }

        System.out.println("\nPassed: " + pass + "  Failed: " + fail);
    }
}
