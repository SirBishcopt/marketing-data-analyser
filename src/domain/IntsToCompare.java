package domain;

public class IntsToCompare extends ValuesToCompare {

    private int previousPeriod;
    private int currentPeriod;

    public IntsToCompare(int previousPeriod, int currentPeriod, double significance) {
        super((double) currentPeriod / previousPeriod - 1.0, significance);
        this.previousPeriod = previousPeriod;
        this.currentPeriod = currentPeriod;
    }

    public int getValueDifference() {
        return currentPeriod - previousPeriod;
    }

    public int getPreviousPeriod() {
        return previousPeriod;
    }

    public int getCurrentPeriod() {
        return currentPeriod;
    }

}