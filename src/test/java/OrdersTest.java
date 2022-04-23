import api.IngredientsApi;
import api.OrderApi;
import api.UserApi;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import requests.CreateLoginRequest;
import requests.CreateOrderRequest;
import requests.CreateUserRequest;

import java.util.ArrayList;

public class OrdersTest {
    public UserApi userApi;
    public String token;
    public IngredientsApi ingredientsApi;
    public OrderApi orderApi;

    @Before
    public void setUp() {
        userApi = new UserApi();
        ingredientsApi = new IngredientsApi();
        orderApi = new OrderApi();
    }

    @After
    public void tearDown() {
        userApi.deleteCustomer(token);
    }

    @Test
    @DisplayName("Check make order with authorisation and correct ingredients")
    public void checkMakeOrderWithAuthorisationAndCorrectIngredientsTest() {
        CreateUserRequest createUserRequest = CreateUserRequest.generateRandomUser();
        Response newUser = userApi.createUser(createUserRequest);
        token = userApi.getAccessToken(newUser);
        userApi.checkStatusCodeCreateUserOk(newUser);
        ArrayList<String> ingredientsList = ingredientsApi.getAllIngredientsList();
        ArrayList<String> ingredientsOrderList =  ingredientsApi.getIngredientsOrderList(ingredientsList);
        CreateOrderRequest createOrderRequest = CreateOrderRequest.generateOrderData(ingredientsOrderList);
        Response response = orderApi.makeOrderWithAuthorization(createOrderRequest, token);
        orderApi.checkStatusCodeCreateOrderWithAuthorization(response);
        orderApi.checkSuccessValueCreateOrderWithAuthorization(response);
        orderApi.checkResponseBodyCreateOrderWithAuthorization(response);
    }

    @Test
    @DisplayName("Check make order without authorisation and correct ingredients")
    public void checkMakeOrderWithoutAuthorisationAndCorrectIngredientsTest() {
        ArrayList<String> ingredientsList = ingredientsApi.getAllIngredientsList();
        ArrayList<String> ingredientsOrderList =  ingredientsApi.getIngredientsOrderList(ingredientsList);
        CreateOrderRequest createOrderRequest = CreateOrderRequest.generateOrderData(ingredientsOrderList);
        Response response = orderApi.makeOrderWithoutAuthorization(createOrderRequest);
        orderApi.checkStatusCodeCreateOrderWithoutAuthorization(response);
        orderApi.checkSuccessValueCreateOrderWithoutAuthorization(response);
        orderApi.checkResponseBodyCreateOrderWithoutAuthorization(response);
    }

    @Test
    @DisplayName("Check make order with authorisation and without ingredients")
    public void checkMakeOrderWithAuthorisationAndWithoutIngredientsTest() {
        CreateUserRequest createUserRequest = CreateUserRequest.generateRandomUser();
        Response newUser = userApi.createUser(createUserRequest);
        token = userApi.getAccessToken(newUser);
        userApi.checkStatusCodeCreateUserOk(newUser);
        CreateOrderRequest createOrderRequest = CreateOrderRequest.generateOrderData();
        Response response = orderApi.makeOrderWithAuthorization(createOrderRequest, token);
        orderApi.checkStatusCodeCreateOrderWithAuthorizationAndWithoutIngredients(response);
        orderApi.checkSuccessValueCreateOrderWithAuthorizationAndWithoutIngredients(response);
        orderApi.checkResponseBodyCreateOrderWithAuthorizationAndWithoutIngredients(response);
    }

    @Test
    @DisplayName("Check make order with authorisation and incorrect ingredients")
    public void checkMakeOrderWithAuthorisationAndIncorrectIngredientsTest() {
        CreateUserRequest createUserRequest = CreateUserRequest.generateRandomUser();
        Response newUser = userApi.createUser(createUserRequest);
        token = userApi.getAccessToken(newUser);
        userApi.checkStatusCodeCreateUserOk(newUser);
        ArrayList<String> ingredientsOrderList = ingredientsApi.getIngredientsOrderListIncorrect();
        CreateOrderRequest createOrderRequest = CreateOrderRequest.generateOrderData(ingredientsOrderList);
        Response response = orderApi.makeOrderWithAuthorization(createOrderRequest, token);
        orderApi.checkStatusCodeCreateOrderWithAuthorizationAndIncorrectIngredients(response);
    }

    @Test
    @DisplayName("Check get order list authorised user")
    public void checkGetOrdersListAuthorisedUserTest() {
        CreateUserRequest createUserRequest = CreateUserRequest.generateRandomUser();
        Response newUser = userApi.createUser(createUserRequest);
        token = userApi.getAccessToken(newUser);
        userApi.checkStatusCodeCreateUserOk(newUser);
        ArrayList<String> ingredientsList = ingredientsApi.getAllIngredientsList();
        ArrayList<String> ingredientsOrderList =  ingredientsApi.getIngredientsOrderList(ingredientsList);
        CreateOrderRequest createOrderRequest = CreateOrderRequest.generateOrderData(ingredientsOrderList);
        orderApi.makeOrderWithAuthorization(createOrderRequest, token);
        orderApi.makeOrderWithAuthorization(createOrderRequest, token);
        orderApi.makeOrderWithAuthorization(createOrderRequest, token);
        Response response = orderApi.getUserOrdersListWithAuthorised(token);
        orderApi.checkStatusCodeGetOrdersListAuthorisedUser(response);
        orderApi.checkSuccessValueGetOrdersListAuthorisedUser(response);
        orderApi.checkResponseBodyGetOrdersListAuthorisedUser(response);
    }

    @Test
    @DisplayName("Check get order list not authorised user")
    public void checkGetOrdersListNotAuthorisedUserTest() {
        CreateUserRequest createUserRequest = CreateUserRequest.generateRandomUser();
        Response newUser = userApi.createUser(createUserRequest);
        token = userApi.getAccessToken(newUser);
        userApi.checkStatusCodeCreateUserOk(newUser);
        ArrayList<String> ingredientsList = ingredientsApi.getAllIngredientsList();
        ArrayList<String> ingredientsOrderList =  ingredientsApi.getIngredientsOrderList(ingredientsList);
        CreateOrderRequest createOrderRequest = CreateOrderRequest.generateOrderData(ingredientsOrderList);
        orderApi.makeOrderWithAuthorization(createOrderRequest, token);
        orderApi.makeOrderWithAuthorization(createOrderRequest, token);
        orderApi.makeOrderWithAuthorization(createOrderRequest, token);
        Response response = orderApi.getUserOrdersListWithoutAuthorised();
        orderApi.checkStatusCodeGetOrdersListNotAuthorisedUser(response);
        orderApi.checkSuccessValueGetOrdersListNotAuthorisedUser(response);
        orderApi.checkResponseBodyGetOrdersListNotAuthorisedUser(response);
    }

}
