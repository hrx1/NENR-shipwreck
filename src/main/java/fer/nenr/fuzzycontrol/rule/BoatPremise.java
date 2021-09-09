package fer.nenr.fuzzycontrol.rule;

import fer.nenr.sets.fuzzy.CalculatedFuzzySet;
import fer.nenr.sets.fuzzy.IFuzzySet;
import fer.nenr.sets.regular.DomainElement;
import fer.nenr.sets.regular.IDomain;

import java.util.function.IntToDoubleFunction;

import static fer.nenr.sets.fuzzy.StandardFuzzySets.*;

public enum BoatPremise implements IFuzzySet {
    NEAR_LK(BoatDomain.premiseDomain, lFunction(40, 50), ParameterChooser.LK),
    NEAR_DK(BoatDomain.premiseDomain, lFunction(40, 50), ParameterChooser.DK),
    GOING_FAST(BoatDomain.speed, gammaFunction(50, 70),ParameterChooser.V),

    FAR_LK(BoatDomain.premiseDomain, gammaFunction(60, 70), ParameterChooser.LK),
    FAR_DK(BoatDomain.premiseDomain, gammaFunction(60, 70), ParameterChooser.DK),
    GOING_SLOW(BoatDomain.speed, lFunction(20, 60), ParameterChooser.V),

    FAR_L(BoatDomain.premiseDomain, gammaFunction(50, 70), ParameterChooser.L),
    FAR_D(BoatDomain.premiseDomain, gammaFunction(50, 70), ParameterChooser.D);

    private IFuzzySet fuzzySet;
    private ParameterChooser parameterChooser;

    BoatPremise(IFuzzySet fuzzySet, ParameterChooser parameterChooser) {
        this.fuzzySet = fuzzySet;
        this.parameterChooser = parameterChooser;
    }

    BoatPremise(IDomain domain, IntToDoubleFunction function, ParameterChooser pc) {
        this(new CalculatedFuzzySet(domain, function), pc);
    }

    @Override
    public IDomain getDomain() {
        return fuzzySet.getDomain();
    }

    @Override
    public double getValueFor(DomainElement domainElement) {
        return fuzzySet.getValueFor(domainElement);
    }

    public double getValue(int L, int D, int LK, int DK, int V, int S) {
        int parameter = parameterChooser.getParameter(L, D, LK, DK, V, S);
        return fuzzySet.getValueFor(DomainElement.of(parameter));
    }

}
