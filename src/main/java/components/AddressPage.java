package components;

import factories.AddressFactory;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import models.Address;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddressPage {
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

    public AddressPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Fill address")
    public Address fillAddress() {
        Address address = AddressFactory.createRandomAddress();
        Allure.addAttachment("Address: ", address.toString());
        addressFirstLine.sendKeys(address.getAddressFirstLine());
        city.sendKeys(address.getCity());
        state.click();
        select = new Select(state);
        select.selectByVisibleText(address.getState());
        zipCode.sendKeys(address.getZipCode());
        saveButton.click();
        return address;
    }

    public String getAddressBody() {
        return addressBody.getText();
    }
}
