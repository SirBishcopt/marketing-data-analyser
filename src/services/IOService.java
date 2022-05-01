package services;

import exceptions.ConclusionFileException;
import exceptions.DataFileException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IOService {

    public List<String> loadFile(String filePath) {
        List<String> lines = null;
        try (Stream<String> loaded = Files.lines(Paths.get(filePath))) {
            lines = loaded.collect(Collectors.toList());
        } catch (IOException e) {
            throw new DataFileException("Błąd dostępowy pliku z danymi.");
        }
        return lines;
    }

    public void saveFile(List<String> lines, String filePath) {
        try (PrintWriter out = new PrintWriter(filePath)) {
            Iterator<String> iterator = lines.iterator();
            iterator.forEachRemaining((line) -> out.println(line));
        } catch (FileNotFoundException e) {
            throw new ConclusionFileException("Błąd dostępowy pliku z wnioskami.");
        }
    }

}