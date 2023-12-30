package br.com.mmmsieto.builders;

import br.com.mmmsieto.financial.domain.entity.User;

public class UserBuilderTest {

    private Long id;
    private String name;
    private String email;
    private String password;

    private UserBuilderTest() {}

    private static void initializeDefaultData(UserBuilderTest builder) {
        builder.id = 1L;
        builder.name = "name";
        builder.email = "email@gmail.com";
        builder.password = "123456";
    }

    public static UserBuilderTest builder() {
        UserBuilderTest builder = new UserBuilderTest();
        initializeDefaultData(builder);
        return builder;
    }

    public UserBuilderTest id(Long id) {
        this.id = id;
        return this;
    }

    public UserBuilderTest name(String name) {
        this.name = name;
        return this;
    }

    public UserBuilderTest email(String email) {
        this.email = email;
        return this;
    }

    public UserBuilderTest password(String password) {
        this.password = password;
        return this;
    }

    public User build() {
        return new User(id, name, email, password);
    }

}

