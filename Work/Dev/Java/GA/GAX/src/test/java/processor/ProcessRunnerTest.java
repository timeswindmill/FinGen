package processor;

import junit.framework.Assert;
import org.junit.Test;

public class ProcessRunnerTest {

    @Test
    public void testCreateProcessRunner() {
        ProcessRunner pr = new ProcessRunner(1);
        Assert.assertNotNull(pr);

    }


}
