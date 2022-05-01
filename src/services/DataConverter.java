package services;

import domain.Brand;
import exceptions.DataDataException;
import repositories.Configurator;

import java.util.ArrayList;
import java.util.List;

public class DataConverter {

    public List<Brand> convertRawDataToBrands(List<String> rawData) {
        List<Brand> brands = new ArrayList<>();
        try {
            String[] total = rawData.get(2).split(";");
            for (int i = 3; i < rawData.size(); i++) {
                String[] currentRow = rawData.get(i).split(";");
                if (currentRow[0].charAt(1) != ' ') {
                    brands.add(createBrand(total, currentRow));
                } else {
                    addProduct(brands.get(brands.size() - 1), currentRow);
                }
            }
        } catch (IndexOutOfBoundsException | NullPointerException | NumberFormatException e) {
            throw new DataDataException("Błąd danych sprzedażowych.");
        }
        return brands;
    }

    private Brand createBrand(String[] total, String[] currentRow) {
        String name = currentRow[0];
        name = name.replaceAll(" \\[TOTAL\\]\"", "").replaceAll("\"", "");
        int[] dataInInts = new int[]{(int) Double.parseDouble(currentRow[Configurator.getQuantitativeSaleColumnPreviousMat()].replaceAll("\\s+", "").replaceAll(",", ".")),
                (int) Double.parseDouble(currentRow[Configurator.getQuantitativeSaleColumnCurrentMat()].replaceAll("\\s+", "").replaceAll(",", ".")),
                (int) Double.parseDouble(currentRow[Configurator.getQuantitativeSaleColumnPreviousMonth()].replaceAll("\\s+", "").replaceAll(",", ".")),
                (int) Double.parseDouble(currentRow[Configurator.getQuantitativeSaleColumnCurrentMonth()].replaceAll("\\s+", "").replaceAll(",", "."))};
        double[] dataInDouble = new double[]{Double.parseDouble(currentRow[Configurator.getQuantitativeSaleColumnPreviousMat()].replaceAll("\\s+", "").replaceAll(",", "."))
                / Double.parseDouble(total[Configurator.getQuantitativeSaleColumnPreviousMat()].replaceAll("\\s+", "").replaceAll(",", ".")),
                Double.parseDouble(currentRow[Configurator.getQuantitativeSaleColumnCurrentMat()].replaceAll("\\s+", "").replaceAll(",", "."))
                        / Double.parseDouble(total[Configurator.getQuantitativeSaleColumnCurrentMat()].replaceAll("\\s+", "").replaceAll(",", ".")),
                Double.parseDouble(currentRow[Configurator.getQuantitativeSaleColumnPreviousMonth()].replaceAll("\\s+", "").replaceAll(",", "."))
                        / Double.parseDouble(total[Configurator.getQuantitativeSaleColumnPreviousMonth()].replaceAll("\\s+", "").replaceAll(",", ".")),
                Double.parseDouble(currentRow[Configurator.getQuantitativeSaleColumnCurrentMonth()].replaceAll("\\s+", "").replaceAll(",", "."))
                        / Double.parseDouble(total[Configurator.getQuantitativeSaleColumnCurrentMonth()].replaceAll("\\s+", "").replaceAll(",", "."))};
        return new Brand(name, dataInInts, dataInDouble);
    }

    private void addProduct(Brand brand, String[] currentRow) {
        String name = currentRow[0];
        name = name.replaceAll("\"        ", "").replaceAll("\"", "");
        double[] dataInDouble = new double[]{Double.parseDouble(currentRow[Configurator.getPricesColumnPreviousMat()].replaceAll(",", ".")),
                Double.parseDouble(currentRow[Configurator.getPricesColumnCurrentMat()].replaceAll(",", ".")),
                Double.parseDouble(currentRow[Configurator.getPricesColumnPreviousMonth()].replaceAll(",", ".")),
                Double.parseDouble(currentRow[Configurator.getPricesColumnCurrentMonth()].replaceAll(",", ".")),
                Double.parseDouble(currentRow[Configurator.getDistributionColumnPreviousMat()].replaceAll(",", ".").replaceAll("%", "")) / 100,
                Double.parseDouble(currentRow[Configurator.getDistributionColumnCurrentMat()].replaceAll(",", ".").replaceAll("%", "")) / 100,
                Double.parseDouble(currentRow[Configurator.getDistributionColumnPreviousMonth()].replaceAll(",", ".").replaceAll("%", "")) / 100,
                Double.parseDouble(currentRow[Configurator.getDistributionColumnCurrentMonth()].replaceAll(",", ".").replaceAll("%", "")) / 100};
        brand.addProduct(name, dataInDouble);
    }

}