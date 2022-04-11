package services;

import domain.Brand;
import repositories.Configurator;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class ConclusionCreator {

    public List<String> analyseQuantitativeSales(List<Brand> brands) {
        List<String> conclusions = new ArrayList<>();
        for (Brand brand : brands) {
            if (brand.getQuantitativeSaleMat().isChangeSignificant() && brand.getMarketShareMat().getCurrentPeriod() > Configurator.getMarketShareMatLimit()) {
                String statement;
                if (brand.getQuantitativeSaleMat().getPercentageDifference() > 0) {
                    statement = " MAT do MATu osiąga wzrost, z ";
                } else {
                    statement = " MAT do MATu zalicza spadek, z ";
                }
                conclusions.add(brand + statement + NumberFormat.getIntegerInstance().format(brand.getQuantitativeSaleMat().getPreviousPeriod()) + " na "
                        + NumberFormat.getIntegerInstance().format(brand.getQuantitativeSaleMat().getCurrentPeriod()) + ", czyli o "
                        + NumberFormat.getIntegerInstance().format(brand.getQuantitativeSaleMat().getValueDifference())
                        + ", co stanowi zmianę o " + (int) Math.round(100.0 * brand.getQuantitativeSaleMat().getPercentageDifference()) + "%.");
            }
            if (brand.getQuantitativeSaleMonthly().isChangeSignificant() && brand.getMarketShareMat().getCurrentPeriod() > Configurator.getMarketShareMatLimit()) {
                String statement;
                if (brand.getQuantitativeSaleMonthly().getPercentageDifference() > 0) {
                    statement = " miesiąc do miesiąca osiąga wzrost, z ";
                } else {
                    statement = " miesiąc do miesiąca zalicza spadek, z ";
                }
                conclusions.add(brand + statement + NumberFormat.getIntegerInstance().format(brand.getQuantitativeSaleMonthly().getPreviousPeriod()) + " na "
                        + NumberFormat.getIntegerInstance().format(brand.getQuantitativeSaleMonthly().getCurrentPeriod()) + ", czyli o "
                        + NumberFormat.getIntegerInstance().format(brand.getQuantitativeSaleMonthly().getValueDifference())
                        + ", co stanowi zmianę o " + (int) Math.round(100.0 * brand.getQuantitativeSaleMonthly().getPercentageDifference()) + "%.");
            }
        }
        return conclusions;
    }

    public List<String> analyseMarketShare(List<Brand> brands) {
        List<String> conclusions = new ArrayList<>();
        for (Brand brand : brands) {
            if (brand.getMarketShareMat().isChangeSignificant()) {
                String statement;
                if (brand.getMarketShareMat().getPercentageDifference() > 0) {
                    statement = " MAT do MATu osiąga wzrost, z ";
                } else {
                    statement = " MAT do MATu zalicza spadek, z ";
                }
                conclusions.add(brand + statement + (int) Math.round(100.0 * brand.getMarketShareMat().getPreviousPeriod()) + "%"
                        + " na " + (int) Math.round(100.0 * brand.getMarketShareMat().getCurrentPeriod()) + "%");
            }
            if (brand.getMarketShareMonthly().isChangeSignificant()) {
                String statement;
                if (brand.getMarketShareMonthly().getPercentageDifference() > 0) {
                    statement = " miesiąc do miesiąca osiąga wzrost, z ";
                } else {
                    statement = " miesiąc do miesiąca zalicza spadek, z ";
                }
                conclusions.add(brand + statement + (int) Math.round(100.0 * brand.getMarketShareMonthly().getPreviousPeriod()) + "%"
                        + " na " + (int) Math.round(100.0 * brand.getMarketShareMonthly().getCurrentPeriod()) + "%");
            }
        }
        return conclusions;
    }

    public List<String> analysePrices(List<Brand> brands) {
        List<String> conclusions = new ArrayList<>();
        for (Brand brand : brands) {
            List<Brand.Product> products = brand.getProducts();
            for (Brand.Product product : products) {
                if (product.getPricesMat().isChangeSignificant() && brand.getMarketShareMat().getCurrentPeriod() > Configurator.getMarketShareMatLimit()) {
                    String statement;
                    if (product.getPricesMat().getPercentageDifference() > 0) {
                        statement = " MAT do MATu osiąga wzrost, z ";
                    } else {
                        statement = " MAT do MATu zalicza spadek, z ";
                    }
                    conclusions.add(product + statement + product.getPricesMat().getPreviousPeriod() + " zł"
                            + " na " + product.getPricesMat().getCurrentPeriod() + " zł"
                            + ", czyli o " + String.format("%.2f", product.getPricesMat().getValueDifference()) + " zł"
                            + ", co stanowi " + (int) Math.round(100.0 * product.getPricesMat().getPercentageDifference()) + "%.");
                }
                if (product.getPricesMonthly().isChangeSignificant() && brand.getMarketShareMat().getCurrentPeriod() > Configurator.getMarketShareMatLimit()) {
                    String statement;
                    if (product.getPricesMonthly().getPercentageDifference() > 0) {
                        statement = " miesiąc do miesiąca osiąga wzrost, z ";
                    } else {
                        statement = " miesiąc do miesiąca zalicza spadek, z ";
                    }
                    conclusions.add(product + statement + product.getPricesMonthly().getPreviousPeriod() + " zł"
                            + " na " + product.getPricesMonthly().getCurrentPeriod() + " zł"
                            + ", czyli o " + String.format("%.2f", product.getPricesMonthly().getValueDifference()) + " zł"
                            + ", co stanowi " + (int) Math.round(100.0 * product.getPricesMonthly().getPercentageDifference()) + "%.");
                }
            }
        }
        return conclusions;
    }

    public List<String> analyseDistribution(List<Brand> brands) {
        List<String> conclusions = new ArrayList<>();
        for (Brand brand : brands) {
            List<Brand.Product> products = brand.getProducts();
            for (Brand.Product product : products) {
                if (product.getDistributionMat().isChangeSignificant()) {
                    String statement;
                    if (product.getDistributionMat().getPercentageDifference() > 0) {
                        statement = " MAT do MATu osiąga wzrost, z ";
                    } else {
                        statement = " MAT do MATu zalicza spadek, z ";
                    }
                    conclusions.add(product + statement + (int) Math.round(product.getDistributionMat().getPreviousPeriod()) + "%"
                            + " na " + (int) Math.round(product.getDistributionMat().getCurrentPeriod()) + "%");
                }
                if (product.getDistributionMonthly().isChangeSignificant()) {
                    String statement;
                    if (product.getDistributionMonthly().getPercentageDifference() > 0) {
                        statement = " miesiąc do miesiąca osiąga wzrost, z ";
                    } else {
                        statement = " miesiąc do miesiąca zalicza spadek, z ";
                    }
                    conclusions.add(product + statement + (int) Math.round(product.getDistributionMonthly().getPreviousPeriod()) + "%"
                            + " na " + (int) Math.round(product.getDistributionMonthly().getCurrentPeriod()) + "%");
                }
            }
        }
        return conclusions;
    }

}