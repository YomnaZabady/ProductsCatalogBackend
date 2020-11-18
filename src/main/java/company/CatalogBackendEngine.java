package company;

import org.json.JSONObject;

public class CatalogBackendEngine {

    public static void main(String[] args) {
        ProductsCatalog productsCatalog = new ProductsCatalog();
        UserShoppingCart userShoppingCart = new UserShoppingCart(
                new String[]{"T-shirt",
                "T-shirt",
                "Shoes",
                "Jacket"},
                Currency.USD );

        JSONObject resultedBill = userShoppingCart.getDetailedBill();
        System.out.println(resultedBill);
    }
}
