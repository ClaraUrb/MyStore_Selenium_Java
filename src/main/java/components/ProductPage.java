package components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ProductPage {
    private WebDriver driver;

    @FindBy(css = "h1[itemprop=\"name\"]")
    private WebElement productName;

    @FindBy(className = "add-to-cart")
    private WebElement addToCart;

    @FindBy(css = "span[itemprop=\"price\"]")
    private WebElement price;

    @FindBy(id = "group_1")
    private WebElement sizeDropdown;

    @FindBy(id = "quantity_wanted")
    private WebElement quantity;

    @FindBy(css = "button[class=\"btn btn-touchspin js-touchspin bootstrap-touchspin-up\"]")
    private WebElement quantityUp;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getProductName() {
        return productName.getText();
    }

    public void addItemToCart() {
        addToCart.click();
    }

    public String getPrice() {
        return price.getText();
    }

    public void setSize(String size) {
        sizeDropdown.click();
        Select select = new Select(sizeDropdown);
        select.selectByVisibleText(size);
    }

    public void setQuantity(int number) {
        for (int i = 1; i < number; i++) {
            quantityUp.click();
        }
    }

    public String getQuantity() {
        return quantity.getAttribute("value");
    }
}
