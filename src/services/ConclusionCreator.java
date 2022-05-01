package services;

import domain.Brand;
import domain.DoublesToCompare;
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
                conclusions.add(createStatementAboutQuantitativeSales(brand, Period.MAT, brand.getQuantitativeSaleMat()));
            }
            if (brand.getQuantitativeSaleMonthly().isChangeSignificant() && isMarketShareAboveLimit) {
                conclusions.add(createStatementAboutQuantitativeSales(brand, Period.MONTH, brand.getQuantitativeSaleMonthly()));
            }
        }
        return addCommentIfEmpty(conclusions);
    }

    private String createStatementAboutQuantitativeSales(Brand brand, Period period, IntsToCompare quantitativeSaleInGivenPeriod) {
        return brand + chooseStatement(period, quantitativeSaleInGivenPeriod.getPercentageDifference())
                + integerFormat.format(quantitativeSaleInGivenPeriod.getPreviousPeriod()) + " na "
                + integerFormat.format(quantitativeSaleInGivenPeriod.getCurrentPeriod()) + ", czyli o "
                + integerFormat.format(quantitativeSaleInGivenPeriod.getValueDifference())
                + ", co stanowi zmianę o " + percentageFormat.format(quantitativeSaleInGivenPeriod.getPercentageDifference()) + ".";
    }

    private List<String> analyseMarketShare(List<Brand> brands) {
        List<String> conclusions = new ArrayList<>();
        for (Brand brand : brands) {
            if (brand.getMarketShareMat().isChangeSignificant()) {
                conclusions.add(createStatementAboutMarketShare(brand, Period.MAT, brand.getMarketShareMat()));
            }
            if (brand.getMarketShareMonthly().isChangeSignificant()) {
                conclusions.add(createStatementAboutMarketShare(brand, Period.MONTH, brand.getMarketShareMonthly()));
            }
        }
        return addCommentIfEmpty(conclusions);
    }

    private String createStatementAboutMarketShare(Brand brand, Period period, DoublesToCompare marketShareInGivenPeriod) {
        return brand + chooseStatement(period, marketShareInGivenPeriod.getPercentageDifference())
                + percentageFormat.format(marketShareInGivenPeriod.getPreviousPeriod())
                + " na " + percentageFormat.format(marketShareInGivenPeriod.getCurrentPeriod()) + ".";
    }

    private List<String> analysePrices(List<Brand> brands) {
        List<String> conclusions = new ArrayList<>();
        for (Brand brand : brands) {
            List<Brand.Product> products = brand.getProducts();
            for (Brand.Product product : products) {
                boolean isMarketShareAboveLimitAndCurrentPriceAboveZero = brand.getMarketShareMat().getCurrentPeriod() > Configurator.getMarketShareMatLimit()
                        && product.getPricesMat().getCurrentPeriod() != 0;
                if (product.getPricesMat().isChangeSignificant() && isMarketShareAboveLimitAndCurrentPriceAboveZero) {
                    conclusions.add(createStatementAboutPrices(product, Period.MAT, product.getPricesMat()));
                }
                if (product.getPricesMonthly().isChangeSignificant() && isMarketShareAboveLimitAndCurrentPriceAboveZero) {
                    conclusions.add(createStatementAboutPrices(product, Period.MONTH, product.getPricesMonthly()));
                }
            }
        }
        return addCommentIfEmpty(conclusions);
    }

    private String createStatementAboutPrices(Brand.Product product, Period period, DoublesToCompare pricesInGivenPeriod) {
        return product + chooseStatement(period, pricesInGivenPeriod.getPercentageDifference())
                + currencyFormat.format(pricesInGivenPeriod.getPreviousPeriod())
                + " na " + currencyFormat.format(pricesInGivenPeriod.getCurrentPeriod())
                + ", czyli o " + currencyFormat.format(pricesInGivenPeriod.getValueDifference())
                + ", co stanowi " + percentageFormat.format(pricesInGivenPeriod.getPercentageDifference()) + ".";
    }

    private List<String> analyseDistribution(List<Brand> brands) {
        List<String> conclusions = new ArrayList<>();
        for (Brand brand : brands) {
            List<Brand.Product> products = brand.getProducts();
            for (Brand.Product product : products) {
                if (product.getDistributionMat().isChangeSignificant()) {
                    conclusions.add(createStatementAboutDistribution(product, Period.MAT, product.getDistributionMat()));
                }
                if (product.getDistributionMonthly().isChangeSignificant()) {
                    conclusions.add(createStatementAboutDistribution(product, Period.MONTH, product.getDistributionMonthly()));
                }
            }
        }
        return addCommentIfEmpty(conclusions);
    }

    private String createStatementAboutDistribution(Brand.Product product, Period period, DoublesToCompare distributionInGivenPeriod) {
        return product + chooseStatement(period, distributionInGivenPeriod.getPercentageDifference())
                + percentageFormat.format(distributionInGivenPeriod.getPreviousPeriod())
                + " na " + percentageFormat.format(distributionInGivenPeriod.getCurrentPeriod()) + ".";
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

    private List<String> addCommentIfEmpty(List<String> conclusions) {
        if (conclusions.isEmpty()) {
            conclusions.add("Brak zmian w podanym zakresie.");
        }
        return conclusions;
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