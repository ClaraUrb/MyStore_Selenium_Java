package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import testComponents.BaseTest;

public class FilteringTest extends BaseTest {
    SoftAssert softAssert = new SoftAssert();
    @Test
    public void registerTest() throws InterruptedException {
        headerPage.openClothesPage();
        softAssert.assertEquals(filterPage.getNumberOfProducts(), 2);
        filterPage.clickWomenCategory();
        softAssert.assertEquals(filterPage.getNumberOfProducts(),1);
        String productName = filterPage.getFirstProductName();
        filterPage.openProductPage();
        softAssert.assertEquals(productPage.getProductName(), productName);

        headerPage.openAccessoriesPage();
        softAssert.assertEquals(filterPage.getNumberOfProducts(), 10);
        filterPage.clickCeramicFilter();
        Thread.sleep(1000);
        softAssert.assertEquals(filterPage.getNumberOfProducts(),4);
        filterPage.clickPolyesterFilter();
        Thread.sleep(1000);
        softAssert.assertEquals(filterPage.getNumberOfProducts(),7);
        filterPage.clickCeramicFilter();
        Thread.sleep(1000);
        filterPage.clickPolyesterFilter();
        Thread.sleep(1000);
        filterPage.clickRecycledCardboardFilter();
        Thread.sleep(1000);
        softAssert.assertEquals(filterPage.getNumberOfProducts(),3);

        softAssert.assertAll();
    }
}
