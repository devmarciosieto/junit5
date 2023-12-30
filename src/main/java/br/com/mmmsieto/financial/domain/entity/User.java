package br.com.mmmsieto.financial.domain.entity;

import br.com.mmmsieto.financial.domain.exceptions.ValidationException;

public class User {
    private Long id;
    private String name;
    private String email;
    private String password;

    public User() {
    }

    public User(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password= password;
        validation();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    private void validation() {
        if (name == null || name.isEmpty()) {
            throw new ValidationException("Name is required");
        }
        if (email == null || email.isEmpty()) {
            throw new ValidationException("Email is required");
        }
        if (password == null || password.isEmpty()) {
            throw new ValidationException("Password is required");
        }
    }

}
