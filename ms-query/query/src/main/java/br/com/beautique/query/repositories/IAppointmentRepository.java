package br.com.beautique.query.repositories;

import br.com.beautique.query.dtos.appointments.FullAppointmentsDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface IAppointmentRepository extends MongoRepository<FullAppointmentsDTO, Long> {

    @Query("{ 'customerId': ?0 }")
    List<FullAppointmentsDTO> listAppointmentsByCustomer(Long customerId);

    @Query("{ 'beautyProcedureId': ?0 }")
    List<FullAppointmentsDTO> listAppointmentsByBeautyProcedure(Long beautyProcedureId);
}
