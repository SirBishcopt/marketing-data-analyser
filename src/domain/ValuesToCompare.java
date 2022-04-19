package domain;

public abstract class ValuesToCompare {

    private double difference;
    private double significance;


    public ValuesToCompare(double difference, double significance) {
        this.difference = difference;
        this.significance = significance;
    }

    public boolean isChangeSignificant() {
        return Math.abs(difference) >= significance;
    }

    public double getPercentageDifference() {
        return difference;
    }

}