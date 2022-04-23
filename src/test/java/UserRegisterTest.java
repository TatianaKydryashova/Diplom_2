import api.UserApi;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import requests.CreateUserRequest;

public class UserRegisterTest {
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
    @DisplayName("Check successful user creation")
    public void checkUserCreationPassedTest() {
        CreateUserRequest createUserRequest = CreateUserRequest.generateRandomUser();
        Response response = userApi.createUser(createUserRequest);
        token = userApi.getAccessToken(response);
        userApi.checkStatusCodeCreateUserOk(response);
        userApi.checkSuccessValueCreateUserOK(response);
        userApi.checkResponseBodyCreateUserOK(response);
    }

    @Test
    @DisplayName("Check existing user creation")
    public void checkExistingUserCreationTest() {
        CreateUserRequest createUserRequest = CreateUserRequest.generateRandomUser();
        Response register = userApi.createUser(createUserRequest);
        token = userApi.getAccessToken(register);
        Response response = userApi.createUser(createUserRequest);
        userApi.checkStatusCodeCreateExistingUser(response);
        userApi.checkSuccessValueCreateExistingUser(response);
        userApi.checkResponseBodyCreateExistingUser(response);
    }

    @Test
    @DisplayName("Check create user without Email")
    public void checkUserCreationWithoutEmail() {
        CreateUserRequest createUserRequest = CreateUserRequest.generateRandomCustomerWithoutEmail();
        Response response = userApi.createUser(createUserRequest);
        userApi.checkStatusCodeCreateUserWithoutRequiredField(response);
        userApi.checkSuccessValueCreateUserWithoutRequiredField(response);
        userApi.checkResponseBodyCreateUserWithoutRequiredField(response);
    }

    @Test
    @DisplayName("Check create user without Name")
    public void checkUserCreationWithoutName() {
        CreateUserRequest createUserRequest = CreateUserRequest.generateRandomCustomerWithoutName();
        Response response = userApi.createUser(createUserRequest);
        userApi.checkStatusCodeCreateUserWithoutRequiredField(response);
        userApi.checkSuccessValueCreateUserWithoutRequiredField(response);
        userApi.checkResponseBodyCreateUserWithoutRequiredField(response);
    }

    @Test
    @DisplayName("Check create user without Password")
    public void checkUserCreationWithoutPassword() {
        CreateUserRequest createUserRequest = CreateUserRequest.generateRandomCustomerWithoutPassword();
        Response response = userApi.createUser(createUserRequest);
        userApi.checkStatusCodeCreateUserWithoutRequiredField(response);
        userApi.checkSuccessValueCreateUserWithoutRequiredField(response);
        userApi.checkResponseBodyCreateUserWithoutRequiredField(response);
    }

}
