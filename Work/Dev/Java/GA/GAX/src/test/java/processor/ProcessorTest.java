package processor;

import critter.Critter;
import evaluator.SimpleEvaluator;
import junit.framework.Assert;
import org.junit.Test;

public class ProcessorTest {


    @Test
    public void createProcessor() {
        Processor proc = new Processor(new SimpleEvaluator());
        Assert.assertNotNull(proc);
    }

    @Test
    public void evaluateCritter() {

        {
            int[] newCode = {1, 2, 3, 4, 5};
            Critter critter = new Critter(1, newCode);
            Processor proc = new Processor(new SimpleEvaluator());
            Assert.assertNotNull(proc);
            Assert.assertEquals(0, proc.getBestFitness());
            Assert.assertEquals(null, proc.getBestCode());
            proc.evaluateCritter(critter);
            Assert.assertEquals(15, proc.getBestFitness());
            int[] bestCode = proc.getBestCode();
            Assert.assertNotNull(bestCode);

            for (int ii = 0; ii < bestCode.length; ii++) {
                Assert.assertEquals(bestCode[ii], newCode[ii]);
            }


        }


    }


}
