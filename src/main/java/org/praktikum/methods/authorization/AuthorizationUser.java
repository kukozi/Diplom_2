package org.praktikum.methods.authorization;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.praktikum.deserialization.user.ResponseBodyRegAndLogin;
import org.praktikum.serialization.AuthorizationUserData;
import org.praktikum.test.data.TestData;

import static io.restassured.RestAssured.given;

public class AuthorizationUser extends TestData {
    public String accessToken;

    @Step("Valid data authorization")
    public Response authorizationValidUserData() {
        AuthorizationUserData json = new AuthorizationUserData(email, password);
        Response user = given()
                .header("Content-type", "application/json")
                .baseUri(MY_URL)
                .body(json)
                .post(ENDPOINT_AUTHORIZATION_USER);
        accessToken = user.body().as(ResponseBodyRegAndLogin.class).getAccessToken();
        return user;
    }

    @Step("Invalid data authorization")
    public Response authorizationInvalidUserData(String email, String password) {
        AuthorizationUserData json = new AuthorizationUserData(email, password);
        return given()
                .header("Content-type", "application/json")
                .baseUri(MY_URL)
                .body(json)
                .post(ENDPOINT_AUTHORIZATION_USER);
    }

}
