package com.logmyspace.api.user;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/** Repository interface for managing {@link User} entities. */
@Repository
public interface UserRepository extends CrudRepository<User, UUID> {}
