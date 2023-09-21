package org.praktikum.get.orders;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.praktikum.ClearUser;
import org.praktikum.methods.authorization.AuthorizationUser;
import org.praktikum.methods.creation.order.CreateOrder;
import org.praktikum.methods.creation.user.CreateUser;
import org.praktikum.methods.get.order.GetUserOrder;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class GetUserOrderTest extends ClearUser {

    GetUserOrder getUserOrder = new GetUserOrder();
    CreateOrder order = new CreateOrder();
    CreateUser user = new CreateUser();
    AuthorizationUser authorizationUser = new AuthorizationUser();

    @Before
    public void testUser() {
        user.createdNewUser();
        authorizationUser.authorizationValidUserData();
        token = authorizationUser.accessToken;
        order.createdOrder(token);
    }

    @Test
    @DisplayName("Get user's order list")
    @Description("Get authorized user order's list")
    public void getAuthorizationUserOrdersTest() {
        getUserOrder.getOrdersUser(token)
                .then().assertThat().statusCode(SC_OK)
                .and()
                .body("orders", notNullValue());
    }

    @Test
    @DisplayName("Get unauthorized user's order list")
    @Description("Get user's order list w/o authorization")
    public void getUnauthorizedUserOrdersTest() {
        getUserOrder.getOrdersUser("")
                .then().assertThat().statusCode(SC_UNAUTHORIZED)
                .and()
                .body("message", equalTo("You should be authorised"));
    }
}
