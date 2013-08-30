package processor;

import critter.Critter;
import evaluator.SimpleEvaluator;

public class Processor {

    private int[] bestCode;
    private int bestFitness;

    private final SimpleEvaluator simpleEvaluator;

    public Processor(SimpleEvaluator simpleEvaluator) {
        this.simpleEvaluator = simpleEvaluator;
    }


    public void evaluateCritter(Critter critter) {
        int[] critterCode = critter.getCode();
        if (compareCodes(critterCode) == false) {

            int fitness = getCritterFitness(critterCode);
            if (fitness > bestFitness) {
                // try to update critter
                critter.updateCode(critterCode, critterCode);
                bestFitness = fitness;
                bestCode = critterCode;
            }


        }

    }


    private int getCritterFitness(int[] code) {
        int fitness = simpleEvaluator.evaluateFitness(code);
        return fitness;
    }


    private boolean compareCodes(int[] newCode) {
        if (bestCode == null) {
            return false;
        }
        for (int ii = 0; ii < bestCode.length; ii++) {
            if (bestCode[ii] != newCode[ii]) {
                return false;
            }
        }
        return true;
    }

    public int[] getBestCode() {
        return bestCode;
    }

    public int getBestFitness() {
        return bestFitness;
    }

    public String resultsToString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Best Fitness : " + bestFitness + "\n");
        sb.append("Best Code : ");
        sb.append("{");
        if (bestCode != null) {
            for (int bit : bestCode) {
                sb.append(bit);
                sb.append(",");
            }
            sb.setLength(sb.length() - 1);
        }
        sb.append("}" + "\n");

        return sb.toString();
    }

}
