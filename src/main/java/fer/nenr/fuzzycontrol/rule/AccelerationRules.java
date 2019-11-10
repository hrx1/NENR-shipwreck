package fer.nenr.fuzzycontrol.rule;

import java.util.LinkedList;
import java.util.List;

public class AccelerationRules implements RuleDatabase {
    private static AccelerationRules ourInstance = new AccelerationRules();

    public static AccelerationRules get() {
        return ourInstance;
    }

    private List<Rule> rules = new LinkedList<>();

    private AccelerationRules() {
        createRules();
    }

    private void createRules() {

        RuleBuilder speedUpRule = RuleBuilder.of(Math::min, Math::max,
                BoatPremise.FAR_LK)
                .or(BoatPremise.FAR_DK)
                .and(BoatPremise.GOING_SLOW)
                .implies(BoatConclusion.SPEED_UP);

        rules.add(speedUpRule.build());

        RuleBuilder slowDownRule = RuleBuilder
                .of(Math::min, Math::max,
                        BoatPremise.NEAR_LK)
                .or(BoatPremise.NEAR_DK)
                .and(BoatPremise.GOING_FAST)
                .implies(BoatConclusion.SLOW_DOWN);

        rules.add(slowDownRule.build());
    }

    @Override
    public List<Rule> getRules() {
        return rules;
    }
}
