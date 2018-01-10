package microservices.vn.nguyen.multiplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import microservices.vn.nguyen.multiplication.entity.Multiplication;
import microservices.vn.nguyen.multiplication.entity.MultiplicationResultAttempt;
import microservices.vn.nguyen.multiplication.entity.User;
import microservices.vn.nguyen.multiplication.service.MultiplicationService;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static microservices.vn.nguyen.multiplication.controller.MultiplicationResultAttemptController.ResultResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Created by nals on 1/5/18.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(MultiplicationResultAttemptController.class)
public class MultiplicationResultAttemptControllerTest {

    @MockBean
    private MultiplicationService multiplicationService;

    @Autowired
    private MockMvc mockMvc;

    private JacksonTester<MultiplicationResultAttempt> attemptJacksonTester;
    private JacksonTester<ResultResponse> responseJacksonTester;
    private JacksonTester<List<MultiplicationResultAttempt>> listAttemptsJacksonTester;


    @Before
    public void setUp() throws Exception {
         JacksonTester.initFields(this,new ObjectMapper());
    }

    @Test
    public void postResultReturnCorrect() throws Exception {
        genericParameterizedTest(true);
    }

    @Test
    public void postResultReturnNotCorrect() throws Exception {
        genericParameterizedTest(false);
    }

    @Test
    public void getStatistics_ShouldReturnListUserWhenCallCorrectService() throws Exception {
        //given
        User user = preparedUser();
        Multiplication multiplication = preparedMultiplication();
        MultiplicationResultAttempt multiplicationResultAttempt =
                new MultiplicationResultAttempt(user,multiplication,2500,true);
        List<MultiplicationResultAttempt> resultAttempts = Lists.newArrayList(multiplicationResultAttempt,multiplicationResultAttempt);
        given(multiplicationService.getStatisticsForUser("john conner")).willReturn(resultAttempts);
        //when call service
        MockHttpServletResponse httpServletResponse = mockMvc.perform(
                get("/results").param("alias","john conner")).
                andReturn().getResponse();
        //then
        assertThat(httpServletResponse.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(httpServletResponse.getContentAsString()).isEqualTo(listAttemptsJacksonTester.write(
                resultAttempts).getJson());
    }

    private void genericParameterizedTest(final boolean correct) throws Exception {
        //given
        given(multiplicationService.checkAttempt(any(MultiplicationResultAttempt.class))).willReturn(correct);
        User user = new User("John Conner");
        Multiplication multiplication = new Multiplication(50,50);
        MultiplicationResultAttempt multiplicationResultAttempt = new MultiplicationResultAttempt(user,multiplication, 2500,false);
        //when
        MockHttpServletResponse httpServletResponse = mockMvc.perform(
                post("/results").contentType(MediaType.APPLICATION_JSON).
                        content(attemptJacksonTester.write(multiplicationResultAttempt).
                                getJson())).andReturn().getResponse();
        //then
        assertThat(httpServletResponse.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(httpServletResponse.getContentAsString()).isEqualTo(attemptJacksonTester.write(new MultiplicationResultAttempt(
                multiplicationResultAttempt.getUser(),multiplicationResultAttempt.getMultiplication(),multiplicationResultAttempt.getResultAttempt(),correct)).getJson());
        
    }

    private Multiplication preparedMultiplication() {
        Multiplication multiplication = new Multiplication(50,50);
        return multiplication;
    }

    private User preparedUser() {
        User user = new User("john conner");
        return user;
    }
}