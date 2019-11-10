package fer.nenr.util;

public class DoubleUtil {

    public static boolean equals(double d1, double d2, double threshold) {
        return Math.abs(d1 - d2) < threshold;
    }
}
