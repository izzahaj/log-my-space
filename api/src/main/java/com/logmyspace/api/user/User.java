package com.logmyspace.api.user;

import com.logmyspace.api.commons.core.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

/**
 * Represents a user entity.
 */
@Entity
@Table(name = "users")
public class User extends BaseEntity { 

    @Id
    @NotBlank
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
}
