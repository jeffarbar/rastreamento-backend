package br.com.send.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.send.model.ModeloModel;

@Repository
public interface ModeloRepository extends JpaRepository<ModeloModel, Long> {

}
