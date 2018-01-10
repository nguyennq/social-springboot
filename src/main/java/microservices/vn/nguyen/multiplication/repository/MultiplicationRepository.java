package microservices.vn.nguyen.multiplication.repository;

import microservices.vn.nguyen.multiplication.entity.Multiplication;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by nals on 1/9/18.
 */
public interface MultiplicationRepository extends CrudRepository<Multiplication,Long> {

}
