package br.com.send.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.send.vo.ResponseVo;


@Service
public class IniciaSistemaService {

	private static final Logger logger = LogManager.getLogger(IniciaSistemaService.class);
	
	@Autowired
	private PerfilService perfilService;
	
	@Autowired
	private EmpresaService empresaService;
	
	public ResponseVo inicia() throws Exception{
		
		try {
			
			perfilService.geraPerfilInicial();
			empresaService.geraEmpresaInicial();
			
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
		
		return new ResponseVo("Dados salvo com sucesso");
	}
}
