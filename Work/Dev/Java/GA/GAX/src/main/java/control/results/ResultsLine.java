package control.results;

import java.io.Serializable;
import java.util.Arrays;

public class ResultsLine implements Comparable<ResultsLine>, Serializable {


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

}
