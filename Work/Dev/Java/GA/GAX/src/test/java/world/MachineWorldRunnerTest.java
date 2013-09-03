package world;

import control.RunConfig;
import log.Log;
import log.LogLevel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class MachineWorldRunnerTest {
    @Before
    public void setUpProperties() {
        Properties props = new Properties();
        props.setProperty("POPULATION_SIZE", "10");
        props.setProperty("MAX_ATTEMPTS", "99");
        props.setProperty("RESULTS_FILE", "./testResults.dat");
        props.setProperty("DNA_BASE_LENGTH", "24");
        props.setProperty("MAX_DNA_BIT", "51");
        props.setProperty("MUTATE_PERCENT", "5");
        props.setProperty("MACHINE_ID", "1");
        props.setProperty("NUMBER_THREADS", "1");

        RunConfig.INSTANCE.setUpProperties(props);

    }

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

        // check for saved file
        Path saveFilePath = new File(RunConfig.INSTANCE.getResultsFileName()).toPath();
        Assert.assertTrue("Save File Doesn't Exist", Files.exists(saveFilePath));
        // now delete it
        Files.delete(saveFilePath);

    }
}
