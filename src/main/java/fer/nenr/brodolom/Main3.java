package fer.nenr.brodolom;

import fer.nenr.fuzzycontrol.BoatFuzzyControlSystem;
import fer.nenr.fuzzycontrol.defuzzifier.CenterOfAreaDefuzzy;
import fer.nenr.fuzzycontrol.defuzzifier.Defuzzifier;
import fer.nenr.fuzzycontrol.rule.RudderRules;
import fer.nenr.fuzzycontrol.rule.Rule;
import fer.nenr.sets.fuzzy.IFuzzySet;

import java.util.Scanner;


public class Main3 {
    public static void main(String[] args) {
        IConclusionMachine conclusionMachine = new MinimumInferenceEngine();
        RudderRules rudderRules = new RudderRules(conclusionMachine);

        Defuzzifier debugDefuzz = new DebugDefuzzy(new CenterOfAreaDefuzzy());

        BoatFuzzyControlSystem rudderSystem = new BoatFuzzyControlSystem(rudderRules, debugDefuzz);

        Scanner sc = new Scanner(System.in);

        System.out.println("L D LK DK V S");
        Scanner lineParser = new Scanner(System.in);
        //L, D, LK, DK, V i S.
        int L = lineParser.nextInt();
        int D = lineParser.nextInt();
        int LK = lineParser.nextInt();
        int DK = lineParser.nextInt();
        int V = lineParser.nextInt();
        int S = lineParser.nextInt();

        int R = rudderSystem.nextMove(L, D, LK, DK, V, S);
        System.out.println(R);

        sc.close();
    }
}
