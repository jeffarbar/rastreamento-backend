package br.com.send.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.send.model.PosicaoAtualPontoMonitoradoModel;

@Repository
public interface PosicaoAtualPontoMonitoradoRepository extends JpaRepository<PosicaoAtualPontoMonitoradoModel, Long> {

	@Query(value = "SELECT 1 FROM posicao_atual_ponto_monitorado WHERE identificador_dispositivo = ?1 " , nativeQuery = true)
	Integer findIsByIdentificadorDispositivo(String identificadorDispositivo);
	

	@Query(value = "SELECT papm.id_posicao_atual_ponto_monitorado, papm.descricao , "
			+ " papm.latitude , papm.longitude , papm.identificador_dispositivo , papm.dt_cadastro "
			+ " FROM ponto_monitorado_dispositivo pad "  
			+ " INNER JOIN dispositivo d ON( d.id_dispositivo = pad.id_dispositivo  )" 
			+ " INNER JOIN posicao_atual_ponto_monitorado papm ON( papm.identificador_dispositivo = d.identificador )"  
			+ " WHERE pad.id_ponto_monitorado = ?1 AND d.fg_ativo = TRUE AND pad.fg_ativo = TRUE " , nativeQuery = true)
	Object[] findPosicaoAtualByIdPontoMonitorado(Long idPontoMonitorado);
	
	
	@Query(value = "SELECT papm.id_posicao_atual_ponto_monitorado, papm.descricao , "
			+ " papm.latitude , papm.longitude , papm.identificador_dispositivo , papm.dt_cadastro "
			+ " FROM ponto_monitorado_dispositivo pad "  
			+ " INNER JOIN dispositivo d ON( d.id_dispositivo = pad.id_dispositivo  )" 
			+ " INNER JOIN posicao_atual_ponto_monitorado papm ON( papm.identificador_dispositivo = d.identificador )" 
			+ " INNER JOIN usuario_ponto_monitorado upm ON( upm.id_ponto_monitorado = pad.id_ponto_monitorado )"
			+ " WHERE upm.id_usuario = ?1 AND d.fg_ativo = TRUE AND pad.fg_ativo = TRUE  " , nativeQuery = true)
	List<Object[]> findPosicaoAtualByIdUsuario(Long idUsuario);
}

