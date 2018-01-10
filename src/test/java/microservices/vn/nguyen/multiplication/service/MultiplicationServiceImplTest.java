package microservices.vn.nguyen.multiplication.service;

import microservices.vn.nguyen.multiplication.entity.Multiplication;
import microservices.vn.nguyen.multiplication.entity.MultiplicationResultAttempt;
import microservices.vn.nguyen.multiplication.entity.User;

import microservices.vn.nguyen.multiplication.repository.MultiplicationRepository;
import microservices.vn.nguyen.multiplication.repository.MultiplicationResultAttemptRepository;
import microservices.vn.nguyen.multiplication.repository.UserRepository;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * Created by nals on 1/4/18.
 */
public class MultiplicationServiceImplTest {

    private MultiplicationServiceImpl multiplicationServiceImpl;

    @Mock
    private RandomGeneratorService randomGeneratorService;
    @Mock
    private MultiplicationResultAttemptRepository multiplicationResultAttemptRepository;
    @Mock
    private UserRepository userRepository;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        multiplicationServiceImpl = new MultiplicationServiceImpl(randomGeneratorService,multiplicationResultAttemptRepository,userRepository);
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
//        Multiplication multiplication = preparedMultiplication();
        User user = new User("john conner");
//        User user = preparedUser();
        MultiplicationResultAttempt multiplicationResultAttempt = new MultiplicationResultAttempt(preparedUser(),multiplication,2500, false);
//        MultiplicationResultAttempt multiplicationResultAttempt = preparedMultiplicationResultAttempt();
        MultiplicationResultAttempt multiplicationResultAfterCheckedAttempt = new MultiplicationResultAttempt(user,multiplication,2500, true);
        given(userRepository.findByAlias("john conner")).willReturn(Optional.empty());
        //when
        boolean attemptResult = multiplicationServiceImpl.checkAttempt(multiplicationResultAttempt);
        //then
        assertThat(attemptResult).isTrue();
        verify(multiplicationResultAttemptRepository).save(multiplicationResultAfterCheckedAttempt);
    }

    @Test
    public void checkAttempt_ShouldReturnFalseWhenInputNotCorrectInfo() throws Exception {
        //given
        Multiplication multiplication = preparedMultiplication();
        User user = preparedUser();
        MultiplicationResultAttempt multiplicationResultAttempt = new MultiplicationResultAttempt(preparedUser(),multiplication,2000,false);
        given(userRepository.findByAlias("john conner")).willReturn(Optional.empty());
        //when
        boolean attemptResult = multiplicationServiceImpl.checkAttempt(multiplicationResultAttempt);
        //then
        assertThat(attemptResult).isFalse();
        verify(multiplicationResultAttemptRepository).save(multiplicationResultAttempt);
    }

    @Test
    public void getStatisticsForUser_ShouldReturnTop5UserOrderByIdDesc() throws Exception {
        //given
        Multiplication multiplication = preparedMultiplication();
        User user = preparedUser();
        MultiplicationResultAttempt multiplicationResultAttempt1 = new MultiplicationResultAttempt(user,multiplication,2510,false);
        MultiplicationResultAttempt multiplicationResultAttempt2 = new MultiplicationResultAttempt(user,multiplication,2520,false);
        List<MultiplicationResultAttempt> latestAttempts = Lists.newArrayList(multiplicationResultAttempt1,multiplicationResultAttempt2);
        given(userRepository.findByAlias("john conner")).willReturn(Optional.empty());
        given(multiplicationResultAttemptRepository.findTop5ByUserAliasOrderByIdDesc("john conner")).willReturn(latestAttempts);
        //when
        List<MultiplicationResultAttempt> latestAttemptsResult =
                multiplicationServiceImpl.getStatisticsForUser("john conner");
        //then
        assertThat(latestAttemptsResult).isEqualTo(latestAttempts);
    }

    private Multiplication preparedMultiplication() {
        Multiplication multiplication = new Multiplication(50,50);
        return multiplication;
    }

    private MultiplicationResultAttempt preparedMultiplicationResultAttempt() {
        MultiplicationResultAttempt multiplicationResultAttempt  = new MultiplicationResultAttempt(preparedUser(),preparedMultiplication(),2500,true);
        return multiplicationResultAttempt;
    }

    private User preparedUser() {
        User user = new User("john conner");
        return user;
    }


}