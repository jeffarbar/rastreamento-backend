package br.com.send.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.com.send.model.PontoMonitoradoDispositivoModel;

@Repository
public interface PontoMonitoradoDispositivoRepository extends JpaRepository<PontoMonitoradoDispositivoModel, Long> {

	@Query(value = "SELECT d.id_dispositivo as idDispositivo, "
			+ " d.nome as nomeDispositivo,"
			+ " d.identificador as identificadorDispositivo, "
			+ " d.dt_cadastro as dtCadastroDispositivo, "
			+ " m.descricao as descricaoModelo, "
			+ " pd.id_ponto_monitorado as idPontoMonitorado "
			+ " FROM ponto_monitorado_dispositivo pd "
			+ " INNER JOIN dispositivo d ON( pd.id_dispositivo = d.id_dispositivo ) "
			+ " INNER JOIN modelo m ON( d.id_modelo = m.id_modelo )"
			+ " WHERE pd.fg_ativo = TRUE AND pd.id_ponto_monitorado = ?1 " , nativeQuery = true)
	List<Object[]> findDispositivosByIdPontoMonitorado(Long idPontoMonitorado);
	
	@Query(value = "SELECT d.id_dispositivo as idDispositivo, "
			+ " d.nome as nomeDispositivo,"
			+ " d.identificador as identificadorDispositivo, "
			+ " d.dt_cadastro as dtCadastroDispositivo, "
			+ " m.descricao as descricaoModelo, "
			+ " pd.id_ponto_monitorado as idPontoMonitorado "
			+ " FROM ponto_monitorado_dispositivo pd "
			+ "INNER JOIN dispositivo d ON( pd.id_dispositivo = d.id_dispositivo ) "
			+ "INNER JOIN modelo m ON( d.id_modelo = m.id_modelo ) "
			+ "INNER JOIN usuario_ponto_monitorado upm ON( upm.id_ponto_monitorado = pd.id_ponto_monitorado ) "
			+ " WHERE pd.fg_ativo = TRUE AND upm.id_usuario = ?1 " , nativeQuery = true)
	List<Object[]> findDispositivosByIdUsuario(Long idUsuario);
	
	@Query(value = "select pd from PontoMonitoradoDispositivoModel pd where pd.pontoMonitorado.idPontoMonitorado = ?1 AND pd.dispositivo.idDispositivo = ?2 ")
	PontoMonitoradoDispositivoModel findByPontoMonitoradoDispositivo(Long idPontoMonitorado, Long idDispositivo);
	
	
	List<PontoMonitoradoDispositivoModel> findByDispositivo(Long idDispositivo);
	
}