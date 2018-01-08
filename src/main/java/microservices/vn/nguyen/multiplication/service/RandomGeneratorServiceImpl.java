package microservices.vn.nguyen.multiplication.service;

import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by nals on 1/3/18.
 */
@Service
public final class RandomGeneratorServiceImpl implements RandomGeneratorService{
    final static int MINIMUM_FACTOR = 11;
    final static int MAXIMUM_FACTOR = 99;
    @Override
    public int generatorRandomFactor() {
        return new Random().nextInt((MAXIMUM_FACTOR - MINIMUM_FACTOR) + 1 ) + MINIMUM_FACTOR;
    }
}
