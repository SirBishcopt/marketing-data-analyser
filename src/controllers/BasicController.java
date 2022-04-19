package controllers;

import domain.Brand;
import services.ConclusionCreator;
import services.DataPreparingService;
import services.ConclusionFileGenerator;
import services.IOService;

import java.util.List;

// STATIC adresy plików - generalnie pojedyncze pliki, czy inty

public class BasicController {

    // private ErrorHandler i tutatj wsie trykecze

    public void start() {

        IOService ioService = new IOService();
        List<Brand> brands = ioService.prepareData("src\\data.csv");
        ConclusionCreator conclusionCreator = new ConclusionCreator();
        List<String> conclusions = conclusionCreator.createConclusions(brands);
        ConclusionFileGenerator conclusionFileGenerator = new ConclusionFileGenerator();
        conclusionFileGenerator.saveConclusions(conclusions, "src\\conclusions.txt");
    }

}

//input / output do pliku tekstowego w jednym obiekcie