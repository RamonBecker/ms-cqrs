package br.com.beautique.repositories;

import br.com.beautique.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICostumerRepository extends JpaRepository<CustomerEntity, Long> {

}
