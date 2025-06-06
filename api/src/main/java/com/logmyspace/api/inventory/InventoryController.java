package com.logmyspace.api.inventory;

import static java.util.Objects.requireNonNull;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** REST controller handling inventory-related API requests. */
@RestController
@RequestMapping(path = "api/v1/inventories")
public class InventoryController {
  private final InventoryService inventoryService;

  /**
   * Constructs an {@code InventoryController} with the specified service.
   *
   * @param inventoryService the service used to perform inventory operations
   */
  public InventoryController(InventoryService inventoryService) {
    requireNonNull(inventoryService);
    this.inventoryService = inventoryService;
  }
}
