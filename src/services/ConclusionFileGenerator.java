package services;

import java.io.*;
import java.util.Iterator;
import java.util.List;

public class ConclusionFileGenerator {

    public void saveConclusions(List<String> conclusions, String file) {
        try (PrintWriter out = new PrintWriter(file)) {
            Iterator<String> iterator = conclusions.iterator();
            iterator.forEachRemaining((text) -> out.println(text));
        } catch (FileNotFoundException e) {
            ErrorFileGenerator errorFileGenerator = new ErrorFileGenerator();
            errorFileGenerator.generateErrorFile("Błąd dostępowy pliku z wnioskami.");
            System.exit(-1);
        }
    }

}