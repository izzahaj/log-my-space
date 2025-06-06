package com.logmyspace.api.inventory;

import static com.logmyspace.api.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import com.logmyspace.api.commons.exceptions.ResourceNotFoundException;
import com.logmyspace.api.user.User;
import jakarta.transaction.Transactional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
  private final InventoryRepository inventoryRepository;

  public InventoryService(InventoryRepository inventoryRepository) {
    requireNonNull(inventoryRepository);
    this.inventoryRepository = inventoryRepository;
  }

  @Transactional
  public Inventory createInventory(String name, User user) {
    requireAllNonNull(name, user);
    // maybe retrieve user here instead of controller
    Inventory inventory = new Inventory(name, user);
    return inventoryRepository.save(inventory);
  }

  public Inventory getInventory(UUID id) {
    requireNonNull(id);
    return inventoryRepository
        .findById(id)
        .orElseThrow(
            () ->
                new ResourceNotFoundException(String.format("Inventory with ID %s not found", id)));
  }

  @Transactional
  public Inventory updateInventory(UUID id, String name) {
    requireAllNonNull(id, name);
    Inventory inventory =
        inventoryRepository
            .findById(id)
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        String.format("Inventory with ID %s not found", id)));
    inventory.setName(name);
    return inventoryRepository.save(inventory);
  }

  @Transactional
  public void deleteInventory(UUID id) {
    requireNonNull(id);
    if (inventoryRepository.existsById(id)) {
      inventoryRepository.deleteById(id);
    } else {
      throw new ResourceNotFoundException(String.format("Inventory with ID %s not found", id));
    }
  }
}
