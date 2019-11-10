package fer.nenr.brodolom;

import fer.nenr.fuzzycontrol.BoatFuzzyControlSystem;
import fer.nenr.fuzzycontrol.defuzzifier.CenterOfAreaDefuzzy;
import fer.nenr.fuzzycontrol.rule.AccelerationRules;

import java.util.Scanner;

public class Main {

    public static final String END_FLAG = "KRAJ";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        AccelerationRules accelerationRules = AccelerationRules.get();
        BoatFuzzyControlSystem accelerationSystem = new BoatFuzzyControlSystem(accelerationRules, new CenterOfAreaDefuzzy());

        while(true) {
            String inputLine = input.nextLine();
            if(inputLine.equals(END_FLAG)) {
                break;
            }
            Scanner lineParser = new Scanner(inputLine);
            //L, D, LK, DK, V i S.
            int L = lineParser.nextInt();
            int D = lineParser.nextInt();
            int LK = lineParser.nextInt();
            int DK = lineParser.nextInt();
            int V = lineParser.nextInt();
            int S = lineParser.nextInt();


            int nextA = accelerationSystem.nextMove(L, D, LK, DK, V, S);
            int nextK = 0;

            System.out.println(String.format("%d %d", nextA, nextK));
//            System.out.println(String.format("%d %d", 100, 100));
            System.out.flush();
        }

        input.close();

    }
}
