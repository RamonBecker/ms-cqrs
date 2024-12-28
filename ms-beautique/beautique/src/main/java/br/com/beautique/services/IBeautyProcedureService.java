package br.com.beautique.services;

import br.com.beautique.dtos.BeautyProcedureDTO;

public interface IBeautyProcedureService {

    BeautyProcedureDTO create(BeautyProcedureDTO beautyProcedure);
    void deleteById(Long id);
    BeautyProcedureDTO update(BeautyProcedureDTO beautyProcedure);
}
