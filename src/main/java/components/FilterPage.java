package components;

import helpers.Waits;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FilterPage {
    private WebDriver driver;

    Waits wait;

    @FindAll(@FindBy(css = "[class='product-description'] a"))
    private List<WebElement> products;

    @FindBy(css = "[class='product-description'] a")
    private WebElement product;

    @FindBy(xpath = "//a[text()=\"Women\"]")
    private WebElement womenCategory;

    @FindBy(xpath = "//a[contains(text(),\"Ceramic\")]/preceding-sibling::span")
    public WebElement ceramicFilter;

    @FindBy(xpath = "//a[contains(text(),\"Polyester\")]/preceding-sibling::span")
    public WebElement polyesterFilter;

    @FindBy(xpath = "//a[contains(text(),\"Recycled cardboard\")]/preceding-sibling::span")
    public WebElement recycledCardboardFilter;

    public FilterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new Waits(driver);
        PageFactory.initElements(driver, this);
    }

    public int getNumberOfProducts() {
        int numberOfProducts = 0;
        wait.waitToLoad();
        for (WebElement product : products) {
            numberOfProducts++;
        }
        log.info("Number of products: {}", numberOfProducts);
        return numberOfProducts;
    }

    public List<String> getAllProductsNames() {
        List<String> productNames = new ArrayList<>();
        for (WebElement product : products) {
            productNames.add(product.getText());
            log.info("Product name is: {}", product.getText());
        }
        return productNames;
    }

    public String getFirstProductName() {
        return product.getText();
    }

    public void clickWomenCategory() {
        womenCategory.click();
    }

    public void clickFilter(WebElement filter) {
        wait.waitToLoad();
        filter.click();
    }

    public void openProductPage() {
        product.click();
    }
}
