package microservices.vn.nguyen.multiplication.service;

import microservices.vn.nguyen.multiplication.domain.Multiplication;
import microservices.vn.nguyen.multiplication.domain.MultiplicationNoLombok;
import microservices.vn.nguyen.multiplication.domain.MultiplicationResultAttempt;
import microservices.vn.nguyen.multiplication.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * Created by nals on 1/4/18.
 */
public class MultiplicationServiceImplTest {

    private MultiplicationServiceImpl multiplicationServiceImpl;

    @Mock
    private RandomGeneratorService randomGeneratorService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        multiplicationServiceImpl = new MultiplicationServiceImpl(randomGeneratorService);
    }

    @Test
    public void createRandomMultiplication_ShouldReturn1500WhenFactorNumber1AndFactorNumber2Are30And50() throws Exception {
        //Given FactorNumber1 is 30 and FactorNumber2 is 50
        given(randomGeneratorService.generatorRandomFactor()).willReturn(30,50);
        //when call service
        Multiplication multiplication = multiplicationServiceImpl.createRandomMultiplication();
        //Then
        assertThat(multiplication.getFactorNumberA()).isEqualTo(30);
        assertThat(multiplication.getFactorNumberB()).isEqualTo(50);
//    TODO return 0*0, need implment again
    }


    @Test
    public void checkAttempt_ShouldReturnTrueWhenInputCorrectInfo() throws Exception {
        //given
        Multiplication multiplication = new Multiplication(50,50);
        User user = new User("john conner");
        MultiplicationResultAttempt multiplicationResultAttempt = new MultiplicationResultAttempt(user,multiplication,2500);
        //when
        boolean attemptResult = multiplicationServiceImpl.checkAttempt(multiplicationResultAttempt);
        //then
        assertThat(attemptResult).isTrue();
    }

    @Test
    public void checkAttempt_ShouldReturnFalseWhenInputNotCorrectInfo() throws Exception {
        //given
        Multiplication multiplication = new Multiplication(50,50);
        User user = new User("john conner");
        MultiplicationResultAttempt multiplicationResultAttempt = new MultiplicationResultAttempt(user,multiplication,2000);
        //when
        boolean attemptResult = multiplicationServiceImpl.checkAttempt(multiplicationResultAttempt);
        //then
        assertThat(attemptResult).isFalse();
    }
}