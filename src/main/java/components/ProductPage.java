package components;

import helpers.NumberFormatter;
import helpers.StringUtils;
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

    public String getSize() {
        return sizeDropdown.getText();
    }

    public String getColor() {
        List<WebElement> elements = colors.stream().filter(WebElement::isSelected).toList();
        System.out.println(elements.size());
        if (elements.size() > 1) {
            log.info("Something went wrong with color");
            return null;
        } else if (elements.size() == 1) {
            return elements.getFirst().getAttribute("title");
        } else {
            return null;
        }
    }

    public String getDimension() {
        Select select = new Select(dimensionsDropdown);
        return select.getFirstSelectedOption().getText();
    }

    public String getPaperType() {
        Select select = new Select(paperTypeDropdown);
        return select.getFirstSelectedOption().getText();
    }

    public void setQuantity(int number) {
        quantity.clear();
        quantity.sendKeys(String.valueOf(number));
    }

    public String getQuantity() {
        return quantity.getAttribute("value");
    }

    public Product getProductInfo() {
        Product product = new Product();
        product.setName(getProductName());
        try {
            product.setSize(getSize());
        } catch (NoSuchElementException e) {
            product.setSize(null);
        }
        try {
            product.setColor(getColor());
        } catch (NoSuchElementException e) {
            product.setColor(null);
        }
        try {
            product.setDimension(getDimension());
        } catch (NoSuchElementException e) {
            product.setDimension(null);
        }
        try {
            product.setPaperType(getPaperType());
        } catch (NoSuchElementException e) {
            product.setPaperType(null);
        }
        product.setOrderedQuantity(Integer.parseInt(getQuantity()));
        product.setPrice(getPrice());

        double totalPrice = StringUtils.getIntFromString(getQuantity()) * NumberFormatter.priceFormatter(getPrice());
        product.setTotalPrice("$" + NumberFormatter.round(totalPrice));
        return product;
    }
}


