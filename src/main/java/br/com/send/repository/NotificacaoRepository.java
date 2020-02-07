package br.com.send.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.send.model.NotificacaoModel;

@Repository
public interface NotificacaoRepository extends JpaRepository<NotificacaoModel, Long> {

	//List<NotificacaoModel> findNotificacaoByUsuarioIdUsuarioAndLidaIsFalse(Long id);
	
	List<NotificacaoModel> findNotificacaoByEmpresaIdEmpresaAndLidaIsFalse(Long id);
}
