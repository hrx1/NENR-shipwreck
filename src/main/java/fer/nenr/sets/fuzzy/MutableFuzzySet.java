package fer.nenr.sets.fuzzy;

import fer.nenr.sets.regular.DomainElement;
import fer.nenr.sets.regular.IDomain;

public class MutableFuzzySet implements IFuzzySet {

    private IDomain domain;
    private double[] values;

    public MutableFuzzySet(IDomain domain) {
        this.domain = domain;
        values = new double[domain.getCardinality()];
    }

    public MutableFuzzySet set(DomainElement element, double value) {
        int elementIndex = domain.indexOfElement(element);
        values[elementIndex] = value;
        return this;
    }

    @Override
    public IDomain getDomain() {
        return domain;
    }

    @Override
    public double getValueFor(DomainElement element) {
        int elementIndex = domain.indexOfElement(element);
        return values[elementIndex];
    }
}
