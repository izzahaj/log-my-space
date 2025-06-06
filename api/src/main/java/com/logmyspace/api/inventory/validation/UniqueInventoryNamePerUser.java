package com.logmyspace.api.inventory.validation;

import static com.logmyspace.api.inventory.InventoryConstants.DUPLICATE_NAME_MESSAGE;

import com.logmyspace.api.inventory.validation.validators.UniqueInventoryNamePerUserValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = UniqueInventoryNamePerUserValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueInventoryNamePerUser {
  String message() default DUPLICATE_NAME_MESSAGE;

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
