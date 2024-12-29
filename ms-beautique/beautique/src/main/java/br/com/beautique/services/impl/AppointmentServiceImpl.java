package br.com.beautique.services.impl;

import br.com.beautique.dtos.AppointmentDTO;
import br.com.beautique.entities.AppointmentsEntity;
import br.com.beautique.repositories.IAppointmentRepository;
import br.com.beautique.services.IAppointmentService;
import br.com.beautique.utils.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements IAppointmentService {

    @Autowired
    private IAppointmentRepository appointmentRepository;

    private final ConvertUtil<AppointmentsEntity, AppointmentDTO> convertUtil = new ConvertUtil<>(AppointmentsEntity.class, AppointmentDTO.class);

    @Override
    public AppointmentDTO create(AppointmentDTO appointment) {
        var appointmentEntity = convertUtil.convertToSource(appointment);
        return convertUtil.convertToTarget(appointmentRepository.save(appointmentEntity));
    }

    @Override
    public AppointmentDTO update(AppointmentDTO appointment) {
        var findAppointment = appointmentRepository.findById(appointment.getId());

        if(findAppointment.isEmpty())
            throw new RuntimeException("Appointment not found!");

        var appointmentEntity = convertUtil.convertToSource(appointment);
        appointmentEntity.setCreatedAt(findAppointment.get().getCreatedAt());

        return convertUtil.convertToTarget(appointmentRepository.save(appointmentEntity));
    }

    @Override
    public AppointmentDTO delete(AppointmentDTO appointment) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public AppointmentDTO setCustomerToAppointment(AppointmentDTO appointment) {
        return null;
    }
}
