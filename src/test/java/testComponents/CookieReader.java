package testComponents;

import io.restassured.RestAssured;
import io.restassured.http.Cookies;

public class CookieReader {

    public static Cookies getCookies() {
        return RestAssured.get("http://146.59.32.4/index.php").getDetailedCookies();
    }
}
