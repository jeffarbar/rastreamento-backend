package br.com.send.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.send.model.TipoPontoMonitoradoModel;

@Repository
public interface TipoPontoMonitoradoRepository extends JpaRepository<TipoPontoMonitoradoModel, Long> {

}
