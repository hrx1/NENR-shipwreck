package fer.nenr.util;

public class ArgumentUtils {

    public static void requireNonNull(Object argument) {
        if (argument == null) {
            throw new IllegalArgumentException();
        }
    }

    public static void requireNonNull(Object ... arguments) {
        for (Object a : arguments) {
            requireNonNull(a);
        }
    }

    public static void requireNonNulls(Object [] arguments) {
        requireNonNull(arguments);
        for (Object a : arguments) {
            requireNonNull(a);
        }
    }

}
