package microservices.vn.nguyen.multiplication.service;

import microservices.vn.nguyen.multiplication.entity.Multiplication;
import microservices.vn.nguyen.multiplication.entity.MultiplicationResultAttempt;
import microservices.vn.nguyen.multiplication.entity.User;
import microservices.vn.nguyen.multiplication.repository.MultiplicationResultAttemptRepository;
import microservices.vn.nguyen.multiplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Created by nals on 1/3/18.
 */
@Service
public class MultiplicationServiceImpl implements MultiplicationService {

    private RandomGeneratorService randomGeneratorService;
    private MultiplicationResultAttemptRepository multiplicationResultAttemptRepository;
    private UserRepository userRepository;


    @Autowired
    public MultiplicationServiceImpl(final RandomGeneratorService randomGeneratorService,
                                     final MultiplicationResultAttemptRepository multiplicationResultAttemptRepository,
                                     final UserRepository userRepository){
        this.randomGeneratorService = randomGeneratorService;
        this.multiplicationResultAttemptRepository = multiplicationResultAttemptRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Multiplication createRandomMultiplication() {
        int factorNumberA = randomGeneratorService.generatorRandomFactor();
        int factorNumberB = randomGeneratorService.generatorRandomFactor();
        return new Multiplication(factorNumberA,factorNumberB);
    }

    @Transactional
    @Override
    public boolean checkAttempt(final MultiplicationResultAttempt multiplicationResultAttempt) {
        //check if the user already exists for that alias
        Optional<User> user = userRepository.findByAlias(multiplicationResultAttempt.getUser().getAlias());
        //Avoids 'hack' attempts
        Assert.isTrue(!multiplicationResultAttempt.isCorrect(), "You can't send an attempt marked as correct!!!");
//        boolean checkIfCorrect = multiplicationResultAttempt.
        boolean checkIfCorrect = multiplicationResultAttempt.getResultAttempt() ==
                multiplicationResultAttempt.getMultiplication().getFactorNumberA() *
                        multiplicationResultAttempt.getMultiplication().getFactorNumberB();
        MultiplicationResultAttempt resultCheckedAttempt = new MultiplicationResultAttempt(user.orElse(multiplicationResultAttempt.getUser()),
                multiplicationResultAttempt.getMultiplication(),multiplicationResultAttempt.getResultAttempt(),checkIfCorrect);
        //stores the attempts
        multiplicationResultAttemptRepository.save(resultCheckedAttempt);
        return checkIfCorrect;

    }

    @Override
    public List<MultiplicationResultAttempt> getStatisticsForUser(String userAlias) {
        return multiplicationResultAttemptRepository.findTop5ByUserAliasOrderByIdDesc(userAlias);
    }
}
