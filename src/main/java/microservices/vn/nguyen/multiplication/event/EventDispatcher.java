package microservices.vn.nguyen.multiplication.event;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by nals on 1/11/18.
 */
@Component
public class EventDispatcher {

    private RabbitTemplate rabbitTemplate;
    private String multiplicationExchange;
    //routing key to use to send this particular event
    private String multiplicationSolvedRoutingKey;

    @Autowired
    public EventDispatcher(final RabbitTemplate rabbitTemplate,
                           @Value("${multiplication.exchange}") final String multiplicationExchange,
                           @Value("${multiplication.solved.key}") final String multiplicationSolvedRoutingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.multiplicationExchange = multiplicationExchange;
        this.multiplicationSolvedRoutingKey = multiplicationSolvedRoutingKey;
    }

    public void send(final MultiplicationSolvedEvent multiplicationSolvedEvent){
        rabbitTemplate.convertAndSend(multiplicationExchange,multiplicationSolvedRoutingKey,multiplicationSolvedEvent);
    }
}
