package microservices.vn.nguyen.multiplication.repository;

import microservices.vn.nguyen.multiplication.entity.MultiplicationResultAttempt;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by nals on 1/9/18.
 */
public interface MultiplicationResultAttemptRepository extends CrudRepository<MultiplicationResultAttempt, Long> {
    List<MultiplicationResultAttempt> findTop5ByUserAliasOrderByIdDesc(String alias);
}
