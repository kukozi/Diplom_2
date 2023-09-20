package org.praktikum.created.test.order;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.praktikum.ClearUser;
import org.praktikum.methods.authorization.AuthorizationUser;
import org.praktikum.methods.creation.order.CreateOrder;
import org.praktikum.methods.creation.user.CreateUser;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class CreateOrderAuthorizationUserTest extends ClearUser {
    CreateOrder order = new CreateOrder();
    CreateUser user = new CreateUser();
    AuthorizationUser authorizationUser = new AuthorizationUser();

    @Before
    public void testUser() {
        user.createdNewUser();
        authorizationUser.authorizationValidUserData();
        token = authorizationUser.accessToken;
    }

    @Test
    @DisplayName("Order creation")
    @Description("Order creation w/ authorized user and valid ingredients")
    public void createValidOrderTest() {
        order.createdOrder(token)
                .then().statusCode(SC_OK)
                .and()
                .body("success", equalTo(true));
    }

    @Test
    @DisplayName("Order creation w/o ingredients")
    @Description("Order creation w/ authorized user and empty ingredients")
    public void createInvalidOrderTest() {
        order.createdOrderEmptyIngredientsList(token)
                .then().statusCode(SC_BAD_REQUEST)
                .and()
                .body("message", equalTo("Ingredient ids must be provided"));
    }

    @Test
    @DisplayName("Order creation w/ invalid list of ingredients")
    @Description("Order creation w/ authorized user and invalid ingredients")
    public void createInvalidIngredientsListTest() {
        order.createOrderInvalidIngredientsList(token)
                .then().statusCode(SC_INTERNAL_SERVER_ERROR);
    }

}
