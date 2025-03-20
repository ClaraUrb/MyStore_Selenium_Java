package components;

import helpers.StringUtils;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import models.Product;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

@Slf4j
public class ProductPage {
    private WebDriver driver;
    private Select select;

    @FindBy(css = "h1[itemprop=\"name\"]")
    private WebElement productName;

    @FindBy(className = "add-to-cart")
    private WebElement addToCart;

    @FindBy(css = "span[itemprop=\"price\"]")
    private WebElement price;

    @FindBy(id = "group_1")
    private WebElement sizeDropdown;

    @FindBy(id = "group_3")
    private WebElement dimensionsDropdown;

    @FindBy(id = "group_4")
    private WebElement paperTypeDropdown;

    @FindAll(@FindBy(className = "input-color"))
    private List<WebElement> colors;

    @FindBy(id = "quantity_wanted")
    private WebElement quantity;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getProductName() {
        return productName.getText();
    }

    @Step("Add item to cart")
    public void addItemToCart() {
        addToCart.click();
    }

    public double getPrice() {
        return StringUtils.priceFormatter(price.getText());
    }

    public void setSize(String size) {
        Allure.step("Set size to: " + size);
        sizeDropdown.click();
        select = new Select(sizeDropdown);
        select.selectByVisibleText(size);
    }

    public String getSize() {
        select = new Select(sizeDropdown);
        return select.getFirstSelectedOption().getText();
    }

    public String getColor() {
        List<WebElement> elements = colors.stream().filter(WebElement::isSelected).toList();
        if (elements.size() > 1) {
            log.info("Something went wrong, too many colors");
            return null;
        } else if (elements.size() == 1) {
            return elements.getFirst().getAttribute("title");
        } else {
            return null;
        }
    }

    public String getDimension() {
        select = new Select(dimensionsDropdown);
        return select.getFirstSelectedOption().getText();
    }

    public String getPaperType() {
        select = new Select(paperTypeDropdown);
        return select.getFirstSelectedOption().getText();
    }

    public void setQuantity(int number) throws InterruptedException {
        Allure.step("Set quantity to: " + number);
        Thread.sleep(1000);
        quantity.clear();
        quantity.sendKeys(String.valueOf(number));
    }

    public int getQuantity() {
        return StringUtils.getIntFromString(quantity.getAttribute("value"));
    }

    public Product getProductInfo() {
        Product product = new Product();
        product.setName(getProductName());
        try {
            product.setSize(getSize());
        } catch (NoSuchElementException e) {
        }
        try {
            product.setColor(getColor());
        } catch (NoSuchElementException e) {
        }
        try {
            product.setDimension(getDimension());
        } catch (NoSuchElementException e) {
        }
        try {
            product.setPaperType(getPaperType());
        } catch (NoSuchElementException e) {
        }
        product.setOrderedQuantity(getQuantity());
        product.setPrice(getPrice());
        product.setTotalPrice(StringUtils.round(getQuantity() * getPrice()));
        return product;
    }
}


