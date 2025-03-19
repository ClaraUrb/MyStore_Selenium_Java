package components;

import helpers.StringUtils;
import helpers.Waits;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class HomePage {
    private WebDriver driver;

    @FindBy(className = "all-product-link")
    private WebElement allProducts;

    @FindAll(@FindBy(css = "[class='product-description'] a"))
    private List<WebElement> products;

    @FindBy(css = "[class='product-description'] a")
    private WebElement product;

    @FindBy(css = "div[class=\"overlay__inner\"")
    private WebElement overlay;

    @FindBy(css = "div[class=\"col-md-6 hidden-sm-down total-products\"]")
    private WebElement allProductsNumber;

    @FindBy(css = "a[class=\"next js-search-link\"]")
    private WebElement nextPage;

    @FindBy(css = "a[class=\"previous js-search-link\"]")
    private WebElement previousPage;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openAllProducts() {
        allProducts.click();
    }

    public int getNumberOfAllProducts() {
        Waits.waitToLoad(overlay, driver);
        return StringUtils.getIntFromString(allProductsNumber.getText());
    }

    public int getNumberOfProductsVisible() {
        Waits.waitToLoad(overlay, driver);
        return products.size();
    }

    public String[] getAllProductsNames() {
        String[] productsList = products.stream().map(WebElement::getText).toList().toArray(new String[0]);
        String[] productsListFinal;
        if (productsList.length != getNumberOfAllProducts()) {
            nextPage.click();
            Waits.waitToLoad(overlay, driver);
            productsListFinal = ArrayUtils.addAll(productsList, products.stream().map(WebElement::getText).toList().toArray(new String[0]));
            previousPage.click();
        } else {
            productsListFinal = productsList;
        }
        return productsListFinal;
    }

    public List<String> getProductNamesContainingString(String string) {
        String[] productList = getAllProductsNames();
        return Arrays.stream(productList).filter(product -> product.contains(string)).toList();
    }

    public void openProductPage() {
        product.click();
    }

    public void openProductPage(String string) {
        Waits.waitToLoad(overlay, driver);
        if (products.stream().noneMatch(product -> product.getText().equalsIgnoreCase(string))) {
            nextPage.click();
            Waits.waitToLoad(overlay, driver);
        }
        products.stream().filter(product -> product.getText().equalsIgnoreCase(string)).findFirst().ifPresent(WebElement::click);
    }
}
