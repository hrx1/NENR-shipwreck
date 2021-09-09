package fer.nenr.fuzzycontrol.rule;

import fer.nenr.sets.regular.Domain;
import fer.nenr.sets.regular.IDomain;

public class BoatDomain {
    public static final IDomain premiseDomain = Domain.intRange(0, 1301);
    public static final IDomain angleDomain  = Domain.intRange(-90, 91);
    public static final IDomain acceleration = Domain.intRange(-50, 50); //zapravo je od -35 do 35
    public static final IDomain speed = Domain.intRange(0, 100);
}
