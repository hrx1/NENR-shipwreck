package fer.nenr.fuzzycontrol;

import fer.nenr.fuzzycontrol.defuzzifier.Defuzzifier;
import fer.nenr.fuzzycontrol.rule.Rule;
import fer.nenr.fuzzycontrol.rule.RuleDatabase;
import fer.nenr.sets.fuzzy.IFuzzySet;
import fer.nenr.sets.fuzzy.operations.Operations;

public class BoatFuzzyControlSystem {

    private final RuleDatabase ruleDatabase;

    private final Defuzzifier defuzzifier;

    public BoatFuzzyControlSystem(RuleDatabase ruleDatabase, Defuzzifier defuzzifier) {
        this.ruleDatabase = ruleDatabase;
        this.defuzzifier = defuzzifier;
    }

//L, D, LK, DK, V i S
    public final int nextMove(int L, int D, int LK, int DK, int V, int S) {

        IFuzzySet resultSet = null;
        for(Rule rule : ruleDatabase.getRules()) {
            IFuzzySet fuzzySet = rule.apply(L, D, LK, DK, V, S);
            if(resultSet == null) {
                resultSet = fuzzySet;
            } else {
                resultSet = Operations.binaryOperation(resultSet, fuzzySet, Operations.zadehOr());
            }
        }

        return defuzzifier.defuzzify(resultSet);

    }

}
