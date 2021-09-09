package fer.nenr.brodolom;

import fer.nenr.fuzzycontrol.defuzzifier.CenterOfAreaDefuzzy;
import fer.nenr.fuzzycontrol.defuzzifier.Defuzzifier;
import fer.nenr.sets.fuzzy.IFuzzySet;

public class DebugDefuzzy implements Defuzzifier {
    Defuzzifier defuzzifier;

    public DebugDefuzzy(Defuzzifier defuzzifier) {
        this.defuzzifier = defuzzifier;
    }

    @Override
    public int defuzzify(IFuzzySet fuzzySet) {
        fuzzySet.printElementsWithValues();;
        return defuzzifier.defuzzify(fuzzySet);
    }
}
