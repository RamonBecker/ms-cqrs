package br.com.beautique.ms_sync.repositories;

import br.com.beautique.ms_sync.dtos.beautyprocedures.BeautyProcedureDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IBeautyProcedureRepository extends MongoRepository<BeautyProcedureDTO, Long> {
}
