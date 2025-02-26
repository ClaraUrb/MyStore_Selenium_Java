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
        product.setName(filterPage.getFirstProductName());
        filterPage.openProductPage();
        softAssert.assertEquals(productPage.getProductName(), product.getName());
        product.setPrice(productPage.getPrice());
        double priceFormatted = NumberFormatter.priceFormatter(product.getPrice());
        product.setSize("M");
        productPage.setSize(product.getSize());
        softAssert.assertEquals(productPage.getQuantity(), "1");
        product.setOrderedQuantity(3);
        productPage.setQuantity(product.getOrderedQuantity());
        softAssert.assertEquals(productPage.getQuantity(), "3");

        productPage.addItemToCart();
        cartModal.proceedToCheckout();

        softAssert.assertEquals(cartPage.getProductName(), product.getName());
        softAssert.assertEquals(cartPage.getUnitPrice(), product.getPrice());
        softAssert.assertEquals(cartPage.getSize(), product.getSize());
        softAssert.assertEquals(cartPage.getQuantity(), String.valueOf(product.getOrderedQuantity()));
        softAssert.assertEquals(cartPage.getTotalProductPrice(), "$" + priceFormatted * product.getOrderedQuantity());
        softAssert.assertEquals(cartPage.getNumberOfItems(), String.valueOf(product.getOrderedQuantity()) + " items");
        softAssert.assertEquals(cartPage.getTotalPriceOfProducts(), "$" + priceFormatted * product.getOrderedQuantity());
        double shippingPrice = NumberFormatter.priceFormatter(cartPage.getShippingPrice());
        double totalPriceExpected = priceFormatted * product.getOrderedQuantity() + shippingPrice;
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
