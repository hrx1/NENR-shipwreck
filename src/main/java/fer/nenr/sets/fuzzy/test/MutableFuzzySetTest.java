package fer.nenr.sets.fuzzy.test;

import fer.nenr.sets.fuzzy.IFuzzySet;
import fer.nenr.sets.fuzzy.MutableFuzzySet;
import fer.nenr.sets.regular.Domain;
import fer.nenr.sets.regular.DomainElement;
import fer.nenr.sets.regular.IDomain;

public class MutableFuzzySetTest {

    public static void main(String[] args) {
        IDomain d = Domain.intRange(0, 11); // {0,1,...,10}
        IFuzzySet set1 = new MutableFuzzySet(d)
                .set(DomainElement.of(0), 1.0)
                .set(DomainElement.of(1), 0.8)
                .set(DomainElement.of(2), 0.6)
                .set(DomainElement.of(3), 0.4)
                .set(DomainElement.of(4), 0.2);

        set1.printElementsWithValues();
    }
}
