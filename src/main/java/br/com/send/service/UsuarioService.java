package br.com.send.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.send.enums.PerfilEnum;
import br.com.send.model.EmpresaModel;
import br.com.send.model.UsuarioModel;
import br.com.send.repository.EmpresaRepository;
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
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private PerfilService perfilService;
	
	@Autowired
	private SenhaUtil senhaUtil;
	
	public List<UsuarioVo> findAll(Long idUsuario) throws Exception{
		
		try {
			
			UsuarioModel usuario = usuarioRepository.findById(idUsuario)
				.orElseThrow(() -> new NotFoundException("Não existe usuario para "+idUsuario));
			
			if( usuario.getEmpresa() == null || usuario.getPerfil() == null || usuario.getPerfil().getDescricao().equals( PerfilEnum.USUARIO.getDescricao() ) ) {
				return Collections.singletonList(new UsuarioVo());
			}else if(usuario.getPerfil().getDescricao().equals( PerfilEnum.ADMIN.getDescricao() )) {
				return usuarioRepository.findAll().stream()
						.map( UsuarioVo :: new )
						.collect(Collectors.toList());
			}else {
				return usuarioRepository.findAll().stream()
						.filter( u -> u.getEmpresa() != null && u.getEmpresa().getIdEmpresa().equals( usuario.getEmpresa().getIdEmpresa() ) )
						.map( UsuarioVo :: new )
						.collect(Collectors.toList());
			}
			
			
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	}

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
	
	public ResponseVo geraAdminInicial(EmpresaModel empresaModel) throws Exception{
		
		UsuarioModel usuarioModel = new UsuarioModel();

		usuarioModel.setProfissao("Rastreamento");
		usuarioModel.setSobre("Admin do sistema Send");
		usuarioModel.setEmail("admin@sendsolutions.me");
		usuarioModel.setDtCadastro(DataUtil.getDataAtual());
		usuarioModel.setNome("Send");
		usuarioModel.setSenha( senhaUtil.geraSenha("boris123") );
		usuarioModel.setZoom(ZOOM_MAP_USUARIO);
		usuarioModel.setLatitude(LATITUDE_SAO_PAULO);
		usuarioModel.setLongitude(LONGITUDE_SAO_PAULO);
		usuarioModel.setAtivo(Boolean.TRUE);
		usuarioModel.setChaveTrocaSenha(senhaUtil.geraChaveTrocaSenha(usuarioModel.getEmail()));
		usuarioModel.setPerfil(perfilService.findPerfil(PerfilEnum.ADMIN));
		usuarioModel.setEmpresa(empresaModel);
	
		usuarioRepository.saveAndFlush( usuarioModel );
		return new ResponseVo("Usuário admin salvo");
	}
	
	public ResponseVo atualizaSalva(UsuarioVo userVo) throws Exception{
		
		if(userVo.getIdUsuario() == null) {
			return salva(userVo);
		}else {
			return atualiza(userVo);
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
		 	
		 	usuario.setDtModificada(DataUtil.getDataAtual());
		 	usuario.setEmail(userVo.getEmail());
		 	if(!usuario.getSenha().equals( userVo.getSenha() )) {
		 		usuario.setSenha(senhaUtil.geraSenha(userVo.getSenha())); 
			}
		 	usuario.setNome(userVo.getNome());
		 	usuario.setProfissao(userVo.getProfissao());
		 	usuario.setSobre(userVo.getSobre());
		 	usuario.setAtivo(Boolean.TRUE);
		 	
		 	if( userVo.getIdPerfil() == null ) {
		 		usuario.setPerfil(perfilService.findPerfil(PerfilEnum.USUARIO));
		 	}else{
		 		usuario.setPerfil(perfilService.findPerfil(userVo.getIdPerfil()));
		 	}
		 	
		 	if( userVo.getIdEmpresa() != null ) {
				try {
					usuario.setEmpresa( empresaRepository.findById(userVo.getIdEmpresa())
							.orElseThrow(() -> new NotFoundException("Não existe empresa para "+userVo.getIdEmpresa())  ));
				} catch (NotFoundException e) {
					logger.error("{}", e);
					throw e;
				}catch (Exception e) {
					logger.error("{}", e);
					throw e;
				}
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
			usuarioModel.setAtivo(Boolean.TRUE);
			
			if( userVo.getPerfilVo() == null || userVo.getPerfilVo().getIdPerfil() == null) {
				usuarioModel.setPerfil(perfilService.findPerfil(PerfilEnum.USUARIO));
		 	}else{
		 		usuarioModel.setPerfil(perfilService.findPerfil(userVo.getPerfilVo().getIdPerfil()));
		 	}
			
			if( userVo.getIdEmpresa() != null ) {
				try {
					usuarioModel.setEmpresa( empresaRepository.findById(userVo.getIdEmpresa())
							.orElseThrow(() -> new NotFoundException("Não existe empresa para "+userVo.getIdEmpresa())  ));
				} catch (NotFoundException e) {
					logger.error("{}", e);
					throw e;
				}catch (Exception e) {
					logger.error("{}", e);
					throw e;
				}
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
