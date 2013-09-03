package world;

import control.RunConfig;
import control.results.ResultsFile;
import control.results.ResultsLine;
import critter.Critter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Population {


    private final Critter[] critterArray;
    private final int codeLength;

    public Population(int populationSize) {
        critterArray = new Critter[populationSize];
        codeLength = RunConfig.INSTANCE.getDnaBaseLength();
    }


    public void createPopulation() {
        for (int ii = 0; ii < critterArray.length; ii++) {
            int[] newCode = Critter.createRandomCode(codeLength);
            critterArray[ii] = new Critter(ii, newCode);
        }

    }

    public Critter getNextCritter(int critterIndex) {

        return critterArray[critterIndex % critterArray.length];
    }

    public int getPopulationSize() {
        return critterArray.length;
    }

//    public void addCritter(Critter newCritter){
//        int oldSize = critterArray.length;
//        Critter[] newArray = new Critter[oldSize+1];
//        System.arraycopy(critterArray,0,newArray,0,oldSize);
//        newArray[oldSize]= newCritter;
//        critterArray= newArray;
//
//    }

    public void createPopulation(ResultsFile resultsFile) {

        ResultsLine[] lines = resultsFile.getResultsLines();

        List<ResultsLine> resultsList = Arrays.asList(lines);
        Collections.sort(resultsList);

        int resultsListSize = resultsList.size();
        for (int ii = 0; ii < critterArray.length; ii++) {
            Critter newCritter;
            if (ii < resultsListSize) {
                newCritter = new Critter(ii, resultsList.get(ii));
            } else {
                int[] randomCode = Critter.createRandomCode(codeLength);
                newCritter = new Critter(ii, randomCode);
            }

            critterArray[ii] = newCritter;

        }
    }

//    public ResultsLine[] getResultsLines(){
//
//        int machineID = RunConfig.INSTANCE.getMachineID();
//        ResultsLine[] resultsLines = new ResultsLine[critterArray.length];
//
//         for(int ii=0;ii<critterArray.length;ii++){
//             ResultsLine line = new ResultsLine(machineID,new Date().getTime(),critterArray[ii].)
//
//
//         }
//
//        return resultsLines;
//
//    }

}
