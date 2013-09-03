package processor;

import control.RunConfig;
import evaluator.SimpleEvaluator;
import junit.framework.Assert;
import log.Log;
import log.LogLevel;
import org.junit.Before;
import org.junit.Test;
import world.Logger;
import world.Population;

import java.util.Properties;

public class ProcessRunnerTest {

    private Population population;


    @Before
    public void setUpProperties() {
        Properties props = new Properties();
        props.setProperty("POPULATION_SIZE", "1000");
        props.setProperty("MAX_ATTEMPTS", "99");
        props.setProperty("RESULTS_FILE", "./results.dat");
        props.setProperty("DNA_BASE_LENGTH", "24");
        props.setProperty("MAX_DNA_BIT", "51");
        props.setProperty("MUTATE_PERCENT", "5");
        props.setProperty("MACHINE_ID", "1");
        props.setProperty("NUMBER_THREADS", "1");

        RunConfig.INSTANCE.setUpProperties(props);
        population = new Population(RunConfig.INSTANCE.getPopulationSize());
        population.createPopulation();

    }


    @Test
    public void testCreateProcessRunner() {
        ProcessRunner pr = new ProcessRunner(1, new SimpleEvaluator(), population);
        Assert.assertNotNull(pr);

    }

    @Test
    public void testPrintResults() {
        ProcessRunner pr = new ProcessRunner(1, new SimpleEvaluator(), population);
        Assert.assertNotNull(pr);

    }


    @Test
    public void testRunNoStop() {
        Log logger = Logger.INSTANCE.getLogger();
        logger.setLevel(LogLevel.ERROR);
        ProcessRunner pr = new ProcessRunner(1, new SimpleEvaluator(), population);
        Assert.assertNotNull(pr);
        pr.run();
    }

}
