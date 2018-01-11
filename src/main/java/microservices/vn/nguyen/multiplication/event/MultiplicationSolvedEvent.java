package microservices.vn.nguyen.multiplication.event;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by nals on 1/11/18.
 */
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class MultiplicationSolvedEvent implements Serializable{

    private final Long multiplicationResultAttemptId;
    private final Long userId;
    private final boolean correct;

}
