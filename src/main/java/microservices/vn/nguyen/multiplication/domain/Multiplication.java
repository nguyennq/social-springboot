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
public final class Multiplication {

    private final int factorNumberA;
    private final int factorNumberB;

    public Multiplication() {
        factorNumberA = 0;
        factorNumberB = 0;
    }

}
