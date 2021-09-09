package fer.nenr.fuzzycontrol.rule;

import fer.nenr.brodolom.IConclusionMachine;
import fer.nenr.sets.fuzzy.IFuzzySet;
import fer.nenr.util.ArgumentUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.function.DoubleBinaryOperator;

public class RuleBuilder {

    private DoubleBinaryOperator tNorm;
    private DoubleBinaryOperator sNorm;
    private DoubleBinaryOperator implicationOperator;

    private LinkedList<DoubleBinaryOperator> stNorms = new LinkedList<>();
    private LinkedList<BoatPremise> premises = new LinkedList<>();

    private IFuzzySet implies;

    private RuleBuilder() {}

    public static RuleBuilder of(
            IConclusionMachine conclusionMachine,
            BoatPremise fuzzySet) {

        ArgumentUtils.requireNonNull(conclusionMachine);

        RuleBuilder ruleBuilder = new RuleBuilder();
        ruleBuilder.sNorm = conclusionMachine.sNorm();
        ruleBuilder.tNorm = conclusionMachine.tNorm();
        ruleBuilder.implicationOperator = conclusionMachine.implicationOperator();

        ruleBuilder.premises.add(fuzzySet);
//        ruleBuilder.parameterChoosers.add(parameter);

        return ruleBuilder;
    }

    public RuleBuilder and(BoatPremise fuzzySet) {
        addRule(fuzzySet, tNorm);
        return this;
    }

    public RuleBuilder or(BoatPremise fuzzySet) {
        addRule(fuzzySet, sNorm);
        return this;
    }

    private void addRule(BoatPremise fuzzySet, DoubleBinaryOperator stNorm) {
        ArgumentUtils.requireNonNull(fuzzySet);

        stNorms.add(stNorm);
        premises.add(fuzzySet);
    }

    public RuleBuilder implies(IFuzzySet fuzzySet) {
        ArgumentUtils.requireNonNull(fuzzySet);
        this.implies = fuzzySet;
        return this;
    }

    public Rule build() {
        Rule result = new Rule(
                new ArrayList<>(premises),
                new ArrayList<>(stNorms),
                implies,
                implicationOperator
        );

        return result;
    }

}
