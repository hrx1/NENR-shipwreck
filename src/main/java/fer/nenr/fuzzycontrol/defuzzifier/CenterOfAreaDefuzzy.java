package fer.nenr.fuzzycontrol.defuzzifier;

import fer.nenr.sets.fuzzy.IFuzzySet;
import fer.nenr.sets.regular.DomainElement;

public class CenterOfAreaDefuzzy implements Defuzzifier {
    @Override
    public int defuzzify(IFuzzySet fuzzySet) {
        double brojnik = 0, nazivnik = 0;
        for(DomainElement element : fuzzySet.getDomain()) {
            double value = fuzzySet.getValueFor(element);
            int eleValue = element.getComponentValue(0);
            brojnik += eleValue * value;
            nazivnik += value;
        }

        return (int) Math.round(brojnik/nazivnik);
    }
}
