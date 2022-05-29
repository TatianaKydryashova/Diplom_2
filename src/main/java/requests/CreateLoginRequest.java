package requests;

import org.apache.commons.lang3.RandomStringUtils;

public class CreateLoginRequest {

    private String email;
    private String password;
    private String name;

    public CreateLoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public CreateLoginRequest(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }

    public static CreateLoginRequest userCredentials (CreateUserRequest createUserRequest) {
        return new CreateLoginRequest(createUserRequest.getEmail(), createUserRequest.getPassword());
    }

    public static CreateLoginRequest userCredentialsIncorrectEmail(CreateUserRequest createUserRequest) {
        return new CreateLoginRequest(RandomStringUtils.randomAlphabetic(10) + "@yandex.ru", createUserRequest.getPassword());
    }

    public static CreateLoginRequest userCredentialsIncorrectPassword(CreateUserRequest createUserRequest) {
        return new CreateLoginRequest(createUserRequest.getName(), RandomStringUtils.randomAlphabetic(10));
    }

    public static CreateLoginRequest userCredentialsNewEmail(CreateUserRequest createUserRequest) {
        return new CreateLoginRequest(RandomStringUtils.randomAlphabetic(10) + "@yandex.ru", createUserRequest.getPassword(), createUserRequest.getName());
    }

    public static CreateLoginRequest userCredentialsNewName(CreateUserRequest createUserRequest) {
        return new CreateLoginRequest(createUserRequest.getEmail(), createUserRequest.getPassword(), RandomStringUtils.randomAlphabetic(10));
    }


}
