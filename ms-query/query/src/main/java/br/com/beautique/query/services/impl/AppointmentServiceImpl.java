package br.com.beautique.query.services.impl;

import br.com.beautique.query.dtos.appointments.FullAppointmentsDTO;
import br.com.beautique.query.repositories.IAppointmentRepository;
import br.com.beautique.query.services.IAppointmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements IAppointmentService {

    private final IAppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(IAppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<FullAppointmentsDTO> listAllAppointments() {
        try {
            return appointmentRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error listing all appointments!");
        }
    }

    @Override
    public List<FullAppointmentsDTO> listAppointmentsByCustomer(Long customerId) {
        try {
            return appointmentRepository.listAppointmentsByCustomer(customerId);
        } catch (Exception e) {
            throw new RuntimeException("Error listing all appointments by customer!");
        }
    }

    @Override
    public List<FullAppointmentsDTO> listAppointmentsByBeautyProcedure(Long beautyProcedureId) {
        try {
            return appointmentRepository.listAppointmentsByBeautyProcedure(beautyProcedureId);
        } catch (Exception e) {
            throw new RuntimeException("Error listing all appointments by beauty procedure!");
        }
    }
}
