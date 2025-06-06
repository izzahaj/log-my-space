package com.logmyspace.api.inventory;

import static com.logmyspace.api.commons.util.CollectionUtil.requireAllNonNull;
import static com.logmyspace.api.inventory.InventoryConstants.BLANK_NAME_MESSAGE;
import static com.logmyspace.api.inventory.InventoryConstants.MAX_NAME_LENGTH;
import static com.logmyspace.api.inventory.InventoryConstants.MIN_NAME_LENGTH;
import static com.logmyspace.api.inventory.InventoryConstants.NAME_LENGTH_MESSAGE;

import com.logmyspace.api.commons.core.BaseEntity;
import com.logmyspace.api.inventory.validation.UniqueInventoryNamePerUser;
import com.logmyspace.api.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(
    name = "inventories",
    uniqueConstraints =
        @UniqueConstraint(
            name = "UniqueUserIdAndName",
            columnNames = {"user_id", "name"}))
@UniqueInventoryNamePerUser
public class Inventory extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @NotBlank(message = BLANK_NAME_MESSAGE) @Size(min = MIN_NAME_LENGTH, max = MAX_NAME_LENGTH, message = NAME_LENGTH_MESSAGE) @Column(nullable = false)
  private String name;

  @ManyToOne(optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  protected Inventory() {}

  public Inventory(String name, User user) {
    requireAllNonNull(name, user);
    this.name = name;
    this.user = user;
  }

  public UUID getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public User getUser() {
    return this.user;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }

    if (!(other instanceof Inventory o)) {
      return false;
    }

    return o.getId().equals(id)
        && o.getName().equals(name)
        && o.getUser().equals(user)
        && o.getCreatedAt().equals(this.getCreatedAt())
        && o.getLastModifiedAt().equals(this.getLastModifiedAt());
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), id, name, user);
  }
}
