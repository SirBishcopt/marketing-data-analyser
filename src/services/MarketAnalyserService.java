package services;

import domain.Brand;
import repositories.DataRepository;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MarketAnalyserService {

    public List<Brand> prepareData(String file) {
        DataRepository repository = new DataRepository();
        return repository.prepareData(file);
    }

    public List<String> createConclusions(List<Brand> brands) {
        ConclusionCreator conclusionCreator = new ConclusionCreator();
        List<String> conclusions = new ArrayList<>();
        conclusions.add("ANALIZA RYNKU PEX " + LocalDate.now());
        conclusions.add("");
        conclusions.add("");
        conclusions.add("SPRZEDAŻ ILOŚCIOWO");
        conclusions.add("");
        conclusions.addAll(conclusionCreator.analyseQuantitativeSales(brands));
        conclusions.add("");
        conclusions.add("");
        conclusions.add("UDZIAŁ W RYNKU");
        conclusions.add("");
        conclusions.addAll(conclusionCreator.analyseMarketShare(brands));
        conclusions.add("");
        conclusions.add("");
        conclusions.add("CENY");
        conclusions.add("");
        conclusions.addAll(conclusionCreator.analysePrices(brands));
        conclusions.add("");
        conclusions.add("");
        conclusions.add("DYSTRYBUCJA APTECZNA");
        conclusions.add("");
        conclusions.addAll(conclusionCreator.analyseDistribution(brands));
        return conclusions;
    }

    public void saveConclusions(List<String> conclusions) {
        try (PrintWriter out = new PrintWriter("src\\conclusions.txt")) {
            Iterator<String> iterator = conclusions.iterator();
            iterator.forEachRemaining((text) -> out.println(text));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}