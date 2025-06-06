package com.logmyspace.api.user;

import static com.logmyspace.api.commons.util.CollectionUtil.requireAllNonNull;
import static com.logmyspace.api.user.validators.UserValidator.MAXIMUM_USERNAME_LENGTH;
import static com.logmyspace.api.user.validators.UserValidator.MINIMUM_USERNAME_LENGTH;
import static com.logmyspace.api.user.validators.UserValidator.USERNAME_REGEX;
import static com.logmyspace.api.user.validators.UserValidator.validateEmail;
import static com.logmyspace.api.user.validators.UserValidator.validateUser;
import static com.logmyspace.api.user.validators.UserValidator.validateUsername;
import static java.util.Objects.requireNonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.logmyspace.api.commons.core.BaseEntity;
import com.logmyspace.api.inventory.Inventory;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/** Represents a user entity. */
@Entity
@Table(name = "users")
public class User extends BaseEntity {

  @Id
  @Column(unique = true, nullable = false)
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @NotBlank @Size(min = MINIMUM_USERNAME_LENGTH, max = MAXIMUM_USERNAME_LENGTH) @Pattern(regexp = USERNAME_REGEX) @Column(unique = true, nullable = false)
  private String username;

  @NotBlank @Email @Column(unique = true, nullable = false)
  private String email;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  @JsonIgnore
  private Set<Inventory> inventories = new HashSet<>();

  protected User() {}

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

  public Set<Inventory> getInventories() {
    return this.inventories;
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

  public void setInventories(Set<Inventory> inventories) {
    requireAllNonNull(inventories);
    this.inventories = inventories;
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
        && o.getInventories().equals(inventories)
        && o.getCreatedAt().equals(this.getCreatedAt())
        && o.getLastModifiedAt().equals(this.getLastModifiedAt());
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), id, username, email, inventories);
  }
}
