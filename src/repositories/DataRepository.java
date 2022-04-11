package repositories;

import domain.Brand;

import java.util.List;

public class DataRepository {

    public List<Brand> prepareData(String file) {
        DataReader dataReader = new DataReader();
        List<String> rawData = dataReader.loadRawData(file);
        return dataReader.convertRawDataToBrands(rawData);
    }

}