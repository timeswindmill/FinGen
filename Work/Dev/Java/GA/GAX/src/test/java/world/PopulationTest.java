package world;

import control.RunConfig;
import control.results.ResultsFile;
import control.results.ResultsLine;
import critter.Critter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.Properties;

public class PopulationTest {

    @Before
    public void setUpProperties() {
        Properties props = new Properties();
        props.setProperty("POPULATION_SIZE", "10");
        props.setProperty("MAX_ATTEMPTS", "99");
        props.setProperty("RESULTS_FILE", "./results.dat");
        props.setProperty("DNA_BASE_LENGTH", "24");
        props.setProperty("MAX_DNA_BIT", "51");
        props.setProperty("MUTATE_PERCENT", "5");
        props.setProperty("MACHINE_ID", "1");
        props.setProperty("NUMBER_THREADS", "1");

        RunConfig.INSTANCE.setUpProperties(props);

    }


    @Test
    public void testCreatePopulation() throws Exception {
        Population pop = new Population(10);
        Assert.assertNotNull(pop);
        pop.createPopulation();

    }

    @Test
    public void testCreatePopulationFromFile() throws Exception {

        ResultsLine rl1 = ResultsLine.createLine(1, new Date().getTime(), 1, new int[]{1, 1, 1, 1});
        ResultsLine rl2 = ResultsLine.createLine(2, new Date().getTime(), 2, new int[]{2, 2, 2, 2});
        ResultsLine rl3 = ResultsLine.createLine(3, new Date().getTime(), 3, new int[]{3, 3, 3, 3});

        ResultsFile rf = new ResultsFile();
        rf.addResultsLine(rl1);
        rf.addResultsLine(rl2);
        rf.addResultsLine(rl3);

        {
            Population pop = new Population(2);
            pop.createPopulation(rf);

            Assert.assertNotNull(pop);
            Assert.assertEquals(2, pop.getPopulationSize());
        }
        {
            Population pop = new Population(5);
            pop.createPopulation(rf);

            Assert.assertNotNull(pop);
            Assert.assertEquals(5, pop.getPopulationSize());

        }


    }


    @Test
    public void testGetNextCritter() throws Exception {
        Population pop = new Population(10);
        Assert.assertNotNull(pop);
        pop.createPopulation();
        for (int ii = 0; ii < 10; ii++) {
            Critter critter = pop.getNextCritter(ii);
            Assert.assertNotNull(critter);
            Assert.assertEquals(ii, critter.getID());
        }


    }

}
