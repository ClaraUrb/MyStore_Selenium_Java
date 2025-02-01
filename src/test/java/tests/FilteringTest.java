package tests;

import org.testng.annotations.Test;
import testComponents.BaseTest;

public class FilteringTest extends BaseTest {

    @Test
    public void registerTest() {
        headerPage.openClothesPage();
        clothesPage.getAllProductsNames();
        clothesPage.clickWomenCategory();
        clothesPage.getAllProductsNames();
    }
}
