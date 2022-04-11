package repositories;

import java.io.File;

public class Configurator {

    private static double quantitativeSaleMatSignificance = 0.2;
    private static double quantitativeSaleMonthlySignificance = 0.2;
    private static double marketShareMatSignificance = 0.2;
    private static double marketShareMonthlySignificance = 0.2;
    private static double pricesMatSignificance = 1.0;
    private static double pricesMonthlySignificance = 0.5;
    private static double distributionMatSignificance = 5.0;
    private static double distributionMonthlySignificance = 5.0;
    private static double marketShareMatLimit = 0.01;

    static {

    }

    public static double getQuantitativeSaleMatSignificance() {
        return quantitativeSaleMatSignificance;
    }

    public static double getQuantitativeSaleMonthlySignificance() {
        return quantitativeSaleMonthlySignificance;
    }

    public static double getMarketShareMatSignificance() {
        return marketShareMatSignificance;
    }

    public static double getMarketShareMonthlySignificance() {
        return marketShareMonthlySignificance;
    }

    public static double getPricesMatSignificance() {
        return pricesMatSignificance;
    }

    public static double getPricesMonthlySignificance() {
        return pricesMonthlySignificance;
    }

    public static double getDistributionMatSignificance() {
        return distributionMatSignificance;
    }

    public static double getDistributionMonthlySignificance() {
        return distributionMonthlySignificance;
    }

    public static double getMarketShareMatLimit() {
        return marketShareMatLimit;
    }

}
