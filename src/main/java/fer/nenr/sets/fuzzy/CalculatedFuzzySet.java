package fer.nenr.sets.fuzzy;

import fer.nenr.sets.regular.DomainElement;
import fer.nenr.sets.regular.IDomain;

import java.util.function.IntToDoubleFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.UnaryOperator;

public class CalculatedFuzzySet implements IFuzzySet {

    private IntToDoubleFunction membershipFunction;

    private IDomain domain;

    public CalculatedFuzzySet(IDomain domain, IntToDoubleFunction membershipFunction) {
        this.membershipFunction = membershipFunction;
        this.domain = domain;
    }

    @Override
    public IDomain getDomain() {
        return domain;
    }

    @Override
    public double getValueFor(DomainElement domainElement) {
        int index = domain.indexOfElement(domainElement);
        return membershipFunction.applyAsDouble(index);
    }
}
