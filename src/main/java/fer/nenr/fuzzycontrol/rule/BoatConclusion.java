package fer.nenr.fuzzycontrol.rule;

import fer.nenr.sets.fuzzy.CalculatedFuzzySet;
import fer.nenr.sets.fuzzy.IFuzzySet;
import fer.nenr.sets.fuzzy.StandardFuzzySets;
import fer.nenr.sets.fuzzy.operations.Operations;

public class BoatConclusion {
    public static final IFuzzySet SLOW_DOWN = new CalculatedFuzzySet(BoatDomain.acceleration, StandardFuzzySets.lFunction(10, 20));
    public static final IFuzzySet SPEED_UP = new CalculatedFuzzySet(BoatDomain.acceleration, StandardFuzzySets.gammaFunction(10 ,20));
}
