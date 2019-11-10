package fer.nenr.sets.relations;

import fer.nenr.sets.fuzzy.IFuzzySet;
import fer.nenr.sets.fuzzy.MutableFuzzySet;
import fer.nenr.sets.regular.Domain;
import fer.nenr.sets.regular.DomainElement;
import fer.nenr.sets.regular.IDomain;

public class Relations {

    public static final double THRESHOLD = 1e-8;
    /*
     * Zapravo testira jesu li domene od kojih je slozen fuzzyset jednake.
     * PROBLEM: Zamisli da postoji skup A, te da je domena seta AxAxAxA (4 puta A),
     * gledamo li onda zapravo (AxA)x(AxA) pa je U times U ili?
     *
     * PRETPOSTAVKA:
     *  Imamo samo AxA
     *
     */
    public static boolean isUtimesURelation(IFuzzySet relation) {
        if(relation.getDomain().getNumberOfComponents() != 2) {
            throw new UnsupportedOperationException("Ne znam kako gledati one koji nisu AxA");
        }

        //TODO dodati u equals od domaina?
        IDomain domain1 = relation.getDomain().getComponent(0);
        IDomain domain2 = relation.getDomain().getComponent(1);
        if(domain1.getNumberOfComponents() != domain2.getNumberOfComponents() || domain1.getNumberOfComponents() != 1) {
            return false;
        }

        if(domain1.getNumberOfComponents() != domain2.getNumberOfComponents()) {
            return false;
        }

        for(DomainElement d : domain1) {
            if(domain2.indexOfElement(d) == -1) {
                return false;
            }
        }


        return true;
    }

    /**
     * Ako je r(a,b) = 1 onda je i r(b,a) = 1
     *
     * @param relation
     * @return
     */
    public static boolean isSymmetric(IFuzzySet relation) {
        if(!isUtimesURelation(relation)){
            return false;
        }

        for(DomainElement domainElement : relation.getDomain()) {

                DomainElement symetric = DomainElement.of(domainElement.getComponentValue(1), domainElement.getComponentValue(0));
                double value = relation.getValueFor(domainElement);
                double symValue = relation.getValueFor(symetric);
                if(Math.abs(value - symValue) > THRESHOLD) {
                    return false;
                }
        }

        return true;
    }
    /**

     * Refleksivna ako: f(x,x) = 1, za svaki x iz U. Odnosno ako je svaki u relaciji sa samim sobom.
     * Ne radi ako je domena slozena s vise od 2 U skupa.
     *
     * @param relation
     * @return
     */
    public static boolean isReflexive(IFuzzySet relation) {
        if(!isUtimesURelation(relation))  {
            return false;
        }

        for(DomainElement d : relation.getDomain().getComponent(0)) {
            int x = d.getComponentValue(0);
            if(relation.getValueFor(DomainElement.of(x, x)) != 1){
                return false;
            }
        }

        return true;
    }

    public static boolean isMaxMinTransitive(IFuzzySet relation) {

        if(!isUtimesURelation(relation)){
            return false;
        }


        IDomain baseDomain = relation.getDomain().getComponent(0);

        for(DomainElement x : baseDomain) {
            int xElement = x.getComponentValue(0);
            for(DomainElement z : baseDomain) {
                int zElement = z.getComponentValue(0);
                double yMax = 0;
                for(DomainElement y : baseDomain) {
                    int yElement = y.getComponentValue(0);
                    double xyValue = relation.getValueFor(DomainElement.of(xElement, yElement));
                    double yzValue = relation.getValueFor(DomainElement.of(yElement, zElement));
                    yMax = Math.max(yMax,Math.min(xyValue,yzValue));
                }

                double xzValue = relation.getValueFor(DomainElement.of(xElement, zElement));
                if(xzValue < yMax) {
                    return false;
                }
            }
        }

        return true;
    }

    //TODO provjera je li AxB kompozicija BxC
    public static IFuzzySet compositionOfBinaryRelations(IFuzzySet r1, IFuzzySet r2) {
        IDomain xes = r1.getDomain().getComponent(0);
        IDomain yes = r1.getDomain().getComponent(1);
        IDomain zes = r2.getDomain().getComponent(1);


        IDomain xzDomain = Domain.combine(xes, zes);
        MutableFuzzySet resultSet = new MutableFuzzySet(xzDomain);
        for(DomainElement xElement : xes) {
            int xElementValue = xElement.getComponentValue(0);
            for(DomainElement zElement : zes) {
                int zElementValue = zElement.getComponentValue(0);
                double xzElementValue = 0;
                for(DomainElement yElement : yes) {
                    int yValue = yElement.getComponentValue(0);
                    double xyValue = r1.getValueFor(DomainElement.of(xElementValue, yValue));
                    double yzValue = r2.getValueFor(DomainElement.of(yValue, zElementValue));
                    xzElementValue = Math.max(xzElementValue, Math.min(xyValue, yzValue));
                }
                DomainElement xzElement = DomainElement.of(xElement.getComponentValue(0), zElement.getComponentValue(0));
                resultSet.set(xzElement, xzElementValue);
            }
        }
        return resultSet;
    }

    public static boolean isFuzzyEquivalence(IFuzzySet fuzzySet) {
        return isMaxMinTransitive(fuzzySet) && isReflexive(fuzzySet) && isSymmetric(fuzzySet);
    }



}
