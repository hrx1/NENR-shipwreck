package fer.nenr.brodolom;

import fer.nenr.fuzzycontrol.defuzzifier.CenterOfAreaDefuzzy;
import fer.nenr.fuzzycontrol.defuzzifier.Defuzzifier;
import fer.nenr.fuzzycontrol.rule.RudderRules;
import fer.nenr.fuzzycontrol.rule.Rule;
import fer.nenr.sets.fuzzy.IFuzzySet;

import java.util.Scanner;

/**
 * Npr.,
 * pravilo: 0
 *
 * Parametri:
 * 10
 * 1000
 * 20
 * 1000
 * 10
 * 1
 *
 * Kaze da treba skrenuti ostro u desno
 *
 * Pravilo 0 bi svagdje dalo 0
 *
 */

public class Main2 {
    public static void main(String[] args) {
        IConclusionMachine conclusionMachine = new MinimumInferenceEngine();
        RudderRules rudderRules = new RudderRules(conclusionMachine);

        System.out.println("Izaberi pravilo od 0 do " + (rudderRules.getRules().size() - 1));
        Scanner scIn = new Scanner(System.in);
        int odabir = scIn.nextInt();
        Rule rule = rudderRules.getRules().get(odabir);

        System.out.println("L D LK DK V S");
        Scanner lineParser = new Scanner(System.in);
        //L, D, LK, DK, V i S.
        int L = lineParser.nextInt();
        int D = lineParser.nextInt();
        int LK = lineParser.nextInt();
        int DK = lineParser.nextInt();
        int V = lineParser.nextInt();
        int S = lineParser.nextInt();

        IFuzzySet fuzzySet = rule.apply(L, D, LK, DK, V, S);
        fuzzySet.printElementsWithValues();

        Defuzzifier defuzzifier = new CenterOfAreaDefuzzy();
        System.out.println(defuzzifier.defuzzify(fuzzySet));

        scIn.close();
    }
}
