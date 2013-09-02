package processor;

import control.RunConfig;
import critter.Critter;
import evaluator.MachineAddTest;
import evaluator.MachineEvaluator;
import evaluator.SimpleEvaluator;
import instruction.OpCodes;
import junit.framework.Assert;
import log.Log;
import log.LogLevel;
import org.junit.Before;
import org.junit.Test;
import world.Logger;

import java.util.Properties;

public class ProcessorTest {
    @Before
    public void setUpProperties() {
        Properties props = new Properties();
        props.setProperty("POPULATION_SIZE", "1000");
        props.setProperty("MAX_ATTEMPTS", "999");
        props.setProperty("RESULTS_FILE", "./results.dat");
        props.setProperty("DNA_BASE_LENGTH", "24");
        props.setProperty("MAX_DNA_BIT", "51");
        props.setProperty("MUTATE_PERCENT", "5");
        props.setProperty("MACHINE_ID", "1");
        props.setProperty("NUMBER_THREADS", "1");

        RunConfig.INSTANCE.setUpProperties(props);
    }


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
            int[] newCode = {OpCodes.ADD.ordinal(), 2, 3, 0, OpCodes.ADD.ordinal(), 1, 2, 0, OpCodes.MOV.ordinal(), 0, 1, 0};
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
