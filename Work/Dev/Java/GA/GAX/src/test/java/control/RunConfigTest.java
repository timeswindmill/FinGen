package control;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

public class RunConfigTest {

    @Test
    public void testReadPropertiesFile() throws Exception {

        String fileName = "./test1.xml";
        FileOutputStream fos = new FileOutputStream(fileName);
        Properties props = new Properties();
        props.setProperty("POPULATION_SIZE", "1000");
        props.setProperty("MAX_ATTEMPTS", "999");
        props.setProperty("RESULTS_FILE", "./results.dat");
        props.setProperty("DNA_BASE_LENGTH", "24");
        props.setProperty("MAX_DNA_BIT", "51");
        props.setProperty("MUTATE_PERCENT", "5");
        props.setProperty("MACHINE_ID", "1");
        props.setProperty("NUMBER_THREADS", "1");


        props.storeToXML(fos, "comment");

        RunConfig.INSTANCE.setUpProperties("./test1.xml");

        Assert.assertEquals(1000, RunConfig.INSTANCE.getPopulationSize());
        Assert.assertEquals(1, RunConfig.INSTANCE.getNumThreads());
        Assert.assertEquals(999, RunConfig.INSTANCE.getMaxAttempts());
        Assert.assertEquals(1, RunConfig.INSTANCE.getMachineID());


        new File(fileName).delete();

    }


}
