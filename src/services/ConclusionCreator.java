package services;

import domain.Brand;
import domain.IntsToCompare;
import repositories.Configurator;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class ConclusionCreator {

    private NumberFormat integerFormat;
    private NumberFormat percentageFormat;
    private NumberFormat currencyFormat;

    public ConclusionCreator() {
        this.integerFormat = NumberFormat.getIntegerInstance();
        this.percentageFormat = NumberFormat.getPercentInstance();
        this.currencyFormat = NumberFormat.getCurrencyInstance(new Locale("pl", "PL"));
    }

    public List<String> createConclusions(List<Brand> brands) {
        boolean containsProducts = false;
        for (Brand brand : brands) {
            if (brand.getProducts() != null) {
                containsProducts = true;
                break;
            }
        }
        if (!containsProducts) {
            ErrorFileGenerator errorFileGenerator = new ErrorFileGenerator();
            errorFileGenerator.generateErrorFile("Błąd zawartości pliku z danymi.");
            System.exit(-1);
        }
        List<String> conclusions = new ArrayList<>();
        Collections.addAll(conclusions, "ANALIZA RYNKU PEX " + LocalDate.now(), "", "");
        Collections.addAll(conclusions, "SPRZEDAŻ ILOŚCIOWO", "");
        conclusions.addAll(analyseQuantitativeSales(brands));
        Collections.addAll(conclusions, "", "");
        Collections.addAll(conclusions, "UDZIAŁ W RYNKU", "");
        conclusions.addAll(analyseMarketShare(brands));
        Collections.addAll(conclusions, "", "");
        Collections.addAll(conclusions, "CENY", "");
        conclusions.addAll(analysePrices(brands));
        Collections.addAll(conclusions, "", "");
        Collections.addAll(conclusions, "DYSTRYBUCJA APTECZNA", "");
        conclusions.addAll(analyseDistribution(brands));
        return conclusions;
    }

    private List<String> analyseQuantitativeSales(List<Brand> brands) {
        List<String> conclusions = new ArrayList<>();
        for (Brand brand : brands) {

            boolean isMarketShareAboveLimit = brand.getMarketShareMat().getCurrentPeriod() > Configurator.getMarketShareMatLimit();
            if (brand.getQuantitativeSaleMat().isChangeSignificant() && isMarketShareAboveLimit) {

            }

            if (brand.getQuantitativeSaleMonthly().isChangeSignificant() && isMarketShareAboveLimit) {
                String statement = chooseStatement(Period.MONTH, brand.getQuantitativeSaleMonthly().getPercentageDifference());
                conclusions.add(brand + statement + integerFormat.format(brand.getQuantitativeSaleMonthly().getPreviousPeriod()) + " na "
                        + integerFormat.format(brand.getQuantitativeSaleMonthly().getCurrentPeriod()) + ", czyli o "
                        + integerFormat.format(brand.getQuantitativeSaleMonthly().getValueDifference())
                        + ", co stanowi zmianę o " + percentageFormat.format(brand.getQuantitativeSaleMonthly().getPercentageDifference()) + ".");
            }


        }
        return conclusions;
    }

    private String aaa(Brand brand, Period period, IntsToCompare ints) {
      //  IntsToCompare ints = brand.getQuantitativeSaleMat();
        String statement = chooseStatement(period, brand.getQuantitativeSaleMat().getPercentageDifference());
        return brand + statement + integerFormat.format(brand.getQuantitativeSaleMat().getPreviousPeriod()) + " na "
                + integerFormat.format(brand.getQuantitativeSaleMat().getCurrentPeriod()) + ", czyli o "
                + integerFormat.format(brand.getQuantitativeSaleMat().getValueDifference())
                + ", co stanowi zmianę o " + percentageFormat.format(brand.getQuantitativeSaleMat().getPercentageDifference()) + ".";
    }

    private List<String> analyseMarketShare(List<Brand> brands) {
        List<String> conclusions = new ArrayList<>();
        for (Brand brand : brands) {
            if (brand.getMarketShareMat().isChangeSignificant()) {
                String statement = chooseStatement(Period.MAT, brand.getMarketShareMat().getPercentageDifference());
                conclusions.add(brand + statement + percentageFormat.format(brand.getMarketShareMat().getPreviousPeriod())
                        + " na " + percentageFormat.format(brand.getMarketShareMat().getCurrentPeriod()) + ".");
            }
            if (brand.getMarketShareMonthly().isChangeSignificant()) {
                String statement = chooseStatement(Period.MONTH, brand.getMarketShareMonthly().getPercentageDifference());
                conclusions.add(brand + statement + percentageFormat.format(brand.getMarketShareMonthly().getPreviousPeriod())
                        + " na " + percentageFormat.format(brand.getMarketShareMonthly().getCurrentPeriod()) + ".");
            }
        }
        return conclusions;
    }

    private List<String> analysePrices(List<Brand> brands) {
        List<String> conclusions = new ArrayList<>();
        for (Brand brand : brands) {
            List<Brand.Product> products = brand.getProducts();
            for (Brand.Product product : products) {
                if (product.getPricesMat().isChangeSignificant() && brand.getMarketShareMat().getCurrentPeriod() > Configurator.getMarketShareMatLimit()
                        && product.getPricesMat().getCurrentPeriod() != 0) {
                    String statement = chooseStatement(Period.MAT, product.getPricesMat().getPercentageDifference());
                    conclusions.add(product + statement + currencyFormat.format(product.getPricesMat().getPreviousPeriod())
                            + " na " + currencyFormat.format(product.getPricesMat().getCurrentPeriod())
                            + ", czyli o " + currencyFormat.format(product.getPricesMat().getValueDifference())
                            + ", co stanowi " + percentageFormat.format(product.getPricesMat().getPercentageDifference()) + ".");
                }
                if (product.getPricesMonthly().isChangeSignificant() && brand.getMarketShareMat().getCurrentPeriod() > Configurator.getMarketShareMatLimit()
                        && product.getPricesMonthly().getCurrentPeriod() != 0) {
                    String statement = chooseStatement(Period.MONTH, product.getPricesMonthly().getPercentageDifference());
                    conclusions.add(product + statement + currencyFormat.format(product.getPricesMonthly().getPreviousPeriod())
                            + " na " + currencyFormat.format(product.getPricesMonthly().getCurrentPeriod())
                            + ", czyli o " + currencyFormat.format(product.getPricesMonthly().getValueDifference())
                            + ", co stanowi " + percentageFormat.format(product.getPricesMonthly().getPercentageDifference()) + ".");
                }
            }
        }
        return conclusions;
    }

    private List<String> analyseDistribution(List<Brand> brands) {
        List<String> conclusions = new ArrayList<>();
        for (Brand brand : brands) {
            List<Brand.Product> products = brand.getProducts();
            for (Brand.Product product : products) {
                if (product.getDistributionMat().isChangeSignificant()) {
                    String statement = chooseStatement(Period.MAT, product.getDistributionMat().getPercentageDifference());
                    conclusions.add(product + statement + percentageFormat.format(product.getDistributionMat().getPreviousPeriod())
                            + " na " + percentageFormat.format(product.getDistributionMat().getCurrentPeriod()) + ".");
                }
                if (product.getDistributionMonthly().isChangeSignificant()) {
                    String statement = chooseStatement(Period.MONTH, product.getDistributionMonthly().getPercentageDifference());
                    conclusions.add(product + statement + percentageFormat.format(product.getDistributionMonthly().getPreviousPeriod())
                            + " na " + percentageFormat.format(product.getDistributionMonthly().getCurrentPeriod()) + ".");
                }
            }
        }
        return conclusions;
    }

    private String chooseStatement(Period period, double percentageDifference) {
        String statement;
        if (percentageDifference > 0) {
            statement = "osiąga wzrost, z ";
        } else {
            statement = "zalicza spadek, z ";
        }
        return period + statement;
    }

    private enum Period {

        MAT,
        MONTH;

        public String toString() {
            switch (this) {
                case MAT:
                    return " MAT do MATu ";
                case MONTH:
                    return " miesiąc do miesiąca ";
                default:
                    return " bieżący okres do poprzedniego ";
            }
        }

    }

}