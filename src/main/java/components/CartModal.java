package components;

import helpers.Waits;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartModal {

    private WebDriver driver;

    @FindBy(css = "a[class=\"btn btn-primary\"]")
    private WebElement proceedToCheckout;

    @FindBy(css = "button[class=\"btn btn-secondary\"]")
    private WebElement continueShopping;

    public CartModal(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Proceed to checkout")
    public void proceedToCheckout() {
        Waits.waitForElementToAppear(proceedToCheckout, driver);
        proceedToCheckout.click();
    }

    @Step("Continue shopping")
    public void continueShopping() {
        Waits.waitForElementToAppear(proceedToCheckout, driver);
        continueShopping.click();
    }
}
