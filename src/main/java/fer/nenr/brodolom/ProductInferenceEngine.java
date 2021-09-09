package fer.nenr.brodolom;

import java.util.function.DoubleBinaryOperator;

public class ProductInferenceEngine implements IConclusionMachine {

    @Override
    public DoubleBinaryOperator implicationOperator() {
        return tNorm();
    }

    @Override
    public DoubleBinaryOperator sNorm() {
        return Math::max;
    }

    @Override
    public DoubleBinaryOperator tNorm() {
        return (a, b) -> a * b;
    }

}
