package com.logmyspace.api.inventory;

import static com.logmyspace.api.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import com.logmyspace.api.commons.exceptions.ResourceNotFoundException;
import com.logmyspace.api.user.User;
import jakarta.transaction.Transactional;
import java.util.UUID;
import org.springframework.stereotype.Service;

/** Service that handles business logic for managing {@link Inventory} entities. */
@Service
public class InventoryService {
  private final InventoryRepository inventoryRepository;

  /**
   * Constructs an {@code InventoryService} with the specified repository.
   *
   * @param inventoryRepository the repository used to access inventory data
   */
  public InventoryService(InventoryRepository inventoryRepository) {
    requireNonNull(inventoryRepository);
    this.inventoryRepository = inventoryRepository;
  }

  /**
   * Creates and persists a new {@link Inventory} for the given user.
   *
   * @param name the name of the inventory
   * @param user the user who owns the inventory
   * @return the saved inventory
   */
  @Transactional
  public Inventory createInventory(String name, User user) {
    requireAllNonNull(name, user);
    // maybe retrieve user here instead of controller

    Inventory inventory = new Inventory(name, user);
    return inventoryRepository.save(inventory);
  }

  /**
   * Retrieves an inventory by its unique identifier.
   *
   * @param id the ID of the inventory
   * @return the inventory with the given ID
   * @throws ResourceNotFoundException if no inventory with the given ID exists
   */
  public Inventory getInventory(UUID id) {
    requireNonNull(id);
    return inventoryRepository
        .findById(id)
        .orElseThrow(
            () ->
                new ResourceNotFoundException(String.format("Inventory with ID %s not found", id)));
  }

  /**
   * Updates an existing inventory.
   *
   * @param id the ID of the inventory to update
   * @param name the new name for the inventory
   * @return the updated inventory
   * @throws ResourceNotFoundException if no inventory with the given ID exists
   */
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

  /**
   * Deletes an inventory by its unique identifier.
   *
   * @param id the ID of the inventory to delete
   * @throws ResourceNotFoundException if no inventory with the given ID exists
   */
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
