package world;

import log.Log;
import log.LogLevel;
import org.junit.Test;

public class MachineWorldRunnerTest {
    @Test
    public void testSetUpWorld() throws Exception {
        MachineWorldRunner machineWorldRunner = MachineWorldRunner.INSTANCE;
        machineWorldRunner.setUpWorld();

    }

    @Test
    public void testRunWorld() throws Exception {
        Log log = Logger.INSTANCE.getLogger();
        log.setLevel(LogLevel.INFO);
        MachineWorldRunner machineWorldRunner = MachineWorldRunner.INSTANCE;
        machineWorldRunner.setUpWorld();
        machineWorldRunner.runWorld();


    }
}
