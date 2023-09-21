package org.praktikum.created.test.order;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.praktikum.ClearUser;
import org.praktikum.methods.creation.order.CreateOrder;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class CreateOrderUnauthorizedUserTest extends ClearUser {

    CreateOrder order = new CreateOrder();


    @Before
    public void tokenForUpdate() {
        token = "";
    }

    @Test
    @DisplayName("Order creation")
    @Description("Order creation w/ ingredients and unauthorized user")
    public void createValidOrder() {
        order.createdOrder(token)
                .then().statusCode(SC_OK)
                .and()
                .body("success", equalTo(true));
    }

    @Test
    @DisplayName("Order creation w/o ingredients")
    @Description("Order creation w/o ingredients and unauthorized user")
    public void createInvalidOrder() {
        order.createdOrderEmptyIngredientsList(token)
                .then().statusCode(SC_BAD_REQUEST)
                .and()
                .body("message", equalTo("Ingredient ids must be provided"));
    }

    @Test
    @DisplayName("Order creation w/ invalid ingredients")
    @Description("Order creation w/ invalid ingredients and unauthorized user")
    public void createOrderInvalidIngredientsListTest() {
        order.createOrderInvalidIngredientsList(token)
                .then().statusCode(SC_INTERNAL_SERVER_ERROR);
    }
}
