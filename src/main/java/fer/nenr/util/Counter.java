package fer.nenr.util;

public class Counter {

    private int counter = 0;

    public void increment() {
        counter++;
    }

    public <T> void increment(T o) {
        counter++;
    }

    public void reset() {
        counter = 0;
    }

    public int getCounter() {
        return counter;
    }

    @Override
    public String toString() {
        return "Counter{" +
                "counter=" + counter +
                '}';
    }
}
