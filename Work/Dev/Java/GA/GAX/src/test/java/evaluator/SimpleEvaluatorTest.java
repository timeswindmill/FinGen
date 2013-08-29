package evaluator;

import junit.framework.Assert;
import org.junit.Test;

public class SimpleEvaluatorTest {
    @Test
    public void testEvaluateFitness() throws Exception {
        int[] testCode = {1, 2, 3, 4, 5};
        SimpleEvaluator eval = new SimpleEvaluator();
        int result = eval.evaluateFitness(testCode);
        Assert.assertEquals(15, result);
    }
}
