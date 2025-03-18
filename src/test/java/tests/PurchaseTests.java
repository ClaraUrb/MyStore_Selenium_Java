package tests;

import helpers.DataSaver;
import helpers.NumberFormatter;
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
    public void purchaseAsLoggedUserTest() {
        headerPage.openSignInPage();
        user = signInPage.logInUsingExistingUser();
        myAccountPage.openAddressSection();
        addressPage.fillAddress();
        address = DataSaver.readAddressFromFile();
        softAssert.assertEquals(addressPage.getAddressBody(), address.toString());
        headerPage.openClothesPage();
        filterPage.clickCategory("Women");
        softAssert.assertEquals(homePage.getNumberOfProductsVisible(), 1);
        Product product = new Product();
        product.setName(homePage.getFirstProductName());
        homePage.openProductPage();
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

    @Test
    public void purchaseWithoutLoggingTest() {
        homePage.openAllProducts();
        homePage.getNumberOfAllProducts();
        List<String> specifiedProductNames = homePage.getProductNamesContainingString("BROWN BEAR");

        System.out.println(specifiedProductNames);

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

        DoubleStream stream = DoubleStream
                .of(NumberFormatter.priceFormatter(product.getTotalPrice()),
                        NumberFormatter.priceFormatter(product1.getTotalPrice()),
                        NumberFormatter.priceFormatter(product2.getTotalPrice()));
        double priceSum = stream.sum();
        softAssert.assertEquals(cartPage.getTotalPriceOfProducts(), "$" + NumberFormatter.round(priceSum));
        double shippingPrice = NumberFormatter.priceFormatter(cartPage.getShippingPrice());
        double totalPriceExpected = priceSum + shippingPrice;
        softAssert.assertEquals(cartPage.getTotalPrice(), "$" + NumberFormatter.round(totalPriceExpected));
        cartPage.proceedToCheckout();

        softAssert.assertAll();
    }
}
