package domain;

public class DoublesToCompare extends ValuesToCompare {

    private double previousPeriod;
    private double currentPeriod;
    private double significance;

    public DoublesToCompare(double previousPeriod, double currentPeriod, double significance) {
        super(currentPeriod / previousPeriod - 1, significance);
        this.previousPeriod = previousPeriod;
        this.currentPeriod = currentPeriod;
        this.significance = significance;
    }

    @Override
    public boolean isChangeSignificant() {
        return Math.abs(currentPeriod - previousPeriod) >= (significance);
    }

    public double getValueDifference(){
        return currentPeriod - previousPeriod;
    }

    public double getPreviousPeriod() {
        return previousPeriod;
    }

    public double getCurrentPeriod() {
        return currentPeriod;
    }

}