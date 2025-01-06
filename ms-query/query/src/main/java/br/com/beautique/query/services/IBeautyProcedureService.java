package br.com.beautique.query.services;

import br.com.beautique.query.dtos.beautyprocedures.BeautyProcedureDTO;

import java.util.List;

public interface IBeautyProcedureService {

    List<BeautyProcedureDTO> listAllBeautyProcedures();

    List<BeautyProcedureDTO> listByNameIgnoreCase(String name);

    List<BeautyProcedureDTO> listByDescriptionIgnoreCase(String description);
}
