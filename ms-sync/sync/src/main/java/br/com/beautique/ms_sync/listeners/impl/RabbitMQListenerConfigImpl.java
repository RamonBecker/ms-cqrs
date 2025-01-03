package br.com.beautique.ms_sync.listeners.impl;

import br.com.beautique.ms_sync.dtos.appointments.FullAppointmentDTO;
import br.com.beautique.ms_sync.dtos.beautyprocedures.BeautyProcedureDTO;
import br.com.beautique.ms_sync.dtos.customers.CustomerDTO;
import br.com.beautique.ms_sync.listeners.IListenerConfig;
import br.com.beautique.ms_sync.services.ISyncService;
import br.com.beautique.ms_sync.utils.SyncLogger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQListenerConfigImpl implements IListenerConfig {


    private final ObjectMapper objectMapper;
    private final ISyncService syncService;

    public RabbitMQListenerConfigImpl(ObjectMapper objectMapper, ISyncService syncService) {
        this.objectMapper = objectMapper;
        this.syncService = syncService;
    }

    @RabbitListener(queues = "customerQueue")
    @Override
    public void listenToCustomerQueue(String message) {
        try {
            var customerDTO = objectMapper.readValue(message, CustomerDTO.class);
            syncService.syncCustomer(customerDTO);
            SyncLogger.info("Message received from queue customerQueue: " + customerDTO.toString());
        } catch (JsonProcessingException e) {
            SyncLogger.error("Error listen customerQueue: " + e.getMessage());
        }
    }

    @RabbitListener(queues = "appoimentQueue")
    @Override
    public void listenToAppointmentQueue(String message) {
        try {
            var appointmentDTO = objectMapper.readValue(message, FullAppointmentDTO.class);
            syncService.syncAppointment(appointmentDTO);
            SyncLogger.info("Message received from queue appoimentQueue: " + appointmentDTO.toString());
        } catch (JsonProcessingException e) {
            SyncLogger.error("Error listen appoimentQueue: " + e.getMessage());
        }
    }

    @RabbitListener(queues = "beautyProcedureQueue")
    @Override
    public void listenToBeautyProcedureQueue(String message) {
        try {
            var beautyProcedureDTO = objectMapper.readValue(message, BeautyProcedureDTO.class);
            syncService.syncBeautyProcedure(beautyProcedureDTO);
            SyncLogger.info("Message received from queue beautyProcedureQueue: " + beautyProcedureDTO.toString());
        } catch (JsonProcessingException e) {
            SyncLogger.error("Error listen beautyProcedureQueue: " + e.getMessage());
        }
    }
}
