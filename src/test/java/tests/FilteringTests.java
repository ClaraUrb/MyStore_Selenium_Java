package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import testComponents.BaseTest;

public class FilteringTests extends BaseTest {
    SoftAssert softAssert = new SoftAssert();
    @Test
    public void filteringTest() {
        headerPage.openClothesPage();
        softAssert.assertEquals(filterPage.getNumberOfProducts(), 2);
        filterPage.clickCategory("Women");
        softAssert.assertEquals(filterPage.getNumberOfProducts(),1);

        headerPage.openAccessoriesPage();
        softAssert.assertEquals(filterPage.getNumberOfProducts(), 10);
        filterPage.clickFilter("Ceramic");
        softAssert.assertEquals(filterPage.getNumberOfProducts(),4);
        filterPage.clickFilter("Polyester");
        softAssert.assertEquals(filterPage.getNumberOfProducts(),7);

        filterPage.clickFilter("Ceramic");
        filterPage.clickFilter("Polyester");
        filterPage.clickFilter("Recycled cardboard");
        softAssert.assertEquals(filterPage.getNumberOfProducts(),3);

        softAssert.assertAll();
    }
}
