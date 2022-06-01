import api.UserApi;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import requests.CreateLoginRequest;
import requests.CreateUserRequest;

/*
По private: обычно переменные классов делают private, но Вы им поменяли модификатор доступа с public на "по умолчанию",
который отличается от private. Это было сделано умышленно? Если да, то какая была идея?
 тут нет никакой разницы, private или default, тестовые классы нигде не вызываются, нигде не наследуются,
  поэтому все равно какой у них будет модификатор доступа конкретно в данной задаче
 */
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
