package fer.nenr.sets.regular;

public abstract class Domain implements IDomain {

    public static IDomain intRange(int start, int end) {
        return new SimpleDomain(start, end);
    }

    public static Domain combine(IDomain first, IDomain second) {
//        int cardinality = first.getCardinality() * second.getCardinality();
        int numberOfComponents = first.getNumberOfComponents() + second.getNumberOfComponents();
        SimpleDomain[] newDomain = new SimpleDomain[numberOfComponents];

        int i = 0;
        for(; i < first.getNumberOfComponents(); ++i) {
            newDomain[i] = (SimpleDomain) first.getComponent(i); //ovo bi se moglo srediti kad bi composit pattern bio zapravo iskoristen
        }

        for(int j = 0; j < second.getNumberOfComponents(); ++j){
            newDomain[i + j] = (SimpleDomain) second.getComponent(j);
        }

        CompositeDomain compositeDomain = new CompositeDomain(newDomain);
        return compositeDomain;
    }

}
