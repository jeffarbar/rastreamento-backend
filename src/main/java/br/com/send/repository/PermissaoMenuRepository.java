package br.com.send.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.send.model.PermissaoMenuModel;

public interface PermissaoMenuRepository extends JpaRepository<PermissaoMenuModel, Long> {

	List<PermissaoMenuModel> findByPerfil_IdPerfil(Integer idPerfil);
}
