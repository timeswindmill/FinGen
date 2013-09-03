package control;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public enum RunConfig {

    INSTANCE;


    private int populationSize;
    private int maxAttempts;
    private String resultsFileName;
    private int dnaBaseLength;
    private int numRegisters;
    private int maxDnaBit;
    private int mutatePercent;
    private int machineID;
    private int numThreads;


    private RunConfig() {
    }


    public void setUpProperties(Properties properties) {
        //       this.properties = properties;

        String sPopSize = properties.getProperty("POPULATION_SIZE");
        String sMaxAttempts = properties.getProperty("MAX_ATTEMPTS");
        String sResultsFile = properties.getProperty("RESULTS_FILE");
        String sDnaBaseLength = properties.getProperty("DNA_BASE_LENGTH");
        String sNumRegisters = properties.getProperty("NUM_REGISTERS");
        String sMaxDnaBit = properties.getProperty("MAX_DNA_BIT");
        String sMutatePercent = properties.getProperty("MUTATE_PERCENT");
        String sMachineID = properties.getProperty("MACHINE_ID");
        String sNumThreads = properties.getProperty("NUMBER_THREADS");

        populationSize = (sPopSize != null) ? Integer.parseInt(sPopSize) : 10000;
        maxAttempts = (sMaxAttempts != null) ? Integer.parseInt(sMaxAttempts) : 999;
        resultsFileName = (sResultsFile != null) ? sResultsFile : "./results.dat";
        dnaBaseLength = (sDnaBaseLength != null) ? Integer.parseInt(sDnaBaseLength) : 24;
        numRegisters = (sNumRegisters != null) ? Integer.parseInt(sNumRegisters) : 20;
        maxDnaBit = (sMaxDnaBit != null) ? Integer.parseInt(sMaxDnaBit) : 50;
        mutatePercent = (sMutatePercent != null) ? Integer.parseInt(sMutatePercent) : 3;
        machineID = (sMachineID != null) ? Integer.parseInt(sMachineID) : 1;
        numThreads = (sNumThreads != null) ? Integer.parseInt(sNumThreads) : 1;


    }

    public void setUpProperties(String propertiesFileName) throws IOException {

        FileInputStream fis = new FileInputStream(propertiesFileName);

        Properties newProperties = new Properties();
        newProperties.loadFromXML(fis);
        setUpProperties(newProperties);

    }


    public int getPopulationSize() {
        return populationSize;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public String getResultsFileName() {
        return resultsFileName;
    }

    public int getDnaBaseLength() {
        return dnaBaseLength;
    }

    public int getNumRegisters() {
        return numRegisters;
    }

    public int getMaxDnaBit() {
        return maxDnaBit;
    }

    public int getMutatePercent() {
        return mutatePercent;
    }

    public int getMachineID() {
        return machineID;
    }

    public int getNumThreads() {
        return numThreads;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Properties are : ");
        sb.append("Population Size = " + populationSize + "\n");
        sb.append("Max Attempts = " + maxAttempts + "\n");
        sb.append("Results File Name = " + resultsFileName + "\n");
        sb.append("DNA Base Length = " + dnaBaseLength + "\n");
        sb.append("Number Registers = " + numRegisters + "\n");
        sb.append("Max DNA Bit = " + maxDnaBit + "\n");
        sb.append("Mutate percent = " + mutatePercent + "\n");
        sb.append("Machine ID = " + machineID + "\n");
        sb.append("Number Threads = " + numThreads + "\n");


        return sb.toString();
    }


}
