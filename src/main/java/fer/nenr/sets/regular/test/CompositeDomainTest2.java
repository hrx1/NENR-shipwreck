package fer.nenr.sets.regular.test;

import fer.nenr.sets.regular.CompositeDomain;
import fer.nenr.sets.regular.SimpleDomain;
import fer.nenr.util.Counter;

public class CompositeDomainTest2 {
    public static void main(String[] args) {
        SimpleDomain simpleDomain = new SimpleDomain(5, 10); //5 elemenata
        SimpleDomain simpleDomain2 = new SimpleDomain(-5, 6); //10 elemenata

        CompositeDomain compositeDomain = new CompositeDomain(new SimpleDomain[]{simpleDomain, simpleDomain2});

        compositeDomain.forEach(System.out::println);
        Counter counter = new Counter();
        compositeDomain.forEach(o -> counter.increment());
        System.out.println(counter);
    }
}
