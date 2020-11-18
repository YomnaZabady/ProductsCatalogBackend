# ProductsCatalogBackend
Yashry Backend Assignment

The aim of this project is to generate a detailed bill for the user shopping cart.

To use this program, you need to do the following steps:
-------------------------------------------------------
1- In the CatalogBackendEngine class, in the main function, define a new instance of the ProductsCatalog, in which it defines the in-memory database of the products we have in the system
2- Create a new instance of UserShoppingCart with the define string array of the products and the currency used, for now 3 different currencies are available (USD -> dollar, EGP -> egyptian pound and PLN -> polish zloty)
3- Call the getDetailedBill function of the UserShoppingCart instance and the detailed bill will be generated in the JSON format included in it the product offers, tax and the discounts.

This project contains 4 classes; each representing specific required entity:
----------------------------------------------------------------------------
1- ProductsCatalog -> where products are defined with their prices in USD along with different currencies and their corresponding symbol and rates.
2- UserShoppingCart -> where the user shopping cart is defined with calculating the prices and the total values. It includes the calculation of the taxes abd the get detailed bill function which get the products offers from the ProductOffers class
3- ProductsOffers -> Defining the product offers and their corresponding logic and calculations.
4- CatalogBackendEngine -> This is the engine class and where the main function exist.
