package org.praktikum.update.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.praktikum.ClearUser;
import org.praktikum.methods.authorization.AuthorizationUser;
import org.praktikum.methods.creation.user.CreateUser;
import org.praktikum.methods.update.user.UpdateUserData;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.equalTo;

public class UpdateAuthorizationUserTest extends ClearUser {
    UpdateUserData update = new UpdateUserData();
    CreateUser createUser = new CreateUser();
    AuthorizationUser user = new AuthorizationUser();


    @Before
    public void createdAndAuthorizationUser() {
        createUser.createdNewUser();
        user.authorizationValidUserData();
        token = user.accessToken;
    }

    @Test
    @DisplayName("Update Email")
    @Description("Update Email of an authorized user")
    public void updateEmailTest() {
        update.updateEmailUser(token)
                .then().assertThat().statusCode(SC_OK)
                .and()
                .body("success", equalTo(true));
    }

    @Test
    @DisplayName("Update Password")
    @Description("Update Password of an authorized user")
    public void updatePasswordTest() {
        update.updatePasswordUser(token)
                .then().assertThat().statusCode(SC_OK)
                .and()
                .body("success", equalTo(true));
    }

    @Test
    @DisplayName("Update Name")
    @Description("Update Name of an authorized user")
    public void updateNameTest() {
        update.updateNameUser(token)
                .then().assertThat().statusCode(SC_OK)
                .and()
                .body("success", equalTo(true));
    }

    @Test
    @DisplayName("Update all user data")
    @Description("Update all authorized user data")
    public void fullUpdateTest() {
        update.fullUpdateUser(token)
                .then().assertThat().statusCode(SC_OK)
                .and()
                .body("success", equalTo(true));
    }

}
