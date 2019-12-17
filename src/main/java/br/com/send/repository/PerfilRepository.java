package br.com.send.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.send.model.PerfilModel;

public interface PerfilRepository extends JpaRepository<PerfilModel, Integer> {

	PerfilModel findByDescricao(String descricao);
}
