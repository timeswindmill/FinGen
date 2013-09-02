package processor;

import critter.Critter;
import evaluator.Evaluator;
import evaluator.MachineEvaluator;
import instruction.Program;
import log.Log;
import world.Logger;

public class Processor {

    private int[] bestCode;
    private int bestFitness = -1;

    private final Evaluator evaluator;
    private Log logger = Logger.INSTANCE.getLogger();
    private int itemsSinceDifference = 0;


    public Processor(Evaluator evaluator) {
        this.evaluator = evaluator;
    }


    public void evaluateCritter(Critter critter) {
//        logger.logDebug("Evaluating Critter " + critter.getID());
//        logger.logDebug("Best Fitness is  " + bestFitness);

        int[] critterCode = critter.getCode();
        if (compareCodes(critterCode) == false) {
            int fitness = getCritterFitness(critterCode);
            if (fitness < bestFitness) {
                // try to update critter
                critter.updateCode(critterCode, bestCode);
            } else if (fitness > bestFitness) {
                bestFitness = fitness;
                bestCode = critterCode;
            }

        }
//        else{
//            itemsSinceDifference++;
//            if(itemsSinceDifference> RunConfig.INSTANCE.getPopulationSize()){
//                int[] newCode = Critter.createRandomCode(RunConfig.INSTANCE.getDnaBaseLength());
//                critter.updateCode(critterCode, bestCode);
//                itemsSinceDifference=0;
//            }
//
//        }
    }


    private int getCritterFitness(int[] code) {
        int fitness = evaluator.evaluateFitness(code);
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
        if (evaluator instanceof MachineEvaluator) {
            Program bestProg = Program.createProgram(bestCode);
            sb.append(bestProg);

        } else {
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

        }


        return sb.toString();
    }

}
