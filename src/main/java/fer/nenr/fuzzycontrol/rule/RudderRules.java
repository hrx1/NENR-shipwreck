package fer.nenr.fuzzycontrol.rule;

import fer.nenr.brodolom.IConclusionMachine;

import java.util.LinkedList;
import java.util.List;
import java.util.function.DoubleBinaryOperator;

public class RudderRules implements RuleDatabase {

    private List<Rule> rules = new LinkedList<>();

    public RudderRules(IConclusionMachine conclusionMachine) {
        createRules(conclusionMachine);
    }

    private void createRules(IConclusionMachine conclusionMachine) {

        DoubleBinaryOperator tNorm = conclusionMachine.tNorm();
        DoubleBinaryOperator sNorm = conclusionMachine.sNorm();

        RuleBuilder turnLeft = RuleBuilder.of(conclusionMachine, BoatPremise.NEAR_LK)
                .implies(BoatConclusion.TURN_RIGHT);

        rules.add(turnLeft.build());

        RuleBuilder turnRight = RuleBuilder.of(conclusionMachine, BoatPremise.NEAR_DK)
                .implies(BoatConclusion.TURN_LEFT);

        rules.add(turnRight.build());
    }

    @Override
    public List<Rule> getRules() {
        return rules;
    }
}
