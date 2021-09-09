package fer.nenr.fuzzycontrol.rule;

import fer.nenr.sets.fuzzy.CalculatedFuzzySet;
import fer.nenr.sets.fuzzy.IFuzzySet;
import fer.nenr.sets.fuzzy.StandardFuzzySets;
import fer.nenr.sets.fuzzy.operations.Operations;

public class BoatConclusion {
    public static final IFuzzySet SLOW_DOWN = new CalculatedFuzzySet(BoatDomain.acceleration, StandardFuzzySets.lFunction(40, 50));
    public static final IFuzzySet SPEED_UP = new CalculatedFuzzySet(BoatDomain.acceleration, StandardFuzzySets.lambdaFunction(60 ,65,70));
    public static final IFuzzySet TURN_RIGHT = new CalculatedFuzzySet(BoatDomain.angleDomain, StandardFuzzySets.lFunction(0, 25));
    public static final IFuzzySet TURN_LEFT = new CalculatedFuzzySet(BoatDomain.angleDomain, StandardFuzzySets.gammaFunction(155, 180));
}
