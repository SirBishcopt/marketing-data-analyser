package repositories;

import domain.Brand;
import services.ErrorFileGenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataReader {


    // UWAG ATU ZMIANA
    public List<Brand>  readBrands(String file){
        List<String> lines = loadRawData(file);
        return convertRawDataToBrands(lines);
    }


    // trykecz z przemapowaniem na własny wyjątek i ziu do góry

    public List<String> loadRawData(String file) {
        try (Stream<String> lines = Files.lines(Paths.get(file))) {
            return lines.collect(Collectors.toList());
        } catch (IOException e) {
            ErrorFileGenerator errorFileGenerator = new ErrorFileGenerator();
            errorFileGenerator.generateErrorFile("Błąd dostępowy pliku z danymi.");
            System.exit(-1);
            return new ArrayList<>();
        }
    }

    public List<Brand> convertRawDataToBrands(List<String> rawData) {
        if (rawData.size() <= 3) {
            ErrorFileGenerator errorFileGenerator = new ErrorFileGenerator();
            errorFileGenerator.generateErrorFile("Błąd zawartości pliku z danymi.");
            System.exit(-1);
            return new ArrayList<>();
        }
        List<Brand> brands = new ArrayList<>();
        String[] total = rawData.get(2).split(";");
        try {
            for (int i = 3; i < rawData.size(); i++) {
                String[] currentRow = rawData.get(i).split(";");
                String name = currentRow[0];
                if (name.charAt(1) != ' ') {
                    name = name.replaceAll(" \\[TOTAL\\]\"", "").replaceAll("\"", "");
                    int[] dataInInts = new int[]{(int) Double.parseDouble(currentRow[2].replaceAll("\\s+", "").replaceAll(",", ".")),
                            (int) Double.parseDouble(currentRow[14].replaceAll("\\s+", "").replaceAll(",", ".")),
                            (int) Double.parseDouble(currentRow[15].replaceAll("\\s+", "").replaceAll(",", ".")),
                            (int) Double.parseDouble(currentRow[27].replaceAll("\\s+", "").replaceAll(",", "."))};
                    double[] dataInDouble = new double[]{Double.parseDouble(currentRow[2].replaceAll("\\s+", "").replaceAll(",", ".")) / Double.parseDouble(total[2].replaceAll("\\s+", "").replaceAll(",", ".")),
                            Double.parseDouble(currentRow[14].replaceAll("\\s+", "").replaceAll(",", ".")) / Double.parseDouble(total[14].replaceAll("\\s+", "").replaceAll(",", ".")),
                            Double.parseDouble(currentRow[15].replaceAll("\\s+", "").replaceAll(",", ".")) / Double.parseDouble(total[15].replaceAll("\\s+", "").replaceAll(",", ".")),
                            Double.parseDouble(currentRow[27].replaceAll("\\s+", "").replaceAll(",", ".")) / Double.parseDouble(total[27].replaceAll("\\s+", "").replaceAll(",", "."))};
                    brands.add(new Brand(name, dataInInts, dataInDouble));
                } else {
                    name = name.replaceAll("\"        ", "").replaceAll("\"", "");
                    double[] dataInDouble = new double[]{Double.parseDouble(currentRow[28].replaceAll(",", ".")),
                            Double.parseDouble(currentRow[40].replaceAll(",", ".")),
                            Double.parseDouble(currentRow[41].replaceAll(",", ".")),
                            Double.parseDouble(currentRow[53].replaceAll(",", ".")),
                            Double.parseDouble(currentRow[54].replaceAll(",", ".").replaceAll("%", "")) / 100,
                            Double.parseDouble(currentRow[66].replaceAll(",", ".").replaceAll("%", "")) / 100,
                            Double.parseDouble(currentRow[67].replaceAll(",", ".").replaceAll("%", "")) / 100,
                            Double.parseDouble(currentRow[79].replaceAll(",", ".").replaceAll("%", "")) / 100};
                    brands.get(brands.size() - 1).addProduct(name, dataInDouble);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            ErrorFileGenerator errorFileGenerator = new ErrorFileGenerator();
            errorFileGenerator.generateErrorFile("Błąd zawartości pliku z danymi.");
            System.exit(-1);
        }
        if (brands.isEmpty()) {
            ErrorFileGenerator errorFileGenerator = new ErrorFileGenerator();
            errorFileGenerator.generateErrorFile("Błąd zawartości pliku z danymi.");
            System.exit(-1);
        }
        return brands;
    }

}