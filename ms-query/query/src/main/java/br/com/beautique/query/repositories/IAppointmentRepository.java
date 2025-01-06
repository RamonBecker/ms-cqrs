package br.com.beautique.query.repositories;

import br.com.beautique.query.dtos.appointments.FullAppointmentsDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IAppointmentRepository extends MongoRepository<FullAppointmentsDTO, Long> {
}
