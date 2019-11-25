package br.com.send.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.send.enums.PerfilEnum;
import br.com.send.model.PerfilModel;
import br.com.send.repository.PerfilRepository;
import br.com.send.vo.PerfilVo;
import br.com.send.vo.ResponseVo;
import javassist.NotFoundException;

@Service
public class PerfilService {

	private static final Logger logger = LogManager.getLogger(PerfilService.class);
	
	@Autowired
	private PerfilRepository perfilRepository;
	
	@Autowired
	private PermissaoMenuService permissaoMenuService;
	
	public ResponseVo geraPerfilInicial() throws Exception{
		
		PerfilModel PerfilModelAdmin = new PerfilModel();
		PerfilModelAdmin.setDescricao(PerfilEnum.ADMIN.getDescricao());
		permissaoMenuService.geraPermissaoAdmin(perfilRepository.saveAndFlush(PerfilModelAdmin) );
		
		PerfilModel PerfilModelUsuario = new PerfilModel();
		PerfilModelUsuario.setDescricao(PerfilEnum.USUARIO.getDescricao());
		permissaoMenuService.geraPermissaoUsuario(perfilRepository.saveAndFlush(PerfilModelUsuario) );
	
		return new ResponseVo("Dados salvos com sucesso");
	}
	
	public PerfilModel findPerfil(PerfilEnum perfilEnum) throws Exception{
		return perfilRepository.findByDescricao(perfilEnum.getDescricao());
	}
	
	public PerfilModel findPerfil(Integer id) throws Exception{
		try {
			return perfilRepository.findById(id)
					.orElseThrow(() -> new NotFoundException("Não existe perdil para "+id)  );
		} catch (NotFoundException e) {
			logger.error("{}", e);
			throw e;
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	}
	
	public PerfilVo find(Integer idPerfil) throws Exception{
		
		try {
			return new PerfilVo( perfilRepository.findById(idPerfil)
					.orElseThrow(() -> new NotFoundException("Não existe perfil para "+idPerfil)  ));
		} catch (NotFoundException e) {
			logger.error("{}", e);
			throw e;
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	}
	
	public List<PerfilVo> findAll() throws Exception{
		
		try {
			return perfilRepository.findAll()
				.stream().map(  PerfilVo :: new ).collect(Collectors.toList());
		}catch (Exception e) {
			logger.error("{}", e);
			 throw e;
		}
	}
	
	public ResponseVo salvaAtualiza(PerfilVo perfilVo) throws Exception{
		
		if(perfilVo.getIdPerfil() == null) {
			return salva(perfilVo);
		}
		return atualiza(perfilVo);
	}

	public ResponseVo salva(PerfilVo perfilVo) throws Exception{
		
		try { 
			
			PerfilModel perfil = new PerfilModel();
			perfil.setDescricao(perfilVo.getDescricao());
			perfilRepository.saveAndFlush(perfil);
			
			return new ResponseVo("Dados salvos com sucesso");
			
		}catch (Exception e) {
			 logger.error("{}", e);
			 throw e;
		}
	}
	
	public ResponseVo atualiza(PerfilVo perfilVo) throws Exception{
		
		try { 
			PerfilModel perfil = perfilRepository.findById(perfilVo.getIdPerfil())
		 			.orElseThrow(() -> new NotFoundException("Não existe perfil para "+perfilVo.getIdPerfil())); 
			
			perfil.setDescricao(perfilVo.getDescricao());
			perfilRepository.saveAndFlush(perfil);
			
			return new ResponseVo("Dados atualizado com sucesso");
			
		}catch (Exception e) {
			 logger.error("{}", e);
			 throw e;
		}
	}
	
}
