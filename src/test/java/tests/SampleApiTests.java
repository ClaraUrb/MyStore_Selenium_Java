package tests;

import org.testng.annotations.Test;
import testComponents.ApiBaseTests;

import static io.restassured.RestAssured.given;

public class SampleApiTests extends ApiBaseTests {

    @Test
    public void sampleApiTest() {
        given().spec(requestSpecBuilder.build())
                .queryParam("controller","authentication")
                .queryParam("back","my-account")
                .when().log().all()
                .body("back=my-account&email=roberto.armstrong@yahoo.com&password=fbqulbl848kpr4&submitLogin=1")
                .post("http://146.59.32.4/index.php")
                .then().statusCode(302).log().all();
    }
}
