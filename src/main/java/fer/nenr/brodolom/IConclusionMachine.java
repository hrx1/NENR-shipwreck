package fer.nenr.brodolom;

import java.util.function.DoubleBinaryOperator;

public interface IConclusionMachine {
    DoubleBinaryOperator implicationOperator();
    DoubleBinaryOperator sNorm();
    DoubleBinaryOperator tNorm();
}
