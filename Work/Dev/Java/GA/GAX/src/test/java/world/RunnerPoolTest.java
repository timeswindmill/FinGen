package world;

import evaluator.SimpleEvaluator;
import junit.framework.Assert;
import org.junit.Test;

public class RunnerPoolTest {
    @Test
    public void testSetUpPool() throws Exception {
        RunnerPool.INSTANCE.setUpPool(new SimpleEvaluator());
        for (int ii = 0; ii < RunnerPool.INSTANCE.getPoolSize(); ii++) {
            Assert.assertNotNull(RunnerPool.INSTANCE.getProcessRunner(ii));
        }
    }

    @Test
    public void testGetPoolSize() throws Exception {

    }

    @Test
    public void testGetProcessRunner() throws Exception {
        RunnerPool.INSTANCE.setUpPool(new SimpleEvaluator());
        Assert.assertEquals(RunnerPool.INSTANCE.getPoolSize(), RunnerPool.NUM_PROCESSORS);
        Assert.assertNotNull(RunnerPool.INSTANCE.getProcessRunner(0));
        Assert.assertNotNull(RunnerPool.INSTANCE.getProcessRunner(RunnerPool.NUM_PROCESSORS - 1));

    }
}
