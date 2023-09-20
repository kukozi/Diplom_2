package org.praktikum.methods.creation.order;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.praktikum.methods.get.order.GetIngredients;
import org.praktikum.serialization.OrderData;
import org.praktikum.test.data.TestData;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class CreateOrder extends TestData {
    GetIngredients getIngredients = new GetIngredients();

    @Step("Create order")
    public Response createdOrder(String accessToken) {
        OrderData json = new OrderData(getIngredients.getIngredientsId());

        return given()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .baseUri(MY_URL)
                .body(json)
                .post(ENDPOINT_CREATED_AND_GET_ORDER);
    }

    @Step("Create order with an empty list of ingredients")
    public Response createdOrderEmptyIngredientsList(String accessToken) {
        OrderData json = new OrderData(new ArrayList<>());

        return given()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .baseUri(MY_URL)
                .body(json)
                .post(ENDPOINT_CREATED_AND_GET_ORDER);
    }

    @Step("Create order with an invalid list of ingredients")
    public Response createOrderInvalidIngredientsList(String accessToken) {
        OrderData json = new OrderData(invalidIngredientsList);

        return given()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .baseUri(MY_URL)
                .body(json)
                .post(ENDPOINT_CREATED_AND_GET_ORDER);
    }


}
