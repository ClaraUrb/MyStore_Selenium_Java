package components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
    private WebDriver driver;

    @FindBy(css = "div[class=\"product-line-info\"] a")
    private WebElement productName;

    @FindBy(css = "span[class=\"price\"]")
    private WebElement unitPrice;

    @FindBy(css = "div[class=\"product-line-info size\"] span[class=\"value\"]")
    private WebElement size;

    @FindBy(css = "input[type=\"number\"]")
    private WebElement quantity;

    @FindBy(tagName = "strong")
    private WebElement productPrice;

    @FindBy(css = "span[class=\"label js-subtotal\"]")
    private WebElement numberOfItems;

    @FindBy(css = "div[id=\"cart-subtotal-products\"] span[class=\"value\"]")
    private WebElement totalProductPrice;

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

    public String getProductName() {
        return productName.getText();
    }

    public String getUnitPrice() {
        return unitPrice.getText();
    }

    public String getSize() {
        return size.getText();
    }

    public String getQuantity() {
        return quantity.getAttribute("value");
    }

    public String getTotalProductPrice() {
        return productPrice.getText();
    }

    public String getNumberOfItems() {
        return numberOfItems.getText();
    }

    public String getTotalPriceOfProducts() {
        return totalProductPrice.getText();
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
}
