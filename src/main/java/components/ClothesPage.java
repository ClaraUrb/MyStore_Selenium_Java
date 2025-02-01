package components;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ClothesPage {
    private WebDriver driver;

    @FindAll(@FindBy(css = "[class='product-description'] a"))
    private List<WebElement> products;

    @FindBy(xpath = "//a[text()=\"Women\"]")
    private WebElement womenCategory;

    public ClothesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<String> getAllProductsNames() {
        List<String> productNames = new ArrayList<>();
        for (WebElement product : products) {
            productNames.add(product.getText());
            log.info("Product name is: " + product.getText());
        }
        return productNames;
    }

    public void clickWomenCategory() {
        womenCategory.click();
    }
}
