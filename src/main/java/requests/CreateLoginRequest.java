package requests;

import org.apache.commons.lang3.RandomStringUtils;

public class CreateLoginRequest {
    public String email;
    public String password;
    public String name;
    public static final String EMAIL_POSTFIX = "@yandex.ru";

    public CreateLoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public CreateLoginRequest(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public static CreateLoginRequest userCredentials (CreateUserRequest createUserRequest) {
        return new CreateLoginRequest(createUserRequest.email, createUserRequest.password);
    }

    public static CreateLoginRequest userCredentialsIncorrectEmail(CreateUserRequest createUserRequest) {
        return new CreateLoginRequest(RandomStringUtils.randomAlphabetic(10) + EMAIL_POSTFIX, createUserRequest.password);
    }

    public static CreateLoginRequest userCredentialsIncorrectPassword(CreateUserRequest createUserRequest) {
        return new CreateLoginRequest(createUserRequest.email, RandomStringUtils.randomAlphabetic(10));
    }

    public static CreateLoginRequest userCredentialsNewEmail(CreateUserRequest createUserRequest) {
        return new CreateLoginRequest(RandomStringUtils.randomAlphabetic(10) + EMAIL_POSTFIX, createUserRequest.password, createUserRequest.name);
    }

    public static CreateLoginRequest userCredentialsNewName(CreateUserRequest createUserRequest) {
        return new CreateLoginRequest(createUserRequest.email, createUserRequest.password, RandomStringUtils.randomAlphabetic(10));
    }
}
