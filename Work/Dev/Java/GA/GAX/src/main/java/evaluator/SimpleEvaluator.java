package evaluator;

public class SimpleEvaluator {

    public int evaluateFitness(int[] code) {
        int sum = 0;
        for (int ii = 0; ii < code.length; ii++) {
            sum += code[ii];
        }
        return sum;
    }


}