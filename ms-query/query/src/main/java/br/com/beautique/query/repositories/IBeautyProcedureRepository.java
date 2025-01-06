package br.com.beautique.query.repositories;

import br.com.beautique.query.dtos.beautyprocedures.BeautyProcedureDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface IBeautyProcedureRepository extends MongoRepository<BeautyProcedureDTO, Long> {

    @Query("{ 'name' : { $regex: ?0, $options: 'i'}}")
    List<BeautyProcedureDTO> findByNameLikeIgnoreCase(String name);

    @Query("{ 'description' : { $regex: ?0, $options: 'i'}}")
    List<BeautyProcedureDTO> findByDescriptionLikeIgnoreCase(String description);
}
