package microservices.vn.nguyen.multiplication.service;

import microservices.vn.nguyen.multiplication.entity.Multiplication;
import microservices.vn.nguyen.multiplication.entity.MultiplicationResultAttempt;

import java.util.List;

/**
 * Created by nals on 1/3/18.
 */
public interface MultiplicationService {
    Multiplication createRandomMultiplication();
    boolean checkAttempt(final MultiplicationResultAttempt multiplicationResultAttempt);
    List<MultiplicationResultAttempt> getStatisticsForUser(final String userAlias);
}
