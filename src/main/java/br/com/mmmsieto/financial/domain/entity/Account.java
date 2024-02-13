package br.com.mmmsieto.financial.domain.entity;

import br.com.mmmsieto.financial.domain.exceptions.ValidationException;
import br.com.mmmsieto.financial.validation.ValidationStrategy;
import br.com.mmmsieto.financial.validation.impl.NameValidation;
import br.com.mmmsieto.financial.validation.impl.UserValidation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Account {

    private Long id;
    private String name;
    private User user;

    public Account() {
    }

    public Account(Long id, String name, User user) {
        this.id = id;
        this.name = name;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public  static class AccountBuilder {
        private Long id;
        private String name;
        private User user;
        private final List<ValidationStrategy> validations = new ArrayList<>();

        public static AccountBuilder newAccount() {

            AccountBuilder builder = new AccountBuilder();
            builder.addValidation(new NameValidation());
            builder.addValidation(new UserValidation());
            return builder;
        }

        public AccountBuilder addValidation(ValidationStrategy validation) {
            this.validations.add(validation);
            return this;
        }

        public AccountBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public AccountBuilder name(String name) {
            this.name = name;
            return this;
        }

        public AccountBuilder user(User user) {
            this.user = user;
            return this;
        }

        public Account build() throws ValidationException {
            for (ValidationStrategy validation : validations) {

                if (validation instanceof UserValidation) {
                    validation.validate(this.user);
                }

                if (validation instanceof NameValidation) {
                    validation.validate(this.name);
                }
            }
            return new Account(id, name, user);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(getId(), account.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
