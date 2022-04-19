package controllers;

import domain.Brand;
import services.ConclusionCreator;
import services.DataConverter;
import services.IOService;

import java.util.List;

public class BasicController {

    private static String DATA_FILE_PATH = "src\\data.csv";
    private static String CONCLUSIONS_FILE_PATH = "src\\conclusions.txt";

    public void start() {

        IOService ioService = new IOService();
        List<String> rawData = ioService.loadFile(DATA_FILE_PATH);

        DataConverter dataConverter = new DataConverter();
        List<Brand> brands = dataConverter.convertRawDataToBrands(rawData);

        ConclusionCreator conclusionCreator = new ConclusionCreator();
        List<String> conclusions = conclusionCreator.createConclusions(brands);

        ioService.saveFile(conclusions, CONCLUSIONS_FILE_PATH);

    }

}