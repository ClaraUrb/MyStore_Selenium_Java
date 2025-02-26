package components;

import helpers.Waits;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class FilterPage {
    private WebDriver driver;

    @FindAll(@FindBy(css = "[class='product-description'] a"))
    private List<WebElement> products;

    @FindBy(css = "[class='product-description'] a")
    private WebElement product;

    @FindAll(@FindBy(css = "ul[class=\"category-sub-menu\"] li"))
    private List<WebElement> category;

    @FindAll(@FindBy(css = "a[class=\"_gray-darker search-link js-search-link\"]"))
    private List<WebElement> filterBy;

    @FindBy(css = "div[class=\"overlay__inner\"")
    private WebElement overlay;

    public FilterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int getNumberOfProducts() {
        Waits.waitToLoad(overlay, driver);
        return products.size();
    }

    public List<String> getAllProductsNames() {
        return products.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public String getFirstProductName() {
        return product.getText();
    }

    public void clickCategory(String categoryName) {
        category
                .stream()
                .filter(c -> c.getText().equalsIgnoreCase(categoryName))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    public void clickFilter(String filterName) {
        Waits.waitToLoad(overlay, driver);
        filterBy
                .stream()
                .filter(filter -> filter.getText().contains(filterName))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    public void openProductPage() {
        product.click();
    }
}
