package org.praktikum.created.test.user;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.praktikum.methods.creation.user.CreateUser;

import static org.apache.http.HttpStatus.SC_FORBIDDEN;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(Parameterized.class)
public class CrearedUserIncompleteDataTest {

    private final String email;
    private final String password;
    private final String name;
    CreateUser user = new CreateUser();

    public CrearedUserIncompleteDataTest(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    @Parameterized.Parameters
    public static Object[][] getUserData() {
        return new Object[][]{
                {"Test@test.ru", "UserPassword", ""},
                {"Test@test.ru", "", "Test"},
                {"", "UserPassword", "Test"},
        };
    }

    @Test
    @DisplayName("User creation w/o full data set")
    @Description("Create user using one empty data field")
    public void createUserNoRequiredFieldsTest() {
        user.createUserNoRequiredFields(email, password, name)
                .then().assertThat().statusCode(SC_FORBIDDEN)
                .and()
                .body("message", equalTo("Email, password and name are required fields"));
    }
}
