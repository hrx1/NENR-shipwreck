package fer.nenr.sets.regular;


import java.util.Iterator;

public class SimpleDomain extends Domain {

    private int first, last;

    public SimpleDomain(int inclusiveStart, int exclusiveEnd) {
        first = inclusiveStart;
        last = exclusiveEnd -1;
    }

    public int getCardinality() {
        return last - first + 1;
    }

    public IDomain getComponent(int orderNumber) {
        if(orderNumber != 0) {
            throw new IllegalArgumentException("Order number of simple domain is not 0");
        }
        return this;
    }

    public int getNumberOfComponents() {
        return 1;
    }

    public int indexOfElement(DomainElement element) {
        int value = element.getComponentValue(0);
        if (value < first || value > last) {
            return -1;
        }
        return value - first;
    }

    public DomainElement elementForIndex(int index) {
        int result = first + index;
        if(result > last) {
            throw new IllegalArgumentException();
        }
        return DomainElement.of(new int[]{result});
    }

    public Iterator iterator() {
        return new SimpleDomainIterator();
    }

    Iterator lightweightIterator(){
        return new SimpleDomainLightIterator();
    }

    public int getFirst() {
        return first;
    }

    public int getLast() {
        return last;
    }

    class SimpleDomainLightIterator implements Iterator<Integer> {

        private int current = first - 1;

        public boolean hasNext() {
            return current < last;
        }

        public Integer next() {
            ++current;
            return current;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class SimpleDomainIterator implements Iterator<DomainElement> {

        private SimpleDomainLightIterator iterator = new SimpleDomainLightIterator();

        public boolean hasNext() {
            return iterator.hasNext();
        }

        public DomainElement next() {
            return DomainElement.of(new int[]{iterator.next()});
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}