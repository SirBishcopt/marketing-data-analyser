package services;

import domain.Brand;

import java.util.ArrayList;
import java.util.List;

public class DataConverter {

    public List<Brand> convertRawDataToBrands(List<String> rawData) {
        List<Brand> brands = new ArrayList<>();
        String[] total = rawData.get(2).split(";");
        for (int i = 3; i < rawData.size(); i++) {
            String[] currentRow = rawData.get(i).split(";");
            if (currentRow[0].charAt(1) != ' ') {
                brands.add(createBrand(total, currentRow));
            } else {
                addProduct(brands.get(brands.size() - 1), currentRow);
            }
        }
        return brands;
    }

    private Brand createBrand(String[] total, String[] currentRow) {
        String name = currentRow[0];
        name = name.replaceAll(" \\[TOTAL\\]\"", "").replaceAll("\"", "");
        int[] dataInInts = new int[]{(int) Double.parseDouble(currentRow[2].replaceAll("\\s+", "").replaceAll(",", ".")),
                (int) Double.parseDouble(currentRow[14].replaceAll("\\s+", "").replaceAll(",", ".")),
                (int) Double.parseDouble(currentRow[15].replaceAll("\\s+", "").replaceAll(",", ".")),
                (int) Double.parseDouble(currentRow[27].replaceAll("\\s+", "").replaceAll(",", "."))};
        double[] dataInDouble = new double[]{Double.parseDouble(currentRow[2].replaceAll("\\s+", "").replaceAll(",", ".")) / Double.parseDouble(total[2].replaceAll("\\s+", "").replaceAll(",", ".")),
                Double.parseDouble(currentRow[14].replaceAll("\\s+", "").replaceAll(",", ".")) / Double.parseDouble(total[14].replaceAll("\\s+", "").replaceAll(",", ".")),
                Double.parseDouble(currentRow[15].replaceAll("\\s+", "").replaceAll(",", ".")) / Double.parseDouble(total[15].replaceAll("\\s+", "").replaceAll(",", ".")),
                Double.parseDouble(currentRow[27].replaceAll("\\s+", "").replaceAll(",", ".")) / Double.parseDouble(total[27].replaceAll("\\s+", "").replaceAll(",", "."))};
        return new Brand(name, dataInInts, dataInDouble);
    }

    private void addProduct(Brand brand, String[] currentRow) {
        String name = currentRow[0];
        name = name.replaceAll("\"        ", "").replaceAll("\"", "");
        double[] dataInDouble = new double[]{Double.parseDouble(currentRow[28].replaceAll(",", ".")),
                Double.parseDouble(currentRow[40].replaceAll(",", ".")),
                Double.parseDouble(currentRow[41].replaceAll(",", ".")),
                Double.parseDouble(currentRow[53].replaceAll(",", ".")),
                Double.parseDouble(currentRow[54].replaceAll(",", ".").replaceAll("%", "")) / 100,
                Double.parseDouble(currentRow[66].replaceAll(",", ".").replaceAll("%", "")) / 100,
                Double.parseDouble(currentRow[67].replaceAll(",", ".").replaceAll("%", "")) / 100,
                Double.parseDouble(currentRow[79].replaceAll(",", ".").replaceAll("%", "")) / 100};
        brand.addProduct(name, dataInDouble);
    }

}