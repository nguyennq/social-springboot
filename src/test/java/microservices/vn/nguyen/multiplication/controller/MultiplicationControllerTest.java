package microservices.vn.nguyen.multiplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import microservices.vn.nguyen.multiplication.domain.Multiplication;
import microservices.vn.nguyen.multiplication.service.MultiplicationService;
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


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * Created by nals on 1/4/18.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(MultiplicationController.class)
public class MultiplicationControllerTest {

    @MockBean
    private MultiplicationService multiplicationService;

    @Autowired
    private MockMvc mockMvc;

    private JacksonTester<Multiplication> jacksonTester;

    @Before
    public void setUp() throws Exception {
        JacksonTester.initFields(this,new ObjectMapper());
    }

    @Test
    public void getRandomMultiplication_ShouldReturnTrueWhenCallCorrectService() throws Exception{
        //given
        given(multiplicationService.createRandomMultiplication()).willReturn(new Multiplication(50,50));
        //when
        MockHttpServletResponse httpServletResponse = mockMvc.perform(
                get("/multiplications/random").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        //then
        assertThat(httpServletResponse.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(httpServletResponse.getContentAsString()).
                isEqualTo(jacksonTester.write(new Multiplication(50,50)).getJson());
    }

}