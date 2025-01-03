package br.com.beautique.ms_sync.services.impl;

import br.com.beautique.ms_sync.dtos.appointments.FullAppointmentDTO;
import br.com.beautique.ms_sync.dtos.beautyprocedures.BeautyProcedureDTO;
import br.com.beautique.ms_sync.dtos.customers.CustomerDTO;
import br.com.beautique.ms_sync.services.ISyncService;
import br.com.beautique.ms_sync.utils.SyncLogger;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class SynServiceImpl implements ISyncService {

    private final AppointmentServiceImpl appointmentService;
    private final BeautyProcedureServiceImpl beautyProcedureService;
    private final CustomerServiceImpl customerService;

    public SynServiceImpl(AppointmentServiceImpl appointmentService, BeautyProcedureServiceImpl beautyProcedureService, CustomerServiceImpl customerService) {
        this.appointmentService = appointmentService;
        this.beautyProcedureService = beautyProcedureService;
        this.customerService = customerService;
    }


    @Override
    public void syncCustomer(CustomerDTO customer) {
        try {
            SyncLogger.info("Saving customer:" + customer.getId());
            customerService.save(customer);
            SyncLogger.info("Update appointment customer:" + customer.getId());
            appointmentService.updateCustomer(customer);
        } catch (Exception e) {
            SyncLogger.error("Error saving customer: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void syncAppointment(FullAppointmentDTO appointment) {
        try {
            SyncLogger.info("Saving appointment:" + appointment.getId());
            appointmentService.save(appointment);
        } catch (Exception e) {
            SyncLogger.error("Error saving appointment: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void syncBeautyProcedure(BeautyProcedureDTO beautyProcedure) {
        try {
            SyncLogger.info("Saving beauty procedure:" + beautyProcedure.getId());
            beautyProcedureService.save(beautyProcedure);
            SyncLogger.info("Update appointment beauty procedure:" + beautyProcedure.getId());
            appointmentService.updateBeautyProcedure(beautyProcedure);
        } catch (Exception e) {
            SyncLogger.error("Error saving beauty procedure: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }
}
