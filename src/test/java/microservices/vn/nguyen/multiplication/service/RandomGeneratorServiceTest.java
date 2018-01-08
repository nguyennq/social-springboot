//package microservices.vn.nguyen.multiplication.service;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//
///**
// * Created by nals on 1/3/18.
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class RandomGeneratorServiceTest {
//
//    @Autowired
//    private RandomGeneratorService randomGeneratorService;
//
//    @Test
//    public void generatorRandomFactorIsBetweenExpectedLimits() throws Exception {
//        //when a good sample of randomly generated factors is generated
//        List<Integer> randomFactors = IntStream.range(0,1000).
//                map(i -> randomGeneratorService.generatorRandomFactor()).
//                boxed().collect(Collectors.toList());
//        //then all of them should be between 11 and 100 because we watn a middle-complexity calculation
//        assertThat(randomFactors).containsOnlyElementsOf(IntStream.range(11,1000).boxed().collect(Collectors.toList()));
//    }
//
//
//}