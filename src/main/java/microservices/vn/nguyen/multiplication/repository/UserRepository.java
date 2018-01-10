package microservices.vn.nguyen.multiplication.repository;

import microservices.vn.nguyen.multiplication.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by nals on 1/9/18.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByAlias(final String alias);
}
