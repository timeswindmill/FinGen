package control.results;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResultsLine implements Comparable<ResultsLine>, Serializable {

    private static final String DELIMITER = "|";
    private final int machineID;
    private final long date;
    private final double fitness;
    private final int[] bits;


    private ResultsLine(int machineID, long date, double fitness, int[] bits) {
        this.machineID = machineID;
        this.date = date;
        this.fitness = fitness;
        this.bits = bits;
    }

    public static ResultsLine createLine(int machine, long longDate, double fitness, int[] bits) {
        if (bits == null) {
            bits = new int[0];
        }
        return new ResultsLine(machine, longDate, fitness, bits);

    }

    public static List<ResultsLine> createLines(List<String> lines) {
        int numLines = lines.size();
        List<ResultsLine> resultsLines = new ArrayList<>(numLines);
        for (int ii = 0; ii < numLines; ii++) {
            ResultsLine line = createLine(lines.get(ii));
            resultsLines.add(line);
        }
        return resultsLines;
    }

    public static ResultsLine createLine(String line) {

        ResultsLine newLine = null;
        String regex = "\\" + DELIMITER;
        String[] fields = line.split(regex);
        if (fields.length > 0) {
            int machineID = Integer.parseInt(fields[0]);
            long date = Long.parseLong(fields[1]);
            double fitness = Double.parseDouble(fields[2]);
            int numIntegers = fields.length > 3 ? fields.length - 3 : 0;
            int[] bits = new int[numIntegers];
            for (int ii = 0; ii < numIntegers; ii++) {
                bits[ii] = Integer.parseInt(fields[ii + 3]);

            }
            newLine = new ResultsLine(machineID, date, fitness, bits);
        }
        return newLine;
    }


    public int[] getBits() {
        return bits;
    }

    @Override
    public int compareTo(ResultsLine o) {
        if (o.fitness == this.fitness) {
            return 0;
        }
        if (o.fitness < this.fitness) {
            return 1;
        } else {
            return -1;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResultsLine that = (ResultsLine) o;

        //    if (date != that.date) return false;
        if (Double.compare(that.fitness, fitness) != 0) return false;
        if (machineID != that.machineID) return false;
        if (!Arrays.equals(bits, that.bits)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = machineID;
        result = 31 * result + (int) (date ^ (date >>> 32));
        temp = Double.doubleToLongBits(fitness);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + Arrays.hashCode(bits);
        return result;
    }

    public String toString() {


        StringBuilder sb = new StringBuilder();
        sb.append(machineID);
        sb.append(DELIMITER);

        sb.append(date);
        sb.append(DELIMITER);

        sb.append(fitness);

        for (int bit : bits) {
            sb.append(DELIMITER);
            sb.append(bit);
        }

        return sb.toString();

    }


}
