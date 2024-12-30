package br.com.beautique.services.impl;

import br.com.beautique.dtos.AppointmentDTO;
import br.com.beautique.dtos.BeautyProcedureDTO;
import br.com.beautique.dtos.CustomerDTO;
import br.com.beautique.dtos.FullAppointmentDTO;
import br.com.beautique.entities.AppointmentsEntity;
import br.com.beautique.entities.BeautyProceduresEntity;
import br.com.beautique.entities.CustomerEntity;
import br.com.beautique.repositories.IAppointmentRepository;
import br.com.beautique.repositories.IBeautyProcedureRepository;
import br.com.beautique.repositories.ICostumerRepository;
import br.com.beautique.services.IAppointmentService;
import br.com.beautique.services.IBrokerService;
import br.com.beautique.utils.ConvertUtil;
import org.modelmapper.ModelMapper;
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

    @Autowired
    private IBrokerService brokerService;

    private final ConvertUtil<AppointmentsEntity, AppointmentDTO> convertUtil = new ConvertUtil<>(AppointmentsEntity.class, AppointmentDTO.class);

    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public AppointmentDTO create(AppointmentDTO appointment) {
        var appointmentEntity = convertUtil.convertToSource(appointment);
        var createAppointment = appointmentRepository.save(appointmentEntity);

        sendAppoimentsTOQueue(createAppointment);

        return convertUtil.convertToTarget(createAppointment);
    }

    @Override
    public AppointmentDTO update(AppointmentDTO appointment) {
        var findAppointment = findAppointmentById(appointment.getId());

        var appointmentEntity = convertUtil.convertToSource(appointment);
        appointmentEntity.setCreatedAt(findAppointment.getCreatedAt());

        var updateAppointment = appointmentRepository.save(appointmentEntity);

        sendAppoimentsTOQueue(updateAppointment);

        return convertUtil.convertToTarget(updateAppointment);
    }

    @Override
    public AppointmentDTO setCustomerToAppointment(AppointmentDTO appointment) {
        var findCustomer = findCustomerById(appointment.getCustomer());
        var findBeautyProcedures = findBeautyProceduresById(appointment.getBeautyProcedure());
        var findAppointment = findAppointmentById(appointment.getId());

        findAppointment.setCustomer(findCustomer);
        findAppointment.setBeautyProcedures(findBeautyProcedures);
        findAppointment.setAppointmentsOpen(false);

        var updateAppointment = appointmentRepository.save(findAppointment);

        sendAppoimentsTOQueue(updateAppointment);

        return buildAppointmentsDTO(updateAppointment);
    }

    @Override
    public void deleteById(Long id) {
        var findAppointment = findAppointmentById(id);
        appointmentRepository.delete(findAppointment);
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

    private void sendAppoimentsTOQueue(AppointmentsEntity appointments) {

        var customerDTO = appointments.getCustomer() != null ? modelMapper.map(appointments.getCustomer(), CustomerDTO.class) : null;
        var beautyProceduresDTO = appointments.getBeautyProcedures() != null ? modelMapper.map(appointments.getBeautyProcedures(), BeautyProcedureDTO.class) : null;

        var fullAppointmentDTO = FullAppointmentDTO.builder()
                .id(appointments.getId())
                .dateTime(appointments.getDateTime())
                .appoimentsOpen(appointments.getAppointmentsOpen())
                .customer(customerDTO)
                .beautyProcedure(beautyProceduresDTO)
                .build();

        brokerService.send("appoiments", fullAppointmentDTO);
    }
}
