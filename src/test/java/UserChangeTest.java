

import api.UserApi;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import requests.CreateLoginRequest;
import requests.CreateUserRequest;

public class UserChangeTest {

     UserApi userApi;
     String token;
     CreateUserRequest createUserRequest;

    @Before
    public void setUp() {
        userApi = new UserApi();
        createUserRequest = CreateUserRequest.generateRandomUser();
        Response response = userApi.createUser(createUserRequest);
        token = userApi.getAccessToken(response);
        userApi.checkStatusCodeCreateUserOk(response);
    }

    @After
    public void tearDown() {
        userApi.deleteCustomer(token);
    }

    @Test
    @DisplayName("Check change email with authorised")
    public void checkUserChangeEmailWithAuthorisedTest() {
        Response responseChanged = userApi.updateUserWithAuthorisation(CreateLoginRequest.userCredentialsNewEmail(createUserRequest), token);
        userApi.checkStatusCodeUpdateUserWithAuthorisation(responseChanged);
        userApi.checkSuccessValueUpdateUserWithAuthorisation(responseChanged);
    }

    @Test
    @DisplayName("Check change name with authorised")
    public void checkUserChangeNameWithAuthorisedTest() {
        Response responseChanged = userApi.updateUserWithAuthorisation(CreateLoginRequest.userCredentialsNewName(createUserRequest), token);
        userApi.checkStatusCodeUpdateUserWithAuthorisation(responseChanged);
        userApi.checkSuccessValueUpdateUserWithAuthorisation(responseChanged);
    }

    @Test
    @DisplayName("Check change email without authorised")
    public void checkUserChangeEmailWithoutAuthorisedTest() {
        Response responseChanged = userApi.updateUserWithoutAuthorisation(CreateLoginRequest.userCredentialsNewEmail(createUserRequest));
        userApi.checkStatusCodeUpdateUserWithoutAuthorisation(responseChanged);
        userApi.checkSuccessValueUpdateUserWithoutAuthorisation(responseChanged);
        userApi.checkResponseBodyUpdateUserWithoutAuthorisation(responseChanged);
    }

    @Test
    @DisplayName("Check change name without authorised")
    public void checkUserChangeNameWithoutAuthorisedTest() {
        Response responseChanged = userApi.updateUserWithoutAuthorisation(CreateLoginRequest.userCredentialsNewName(createUserRequest));
        userApi.checkStatusCodeUpdateUserWithoutAuthorisation(responseChanged);
        userApi.checkSuccessValueUpdateUserWithoutAuthorisation(responseChanged);
        userApi.checkResponseBodyUpdateUserWithoutAuthorisation(responseChanged);
    }


}
