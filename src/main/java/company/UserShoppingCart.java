package company;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UserShoppingCart {

    int userId;
    HashMap<String, Integer> userShoppingCart;
    Currency userCurrency;
    double subTotalvalue;
    double taxValue;
    double totalWzAddedTax;

    public UserShoppingCart() {
        userShoppingCart = new HashMap<String, Integer>();
        subTotalvalue = 0.0;
        totalWzAddedTax = 0.0;
        taxValue= 0.0;
    }

    public UserShoppingCart(String[] products, Currency curr) {
        userShoppingCart = new HashMap<String, Integer>();
        for (String product: products) {
            if (userShoppingCart.get(product) == null) {
                userShoppingCart.put(product,1);
            } else {
                int prouductCnt = userShoppingCart.get(product);
                prouductCnt++;
                userShoppingCart.put(product, prouductCnt);
            }
        }
        userCurrency = curr;
        subTotalvalue = 0.0;
        totalWzAddedTax = 0.0;
        taxValue= 0.0;
    }

    public void addShoppingItem(String product) {
        if (userShoppingCart.get(product) == null) {
            userShoppingCart.put(product,1);
        } else {
            int prouductCnt = userShoppingCart.get(product);
            prouductCnt++;
            userShoppingCart.put(product, prouductCnt);
        }
    }

    public double calculateShoppingCartSubtotal(){
        Iterator<Map.Entry<String, Integer>> it = userShoppingCart.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<String, Integer> entry = (Map.Entry<String, Integer> ) it.next();
            subTotalvalue += ProductsCatalog.catalogueInUSD.get(entry.getKey()) * entry.getValue();
        }
        return subTotalvalue * ProductsCatalog.currrencyRatesVsUSD.get(userCurrency);
    }

    public double addTaxPercentageToTotal() {
        taxValue = (subTotalvalue * ProductsCatalog.taxPercentage);
        totalWzAddedTax = subTotalvalue + taxValue;
        return totalWzAddedTax * ProductsCatalog.currrencyRatesVsUSD.get(userCurrency);
    }
/*
Subtotal: $66.96
Taxes: $9.37
Discounts:
	10% off shoes: -$2.499
	50% off jacket: -$9.995
Total: $63.8404
 */
    public JSONObject getDetailedBill() {
        calculateShoppingCartSubtotal();
        addTaxPercentageToTotal();
        ProductsOffers prodOff = new ProductsOffers();
        JSONObject discountsObj = new JSONObject();
        String currencySymbol = ProductsCatalog.currrencySymbol.get(userCurrency);
        double discountsValue = 0.0;
        boolean offersExist = false;

        if (prodOff.checkAndApplyShoesOffer(userShoppingCart)) {
            discountsValue += prodOff.shoesDiscount;
            discountsObj.put("10% off shoes: ", currencySymbol + prodOff.shoesDiscount*-1);
            offersExist = true;
        }
        if (prodOff.checkAndApply2TShirtsJacketOffer(userShoppingCart)) {
            discountsValue += prodOff.tshirtsJacketDiscount;
            discountsObj.put("50% off jacket: ", currencySymbol + prodOff.tshirtsJacketDiscount*-1);
            offersExist= true;
        }

        JSONObject jObj = new JSONObject();
        jObj.put("Subtotal: ", currencySymbol + subTotalvalue);
        jObj.put("Taxes: ", currencySymbol + taxValue);

        if (offersExist) {
            jObj.put("Discounts:", discountsObj);
        }

        jObj.put("Total: ", currencySymbol + (totalWzAddedTax - discountsValue));
        return jObj;
    }
}
