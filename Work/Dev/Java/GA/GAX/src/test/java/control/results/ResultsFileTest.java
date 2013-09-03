package control.results;

import evaluator.MachineEvaluator;
import evaluator.SimpleEvaluator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import processor.Processor;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class ResultsFileTest {
    private ResultsFile resultsFile;

    @Before
    public void setUpResultsFile() {
        resultsFile = new ResultsFile();
        ResultsLine[] lines = new ResultsLine[10];
        for (int ii = 0; ii < lines.length; ii++) {
            lines[ii] = ResultsLine.createLine(1, 123456, 1, new int[]{1, 1, 1, 1});
            resultsFile.addResultsLine(lines[ii]);
        }

    }

    @Test
    public void testGetResultsLines() throws Exception {
        ResultsLine[] lines = resultsFile.getResultsLines();
        Assert.assertNotNull(lines);

    }

    @Test
    public void testSaveAndReadResults() throws Exception {
        Processor proc1 = new Processor(new SimpleEvaluator());
        Processor proc2 = new Processor(new MachineEvaluator());
        Processor[] procs = {proc1, proc2};
        String fileName = "./testSave.dat";
        Path savePath = new File(fileName).toPath();

        ResultsFile.saveResults(procs, fileName);

        Assert.assertTrue(Files.exists(savePath));
        // now try to read

        ResultsFile readFile = ResultsFile.readResults(fileName);
        Assert.assertNotNull(readFile);
        ResultsLine[] lines = readFile.getResultsLines();
        Assert.assertNotNull(lines);
        Assert.assertEquals(2, lines.length);

        ResultsLine line1 = proc1.createResults();
        ResultsLine line2 = proc2.createResults();

        Assert.assertEquals(line1, lines[0]);
        Assert.assertEquals(line2, lines[1]);

        new File(fileName).delete();

    }


}
