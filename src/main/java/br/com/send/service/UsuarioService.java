package br.com.send.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.send.enums.PerfilEnum;
import br.com.send.model.UsuarioModel;
import br.com.send.repository.UsuarioRepository;
import br.com.send.util.DataUtil;
import br.com.send.util.SenhaUtil;
import br.com.send.vo.ResetSenhaVo;
import br.com.send.vo.ResponseVo;
import br.com.send.vo.UsuarioVo;
import javassist.NotFoundException;


@Service
public class UsuarioService {
	
	private static final Logger logger = LogManager.getLogger(UsuarioService.class);
	
	private static Double LATITUDE_SAO_PAULO = -23.5475;
	
	private static Double LONGITUDE_SAO_PAULO = -46.63611;
	
	private static Integer ZOOM_MAP_USUARIO = 10;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PerfilService perfilService;
	
	@Autowired
	private SenhaUtil senhaUtil;
	
	public List<UsuarioVo> findAll() throws Exception{
		
		try {
			return usuarioRepository.findAll().stream().map( UsuarioVo :: new )
					.collect(Collectors.toList());
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	}
	
	public UsuarioVo find(Long id) throws Exception{
		try {
			return new UsuarioVo( usuarioRepository.findById(id)
					.orElseThrow(() -> new NotFoundException("Não existe usuario para "+id)  ));
		} catch (NotFoundException e) {
			logger.error("{}", e);
			throw e;
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	}
	
	public ResponseVo atualiza(UsuarioVo userVo) throws Exception{
		
		UsuarioModel usuario = null;
		try {
			
			this.validaSenha(userVo.getSenha(), userVo.getConfirmarSenha());
			
		 	usuario = usuarioRepository.findById(userVo.getIdUsuario())
		 			.orElseThrow(() -> new NotFoundException("Não existe usuario para "+userVo.getIdUsuario()));
		 	
		 	// Se alterou o email, analisar se o novo email já não existe cadastrado
		 	if(!usuario.getEmail().equals(userVo.getEmail())) {
		 		this.validaEmail(userVo.getEmail());
		 	}
		 	
		 	usuario.setCompanhia(userVo.getCompanhia());
		 	usuario.setDtModificada(DataUtil.getDataAtual());
		 	usuario.setEmail(userVo.getEmail());
		 	usuario.setSenha(senhaUtil.geraSenha(userVo.getSenha())); 
		 	usuario.setNome(userVo.getNome());
		 	usuario.setProfissao(userVo.getProfissao());
		 	usuario.setSobre(userVo.getSobre());
		 	
		 	if( userVo.getPerfilVo() == null || userVo.getPerfilVo().getIdPerfil() == null) {
		 		usuario.setPerfil(perfilService.findPerfil(PerfilEnum.USUARIO));
		 	}else{
		 		usuario.setPerfil(perfilService.findPerfil(userVo.getPerfilVo().getIdPerfil()));
		 	}
		 	
		 	usuarioRepository.saveAndFlush( usuario );

		 	return new ResponseVo("Usuário atualizado");
		 	
		} catch (NotFoundException e) {
			logger.error("{}", e);
			throw e;
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	}
	
	public ResponseVo alterarSenha(ResetSenhaVo resetSenhaVo) throws Exception{
		
		try {
			
			this.validaSenha(resetSenhaVo.getSenha(), resetSenhaVo.getConfirmarSenha());
			
			UsuarioModel user = usuarioRepository.findUsuarioByChaveTrocaSenha(resetSenhaVo.getChaveTroca());
			user.setSenha( senhaUtil.geraSenha(resetSenhaVo.getSenha()) );
			usuarioRepository.saveAndFlush(user );
			return new ResponseVo("Senha Alterada com sucesso");
			
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	}
	
	
	public ResponseVo salva(UsuarioVo userVo) throws Exception{
		
		try {
			
			this.validaSenha(userVo.getSenha(), userVo.getConfirmarSenha());
			this.validaEmail(userVo.getEmail());
			
			userVo.setSenha( senhaUtil.geraSenha(userVo.getSenha()) );
			
			UsuarioModel usuarioModel = new UsuarioModel( userVo);
			usuarioModel.setZoom(ZOOM_MAP_USUARIO);
			usuarioModel.setLatitude(LATITUDE_SAO_PAULO);
			usuarioModel.setLongitude(LONGITUDE_SAO_PAULO);
			usuarioModel.setChaveTrocaSenha(senhaUtil.geraChaveTrocaSenha(userVo.getEmail()));
			
			if( userVo.getPerfilVo() == null || userVo.getPerfilVo().getIdPerfil() == null) {
				usuarioModel.setPerfil(perfilService.findPerfil(PerfilEnum.USUARIO));
		 	}else{
		 		usuarioModel.setPerfil(perfilService.findPerfil(userVo.getPerfilVo().getIdPerfil()));
		 	}
			
			usuarioRepository.saveAndFlush( usuarioModel );
			return new ResponseVo("Usuário salvo");
			
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	}
	
	private void validaSenha(String senha, String confirmarSenha) throws Exception{
		if(!senha.equals( confirmarSenha )) {
			logger.error("Senhas não confere");
			throw new Exception("Senhas não confere");
		}
	}
	
	private void validaEmail(String email) throws Exception{
	
		UsuarioModel usuarioModel = usuarioRepository.findUsuarioByEmail(email);
		if( usuarioModel != null ) {
			logger.error("Email já cadastrado");
			throw new Exception("Email já cadastrado");
		}
	}

	public ResponseVo upload(Long idUsuario,  MultipartFile fotoPerfil) throws Exception{
		
		try {
			
			System.out.println( fotoPerfil.getOriginalFilename() ); 
			return new ResponseVo("Foto do perfil salva");
			
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
		
	}
}
