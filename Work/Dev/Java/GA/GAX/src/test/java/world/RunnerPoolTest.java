package world;

import control.RunConfig;
import evaluator.SimpleEvaluator;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

public class RunnerPoolTest {
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
    public void testSetUpPool() throws Exception {
        RunnerPool.INSTANCE.setUpPool(new SimpleEvaluator(), RunConfig.INSTANCE.getNumThreads());
        for (int ii = 0; ii < RunnerPool.INSTANCE.getPoolSize(); ii++) {
            Assert.assertNotNull(RunnerPool.INSTANCE.getProcessRunner(ii));
        }
    }

    @Test
    public void testGetPoolSize() throws Exception {

    }

    @Test
    public void testGetProcessRunner() throws Exception {

        RunnerPool.INSTANCE.setUpPool(new SimpleEvaluator(), RunConfig.INSTANCE.getNumThreads());
        System.out.println(RunConfig.INSTANCE.getNumThreads());
        Assert.assertEquals(RunnerPool.INSTANCE.getPoolSize(), RunConfig.INSTANCE.getNumThreads());
        Assert.assertNotNull(RunnerPool.INSTANCE.getProcessRunner(0));
        Assert.assertNotNull(RunnerPool.INSTANCE.getProcessRunner(RunConfig.INSTANCE.getNumThreads() - 1));

    }
}
