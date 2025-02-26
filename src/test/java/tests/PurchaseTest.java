package tests;

import helpers.DataSaver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import testComponents.BaseTest;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class PurchaseTest extends BaseTest {
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void purchaseTest() {
        headerPage.openSignInPage();
        user = signInPage.logInUsingExistingUser();
        myAccountPage.openAddressSection();
        addressPage.fillAddress();
        address = DataSaver.readAddressFromFile();
        System.out.println(address.toString());
        softAssert.assertEquals(addressPage.getAddressBody(), address.toString());
        headerPage.openClothesPage();
        filterPage.clickCategory("Women");
        softAssert.assertEquals(filterPage.getNumberOfProducts(), 1);
        String productName = filterPage.getFirstProductName();
        filterPage.openProductPage();
        softAssert.assertEquals(productPage.getProductName(), productName);
        String price = "$28.72";
        double priceFormatted = Double.parseDouble(price.replace("$", ""));
        softAssert.assertEquals(productPage.getPrice(), price);
        String size = "M";
        productPage.setSize(size);
        softAssert.assertEquals(productPage.getQuantity(), "1");
        int quantity = 3;
        productPage.setQuantity(quantity);
        softAssert.assertEquals(productPage.getQuantity(), "3");

        productPage.addItemToCart();
        cartModal.proceedToCheckout();

        softAssert.assertEquals(cartPage.getProductName(), productName);
        softAssert.assertEquals(cartPage.getUnitPrice(), price);
        softAssert.assertEquals(cartPage.getSize(), size);
        softAssert.assertEquals(cartPage.getQuantity(), String.valueOf(quantity));
        softAssert.assertEquals(cartPage.getTotalProductPrice(), "$" + priceFormatted * quantity);
        softAssert.assertEquals(cartPage.getNumberOfItems(), String.valueOf(quantity) + " items");
        softAssert.assertEquals(cartPage.getTotalPriceOfProducts(), "$" + priceFormatted * quantity);
        double shippingPrice = 7.00;
        DecimalFormat decimalFormat = new DecimalFormat("##.00", DecimalFormatSymbols.getInstance(Locale.US));
        softAssert.assertEquals(cartPage.getShippingPrice(), "$" + decimalFormat.format(shippingPrice));
        double totalPriceExpected = priceFormatted * quantity + shippingPrice;
        softAssert.assertEquals(cartPage.getTotalPrice(), "$" + totalPriceExpected);
        cartPage.proceedToCheckout();

        softAssert.assertEquals(cartPage.getAddress(), address.toString());
        cartPage.confirmAddress();
        cartPage.setShippingMethod("Lorem ipsum");
        cartPage.finalizeOrder();
        softAssert.assertEquals(cartPage.getConfirmationText().substring(1), "YOUR ORDER IS CONFIRMED");

        softAssert.assertAll();
    }
}
