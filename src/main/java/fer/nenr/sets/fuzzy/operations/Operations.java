package fer.nenr.sets.fuzzy.operations;

import fer.nenr.sets.fuzzy.CalculatedFuzzySet;
import fer.nenr.sets.fuzzy.IFuzzySet;
import fer.nenr.sets.regular.*;
import fer.nenr.util.ArgumentUtils;

import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntToDoubleFunction;

/*
 *  Kako trenutno postoje dvije vrste fuzzyseta: Calculated i Mutable, pitanje je trebamo li od svakog stvoriti
 *  Calculated, ili od svakog stvoriti mutable.
 *
 * Ako od svakog stvaramo Calculated, mozemo napraviti wrapper na bilo koju vrstu seta i samo nad vrijednosti
 * izvrsiti promjenu
 * Ako od svakog stvaramo Mutable moramo proci po cijeloj domeni i izvrsiti promjenu
 *
 * Moguce je i od Calculated napraviti Calculated, a od mutable opet mutable. To bi bilo zgodno napraviti nekim visitorom.
 * TODO trenutno je Calculated od originala (bilo Calc ili Mut) jer je bilo lakse napraviti. Ispravit cu ako skuzim bottleneck
 *
 */
public class Operations {

    public static IFuzzySet unaryOperation(IFuzzySet fuzzySet, DoubleUnaryOperator function) {
        return new ValueTransformationFuzzySet(fuzzySet, function);
    }

    public static DoubleUnaryOperator zadehNot() {
        return (value) -> 1-value;
    }

    //TODO Ovo je zapravo relacija.
//    public static IFuzzySet binaryOperation(IFuzzySet set1, IFuzzySet set2, DoubleBinaryOperator function) {
//        //composeaj domene
//        //zapakiraj
//        IDomain domain = Domain.combine(set1.getDomain(), set2.getDomain());
//        IFuzzySet fuzzySet = new CalculatedFuzzySet(domain, new MembershipFunctionCombination(set1, set2, domain, function));
//
//        return fuzzySet;
//    }

    public static IFuzzySet binaryOperation(IFuzzySet set1, IFuzzySet set2, DoubleBinaryOperator function) {
        //TODO provjera jednakosti domena je potrebna?
        return new ValueCombinationFuzzySet(set1, set2, function);

    }
    public static DoubleBinaryOperator zadehOr() {
        return Math::max;
    }

    public static DoubleBinaryOperator zadehAnd() {
        return Math::min;
    }

    public static DoubleBinaryOperator hamacherTNorm(double v) {
        return (a, b) -> a*b/(v+(1-v)*(a+b-a*b));
    }

    public static DoubleBinaryOperator hamacherSNorm(double v) {
        return (a, b) -> (a+b-(2-v)*a*b)/(1-(1-v)*a*b);
    }

    private static class MembershipFunctionCombination implements IntToDoubleFunction {

        private final IDomain domain;
        private final IFuzzySet set1;
        private final IFuzzySet set2;
        private DoubleBinaryOperator function;

        private int[] firstValues;
        private int[] secondValues;
        private int n,m;

        public MembershipFunctionCombination(IFuzzySet set1, IFuzzySet set2, IDomain domain, DoubleBinaryOperator function) {
            this.domain = domain;
            this.function = function;
            this.set1 = set1;
            this.set2 = set2;

            n = set1.getDomain().getNumberOfComponents();
            m = set2.getDomain().getNumberOfComponents();

            firstValues = new int[n];
            secondValues = new int[m];

        }

        @Override
        public double applyAsDouble(int index) {
            DomainElement fuzzySetElement = domain.elementForIndex(index);

            for(int i = 0; i < n; ++i) {
                firstValues[i] = fuzzySetElement.getComponentValue(i);
            }

            for(int i = 0; i < m; ++i) {
                secondValues[i] = fuzzySetElement.getComponentValue(i + n);
            }

            DomainElement set1Element = DomainElement.of(firstValues);
            DomainElement set2Element = DomainElement.of(secondValues);

            double set1Value = set1.getValueFor(set1Element);
            double set2Value = set2.getValueFor(set2Element);

            return function.applyAsDouble(set1Value, set2Value);
        }
    }

    private static class ValueCombinationFuzzySet implements IFuzzySet {
        private IFuzzySet fuzzySet1;
        private IFuzzySet fuzzySet2;
        private DoubleBinaryOperator function;

        public ValueCombinationFuzzySet(IFuzzySet fuzzySet1, IFuzzySet fuzzySet2, DoubleBinaryOperator function) {
            ArgumentUtils.requireNonNull(fuzzySet1, fuzzySet2, function);
            this.fuzzySet1 = fuzzySet1;
            this.fuzzySet2 = fuzzySet2;
            this.function = function;
        }

        @Override
        public IDomain getDomain() {
            return fuzzySet1.getDomain();
        }

        @Override
        public double getValueFor(DomainElement domainElement) {
            double v1 = fuzzySet1.getValueFor(domainElement);
            double v2 = fuzzySet2.getValueFor(domainElement);
            return function.applyAsDouble(v1, v2);
        }
    }


    private static class ValueTransformationFuzzySet implements IFuzzySet {

            private IFuzzySet fuzzySet;
            private DoubleUnaryOperator function;

            public ValueTransformationFuzzySet(IFuzzySet fuzzySet, DoubleUnaryOperator function) {
                ArgumentUtils.requireNonNull(fuzzySet, function);
                this.fuzzySet = fuzzySet;
                this.function = function;
            }

            @Override
            public IDomain getDomain() {
                return fuzzySet.getDomain();
            }

            @Override
            public double getValueFor(DomainElement domainElement) {
                double originalValue = fuzzySet.getValueFor(domainElement);
                return function.applyAsDouble(originalValue);
            }
        }

}
