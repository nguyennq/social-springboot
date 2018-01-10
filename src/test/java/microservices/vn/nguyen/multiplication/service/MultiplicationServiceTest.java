package microservices.vn.nguyen.multiplication.service;

import microservices.vn.nguyen.multiplication.entity.Multiplication;
import microservices.vn.nguyen.multiplication.domain.MultiplicationNoLombok;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * Created by nals on 1/3/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MultiplicationServiceTest {

    @MockBean
    private RandomGeneratorService randomGeneratorService;

    @Autowired
    private MultiplicationService multiplicationService;

    @Test
    public void createRandomMultiplication() throws Exception {
        //given (our mocked Random Generator service will return  rst 50, then 30)
        given(randomGeneratorService.generatorRandomFactor()).willReturn(50, 30);
        //when
        Multiplication multiplication = multiplicationService.createRandomMultiplication();
        //then
        assertThat(multiplication.getFactorNumberA()).isEqualTo(50);
        assertThat(multiplication.getFactorNumberB()).isEqualTo(30);
//        assertThat(multiplication.getResultMulTwoFactor()).isEqualTo(1500);
    }

}