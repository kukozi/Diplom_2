package org.praktikum.authorizations.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.praktikum.methods.authorization.AuthorizationUser;

import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(Parameterized.class)
public class AuthorizationInvalidUserDataTest {
    private final String email;
    private final String password;
    AuthorizationUser user = new AuthorizationUser();

    public AuthorizationInvalidUserDataTest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Parameterized.Parameters
    public static Object[][] getUserLoginData() {
        return new Object[][]{
                {"testuser@yandex.ru", ""},
                {"", "password"},
        };
    }

    @Test
    @DisplayName("User authorization w/o full set of data")
    @Description("User authorization w/o email or password")
    public void authorizationNotValidUserDataTest() {
        user.authorizationInvalidUserData(email, password)
                .then().assertThat().statusCode(SC_UNAUTHORIZED)
                .and()
                .body("message", equalTo("email or password are incorrect"));
    }
}
