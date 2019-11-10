package fer.nenr.sets.regular;

public interface IDomain extends Iterable<DomainElement> {
    int getCardinality();
    IDomain getComponent(int orderNumber);
    int getNumberOfComponents();

    /**
     *
     * @param element
     * @return -1 if element not found.
     */
    int indexOfElement(DomainElement element);
    DomainElement elementForIndex(int index);

}
