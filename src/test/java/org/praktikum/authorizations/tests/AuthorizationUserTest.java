package org.praktikum.authorizations.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.praktikum.ClearUser;
import org.praktikum.methods.authorization.AuthorizationUser;
import org.praktikum.methods.creation.user.CreateUser;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.equalTo;

public class AuthorizationUserTest extends ClearUser {
    AuthorizationUser authorizationUser = new AuthorizationUser();

    @Before
    public void createdUser() {
        CreateUser createUser = new CreateUser();
        createUser.createdNewUser();
    }

    @Test
    @DisplayName("User authorization")
    @Description("User authorization w/ valid data")
    public void createdNewUserTest() {
        authorizationUser.authorizationValidUserData()
                .then().assertThat().statusCode(SC_OK)
                .and()
                .body("success", equalTo(true));
        token = authorizationUser.accessToken;
    }

}
