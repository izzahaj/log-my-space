package com.logmyspace.api.inventory;

import static java.util.Objects.requireNonNull;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/inventories")
public class InventoryController {
  private final InventoryService inventoryService;

  public InventoryController(InventoryService inventoryService) {
    requireNonNull(inventoryService);
    this.inventoryService = inventoryService;
  }
}
