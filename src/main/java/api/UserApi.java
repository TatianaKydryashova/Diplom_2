package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import requests.CreateLoginRequest;
import requests.CreateUserRequest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

public class UserApi extends Base {
    @Step("Create new user")
    public Response createUser(CreateUserRequest createUserRequest) {
        return given()
                .spec(getBaseSpec())
                .body(createUserRequest)
                .when()
                .post(EndpointsUserApi.POST_REGISTER_CUSTOMER);
    }



    @Step("Check statusCode create user OK")
    public void checkStatusCodeCreateUserOk(Response response){

        response.then().assertThat().statusCode(200);
    }

    @Step("Check success value create user OK")
    public void checkSuccessValueCreateUserOK(Response response){
        response.then().assertThat().body("success", equalTo(true));
    }

    @Step("Check statusCode create existing user")
    public void checkStatusCodeCreateExistingUser(Response response){

        response.then().assertThat().statusCode(403);
    }

    @Step("Check success value create existing user")
    public void checkSuccessValueCreateExistingUser(Response response){
        response.then().assertThat().body("success", equalTo(false));
    }

    @Step("Check response body create existing user")
    public void checkResponseBodyCreateExistingUser(Response response){
        response.then().assertThat().body("message", equalTo("User already exists"));
    }

    @Step("Check statusCode create user without required field")
    public void checkStatusCodeCreateUserWithoutRequiredField(Response response){

        response.then().assertThat().statusCode(403);
    }

    @Step("Check success value create user without required field")
    public void checkSuccessValueCreateUserWithoutRequiredField(Response response){
        response.then().assertThat().body("success", equalTo(false));
    }



    @Step("Check response body create user without required field")
    public void checkResponseBodyCreateUserWithoutRequiredField(Response response){
        response.then().assertThat().body("message", equalTo("Email, password and name are required fields"));
    }

    @Step("Check response body create user OK")
    public void checkResponseBodyCreateUserOK(Response response){
        response.then().assertThat()
                .body("user.email", notNullValue())
                .and().body("user.name", notNullValue())
                .and().body("accessToken", notNullValue())
                .and().body("accessToken", notNullValue());
    }

    @Step("Get accessToken user")
    public String getAccessToken(Response response) {
        String token = response.then()
                .extract()
                .path("accessToken");

        return token.substring(7);
    }

    @Step("Delete user")
    public void deleteCustomer(String token) {
        if (token == null) {
            return;
        }
        given()
                .spec(getBaseSpec())
                .auth().oauth2(token)
                .when()
                .delete(EndpointsUserApi.DELETE_CUSTOMER).then()
                .statusCode(202);
    }

    @Step("Login user")
    public Response loginUser(CreateLoginRequest createloginRequest) {
        return given()
                .spec(getBaseSpec())
                .body(createloginRequest)
                .when()
                .post(EndpointsUserApi.POST_LOGIN_CUSTOMER);
    }

    @Step("Check statusCode login user Ok")
    public void checkStatusCodeLoginUserOk(Response response){

        response.then().assertThat().statusCode(200);
    }

    @Step("Check success value login user Ok")
    public void checkSuccessValueLoginUserOk(Response response){
        response.then().assertThat().body("success", equalTo(true));
    }

    @Step("Check response body login user OK")
    public void checkResponseBodyLoginUserOK(Response response){
        response.then().assertThat()
                .body("user.email", notNullValue())
                .and().body("user.name", notNullValue())
                .and().body("accessToken", notNullValue())
                .and().body("accessToken", notNullValue());
    }

    @Step("Check statusCode login user without required field")
    public void checkStatusCodeLoginUserWithoutRequiredField(Response response){

        response.then().assertThat().statusCode(401);
    }

    @Step("Check success value login user without required field")
    public void checkSuccessValueLoginUserWithoutRequiredField(Response response){
        response.then().assertThat().body("success", equalTo(false));
    }

    @Step("Check response body login user without required field")
    public void checkResponseBodyLoginUserWithoutRequiredField(Response response){
        response.then().assertThat().body("message", equalTo("email or password are incorrect"));
    }

    @Step("Update user with authorisation")
    public Response updateUserWithAuthorisation(CreateLoginRequest createloginRequest, String token) {
        return given()
                .spec(getBaseSpec())
                .auth().oauth2(token)
                .body(createloginRequest)
                .when()
                .patch(EndpointsUserApi.PUTCH_UPDATE_CUSTOMER);
    }

    @Step("Check statusCode update user with authorisation")
    public void checkStatusCodeUpdateUserWithAuthorisation(Response response){

        response.then().assertThat().statusCode(200);
    }

    @Step("Check success value update user with authorisation")
    public void checkSuccessValueUpdateUserWithAuthorisation(Response response){
        response.then().assertThat().body("success", equalTo(true));
    }

    @Step("Update user without authorisation")
    public Response updateUserWithoutAuthorisation(CreateLoginRequest createloginRequest) {
        return given()
                .spec(getBaseSpec())
                .body(createloginRequest)
                .when()
                .patch(EndpointsUserApi.PUTCH_UPDATE_CUSTOMER);
    }

    @Step("Check statusCode update user without authorisation")
    public void checkStatusCodeUpdateUserWithoutAuthorisation(Response response){

        response.then().assertThat().statusCode(401);
    }

    @Step("Check success value update user without authorisation")
    public void checkSuccessValueUpdateUserWithoutAuthorisation(Response response){
        response.then().assertThat().body("success", equalTo(false));
    }

    @Step("Check response body update user without authorisation")
    public void checkResponseBodyUpdateUserWithoutAuthorisation(Response response){
        response.then().assertThat().body("message", equalTo("You should be authorised"));
    }


}
