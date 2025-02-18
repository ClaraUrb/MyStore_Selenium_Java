package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import testComponents.BaseTest;

public class FilteringTest extends BaseTest {
    SoftAssert softAssert = new SoftAssert();
    @Test
    public void filteringTest() {
        headerPage.openClothesPage();
        softAssert.assertEquals(filterPage.getNumberOfProducts(), 2);
        filterPage.clickWomenCategory();
        softAssert.assertEquals(filterPage.getNumberOfProducts(),1);
        String productName = filterPage.getFirstProductName();
        filterPage.openProductPage();
        softAssert.assertEquals(productPage.getProductName(), productName);

        headerPage.openAccessoriesPage();
        softAssert.assertEquals(filterPage.getNumberOfProducts(), 10);
        filterPage.clickFilter(filterPage.ceramicFilter);

        softAssert.assertEquals(filterPage.getNumberOfProducts(),4);
        filterPage.clickFilter(filterPage.polyesterFilter);

        softAssert.assertEquals(filterPage.getNumberOfProducts(),7);
        filterPage.clickFilter(filterPage.ceramicFilter);

        filterPage.clickFilter(filterPage.polyesterFilter);

        filterPage.clickFilter(filterPage.recycledCardboardFilter);

        softAssert.assertEquals(filterPage.getNumberOfProducts(),3);

        softAssert.assertAll();
    }
}
