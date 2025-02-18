package components;

import helpers.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartModal {

    private WebDriver driver;

    @FindBy(css = "a[class=\"btn btn-primary\"]")
    private WebElement proceedToCheckout;

    public CartModal(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void proceedToCheckout() {
        Waits wait = new Waits(driver);
        wait.waitForElementToAppear(proceedToCheckout);
        proceedToCheckout.click();
    }
}
