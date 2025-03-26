package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import testComponents.BaseTest;

public class FilteringTests extends BaseTest {
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void filteringTest() {
        headerPage.openClothesPage();
        softAssert.assertEquals(homePage.getNumberOfProductsVisible(), 2);
        filterPage.clickCategory("Women");
        softAssert.assertEquals(homePage.getNumberOfProductsVisible(), 1);

        headerPage.openAccessoriesPage();
        softAssert.assertEquals(homePage.getNumberOfProductsVisible(), 10);
        filterPage.clickFilter("Ceramic");
        softAssert.assertEquals(homePage.getNumberOfProductsVisible(), 4);
        filterPage.clickFilter("Polyester");
        softAssert.assertEquals(homePage.getNumberOfProductsVisible(), 7);

        filterPage
                .clickFilter("Ceramic")
                .clickFilter("Polyester")
                .clickFilter("Recycled cardboard");
        softAssert.assertEquals(homePage.getNumberOfProductsVisible(), 3);

        softAssert.assertAll();
    }
}
