import api.UserApi;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import requests.CreateLoginRequest;
import requests.CreateUserRequest;

public class UserLoginTest {
    public UserApi userApi;
    public String token;

    @Before
    public void setUp() {
        userApi = new UserApi();
    }

    @After
    public void tearDown() {
        userApi.deleteCustomer(token);
    }

    @Test
    @DisplayName("Check successful login user")
    public void checkUserLoginPassedTest() {
        CreateUserRequest createUserRequest = CreateUserRequest.generateRandomUser();
        userApi.createUser(createUserRequest);
        Response response = userApi.loginUser(CreateLoginRequest.userCredentials(createUserRequest));
        token = userApi.getAccessToken(response);
        userApi.checkStatusCodeLoginUserOk(response);
        userApi.checkSuccessValueLoginUserOk(response);
        userApi.checkResponseBodyLoginUserOK(response);
    }

    @Test
    @DisplayName("Check login user without email")
    public void checkUserLoginWithoutEmailTest() {
        CreateUserRequest createUserRequest = CreateUserRequest.generateRandomUser();
        userApi.createUser(createUserRequest);
        Response response = userApi.loginUser(CreateLoginRequest.userCredentialsIncorrectEmail(createUserRequest));
        userApi.checkStatusCodeLoginUserWithoutRequiredField(response);
        userApi.checkSuccessValueLoginUserWithoutRequiredField(response);
        userApi.checkResponseBodyLoginUserWithoutRequiredField(response);
    }

    @Test
    @DisplayName("Check login user without password")
    public void checkUserLoginWithoutPasswordTest() {
        CreateUserRequest createUserRequest = CreateUserRequest.generateRandomUser();
        userApi.createUser(createUserRequest);
        Response response = userApi.loginUser(CreateLoginRequest.userCredentialsIncorrectPassword(createUserRequest));
        userApi.checkStatusCodeLoginUserWithoutRequiredField(response);
        userApi.checkSuccessValueLoginUserWithoutRequiredField(response);
        userApi.checkResponseBodyLoginUserWithoutRequiredField(response);
    }
}
