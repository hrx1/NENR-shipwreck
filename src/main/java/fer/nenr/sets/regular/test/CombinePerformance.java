package fer.nenr.sets.regular.test;

import fer.nenr.sets.regular.Domain;
import fer.nenr.sets.regular.SimpleDomain;
import fer.nenr.util.Counter;

public class CombinePerformance {
    public static void main(String[] args) {

        long start = System.nanoTime();

        SimpleDomain simpleDomain = new SimpleDomain(0, 1000); //1000 elemenata
        SimpleDomain simpleDomain2 = new SimpleDomain(-500, 0); //500 elemenata

        Domain compositeDomain = Domain.combine(
                Domain.combine(simpleDomain, simpleDomain2),
                new SimpleDomain(0, 1000) //1000 elementa
        );


        Counter counter = new Counter();
        compositeDomain.forEach(counter::increment);
//        compositeDomain.forEach(o -> counter.increment());
        System.out.println(counter);

        long end = System.nanoTime();
        System.out.println("Potrebno: /ms");
        System.out.println((end - start)/1e6);
    }
}
