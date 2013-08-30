package processor;

import evaluator.SimpleEvaluator;
import junit.framework.Assert;
import log.FileLogger;
import log.Log;
import org.junit.Test;
import world.Population;

public class ProcessRunnerTest {

    private Log logger = new FileLogger();

    @Test
    public void testCreateProcessRunner() {
        ProcessRunner pr = new ProcessRunner(1, new SimpleEvaluator());
        Assert.assertNotNull(pr);

    }

    @Test
    public void testPrintResults() {
        ProcessRunner pr = new ProcessRunner(1, new SimpleEvaluator());
        Assert.assertNotNull(pr);
        logger.logInfo(pr.toString());

    }


    @Test
    public void testRunNoStop() {
        Population testPopulation = Population.INSTANCE;
        testPopulation.createPopulation();
        ProcessRunner pr = new ProcessRunner(1, new SimpleEvaluator());
        Assert.assertNotNull(pr);
        pr.run();


    }

}
