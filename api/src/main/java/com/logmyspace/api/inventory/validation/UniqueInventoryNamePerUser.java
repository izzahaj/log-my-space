package com.logmyspace.api.inventory.validation;

import static com.logmyspace.api.inventory.InventoryConstants.DUPLICATE_NAME_MESSAGE;

import com.logmyspace.api.inventory.Inventory;
import com.logmyspace.api.inventory.validation.validators.UniqueInventoryNamePerUserValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom validation annotation to ensure that an {@link Inventory} has a unique name for a given
 * user.
 */
@Documented
@Constraint(validatedBy = UniqueInventoryNamePerUserValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueInventoryNamePerUser {
  /** The error message returned when validation fails. */
  String message() default DUPLICATE_NAME_MESSAGE;

  /** The validation groups this constraint belongs to. */
  Class<?>[] groups() default {};

  /** The payload associated with this constraint. */
  Class<? extends Payload>[] payload() default {};
}
