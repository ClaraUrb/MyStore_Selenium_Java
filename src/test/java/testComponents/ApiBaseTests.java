package testComponents;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;

public class ApiBaseTests extends CookieReader {
    protected RequestSpecBuilder requestSpecBuilder;

    @BeforeMethod
    public void setup() {
        requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("http://146.59.32.4/index.php");
        requestSpecBuilder.setContentType(ContentType.URLENC);
        requestSpecBuilder.addCookies(getCookies());
    }
}
