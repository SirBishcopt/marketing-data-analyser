package services;

import domain.Brand;
import repositories.DataReader;

import java.util.List;

public class IOService {

    public List<Brand> prepareData(String filePath) {
        DataReader dataReader = new DataReader();
        List<String> rawData = dataReader.loadRawData(filePath);
        return dataReader.convertRawDataToBrands(rawData);
    }

}
