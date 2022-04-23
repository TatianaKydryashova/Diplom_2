package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import requests.CreateOrderRequest;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

public class OrderApi extends Base  {
    @Step("Create order with authorization")
    public Response makeOrderWithAuthorization(CreateOrderRequest createOrderRequest, String token) {
        return given()
                .spec(getBaseSpec())
                .auth().oauth2(token)
                .body(createOrderRequest)
                .when()
                .post(EndpointsOrderApi.POST_ORDERS);
    }

    @Step("Create order without authorization")
    public Response makeOrderWithoutAuthorization(CreateOrderRequest createOrderRequest) {
        return given()
                .spec(getBaseSpec())
                .body(createOrderRequest)
                .when()
                .post(EndpointsOrderApi.POST_ORDERS);
    }

    @Step("Get user orders list with authorised")
    public Response getUserOrdersListWithAuthorised(String token) {
        return given()
                .spec(getBaseSpec())
                .auth().oauth2(token)
                .when()
                .get(EndpointsOrderApi.POST_ORDERS);
    }

    @Step("Get user orders list without authorised")
    public Response getUserOrdersListWithoutAuthorised() {
        return given()
                .spec(getBaseSpec())
                .when()
                .get(EndpointsOrderApi.POST_ORDERS);
    }

    @Step("Check statusCode create order with authorization")
    public void checkStatusCodeCreateOrderWithAuthorization(Response response){

        response.then().assertThat().statusCode(200);
    }

    @Step("Check success value create order with authorization")
    public void checkSuccessValueCreateOrderWithAuthorization(Response response){
        response.then().assertThat().body("success", equalTo(true));
    }

    @Step("Check response body create order with authorization")
    public void checkResponseBodyCreateOrderWithAuthorization(Response response){
        response.then().assertThat()
                .body("name", notNullValue())
                .and().body("order.ingredients", not(emptyArray()))
                .and().body("order._id", not(equalTo(0)))
                .and().body("order.owner", notNullValue())
                .and().body("order.status", notNullValue())
                .and().body("order.name", notNullValue())
                .and().body("order.createdAt", notNullValue())
                .and().body("order.updatedAt", notNullValue())
                .and().body("order.number", not(equalTo(0)))
                .and().body("order.price", not(equalTo(0)));
    }

    @Step("Check statusCode create order without authorization")
    public void checkStatusCodeCreateOrderWithoutAuthorization(Response response){

        response.then().assertThat().statusCode(200);
    }

    @Step("Check success value create order without authorization")
    public void checkSuccessValueCreateOrderWithoutAuthorization(Response response){
        response.then().assertThat().body("success", equalTo(true));
    }

    @Step("Check response body create order without authorization")
    public void checkResponseBodyCreateOrderWithoutAuthorization(Response response){
        response.then().assertThat()
                .body("name", notNullValue())
                .and().body("order.number", not(equalTo(0)));
    }

    @Step("Check status code create order with authorization and without ingredients")
    public void checkStatusCodeCreateOrderWithAuthorizationAndWithoutIngredients(Response response){

        response.then().assertThat().statusCode(400);
    }

    @Step("Check success value create order with authorization and without ingredients")
    public void checkSuccessValueCreateOrderWithAuthorizationAndWithoutIngredients(Response response){
        response.then().assertThat().body("success", equalTo(false));
    }

    @Step("Check response body create order with authorization and without ingredients")
    public void checkResponseBodyCreateOrderWithAuthorizationAndWithoutIngredients(Response response){
        response.then().assertThat().body("message", equalTo("Ingredient ids must be provided"));
    }

    @Step("Check status code create order with authorization and incorrect ingredients")
    public void checkStatusCodeCreateOrderWithAuthorizationAndIncorrectIngredients(Response response){

        response.then().assertThat().statusCode(500);
    }

    @Step("Check statusCode get orders list authorised user")
    public void checkStatusCodeGetOrdersListAuthorisedUser(Response response){

        response.then().assertThat().statusCode(200);
    }

    @Step("Check success value get orders list authorised user")
    public void checkSuccessValueGetOrdersListAuthorisedUser(Response response){
        response.then().assertThat().body("success", equalTo(true));
    }

    @Step("Check response body get orders list authorised user")
    public void checkResponseBodyGetOrdersListAuthorisedUser(Response response){
        response.then().assertThat()
                .and().body("order", not(emptyArray()))
                .and().body("total", not(equalTo(0)))
                .and().body("totalToday", not(equalTo(0)));
    }

    @Step("Check status code get orders list not authorised user")
    public void checkStatusCodeGetOrdersListNotAuthorisedUser(Response response){

        response.then().assertThat().statusCode(401);
    }

    @Step("Check success value get orders list not authorised user")
    public void checkSuccessValueGetOrdersListNotAuthorisedUser(Response response){
        response.then().assertThat().body("success", equalTo(false));
    }

    @Step("Check response body get orders list not authorised user")
    public void checkResponseBodyGetOrdersListNotAuthorisedUser(Response response){
        response.then().assertThat().body("message", equalTo("You should be authorised"));
    }
}
