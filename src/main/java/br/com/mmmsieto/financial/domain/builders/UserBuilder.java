package br.com.mmmsieto.financial.domain.builders;

import br.com.mmmsieto.financial.domain.entity.User;

public class UserBuilder {

    private Long id;
    private String name;
    private String email;
    private String password;

    private UserBuilder() {}

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public UserBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public UserBuilder name(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder password(String password) {
        this.password = password;
        return this;
    }

    public User build() {
        return new User(id, name, email, password);
    }

}
