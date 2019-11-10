package fer.nenr.sets.regular;

import fer.nenr.util.ArgumentUtils;

import java.util.Iterator;

public class CompositeDomain extends Domain {

    private SimpleDomain[] components;

    private int cardinality;

    private int dimensions;

    public CompositeDomain(SimpleDomain[] components) {
        ArgumentUtils.requireNonNulls(components);
        this.components = components;

        cardinality = 1;
        for (SimpleDomain simpleDomain : components) {
            cardinality *= simpleDomain.getCardinality();
        }
    }

    public int getCardinality() {
        return cardinality;
    }

    public IDomain getComponent(int orderNumber) {
        return components[orderNumber];
    }

    /**
     * Odgovara dimenziji
     * @return
     */
    public int getNumberOfComponents() {
        return components.length;
    }

    //Kako se radi o cijelim brojevima, ovo se moglo izracunati. Izracunaj ako bude bottleneck
    //TODO trenutna slozenost je n1*n2*...*ni, a moze biti n1+...+ni. nk je kardinalitet k-te komponente
    public int indexOfElement(DomainElement element) {

        int position = 0;
        for (DomainElement domainElement : this) {
            if(domainElement.equals(element)) {
                return position;
            }
            ++position;
        }

        return -1;
    }

    //Kako se radi o cijelim brojevima, ovo se moglo izracunati. Izracunaj ako bude bottleneck
    public DomainElement elementForIndex(int index) {
        int position = 0;
        for (DomainElement domainElement : this) {
            if(position == index) {
                return domainElement;
            }
            ++position;
        }

        return null;
    }

    public Iterator<DomainElement> iterator() {
        return new CompositeDomainIterator();
    }

    private class CompositeDomainIterator implements Iterator<DomainElement> {

        private Iterator<Integer>[] iterators;

        private int[] currentElement;

        private boolean firstOccurance;

        private CompositeDomainIterator(){

            iterators = new Iterator[getNumberOfComponents()];
            currentElement = new int[getNumberOfComponents()];
            firstOccurance = true;

            for (int i = 0; i < iterators.length; ++i) {
                iterators[i] = components[i].lightweightIterator();
            }
        }

        public boolean hasNext() {
            for(var iter : iterators) {
                if(iter.hasNext()) {
                    return true;
                }
            }
            return false;
        }

        public DomainElement next() {

            if(firstOccurance) {
                firstOccurance = false;
                for (int i = 0; i < getNumberOfComponents(); ++i) {
                    currentElement[i] = iterators[i].next();
                }

                return new DomainElement(currentElement);
            }

            for (int i = iterators.length - 1; i >= 0; --i) {
                if (iterators[i].hasNext()) {
                    currentElement[i] = iterators[i].next();
                    break;
                } else {
                    iterators[i] = components[i].lightweightIterator();
                    currentElement[i] = iterators[i].next();
                }
            }

            return new DomainElement(currentElement); //TODO pazi treba li clone
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
