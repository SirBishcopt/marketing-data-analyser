package services;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ErrorFileGenerator {

    public void generateErrorFile(String text) {
        try (PrintWriter out = new PrintWriter("src//error.txt")) {
            out.println(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
