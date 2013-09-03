package evaluator;

public class SimpleEvaluator implements Evaluator {

    public int evaluateFitness(int[] code) {
        int sum = 0;
        for (int aCode : code) {
            sum += aCode;
        }
        return sum;
    }


}
