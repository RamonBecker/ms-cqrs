package br.com.beautique.query.repositories;

import br.com.beautique.query.dtos.beautyprocedures.BeautyProcedureDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IBeautyProcedureRepository extends MongoRepository<BeautyProcedureDTO, Long> {
}
