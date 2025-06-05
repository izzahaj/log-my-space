package com.logmyspace.api.user;

import static com.logmyspace.api.commons.util.CollectionUtil.requireAllNonNull;
import static com.logmyspace.api.user.validators.UserValidator.validateEmail;
import static com.logmyspace.api.user.validators.UserValidator.validateUser;
import static com.logmyspace.api.user.validators.UserValidator.validateUsername;
import static java.util.Objects.requireNonNull;

import com.logmyspace.api.commons.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a user entity.
 */
@Entity
@Table(name = "users")
public class User extends BaseEntity {

  @Id
  @NotBlank
  @Column(unique = true, nullable = false)
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String username;

  private String email;

  protected User() {
  }

  public User(UUID id, String username, String email) {
    requireAllNonNull(id, username, email);
    validateUser(id, username, email);
    this.id = id;
    this.username = username;
    this.email = email;
  }

  public UUID getId() {
    return this.id;
  }

  public String getUsername() {
    return this.username;
  }

  public String getEmail() {
    return this.email;
  }

  public void setUsername(String username) {
    requireNonNull(username);
    validateUsername(username);
    this.username = username;
  }

  public void setEmail(String email) {
    requireNonNull(email);
    validateEmail(email);
    this.email = email;
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }

    if (!(other instanceof User o)) {
      return false;
    }

    return o.getId().equals(id)
        && o.getUsername().equals(username)
        && o.getEmail().equals(email)
        && o.getCreatedAt().equals(this.getCreatedAt())
        && o.getLastModifiedAt().equals(this.getLastModifiedAt());
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), id, username, email);
  }
}
