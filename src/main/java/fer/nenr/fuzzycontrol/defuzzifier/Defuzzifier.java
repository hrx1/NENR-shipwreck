package fer.nenr.fuzzycontrol.defuzzifier;

import fer.nenr.sets.fuzzy.IFuzzySet;

public interface Defuzzifier {
    int defuzzify(IFuzzySet fuzzySet);
}
