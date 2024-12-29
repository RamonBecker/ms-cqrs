package br.com.beautique.services.impl;

import br.com.beautique.configurations.RabbitMQTopicConfig;
import br.com.beautique.services.IBrokerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQServiceImpl implements IBrokerService {

    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;
    private final RabbitMQTopicConfig rabbitMQTopicConfig;

    public RabbitMQServiceImpl(ObjectMapper objectMapper, RabbitTemplate rabbitTemplate, RabbitMQTopicConfig rabbitMQTopicConfig) {
        this.objectMapper = objectMapper;
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitMQTopicConfig = rabbitMQTopicConfig;
    }

    @Override
    public void send(String type, Object data) {
        var routingKey = type + ".#";

        try {

            var jsonData = objectMapper.writeValueAsString(data);
            rabbitTemplate.convertAndSend(rabbitMQTopicConfig.EXCHANGENAME, routingKey, jsonData, message -> {
                message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                return message;
            });

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing data!" + e.getMessage());
        }

    }
}
