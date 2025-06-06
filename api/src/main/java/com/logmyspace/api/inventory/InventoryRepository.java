package com.logmyspace.api.inventory;

import com.logmyspace.api.user.User;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/** Repository interface for managing {@link Inventory} entities. */
@Repository
public interface InventoryRepository extends CrudRepository<Inventory, UUID> {
  /**
   * Checks if an inventory with the given name exists for the specified user.
   *
   * @param user the user to check ownership against
   * @param name the inventory name to check for
   * @return {@code true} if an inventory with the same name exists for the user, {@code false}
   *     otherwise
   */
  boolean existsByUserAndName(User user, String name);
}
