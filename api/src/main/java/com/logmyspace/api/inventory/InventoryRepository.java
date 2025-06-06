package com.logmyspace.api.inventory;

import com.logmyspace.api.user.User;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/** Repository interface for managing {@link Inventory} entities. */
@Repository
public interface InventoryRepository extends CrudRepository<Inventory, UUID> {
  boolean existsByUserAndNameIgnoreCase(User user, String name);
}
