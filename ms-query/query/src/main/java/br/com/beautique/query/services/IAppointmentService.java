package br.com.beautique.query.services;

import br.com.beautique.query.dtos.appointments.FullAppointmentsDTO;

import java.util.List;

public interface IAppointmentService {

    List<FullAppointmentsDTO> listAllAppointments();

    List<FullAppointmentsDTO> listAppointmentsByCustomer(Long customerId);
    List<FullAppointmentsDTO> listAppointmentsByBeautyProcedure(Long beautyProcedureId);
}
