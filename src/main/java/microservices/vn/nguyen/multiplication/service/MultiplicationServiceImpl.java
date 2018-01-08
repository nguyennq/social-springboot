package microservices.vn.nguyen.multiplication.service;

import microservices.vn.nguyen.multiplication.domain.Multiplication;
import microservices.vn.nguyen.multiplication.domain.MultiplicationNoLombok;
import microservices.vn.nguyen.multiplication.domain.MultiplicationResultAttempt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by nals on 1/3/18.
 */
@Service
public class MultiplicationServiceImpl implements MultiplicationService {

    private RandomGeneratorService randomGeneratorService;

    @Autowired
    public MultiplicationServiceImpl(RandomGeneratorService randomGeneratorService){
        this.randomGeneratorService = randomGeneratorService;
    }

    @Override
    public Multiplication createRandomMultiplication() {
        int factorNumberA = randomGeneratorService.generatorRandomFactor();
        int factorNumberB = randomGeneratorService.generatorRandomFactor();
        return new Multiplication(factorNumberA,factorNumberB);
    }

    @Override
    public boolean checkAttempt(final MultiplicationResultAttempt multiplicationResultAttempt) {
        return multiplicationResultAttempt.getResultAttempt() == multiplicationResultAttempt.getMultiplication().
                getFactorNumberA()* multiplicationResultAttempt.getMultiplication().getFactorNumberB();
    }
}
