package br.com.beautique.ms_sync.services;

import br.com.beautique.ms_sync.dtos.appointments.FullAppointmentDTO;
import br.com.beautique.ms_sync.dtos.beautyprocedures.BeautyProcedureDTO;
import br.com.beautique.ms_sync.dtos.customers.CustomerDTO;

public interface IAppointmentService {

    void save(FullAppointmentDTO appointment);
    void updateCustomer(CustomerDTO customer);
    void updateBeautyProcedure(BeautyProcedureDTO beautyProcedure);

}
