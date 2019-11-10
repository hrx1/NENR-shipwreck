package fer.nenr.sets.fuzzy;

import java.util.function.IntToDoubleFunction;
import java.util.function.IntUnaryOperator;

public class StandardFuzzySets {
    public static IntToDoubleFunction lFunction(int alpha, int beta) {
        return index -> {
            if (index < alpha) return 1;
            if (index >= beta) return 0;

            return (beta - index)/((double)(beta - alpha));
        };
    }

    public static IntToDoubleFunction lambdaFunction(int alpha, int beta, int gamma) {
        return index -> {
            if (index < alpha || index >= gamma) return 0;
            if (index >= alpha && index < beta) {
                return (index - alpha)/((double)(beta - alpha));
            }

            return (gamma - index)/((double)(gamma - beta));
        };
    }

    public static IntToDoubleFunction gammaFunction(int alpha, int beta) {
        return index -> {
            if (index < alpha) return 0;
            if (index >= beta) return 1;

            return (index - alpha)/((double)(beta - alpha));
        };

    }


}
