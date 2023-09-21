package org.praktikum.update.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.praktikum.ClearUser;
import org.praktikum.methods.update.user.UpdateUserData;

import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static org.hamcrest.CoreMatchers.equalTo;

public class UpdateUnauthorizedUserTest extends ClearUser {
    UpdateUserData update = new UpdateUserData();


    @Before
    public void tokenForUpdate() {
        token = "";
    }

    @Test
    @DisplayName("Update Email")
    @Description("Update Email unauthorized user")
    public void updateEmailUserTest() {
        update.updateEmailUser(token)
                .then().assertThat().statusCode(SC_UNAUTHORIZED)
                .and()
                .body("message", equalTo("You should be authorised"));
    }

    @Test
    @DisplayName("Update Password")
    @Description("Update Password unauthorized user")
    public void updatePasswordUserTest() {
        update.updatePasswordUser(token)
                .then().assertThat().statusCode(SC_UNAUTHORIZED)
                .and()
                .body("message", equalTo("You should be authorised"));
    }

    @Test
    @DisplayName("Update Name")
    @Description("Update Name authorized user")
    public void updateNameUserTest() {
        update.updateNameUser(token)
                .then().assertThat().statusCode(SC_UNAUTHORIZED)
                .and()
                .body("message", equalTo("You should be authorised"));
    }

    @Test
    @DisplayName("All user data update")
    @Description("Update all user data of an unauthorized user")
    public void fullUpdateUserTest() {
        update.fullUpdateUser(token)
                .then().assertThat().statusCode(SC_UNAUTHORIZED)
                .and()
                .body("message", equalTo("You should be authorised"));
    }
}
