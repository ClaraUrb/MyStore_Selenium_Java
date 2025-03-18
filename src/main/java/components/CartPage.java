package components;

import lombok.extern.slf4j.Slf4j;
import models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Slf4j
public class CartPage {
    private WebDriver driver;

    @FindAll(@FindBy(className = "cart-item"))
    private List<WebElement> cartItems;

    private By productName = By.cssSelector("div[class=\"product-line-info\"] a");

    private By unitPrice = By.cssSelector("span[class=\"price\"]");

    private By size = By.cssSelector("div[class=\"product-line-info size\"] span[class=\"value\"]");

    private By color = By.cssSelector("div[class=\"product-line-info color\"] span[class=\"value\"]");

    private By dimension = By.cssSelector("div[class=\"product-line-info dimension\"] span[class=\"value\"]");

    private By paperType = By.cssSelector("div[class=\"product-line-info paper type\"] span[class=\"value\"]");

    private By quantity = By.cssSelector("input[type=\"number\"]");

    private By productPrice = By.tagName("strong");

    @FindBy(css = "span[class=\"label js-subtotal\"]")
    private WebElement numberOfItems;

    private By totalProductPrice = By.cssSelector("div[id=\"cart-subtotal-products\"] span[class=\"value\"]");

    @FindBy(css = "div[id=\"cart-subtotal-shipping\"] span[class=\"value\"]")
    private WebElement shippingPrice;

    @FindBy(css = "div[class=\"cart-summary-line cart-total\"] span[class=\"value\"]")
    private WebElement totalPrice;

    @FindBy(css = "div[class=\"text-sm-center\"] a")
    private WebElement proceedToCheckoutButton;

    @FindBy(className = "address")
    private WebElement address;

    @FindBy(css = "button[name=\"confirm-addresses\"]")
    private WebElement confirmAddressButton;

    @FindBy(id = "delivery_message")
    private WebElement deliveryMessage;

    @FindBy(css = "button[name=\"confirmDeliveryOption\"]")
    private WebElement confirmDeliveryButton;

    @FindBy(id = "payment-option-1")
    private WebElement paymentOption;

    @FindBy(id = "conditions_to_approve[terms-and-conditions]")
    private WebElement termsAndConditions;

    @FindBy(css = "div[id=\"payment-confirmation\"] button")
    private WebElement placeOrderButton;

    @FindBy(css = "h3[class=\"h1 card-title\"]")
    private WebElement orderConfirmationText;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getTotalProductPrice() {
        return driver.findElement(productPrice).getText();
    }

    public String getNumberOfItems() {
        return numberOfItems.getText();
    }

    public String getTotalPriceOfProducts() {
        return driver.findElement(totalProductPrice).getText();
    }

    public String getShippingPrice() {
        return shippingPrice.getText();
    }

    public String getTotalPrice() {
        return totalPrice.getText();
    }

    public void proceedToCheckout() {
        proceedToCheckoutButton.click();
    }

    public String getAddress() {
        return address.getText();
    }

    public void confirmAddress() {
        confirmAddressButton.click();
    }

    public void setShippingMethod(String comment) {
        deliveryMessage.sendKeys(comment);
        confirmDeliveryButton.click();
    }

    public void finalizeOrder() {
        paymentOption.click();
        termsAndConditions.click();
        placeOrderButton.click();
    }

    public String getConfirmationText() {
        return orderConfirmationText.getText();
    }

    public Product getProductInfo(String name) {
        WebElement cartItem = cartItems
                .stream()
                .filter(item -> item.findElement(By.cssSelector("div[class=\"product-line-info\"] a")).getText().equalsIgnoreCase(name))
                .findFirst().get();
        Product cartProduct = new Product();
        cartProduct.setName(cartItem.findElement(productName).getText());
        cartProduct.setPrice(cartItem.findElement(unitPrice).getText());
        cartProduct.setOrderedQuantity(Integer.parseInt(cartItem.findElement(quantity).getAttribute("value")));
        cartProduct.setTotalPrice(cartItem.findElement(productPrice).getText());

        List<WebElement> elements = cartItem.findElements(color);
        if (!elements.isEmpty()) {
            cartProduct.setColor(cartItem.findElement(color).getText());
        }
        elements = cartItem.findElements(size);
        if (!elements.isEmpty()) {
            cartProduct.setSize(cartItem.findElement(size).getText());
        }
        elements = cartItem.findElements(dimension);
        if (!elements.isEmpty()) {
            cartProduct.setDimension(cartItem.findElement(dimension).getText());
        }
        elements = cartItem.findElements(paperType);
        if (!elements.isEmpty()) {
            cartProduct.setPaperType(cartItem.findElement(paperType).getText());
        }
        return cartProduct;
    }
}
