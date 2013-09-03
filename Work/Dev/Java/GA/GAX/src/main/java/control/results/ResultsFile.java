package control.results;

import processor.Processor;
import world.Logger;

import java.io.*;

public class ResultsFile implements Serializable {

    private ResultsLine[] resultLines = new ResultsLine[0];
    private static final Logger logger = Logger.INSTANCE;


    public ResultsLine[] getResultsLines() {
        return resultLines;
    }

    public void addResultsLine(ResultsLine newLine) {
        int oldSize = resultLines.length;
        ResultsLine[] newArray = new ResultsLine[oldSize + 1];
        System.arraycopy(resultLines, 0, newArray, 0, oldSize);
        newArray[oldSize] = newLine;
        resultLines = newArray;

    }

    public static void saveResults(Processor[] processors, String fileName) {
        ResultsFile rf = new ResultsFile();
        for (Processor processor : processors) {
            rf.addResultsLine(processor.createResults());
        }

        //  now save
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(rf);

        } catch (IOException e) {
            logger.getLogger().logError("Error : " + e);
        }

    }

    public static ResultsFile readResults(String fileName) {
        FileInputStream fis = null;
        ResultsFile resultsFile = null;
        try {
            fis = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            logger.getLogger().logError("Error : " + e);
            return null;
        }
        ObjectInputStream iis;
        try {
            iis = new ObjectInputStream(fis);
            resultsFile = (ResultsFile) iis.readObject();

        } catch (IOException | ClassNotFoundException e) {
            logger.getLogger().logError("Error : " + e);
        }

        return resultsFile;
    }


}
