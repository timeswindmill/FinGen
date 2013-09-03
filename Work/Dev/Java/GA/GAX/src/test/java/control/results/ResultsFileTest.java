package control.results;

import evaluator.MachineEvaluator;
import evaluator.SimpleEvaluator;
import org.junit.Assert;
import org.junit.Test;
import processor.Processor;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ResultsFileTest {
    private ResultsFile resultsFile;
//
//    @Before
//    public void setUpResultsFile() {
//        resultsFile = new ResultsFile();
//        ResultsLine[] lines = new ResultsLine[10];
//        for (int ii = 0; ii < lines.length; ii++) {
//            lines[ii] = ResultsLine.createLine(1, 123456, 1, new int[]{1, 1, 1, 1});
//            resultsFile.addResultsLine(lines[ii]);
//        }
//
//    }

//    @Test
//    public void testGetResultsLines() throws Exception {
//        ResultsLine[] lines = resultsFile.getResultsLines();
//        Assert.assertNotNull(lines);
//
//    }

    @Test
    public void testSaveAndReadResults() throws Exception {
        Processor proc1 = new Processor(new SimpleEvaluator());
        Processor proc2 = new Processor(new MachineEvaluator());
        Processor[] procs = {proc1, proc2};
        String fileName = "./testSave.dat";
        Path savePath = new File(fileName).toPath();

        // delete file if it exists
        if (Files.exists(savePath)) {
            Files.delete(savePath);
        }

        ResultsFile.saveResults(procs, fileName);

        Assert.assertTrue(Files.exists(savePath));
        // now try to read

        List<ResultsLine> resultsLines = ResultsFile.readResults(fileName);
        Assert.assertNotNull(resultsLines);
        Assert.assertTrue(resultsLines.size() > 0);


        Assert.assertNotNull(resultsLines);
        Assert.assertEquals(2, resultsLines.size());

        ResultsLine line1 = proc1.createResults();
        ResultsLine line2 = proc2.createResults();

        Assert.assertEquals(line1, resultsLines.get(0));
        Assert.assertEquals(line2, resultsLines.get(1));

        Files.delete(savePath);

    }


}
