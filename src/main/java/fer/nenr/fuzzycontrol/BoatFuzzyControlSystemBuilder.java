package fer.nenr.fuzzycontrol;

import fer.nenr.fuzzycontrol.defuzzifier.Defuzzifier;

public class BoatFuzzyControlSystemBuilder {
    private BoatFuzzyControlSystemBuilder() {}

    public BoatFuzzyControlSystemBuilder get() {
        return new BoatFuzzyControlSystemBuilder();
    }

    public BoatFuzzyControlSystemBuilder with(Defuzzifier defuzzifier) {

        return this;
    }

    public BoatFuzzyControlSystem build() {
//        return new BoatFuzzyControlSystem();
        return null;
    }
}
