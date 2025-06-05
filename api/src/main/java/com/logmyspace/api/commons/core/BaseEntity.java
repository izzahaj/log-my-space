package com.logmyspace.api.commons.core;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Objects;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * An abstract base entity class serving as a common superclass for all entities in the application.
 *
 * <p>This class provides common fields and methods related to entity auditing such as creation
 * timestamp, last modified timestamp, and lifecycle callbacks for setting the timestamps.
 *
 * <p>Subclasses of this class can extend it to inherit the auditing fields and behavior.
 */
@MappedSuperclass
public abstract class BaseEntity {

  /** The creation timestamp of the entity. */
  @CreationTimestamp
  @Column(name = "created_at", nullable = false, updatable = false)
  private OffsetDateTime createdAt;

  /** The last modified timestamp of the entity. */
  @UpdateTimestamp
  @Column(name = "last_modified_at", nullable = false)
  private OffsetDateTime lastModifiedAt;

  /**
   * Sets the creation and last modified timestamps to the current UTC time. This callback method is
   * automatically invoked before persisting the entity.
   */
  @PrePersist
  protected void onCreate() {
    OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
    this.createdAt = now;
    this.lastModifiedAt = now;
  }

  /**
   * Sets the last modified timestamp to the current UTC time. Callback method automatically invoked
   * before updating the entity.
   */
  @PreUpdate
  protected void onUpdate() {
    this.lastModifiedAt = OffsetDateTime.now(ZoneOffset.UTC);
  }

  /**
   * Retrieves the creation timestamp of the entity.
   *
   * @return the creation timestamp
   */
  public OffsetDateTime getCreatedAt() {
    return this.createdAt;
  }

  /**
   * Retrieves the last modified timestamp of the entity.
   *
   * @return the last modified timestamp
   */
  public OffsetDateTime getLastModifiedAt() {
    return this.lastModifiedAt;
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }

    if (!(other instanceof BaseEntity o)) {
      return false;
    }

    return o.getCreatedAt().equals(createdAt) && o.getLastModifiedAt().equals(lastModifiedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(createdAt, lastModifiedAt);
  }
}
