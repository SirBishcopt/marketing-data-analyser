package repositories;

import exceptions.ConfigDataException;
import exceptions.ConfigFileException;

import java.io.IOException;
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
    private static int quantitativeSaleColumnPreviousMat;
    private static int quantitativeSaleColumnCurrentMat;
    private static int quantitativeSaleColumnPreviousMonth;
    private static int quantitativeSaleColumnCurrentMonth;
    private static int pricesColumnPreviousMat;
    private static int pricesColumnCurrentMat;
    private static int pricesColumnPreviousMonth;
    private static int pricesColumnCurrentMonth;
    private static int distributionColumnPreviousMat;
    private static int distributionColumnCurrentMat;
    private static int distributionColumnPreviousMonth;
    private static int distributionColumnCurrentMonth;

    static {
        try (Stream<String> lines = Files.lines(Paths.get("src/config.txt"))) {
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
            String[] row10 = configDataFromFile.get(9).split(":");
            quantitativeSaleColumnPreviousMat = Integer.parseInt(row10[1]);
            String[] row11 = configDataFromFile.get(10).split(":");
            quantitativeSaleColumnCurrentMat = Integer.parseInt(row11[1]);
            String[] row12 = configDataFromFile.get(11).split(":");
            quantitativeSaleColumnPreviousMonth = Integer.parseInt(row12[1]);
            String[] row13 = configDataFromFile.get(12).split(":");
            quantitativeSaleColumnCurrentMonth = Integer.parseInt(row13[1]);
            String[] row14 = configDataFromFile.get(13).split(":");
            pricesColumnPreviousMat = Integer.parseInt(row14[1]);
            String[] row15 = configDataFromFile.get(14).split(":");
            pricesColumnCurrentMat = Integer.parseInt(row15[1]);
            String[] row16 = configDataFromFile.get(15).split(":");
            pricesColumnPreviousMonth = Integer.parseInt(row16[1]);
            String[] row17 = configDataFromFile.get(16).split(":");
            pricesColumnCurrentMonth = Integer.parseInt(row17[1]);
            String[] row18 = configDataFromFile.get(17).split(":");
            distributionColumnPreviousMat = Integer.parseInt(row18[1]);
            String[] row19 = configDataFromFile.get(18).split(":");
            distributionColumnCurrentMat = Integer.parseInt(row19[1]);
            String[] row20 = configDataFromFile.get(19).split(":");
            distributionColumnPreviousMonth = Integer.parseInt(row20[1]);
            String[] row21 = configDataFromFile.get(20).split(":");
            distributionColumnCurrentMonth = Integer.parseInt(row21[1]);
        } catch (IOException e) {
            throw new ConfigFileException("Błąd dostępowy pliku konfiguracyjnego.");
        } catch (NullPointerException | IndexOutOfBoundsException | NumberFormatException e) {
            throw new ConfigDataException("Błąd danych konfiguracyjnych.");
        }
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

    public static int getQuantitativeSaleColumnPreviousMat() {
        return quantitativeSaleColumnPreviousMat;
    }

    public static int getQuantitativeSaleColumnCurrentMat() {
        return quantitativeSaleColumnCurrentMat;
    }

    public static int getQuantitativeSaleColumnPreviousMonth() {
        return quantitativeSaleColumnPreviousMonth;
    }

    public static int getQuantitativeSaleColumnCurrentMonth() {
        return quantitativeSaleColumnCurrentMonth;
    }

    public static int getPricesColumnPreviousMat() {
        return pricesColumnPreviousMat;
    }

    public static int getPricesColumnCurrentMat() {
        return pricesColumnCurrentMat;
    }

    public static int getPricesColumnPreviousMonth() {
        return pricesColumnPreviousMonth;
    }

    public static int getPricesColumnCurrentMonth() {
        return pricesColumnCurrentMonth;
    }

    public static int getDistributionColumnPreviousMat() {
        return distributionColumnPreviousMat;
    }

    public static int getDistributionColumnCurrentMat() {
        return distributionColumnCurrentMat;
    }

    public static int getDistributionColumnPreviousMonth() {
        return distributionColumnPreviousMonth;
    }

    public static int getDistributionColumnCurrentMonth() {
        return distributionColumnCurrentMonth;
    }

}