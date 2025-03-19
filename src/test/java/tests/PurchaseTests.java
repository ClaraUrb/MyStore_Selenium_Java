package tests;

import helpers.DataSaver;
import helpers.StringUtils;
import models.Product;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import testComponents.BaseTest;

import java.util.List;
import java.util.stream.DoubleStream;

public class PurchaseTests extends BaseTest {
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void purchaseAsLoggedUserTest() throws InterruptedException {
        headerPage.openSignInPage();
        user = signInPage.logInUsingExistingUser();
        myAccountPage.openAddressSection();
        addressPage.fillAddress();
        address = DataSaver.readAddressFromFile();
        softAssert.assertEquals(addressPage.getAddressBody(), address.toString());
        headerPage.openClothesPage();
        filterPage.clickCategory("Women");
        softAssert.assertEquals(homePage.getNumberOfProductsVisible(), 1);
        homePage.openProductPage();
        productPage.setSize("M");
        productPage.setQuantity(3);
        softAssert.assertEquals(productPage.getQuantity(), 3);
        Product product = productPage.getProductInfo();
        productPage.addItemToCart();
        cartModal.proceedToCheckout();

        Product cartProduct = cartPage.getProductInfo(product.getName());
        softAssert.assertEquals(cartProduct, product);
        softAssert.assertEquals(cartPage.getTotalProductPrice(), StringUtils.round(product.getPrice() * product.getOrderedQuantity()));
        softAssert.assertEquals(cartPage.getNumberOfItems(), product.getOrderedQuantity() + " items");
        softAssert.assertEquals(cartPage.getTotalPriceOfProducts(), StringUtils.round(product.getPrice() * product.getOrderedQuantity()));
        double totalPriceExpected = product.getPrice() * product.getOrderedQuantity() + cartPage.getShippingPrice();
        softAssert.assertEquals(cartPage.getTotalPrice(), totalPriceExpected);
        cartPage.proceedToCheckout();

        softAssert.assertEquals(cartPage.getAddress(), address.toString());
        cartPage.confirmAddress();
        cartPage.setShippingMethod("Lorem ipsum");
        cartPage.finalizeOrder();
        softAssert.assertEquals(StringUtils.removeFirstChar(cartPage.getConfirmationText()), "YOUR ORDER IS CONFIRMED");

        softAssert.assertAll();
    }

    @Test
    public void purchaseWithoutLoggingTest() {
        homePage.openAllProducts();
        homePage.getNumberOfAllProducts();
        List<String> specifiedProductNames = homePage.getProductNamesContainingString("BROWN BEAR");

        homePage.openProductPage(specifiedProductNames.get(0));
        Product product = productPage.getProductInfo();
        productPage.addItemToCart();
        cartModal.continueShopping();
        driver.navigate().back();

        homePage.openProductPage(specifiedProductNames.get(1));
        Product product1 = productPage.getProductInfo();
        productPage.addItemToCart();
        cartModal.continueShopping();
        driver.navigate().back();

        homePage.openProductPage(specifiedProductNames.get(2));
        Product product2 = productPage.getProductInfo();
        productPage.addItemToCart();
        cartModal.proceedToCheckout();

        softAssert.assertEquals(cartPage.getProductInfo(product.getName()), product);
        softAssert.assertEquals(cartPage.getProductInfo(product1.getName()), product1);
        softAssert.assertEquals(cartPage.getProductInfo(product2.getName()), product2);

        int numberOfItems = product.getOrderedQuantity() + product1.getOrderedQuantity() + product2.getOrderedQuantity();
        softAssert.assertEquals(cartPage.getNumberOfItems(), numberOfItems + " items");

        DoubleStream doubleStream = DoubleStream.of(product.getTotalPrice(), product1.getTotalPrice(), product2.getTotalPrice());
        double priceSum = doubleStream.sum();
        softAssert.assertEquals(cartPage.getTotalPriceOfProducts(), StringUtils.round(priceSum));
        double shippingPrice = cartPage.getShippingPrice();
        double totalPriceExpected = priceSum + shippingPrice;
        softAssert.assertEquals(cartPage.getTotalPrice(), StringUtils.round(totalPriceExpected));
        cartPage.proceedToCheckout();

        softAssert.assertAll();
    }
}
