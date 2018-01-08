package microservices.vn.nguyen.multiplication.service;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by nals on 1/3/18.
 */
public class RandomGeneratorServiceImplTest {

    private RandomGeneratorServiceImpl randomGeneratorServiceImpl;

    @Before
    public void setUp() throws Exception {
        randomGeneratorServiceImpl = new RandomGeneratorServiceImpl();
    }

    @Test
    public void generatorRandomFactorIsBetweenExpectedLimits() throws Exception {
        //when a good sample of randomly generated factors is generated
        List<Integer> randomFactors = IntStream.range(0,1000).
                map(i -> randomGeneratorServiceImpl.generatorRandomFactor()).
                boxed().collect(Collectors.toList());
        //then all of them should be between 11 and 100 because we watn a middle-complexity calculation
        assertThat(randomFactors).containsOnlyElementsOf(IntStream.range(11,100).boxed().collect(Collectors.toList()));
    }
}
