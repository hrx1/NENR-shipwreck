package fer.nenr.sets.regular;

import fer.nenr.util.ArgumentUtils;

import java.util.Arrays;

public class DomainElement {
    private int[] values;

    public DomainElement(int[] values) {
        ArgumentUtils.requireNonNull(values);
        this.values = values;
    }

    public int getNumberOfComponents() {
        return values.length;
    }

    @Override
    public int hashCode() {
        return values.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomainElement that = (DomainElement) o;
        return Arrays.equals(values, that.values);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(values.length * 3 + 5);
        sb.append("(");
        for (int value : values) {
            sb.append(value);
            sb.append(",");
        }
        sb.setLength(sb.length() - 1);
        sb.append(")");
        return sb.toString();
    }

    public static DomainElement of(int ... values){
        return new DomainElement(values);
    }

    /**
     *
     *
     * @param index
//     * @throws IllegalArgumentException if index > numberOfComponents
     * @return
     */
    public int getComponentValue(int index) {
        return values[index];
    }

}
