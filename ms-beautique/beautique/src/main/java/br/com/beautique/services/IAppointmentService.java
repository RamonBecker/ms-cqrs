package br.com.beautique.services;

import br.com.beautique.dtos.AppointmentDTO;

public interface IAppointmentService {

    AppointmentDTO create(AppointmentDTO appointment);
    AppointmentDTO update(AppointmentDTO appointment);
    void deleteById(Long id);
    AppointmentDTO setCustomerToAppointment(AppointmentDTO appointment);
}
