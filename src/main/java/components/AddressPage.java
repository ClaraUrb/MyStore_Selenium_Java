package components;

import factories.AddressFactory;
import io.qameta.allure.Allure;
import models.Address;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import static io.qameta.allure.Allure.step;

public class AddressPage extends BasePage {
    private WebDriver driver;
    Select select;

    @FindBy(name = "address1")
    private WebElement addressFirstLine;

    @FindBy(name = "city")
    private WebElement city;

    @FindBy(name = "id_state")
    private WebElement state;

    @FindBy(name = "postcode")
    private WebElement zipCode;

    @FindBy(name = "id_country")
    private WebElement country;

    @FindBy(css = "button[class=\"btn btn-primary form-control-submit float-xs-right\"]")
    private WebElement saveButton;

    @FindBy(css = "div[class=\"address-body\"] address")
    private WebElement addressBody;

    @FindBy(css = "a[data-link-action=\"delete-address\"] span")
    private WebElement deleteAddress;

    @FindBy(css = "a[data-link-action=\"add-address\"] span")
    private WebElement newAddress;

    public AddressPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public Address fillAddress() {
        Address address = AddressFactory.createRandomAddress();
        step("Fill address", () -> {
            Allure.addAttachment("Address: ", address.toString());
            try {
                addressFirstLine.sendKeys(address.getAddressFirstLine());
            } catch (NoSuchElementException e) {
                deleteAddress.click();
                newAddress.click();
                addressFirstLine.sendKeys(address.getAddressFirstLine());
            } finally {
                city.sendKeys(address.getCity());
                state.click();
                select = new Select(state);
                select.selectByVisibleText(address.getState());
                zipCode.sendKeys(address.getZipCode());
                saveButton.click();
            }
        });
        return address;
    }

    public String getAddressBody() {
        return addressBody.getText();
    }
}
