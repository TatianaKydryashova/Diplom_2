import api.UserApi;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import requests.CreateLoginRequest;
import requests.CreateUserRequest;

public class UserLoginTest {

     UserApi userApi;
     String token;
     CreateUserRequest createUserRequest;

    @Before
    public void setUp() {
        userApi = new UserApi();
        createUserRequest = CreateUserRequest.generateRandomUser();
        userApi.createUser(createUserRequest);
    }

    @After
    public void tearDown() {
        userApi.deleteCustomer(token);
    }

    @Test
    @DisplayName("Check successful login user")
    public void checkUserLoginPassedTest() {
        Response response = userApi.loginUser(CreateLoginRequest.userCredentials(createUserRequest));
        token = userApi.getAccessToken(response);
        userApi.checkStatusCodeLoginUserOk(response);
        userApi.checkSuccessValueLoginUserOk(response);
        userApi.checkResponseBodyLoginUserOK(response);
    }

    @Test
    @DisplayName("Check login user without email")
    public void checkUserLoginWithoutEmailTest() {
        Response response = userApi.loginUser(CreateLoginRequest.userCredentialsIncorrectEmail(createUserRequest));
        userApi.checkStatusCodeLoginUserWithoutRequiredField(response);
        userApi.checkSuccessValueLoginUserWithoutRequiredField(response);
        userApi.checkResponseBodyLoginUserWithoutRequiredField(response);
    }

    @Test
    @DisplayName("Check login user without password")
    public void checkUserLoginWithoutPasswordTest() {
        Response response = userApi.loginUser(CreateLoginRequest.userCredentialsIncorrectPassword(createUserRequest));
        userApi.checkStatusCodeLoginUserWithoutRequiredField(response);
        userApi.checkSuccessValueLoginUserWithoutRequiredField(response);
        userApi.checkResponseBodyLoginUserWithoutRequiredField(response);
    }


}
