package fer.nenr.sets.fuzzy;

import fer.nenr.sets.regular.DomainElement;
import fer.nenr.sets.regular.IDomain;

public interface IFuzzySet {
    IDomain getDomain();
    double getValueFor(DomainElement domainElement);

    default void printElementsWithValues() {
        for(DomainElement element : getDomain()) {
            System.out.println(String.format("%s, %f", element, getValueFor(element)));
        }
    }
}
