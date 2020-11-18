package company;

import java.util.HashMap;

enum Currency {
    USD,
    EGP,
    PLN
}

public class ProductsCatalog {

    static HashMap<String, Double> catalogueInUSD;
    static HashMap<Currency, Double> currrencyRatesVsUSD;
    static HashMap<Currency, String> currrencySymbol;
    static double taxPercentage = 0.14;

    public ProductsCatalog() {
        catalogueInUSD = new HashMap<>();
        catalogueInUSD.put("T-shirt", 10.99);
        catalogueInUSD.put("Pants", 14.99);
        catalogueInUSD.put("Jacket", 19.99);
        catalogueInUSD.put("Shoes", 24.99);
        setCurrencyRates();
    }

    public void setCurrencyRates() {
        currrencyRatesVsUSD = new HashMap<>();
        currrencySymbol = new HashMap<>();
        currrencySymbol.put(Currency.USD, "$");
        currrencyRatesVsUSD.put(Currency.USD, 1.0);
        currrencyRatesVsUSD.put(Currency.EGP, 15.63);
        currrencySymbol.put(Currency.EGP, "E£");
        currrencyRatesVsUSD.put(Currency.PLN, 3.78);
        currrencySymbol.put(Currency.PLN, "zł");
    }

    public double getProductPriceInUSD(String product) {
        return catalogueInUSD.getOrDefault(product, 0.0);
    }

    public void addProductToCatalog(String productName, double valueInUSD) {
        catalogueInUSD.put(productName, valueInUSD);
    }

    public boolean checkIfProductExistsInCatalog(String productName) {
        if (catalogueInUSD.get(productName) == null) {
            return false;
        } else {
            return true;
        }
    }
}
