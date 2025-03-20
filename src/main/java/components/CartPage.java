package components;

import helpers.StringUtils;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
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

    private By totalProductPrice = By.cssSelector("div[id=\"cart-subtotal-products\"] span[class=\"value\"]");

    @FindBy(css = "span[class=\"label js-subtotal\"]")
    private WebElement numberOfItems;

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

    public double getTotalProductPrice() {
        return StringUtils.priceFormatter(driver.findElement(productPrice).getText());
    }

    public String getNumberOfItems() {
        return numberOfItems.getText();
    }

    public double getTotalPriceOfProducts() {
        return StringUtils.priceFormatter(driver.findElement(totalProductPrice).getText());
    }

    public double getShippingPrice() {
        return StringUtils.priceFormatter(shippingPrice.getText());
    }

    public double getTotalPrice() {
        return StringUtils.priceFormatter(totalPrice.getText());
    }

    @Step("Proceed to checkout")
    public void proceedToCheckout() {
        proceedToCheckoutButton.click();
    }

    public String getAddress() {
        return address.getText();
    }

    @Step("Confirm address")
    public void confirmAddress() {
        confirmAddressButton.click();
    }

    public void setShippingMethod(String comment) {
        Allure.step("Set shipping method to: " + comment);
        deliveryMessage.sendKeys(comment);
        confirmDeliveryButton.click();
    }

    @Step("Finalize order")
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
        cartProduct.setPrice(StringUtils.priceFormatter(cartItem.findElement(unitPrice).getText()));
        cartProduct.setOrderedQuantity(Integer.parseInt(cartItem.findElement(quantity).getAttribute("value")));
        cartProduct.setTotalPrice(StringUtils.priceFormatter(cartItem.findElement(productPrice).getText()));

        if (!cartItem.findElements(color).isEmpty()) {
            cartProduct.setColor(cartItem.findElement(color).getText());
        }
        if (!cartItem.findElements(size).isEmpty()) {
            cartProduct.setSize(cartItem.findElement(size).getText());
        }
        if (!cartItem.findElements(dimension).isEmpty()) {
            cartProduct.setDimension(cartItem.findElement(dimension).getText());
        }
        if (!cartItem.findElements(paperType).isEmpty()) {
            cartProduct.setPaperType(cartItem.findElement(paperType).getText());
        }
        return cartProduct;
    }
}
