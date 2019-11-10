package fer.nenr.fuzzycontrol.rule;

import fer.nenr.sets.fuzzy.CalculatedFuzzySet;
import fer.nenr.sets.fuzzy.IFuzzySet;
import fer.nenr.sets.fuzzy.MutableFuzzySet;
import fer.nenr.sets.regular.DomainElement;

import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleBinaryOperator;

public class Rule {

    private ArrayList<DoubleBinaryOperator> stNorms;

    private IFuzzySet implies;

    private ArrayList<BoatPremise> premises;

    Rule(ArrayList<BoatPremise> premises, ArrayList<DoubleBinaryOperator> stNorms, IFuzzySet implies) {
        this.premises = premises;
        this.stNorms = stNorms;
        this.implies = implies;
    }

    public IFuzzySet apply(int L, int D, int LK, int DK, int V, int S) {
        double relationValue = premises.get(0).getValue(L, D, LK, DK, V, S);

        for (int i = 1; i < premises.size(); i++) {
            double currentValue = premises.get(0).getValue(L, D, LK, DK, V, S);
            DoubleBinaryOperator stNorm = stNorms.get(i-1);
            relationValue = stNorm.applyAsDouble(relationValue, currentValue);
        }

        MutableFuzzySet resultSet = new MutableFuzzySet(implies.getDomain());
        for(DomainElement yDomainElement : implies.getDomain()) {
             double yValue = implies.getValueFor(yDomainElement);
             yValue = Math.min(relationValue, yValue);
             resultSet.set(yDomainElement, yValue);
        }

        return resultSet;
    }
}
