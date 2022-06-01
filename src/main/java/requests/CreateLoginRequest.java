package requests;

import org.apache.commons.lang3.RandomStringUtils;


/*
 почему убрали "public static final String EMAIL_POSTFIX = "@yandex.ru";"
  Данная переменная, была дана в подобном фармате самим практикумом.
  Поэтому почему Практикум давет подобного формата код, вопрос не ко мне.
  Именно поэтому я ее убрала, и просто оформила формирование адреса с помощью конкатенации

  зачем в классе CreateLoginRequest кроме конструкторов используются статичные методы (по сути тоже конструкторы)
  userCredentials, userCredentialsIncorrectEmail и т.п.?
   конструкторы не очевидны. Статик методы можно как-то наззвать userCredentials или еще как-то... так код более читаем. А конструктор - "что то мы инициализируем"
  Использовала данный паттеерн, так как делала через него и ранее инициализацию объектов. Как пример Паттерн Builder используется
  в более сложно структурных задачах и проектах, что не совсем относится к данному диплому. (и да, кода значительно меньше,
  опять же потому что "все зависит от проекта") .
 */
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
