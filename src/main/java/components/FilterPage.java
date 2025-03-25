package components;

import helpers.Waits;
import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Slf4j
public class FilterPage extends BasePage {

    @FindBy(css = "div[class=\"overlay__inner\"")
    private WebElement overlay;

    @FindAll(@FindBy(css = "ul[class=\"category-sub-menu\"] li"))
    private List<WebElement> category;

    @FindAll(@FindBy(css = "a[class=\"_gray-darker search-link js-search-link\"]"))
    private List<WebElement> filterBy;

    public FilterPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickCategory(String categoryName) {
        Allure.step("Click category: " + categoryName);
        category
                .stream()
                .filter(c -> c.getText().equalsIgnoreCase(categoryName))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    public void clickFilter(String filterName) {
        Waits.waitToLoad(overlay, driver);
        Allure.step("Click filter: " + filterName);
        filterBy
                .stream()
                .filter(filter -> filter.getText().contains(filterName))
                .findFirst()
                .ifPresent(WebElement::click);
    }
}
