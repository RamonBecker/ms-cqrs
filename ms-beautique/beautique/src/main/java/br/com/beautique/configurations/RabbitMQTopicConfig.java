package br.com.beautique.configurations;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQTopicConfig {

    public final String EXCHANGENAME = "beautiqueExchange";

    @Bean
    TopicExchange exchange(){
        return new TopicExchange(EXCHANGENAME);
    }

    @Bean
    public Queue customerQueue(){
        return new Queue("customerQueue", true);
    }

    @Bean
    Binding bindingCustomer(Queue customerQueue, TopicExchange exchange){
        return BindingBuilder.bind(customerQueue).to(exchange).with("customer.#");
    }
}
