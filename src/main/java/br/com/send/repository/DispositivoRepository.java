package br.com.send.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.send.model.DispositivoModel;

@Repository
public interface DispositivoRepository extends JpaRepository<DispositivoModel, Long> {

	
}
