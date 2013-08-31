package processor;

import critter.Critter;
import evaluator.MachineAddTest;
import evaluator.MachineEvaluator;
import evaluator.SimpleEvaluator;
import instruction.OpCodes;
import junit.framework.Assert;
import log.Log;
import log.LogLevel;
import org.junit.Test;
import world.Logger;

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
            Assert.assertEquals(-1, proc.getBestFitness());
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


    @Test
    public void createMachineProcessor() {
        Processor proc = new Processor(new MachineEvaluator());
        Assert.assertNotNull(proc);
    }


    @Test
    public void testMachineProcessor() {
        {
            int[] newCode = {OpCodes.ADD.ordinal(), 2, 3, 0, OpCodes.HALT.ordinal(), 0, 0, 0};
            Processor proc = new Processor(new MachineEvaluator());
            Assert.assertNotNull(proc);

            Critter critter = new Critter(1, newCode);
            Assert.assertEquals(-1, proc.getBestFitness());
            Assert.assertEquals(null, proc.getBestCode());
            proc.evaluateCritter(critter);
            Assert.assertEquals(0, proc.getBestFitness());
            int[] bestCode = proc.getBestCode();
            Assert.assertNotNull(bestCode);

            for (int ii = 0; ii < bestCode.length; ii++) {
                Assert.assertEquals(bestCode[ii], newCode[ii]);
            }
        }

        {
            int[] newCode = {OpCodes.ADD.ordinal(), 1, 2, 0, OpCodes.MOV.ordinal(), 0, 1, 0, OpCodes.JMP.ordinal(), 1, 2, 7};
            Processor proc = new Processor(new MachineEvaluator());
            Assert.assertNotNull(proc);

            Critter critter = new Critter(1, newCode);
            Assert.assertEquals(-1, proc.getBestFitness());
            Assert.assertEquals(null, proc.getBestCode());
            proc.evaluateCritter(critter);
            Assert.assertEquals(MachineAddTest.NUM_TESTS, proc.getBestFitness());
            int[] bestCode = proc.getBestCode();
            Assert.assertNotNull(bestCode);
            Log logger = Logger.INSTANCE.getLogger();
            logger.setLevel(LogLevel.INFO);
            logger.logInfo(proc.resultsToString());
        }


    }


}
