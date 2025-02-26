package tests;

import helpers.DataSaver;
import helpers.NumberFormatter;
import helpers.StringUtils;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import testComponents.BaseTest;

public class PurchaseTests extends BaseTest {
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void purchaseTest() {
        headerPage.openSignInPage();
        user = signInPage.logInUsingExistingUser();
        myAccountPage.openAddressSection();
        addressPage.fillAddress();
        address = DataSaver.readAddressFromFile();
        softAssert.assertEquals(addressPage.getAddressBody(), address.toString());
        headerPage.openClothesPage();
        filterPage.clickCategory("Women");
        softAssert.assertEquals(filterPage.getNumberOfProducts(), 1);
        String productName = filterPage.getFirstProductName();
        filterPage.openProductPage();
        softAssert.assertEquals(productPage.getProductName(), productName);
        String price = productPage.getPrice();
        double priceFormatted = NumberFormatter.priceFormatter(price);
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
        double shippingPrice = NumberFormatter.priceFormatter(cartPage.getShippingPrice());
        double totalPriceExpected = priceFormatted * quantity + shippingPrice;
        softAssert.assertEquals(cartPage.getTotalPrice(), "$" + totalPriceExpected);
        cartPage.proceedToCheckout();

        softAssert.assertEquals(cartPage.getAddress(), address.toString());
        cartPage.confirmAddress();
        cartPage.setShippingMethod("Lorem ipsum");
        cartPage.finalizeOrder();
        softAssert.assertEquals(StringUtils.removeFirstChar(cartPage.getConfirmationText()), "YOUR ORDER IS CONFIRMED");

        softAssert.assertAll();
    }
}
