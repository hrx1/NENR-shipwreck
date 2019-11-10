package fer.nenr.sets.regular.test;

import fer.nenr.sets.regular.SimpleDomain;

public class SimpleDomainTest {

    public static void main(String[] args) {
        SimpleDomain simpleDomain = new SimpleDomain(5, 10);
        for(var element : simpleDomain) {
            System.out.println(element);
        }

        System.out.println("===");

        SimpleDomain simpleDomain2 = new SimpleDomain(-5, 6);
        for(var element : simpleDomain2) {
            System.out.println(element);
        }

    }
}
