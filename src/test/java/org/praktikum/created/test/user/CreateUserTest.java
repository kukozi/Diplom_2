package org.praktikum.created.test.user;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.praktikum.ClearUser;
import org.praktikum.methods.creation.user.CreateUser;

import static org.apache.http.HttpStatus.SC_FORBIDDEN;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.equalTo;

public class CreateUserTest extends ClearUser {
    CreateUser createUser = new CreateUser();

    @Test
    @DisplayName("User creation")
    @Description("User creation w/ valid data set")
    public void createUserTest() {
        createUser.createdNewUser()
                .then().assertThat().statusCode(SC_OK)
                .and()
                .body("success", equalTo(true));
        token = createUser.accessToken;
    }

    @Test
    @DisplayName("Creation of registered user")
    @Description("Creation of a user using data of an already registered user")
    public void createRegisteredUserTest() {
        createUser.createdNewUser();
        token = createUser.accessToken;
        createUser.createdNewUser()
                .then().assertThat().statusCode(SC_FORBIDDEN)
                .and()
                .body("message", equalTo("User already exists"));

    }
}
