package company;

import java.util.HashMap;

/*
Available offers:
----------------
- Shoes are on 10% off.
- Buy two t-shirts and get a jacket half its price.
*/

public class ProductsOffers {

    double shoesDiscount;
    double tshirtsJacketDiscount;

    public boolean checkAndApplyShoesOffer(HashMap<String,Integer> userShoppingList) {
        if (userShoppingList.get("Shoes") != null) {
            double shoesPrice = ProductsCatalog.catalogueInUSD.get("Shoes");
            shoesDiscount = userShoppingList.get("Shoes") * shoesPrice * 0.10;
            return true;
        }
        return false;
    }

    public boolean checkAndApply2TShirtsJacketOffer(HashMap<String,Integer> userShoppingList) {
        if (userShoppingList.get("T-shirt") != null
                && userShoppingList.get("T-shirt") >= 2
                && userShoppingList.get("Jacket") !=null) {
            double jacketPrice = ProductsCatalog.catalogueInUSD.get("Jacket");
            int numOfAvailableJacketsDiscounts = userShoppingList.get("T-shirt")/2;
            int numOfJacketsDiscounts = userShoppingList.get("Jacket") / numOfAvailableJacketsDiscounts;
            tshirtsJacketDiscount = numOfJacketsDiscounts * jacketPrice * 0.5;
            return true;
        }
        return false;
    }

}
