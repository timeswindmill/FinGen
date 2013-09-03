package control.results;

import processor.Processor;
import world.Logger;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class ResultsFile implements Serializable {

    //    private ResultsLine[] resultLines = new ResultsLine[0];
    private static final Logger logger = Logger.INSTANCE;


//    public ResultsLine[] getResultsLines() {
//        return resultLines;
//    }
//
//    public void addResultsLine(ResultsLine newLine) {
//        int oldSize = resultLines.length;
//        ResultsLine[] newArray = new ResultsLine[oldSize + 1];
//        System.arraycopy(resultLines, 0, newArray, 0, oldSize);
//        newArray[oldSize] = newLine;
//        resultLines = newArray;
//
//    }

    public static void saveResults(Processor[] processors, String fileName) {
//        ResultsFile rf = new ResultsFile();
        List<String> lines = new ArrayList<>();

        for (Processor processor : processors) {
            ResultsLine line = processor.createResults();
            lines.add(line.toString());
        }

        //  now save
        Path filePath = new File(fileName).toPath();
        try {
            if (!Files.exists(filePath)) {
                File saveFile = filePath.toFile();
                boolean created = saveFile.createNewFile();
                if (!created) {
                    logger.getLogger().logError("Unable to create file " + fileName);
                    return;
                }
            }
            Files.write(filePath, lines, Charset.defaultCharset(), StandardOpenOption.APPEND);

        } catch (IOException e) {
            logger.getLogger().logError("Error : " + e);
        }

    }

    public static List<ResultsLine> readResults(String fileName) {
//        FileInputStream fis = null;
//        ResultsFile resultsFile = null;
//        List<ResultsLine> results = new ArrayList<>();

        List<String> lines = new ArrayList<>();
        Path filePath = new File(fileName).toPath();
        try {
            lines = Files.readAllLines(filePath, Charset.defaultCharset());

        } catch (IOException e) {
            logger.getLogger().logError("ERROR " + e);
            logger.getLogger().logError("Unable to read file " + fileName);
        }
        return ResultsLine.createLines(lines);
    }

}