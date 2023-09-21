package org.praktikum.methods.update.user;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.praktikum.serialization.UserData;
import org.praktikum.serialization.UserEmail;
import org.praktikum.serialization.UserName;
import org.praktikum.serialization.UserPassword;
import org.praktikum.test.data.TestData;

import static io.restassured.RestAssured.given;

public class UpdateUserData extends TestData {

    @Step("Update Email")
    public Response updateEmailUser(String accessToken) {
        UserEmail json = new UserEmail(updateEmail);
        return given()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .baseUri(MY_URL)
                .body(json)
                .patch(ENDPOINT_DELETE_UPDATE_GET_USER);
    }

    @Step("Update Password")
    public Response updatePasswordUser(String accessToken) {
        UserPassword json = new UserPassword(updatePassword);
        return given()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .baseUri(MY_URL)
                .body(json)
                .patch(ENDPOINT_DELETE_UPDATE_GET_USER);
    }

    @Step("Update Name")
    public Response updateNameUser(String accessToken) {
        UserName json = new UserName(updateName);
        return given()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .baseUri(MY_URL)
                .body(json)
                .patch(ENDPOINT_DELETE_UPDATE_GET_USER);
    }

    @Step("Full update")
    public Response fullUpdateUser(String accessToken) {
        UserData json = new UserData(updateEmail, updatePassword, updateName);
        return given()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .baseUri(MY_URL)
                .body(json)
                .patch(ENDPOINT_DELETE_UPDATE_GET_USER);
    }
}
