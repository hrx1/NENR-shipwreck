package fer.nenr.sets.fuzzy.test;

import fer.nenr.sets.fuzzy.CalculatedFuzzySet;
import fer.nenr.sets.fuzzy.IFuzzySet;
import fer.nenr.sets.fuzzy.StandardFuzzySets;
import fer.nenr.sets.regular.DomainElement;
import fer.nenr.sets.regular.IDomain;

import static fer.nenr.sets.regular.Domain.*;

public class CalculatedFuzzySetTest {
    public static void main(String[] args) {
        IDomain d2 = intRange(-5, 6); // {-5,-4,...,4,5}
        IFuzzySet set2 = new CalculatedFuzzySet(
                d2,
                StandardFuzzySets.lambdaFunction(
                        d2.indexOfElement(DomainElement.of(-4)),
                        d2.indexOfElement(DomainElement.of( 0)),
                        d2.indexOfElement(DomainElement.of( 4))
                )
        );

        set2.printElementsWithValues();
    }
}
