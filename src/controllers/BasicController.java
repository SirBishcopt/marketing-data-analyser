package controllers;

import domain.Brand;
import services.MarketAnalyserService;

import java.util.List;

public class BasicController {

    private MarketAnalyserService service = new MarketAnalyserService();

    public void start() {
        List<Brand> brands = service.prepareData("src\\data.csv");
        List<String> conclusions = service.createConclusions(brands);
        service.saveConclusions(conclusions);
    }

}