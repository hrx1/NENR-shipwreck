package fer.nenr.sets.fuzzy.ferprimjeri;

import fer.nenr.sets.fuzzy.IFuzzySet;
import fer.nenr.sets.regular.DomainElement;
import fer.nenr.sets.regular.IDomain;

public class Debug {

    public static void print(IDomain domain, String headingText) {
        if(headingText!=null) {
            System.out.println(headingText);
        }
        for(DomainElement e : domain) {
            System.out.println("Element domene: " + e);
        }
        System.out.println("Kardinalitet domene je: " + domain.getCardinality());
        System.out.println();
    }

    public static void print(IFuzzySet set, String headingText) {
        if(headingText!=null) {
            System.out.println(headingText);
        }
        for(DomainElement element : set.getDomain()) {
            String rowText = String.format("d(%s)=%f",element, set.getValueFor(element));
            System.out.println(rowText);
        }
    }
}
