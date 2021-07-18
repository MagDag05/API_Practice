package helper;

import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AuthCookie {

    public static Cookies getByCredentials(String login, String password) {

        RequestSpecification httpRequest = RestAssured.given();
        httpRequest
                .formParam("login", login)
                .formParam("password", password);

        Response response = httpRequest.post("https://playground.learnqa.ru/ajax/api/get_auth_cookie");

        return response.getDetailedCookies();

    }

    public static String check(Cookies cookies) {

        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.cookies(cookies);

        Response response = httpRequest.post("https://playground.learnqa.ru/ajax/api/check_auth_cookie");

        if (!response.getBody().asString().equalsIgnoreCase("You are NOT authorized")) {
            return response.getBody().asString();
        } else {
            return "";
        }

    }

}
