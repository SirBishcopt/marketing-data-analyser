package services;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IOService {

    public List<String> loadFile(String filePath) {
        Stream<String> lines = Files.lines(Paths.get(filePath));
        return lines.collect(Collectors.toList());
    }

    public void saveFile(List<String> lines, String filePath) {
        PrintWriter out = new PrintWriter(filePath);
        Iterator<String> iterator = lines.iterator();
        iterator.forEachRemaining((line) -> out.println(line));
    }

}