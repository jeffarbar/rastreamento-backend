package br.com.send.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.send.model.DispositivoModel;
import br.com.send.model.EmpresaModel;

@Repository
public interface EmpresaRepository  extends JpaRepository<EmpresaModel, Long> {

	EmpresaModel findByCnpjAndAtivoFalse(String cnpj);
}
