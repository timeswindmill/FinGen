package processor;

import evaluator.SimpleEvaluator;
import junit.framework.Assert;
import log.Log;
import log.LogLevel;
import org.junit.Test;
import world.Logger;
import world.Population;

public class ProcessRunnerTest {


    @Test
    public void testCreateProcessRunner() {
        ProcessRunner pr = new ProcessRunner(1, new SimpleEvaluator());
        Assert.assertNotNull(pr);

    }

    @Test
    public void testPrintResults() {
        ProcessRunner pr = new ProcessRunner(1, new SimpleEvaluator());
        Assert.assertNotNull(pr);

    }


    @Test
    public void testRunNoStop() {
        {
            Population testPopulation = Population.INSTANCE;
            testPopulation.createPopulation();
            Log logger = Logger.INSTANCE.getLogger();
            logger.setLevel(LogLevel.ERROR);
            ProcessRunner pr = new ProcessRunner(1, new SimpleEvaluator());
            Assert.assertNotNull(pr);
            pr.run();
        }
    }

}
