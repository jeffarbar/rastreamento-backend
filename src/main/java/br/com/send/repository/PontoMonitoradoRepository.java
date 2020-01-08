package br.com.send.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.send.model.PontoMonitoradoModel;

@Repository
public interface PontoMonitoradoRepository  extends JpaRepository<PontoMonitoradoModel, Long> {

	//List<PontoMonitoradoModel> findByAtivoIsTrueAndUsuarios_IdUsuario(Long idUsuario);
	
	List<PontoMonitoradoModel> findByAtivoIsTrueAndEmpresas_IdEmpresa(Long idEmpresa);
}
