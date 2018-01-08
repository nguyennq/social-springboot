package microservices.vn.nguyen.multiplication.service;

import microservices.vn.nguyen.multiplication.domain.Multiplication;
import microservices.vn.nguyen.multiplication.domain.MultiplicationResultAttempt;

/**
 * Created by nals on 1/3/18.
 */
public interface MultiplicationService {
    Multiplication createRandomMultiplication();
    boolean checkAttempt(final MultiplicationResultAttempt multiplicationResultAttempt);
}
