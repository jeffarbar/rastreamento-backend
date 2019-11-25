package br.com.send.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.com.send.model.UsuarioModel;



@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

	//@Query(value = "SELECT u FROM UsuarioModel u WHERE u.email = ?1 AND u.senha = ?2 ") 
	UsuarioModel findUsuarioByEmailAndSenhaAndAtivoIsTrue(String email, String senha);
	
	UsuarioModel findUsuarioByEmail(String email);
	
	UsuarioModel findUsuarioByChaveTrocaSenha(String chaveTrocaSenha);
}
