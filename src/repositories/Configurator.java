package repositories;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Configurator {

    private static double quantitativeSaleMatSignificance;
    private static double quantitativeSaleMonthlySignificance;
    private static double marketShareMatSignificance;
    private static double marketShareMonthlySignificance;
    private static double pricesMatSignificance;
    private static double pricesMonthlySignificance;
    private static double distributionMatSignificance;
    private static double distributionMonthlySignificance;
    private static double marketShareMatLimit;

    static {

        Stream<String> lines = Files.lines(Paths.get("src/config.txt"));
        List<String> configDataFromFile = lines.collect(Collectors.toList());
        String[] row1 = configDataFromFile.get(0).split(":");
        quantitativeSaleMatSignificance = Double.parseDouble(row1[1]);
        String[] row2 = configDataFromFile.get(1).split(":");
        quantitativeSaleMonthlySignificance = Double.parseDouble(row2[1]);
        String[] row3 = configDataFromFile.get(2).split(":");
        marketShareMatSignificance = Double.parseDouble(row3[1]);
        String[] row4 = configDataFromFile.get(3).split(":");
        marketShareMonthlySignificance = Double.parseDouble(row4[1]);
        String[] row5 = configDataFromFile.get(4).split(":");
        pricesMatSignificance = Double.parseDouble(row5[1]);
        String[] row6 = configDataFromFile.get(5).split(":");
        pricesMonthlySignificance = Double.parseDouble(row6[1]);
        String[] row7 = configDataFromFile.get(6).split(":");
        distributionMatSignificance = Double.parseDouble(row7[1]);
        String[] row8 = configDataFromFile.get(7).split(":");
        distributionMonthlySignificance = Double.parseDouble(row8[1]);
        String[] row9 = configDataFromFile.get(8).split(":");
        marketShareMatLimit = Double.parseDouble(row9[1]);
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
