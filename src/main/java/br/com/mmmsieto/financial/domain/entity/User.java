package br.com.mmmsieto.financial.domain.entity;

import br.com.mmmsieto.financial.annotation.Mandatory;
import br.com.mmmsieto.financial.domain.exceptions.ValidationException;

import java.util.Objects;

public class User extends Object{
    private Long id;

    @Mandatory
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getName(), user.getName()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getEmail(), getPassword());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
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
