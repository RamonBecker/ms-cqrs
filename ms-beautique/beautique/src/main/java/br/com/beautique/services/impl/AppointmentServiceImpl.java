package br.com.beautique.services.impl;

import br.com.beautique.dtos.AppointmentDTO;
import br.com.beautique.entities.AppointmentsEntity;
import br.com.beautique.entities.BeautyProceduresEntity;
import br.com.beautique.entities.CustomerEntity;
import br.com.beautique.repositories.IAppointmentRepository;
import br.com.beautique.repositories.IBeautyProcedureRepository;
import br.com.beautique.repositories.ICostumerRepository;
import br.com.beautique.services.IAppointmentService;
import br.com.beautique.utils.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements IAppointmentService {

    @Autowired
    private IAppointmentRepository appointmentRepository;

    @Autowired
    private IBeautyProcedureRepository beautyProcedureRepository;

    @Autowired
    private ICostumerRepository costumerRepository;

    private final ConvertUtil<AppointmentsEntity, AppointmentDTO> convertUtil = new ConvertUtil<>(AppointmentsEntity.class, AppointmentDTO.class);

    @Override
    public AppointmentDTO create(AppointmentDTO appointment) {
        var appointmentEntity = convertUtil.convertToSource(appointment);
        return convertUtil.convertToTarget(appointmentRepository.save(appointmentEntity));
    }

    @Override
    public AppointmentDTO update(AppointmentDTO appointment) {
        var findAppointment = findAppointmentById(appointment.getId());

        var appointmentEntity = convertUtil.convertToSource(appointment);
        appointmentEntity.setCreatedAt(findAppointment.getCreatedAt());

        return convertUtil.convertToTarget(appointmentRepository.save(appointmentEntity));
    }

    @Override
    public void deleteById(Long id) {
        var findAppointment = findAppointmentById(id);
        appointmentRepository.delete(findAppointment);
    }

    @Override
    public AppointmentDTO setCustomerToAppointment(AppointmentDTO appointment) {
        var findCustomer = findCustomerById(appointment.getCustomer());
        var findBeautyProcedures = findBeautyProceduresById(appointment.getBeautyProcedure());
        var findAppointment = findAppointmentById(appointment.getId());

        findAppointment.setCustomer(findCustomer);
        findAppointment.setBeautyProcedures(findBeautyProcedures);
        findAppointment.setAppointmentsOpen(false);

        return buildAppointmentsDTO(appointmentRepository.save(findAppointment));
    }

    private AppointmentsEntity findAppointmentById(Long id) {
        return appointmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Appointment not found!"));
    }

    private BeautyProceduresEntity findBeautyProceduresById(Long id) {
        return beautyProcedureRepository.findById(id).orElseThrow(() -> new RuntimeException("Beauty Procedures not found!"));
    }

    private CustomerEntity findCustomerById(Long id) {
        return costumerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found!"));
    }

    private AppointmentDTO buildAppointmentsDTO(AppointmentsEntity appointments) {
        return AppointmentDTO.builder()
                .id(appointments.getId())
                .beautyProcedure(appointments.getBeautyProcedures().getId())
                .dateTime(appointments.getDateTime())
                .appointmentsOpen(appointments.getAppointmentsOpen())
                .customer(appointments.getCustomer().getId())
                .build();
    }


}
