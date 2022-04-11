package domain;

import repositories.Configurator;

import java.util.ArrayList;
import java.util.List;

public class Brand {

    private String name;
    private List<Product> products = new ArrayList<>();
    private IntsToCompare quantitativeSaleMat;
    private IntsToCompare quantitativeSaleMonthly;
    private DoublesToCompare marketShareMat;
    private DoublesToCompare marketShareMonthly;

    public Brand(String name, int[] dataInInts, double[] dataInDouble) {
        this.name = name;
        readBrandsDataInInts(dataInInts);
        readBrandsDataInDouble(dataInDouble);
    }

    private void readBrandsDataInInts(int[] dataInInts) {
        quantitativeSaleMat = new IntsToCompare(dataInInts[0],dataInInts[1], Configurator.getQuantitativeSaleMatSignificance());
        quantitativeSaleMonthly = new IntsToCompare(dataInInts[2],dataInInts[3],Configurator.getQuantitativeSaleMonthlySignificance());
    }

    private void readBrandsDataInDouble(double[] dataInDouble) {
        marketShareMat = new DoublesToCompare(dataInDouble[0],dataInDouble[1],Configurator.getMarketShareMatSignificance());
        marketShareMonthly = new DoublesToCompare(dataInDouble[2],dataInDouble[3],Configurator.getMarketShareMonthlySignificance());
    }

    public void addProduct(String name, double[] dataInDouble) {
        products.add(new Product(name, dataInDouble));
    }

    public IntsToCompare getQuantitativeSaleMat() {
        return quantitativeSaleMat;
    }

    public IntsToCompare getQuantitativeSaleMonthly() {
        return quantitativeSaleMonthly;
    }

    public DoublesToCompare getMarketShareMat() {
        return marketShareMat;
    }

    public DoublesToCompare getMarketShareMonthly() {
        return marketShareMonthly;
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return name;
    }

    public class Product {
        private String name;
        private DoublesToCompare pricesMat;
        private DoublesToCompare pricesMonthly;
        private DoublesToCompare distributionMat;
        private DoublesToCompare distributionMonthly;

        private Product(String name, double[] dataInDouble) {
            this.name = name;
            readProductsDataInDouble(dataInDouble);
        }

        private void readProductsDataInDouble(double[] dataInDouble) {
            pricesMat = new DoublesToCompare(dataInDouble[0],dataInDouble[1],Configurator.getPricesMatSignificance());
            pricesMonthly = new DoublesToCompare(dataInDouble[2],dataInDouble[3],Configurator.getPricesMonthlySignificance());
            distributionMat = new DoublesToCompare(dataInDouble[4],dataInDouble[5],Configurator.getDistributionMatSignificance());
            distributionMonthly = new DoublesToCompare(dataInDouble[6],dataInDouble[7],Configurator.getDistributionMonthlySignificance());
        }

        public DoublesToCompare getPricesMat() {
            return pricesMat;
        }

        public DoublesToCompare getPricesMonthly() {
            return pricesMonthly;
        }

        public DoublesToCompare getDistributionMat() {
            return distributionMat;
        }

        public DoublesToCompare getDistributionMonthly() {
            return distributionMonthly;
        }

        @Override
        public String toString() {
            return Brand.this + " - " + name;
        }

    }

}