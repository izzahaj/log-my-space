package com.logmyspace.api.inventory.validation.validators;

import static com.logmyspace.api.commons.util.CollectionUtil.requireAllNonNull;
import static com.logmyspace.api.inventory.InventoryConstants.DUPLICATE_NAME_MESSAGE;

import com.logmyspace.api.inventory.Inventory;
import com.logmyspace.api.inventory.InventoryRepository;
import com.logmyspace.api.inventory.validation.UniqueInventoryNamePerUser;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueInventoryNamePerUserValidator
    implements ConstraintValidator<UniqueInventoryNamePerUser, Inventory> {

  @Autowired private InventoryRepository inventoryRepository;

  @Override
  public boolean isValid(Inventory inventory, ConstraintValidatorContext context) {
    requireAllNonNull(inventory.getUser(), inventory.getName());

    boolean exists =
        inventoryRepository.existsByUserAndNameIgnoreCase(inventory.getUser(), inventory.getName());

    if (exists) {
      context.disableDefaultConstraintViolation();
      context
          .buildConstraintViolationWithTemplate(DUPLICATE_NAME_MESSAGE)
          .addPropertyNode("name")
          .addConstraintViolation();
      return false;
    }

    return true;
  }
}
