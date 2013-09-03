package processor;

import control.RunConfig;
import critter.Critter;
import evaluator.MachineEvaluator;
import evaluator.SimpleEvaluator;
import instruction.OpCodes;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

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
            int[] newCode = {
                    OpCodes.LOAD.ordinal(), 0, 70, 0,
                    OpCodes.LOAD.ordinal(), 1, 37, 0,
                    OpCodes.LOAD.ordinal(), 2, 75, 0,
                    OpCodes.LOAD.ordinal(), 3, 74, 0,
                    OpCodes.HALT.ordinal(), 0, 0, 0


            };
            Processor proc = new Processor(new MachineEvaluator());
            Assert.assertNotNull(proc);

            Critter critter = new Critter(1, newCode);
            Assert.assertEquals(-1, proc.getBestFitness());
            proc.evaluateCritter(critter);
            Assert.assertEquals(0, proc.getBestFitness());
            int[] bestCode = proc.getBestCode();
            Assert.assertNotNull(bestCode);

            for (int ii = 0; ii < bestCode.length; ii++) {
                Assert.assertEquals(bestCode[ii], newCode[ii]);
            }
        }

//        {
//            int[] newCode = {
//                    OpCodes.LOAD.ordinal(), 0, 0, 0,
//                    OpCodes.LOAD.ordinal(), 1, 1, 0,
//                    OpCodes.LOAD.ordinal(), 2, 2, 0,
//                    OpCodes.LOAD.ordinal(), 3, 3, 0,
//                    OpCodes.HALT.ordinal(), 0, 0, 0
//            };
//            Processor proc = new Processor(new MachineEvaluator());
//            Assert.assertNotNull(proc);
//
//            Critter critter = new Critter(1, newCode);
//            Assert.assertEquals(-1, proc.getBestFitness());
//            proc.evaluateCritter(critter);
//            Assert.assertEquals(MachineSortTest.NUM_TESTS*4, proc.getBestFitness());
//            int[] bestCode = proc.getBestCode();
//            Assert.assertNotNull(bestCode);
//            Log logger = Logger.INSTANCE.getLogger();
//            logger.setLevel(LogLevel.INFO);
//            logger.logInfo(proc.resultsToString());
//        }


    }


}
