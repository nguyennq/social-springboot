package microservices.vn.nguyen.multiplication.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Created by nals on 1/4/18.
 */
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public final class MultiplicationResultAttempt {

    private final User user;
    private final Multiplication multiplication;
    private final int resultAttempt;

    private final boolean correct;

    MultiplicationResultAttempt(){
        user = null;
        multiplication = null;
        resultAttempt = -1;
        correct = false;
    }
}
