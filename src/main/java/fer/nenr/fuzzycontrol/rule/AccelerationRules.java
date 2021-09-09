package fer.nenr.fuzzycontrol.rule;

import fer.nenr.brodolom.IConclusionMachine;

import java.util.LinkedList;
import java.util.List;
import java.util.function.DoubleBinaryOperator;

public class AccelerationRules implements RuleDatabase {

    private List<Rule> rules = new LinkedList<>();

    public AccelerationRules(IConclusionMachine conclusionMachine) {
        createRules(conclusionMachine);
    }

    private void createRules(IConclusionMachine conclusionMachine) {

//        RuleBuilder speedUpRule = RuleBuilder.of(tNorm, sNorm,
//                BoatPremise.FAR_LK)
//                .or(BoatPremise.FAR_DK)
//                .and(BoatPremise.GOING_SLOW)
//                .implies(BoatConclusion.SPEED_UP);
//
//        rules.add(speedUpRule.build());

        RuleBuilder slowWhenFast = RuleBuilder.of(conclusionMachine, BoatPremise.GOING_FAST)
                .implies(BoatConclusion.SLOW_DOWN);

        rules.add(slowWhenFast.build());

        RuleBuilder accWhenSlow = RuleBuilder.of(conclusionMachine, BoatPremise.GOING_SLOW)
                .implies(BoatConclusion.SPEED_UP);
        rules.add(accWhenSlow.build());

        RuleBuilder speedUpWhenEmpty = RuleBuilder.of(conclusionMachine,
                BoatPremise.FAR_DK)
                .and(BoatPremise.FAR_LK)
                .and(BoatPremise.FAR_L)
                .and(BoatPremise.FAR_D)
                .implies(BoatConclusion.SPEED_UP);

        rules.add(speedUpWhenEmpty.build());


//        RuleBuilder slowDownRule = RuleBuilder
//                .of(tNorm, sNorm,
//                        BoatPremise.NEAR_LK)
//                .or(BoatPremise.NEAR_DK)
//                .and(BoatPremise.GOING_FAST)
//                .implies(BoatConclusion.SLOW_DOWN);

//        rules.add(slowDownRule.build());

//        RuleBuilder sharpTurnLeft = RuleBuilder.of(tNorm, sNorm, BoatPremise.NEAR_LK).implies(BoatConclusion.SPEED_UP);
//        rules.add(sharpTurnLeft.build());
//
//        RuleBuilder sharpTurnRight = RuleBuilder.of(tNorm, sNorm, BoatPremise.NEAR_DK).implies(BoatConclusion.SPEED_UP);
//        rules.add(sharpTurnRight.build());

        RuleBuilder sharpTurn = RuleBuilder.of(conclusionMachine, BoatPremise.NEAR_LK)
                .or(BoatPremise.NEAR_DK)
                .implies(BoatConclusion.SPEED_UP);
        rules.add(sharpTurn.build());

    }

    @Override
    public List<Rule> getRules() {
        return rules;
    }
}
