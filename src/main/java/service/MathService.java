package service;

/**
 * Reusable maths methods used by the game (Tasks 03 - 05).
 */
public class MathService {

    /** Returns 1 if the number is prime, otherwise 0. Numbers below 2 are not prime. */
    public static int isPrime(int number) {
        if (number < 2) return 0;
        for (int i = 2; (long) i * i <= number; i++) {
            if (number % i == 0) return 0;
        }
        return 1;
    }

    /** Returns a % b. Throws IllegalArgumentException when the divisor is zero. */
    public static int calculateMod(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Divisor cannot be zero");
        }
        return a % b;
    }

    public static double calculateSin(double degree) {
        return Math.sin(Math.toRadians(degree));
    }

    public static double calculateCos(double degree) {
        return Math.cos(Math.toRadians(degree));
    }

    /** Throws ArithmeticException where tangent is undefined (90, 270, ... degrees). */
    public static double calculateTan(double degree) {
        double r = Math.abs(degree % 180);
        if (Math.abs(r - 90) < 1e-9) {
            throw new ArithmeticException("Tangent is undefined at " + degree + " degrees");
        }
        return Math.tan(Math.toRadians(degree));
    }

    /** type = "sin", "cos" or "tan". */
    public static double calculateTrig(String type, double degree) {
        switch (type) {
            case "sin": return calculateSin(degree);
            case "cos": return calculateCos(degree);
            case "tan": return calculateTan(degree);
            default: throw new IllegalArgumentException("Unknown trig type: " + type);
        }
    }

    /** Compares decimal answers with a tolerance of 0.01. */
    public static boolean isClose(double a, double b) {
        return Math.abs(a - b) <= 0.01;
    }
}
