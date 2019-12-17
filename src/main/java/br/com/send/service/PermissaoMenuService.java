package br.com.send.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.send.model.PerfilModel;
import br.com.send.model.PermissaoMenuModel;
import br.com.send.repository.PerfilRepository;
import br.com.send.repository.PermissaoMenuRepository;
import br.com.send.vo.PermissaoMenuVo;
import br.com.send.vo.ResponseVo;
import javassist.NotFoundException;

@Service
public class PermissaoMenuService {

	@Autowired
	private PermissaoMenuRepository permissaoMenuRepository;
	
	@Autowired
	private PerfilRepository perfilRepository;
	
	private static final Logger logger = LogManager.getLogger(PermissaoMenuService.class);
	
	public void geraPermissaoUsuario( PerfilModel perfilModel ) throws Exception{
		
		try {
			
			PermissaoMenuModel permissaoMenuMapa = getPermissaoMenu( "Mapa", "/maps", "location_on", 0 ,false);
			permissaoMenuMapa.setPerfil(perfilModel);
			permissaoMenuRepository.saveAndFlush(permissaoMenuMapa);
			PermissaoMenuModel permissaoMenuTrajeto = getPermissaoMenu( "Trajeto", "/trajeto", "navigation", 1 ,false);
			permissaoMenuTrajeto.setPerfil(perfilModel);
			permissaoMenuRepository.saveAndFlush(permissaoMenuTrajeto);
			PermissaoMenuModel permissaoMenuPontoMonitorado = getPermissaoMenu( "Ponto Monitorado", "/pontomonitorado", "directions_car", 2,false);
			permissaoMenuPontoMonitorado.setPerfil(perfilModel);
			permissaoMenuRepository.saveAndFlush(permissaoMenuPontoMonitorado);
			PermissaoMenuModel permissaoMenuDispositivo = getPermissaoMenu( "Dispositivo", "/dispositivo", "phonelink_ring", 3, false);
			permissaoMenuDispositivo.setPerfil(perfilModel);
			permissaoMenuRepository.saveAndFlush(permissaoMenuDispositivo);
			PermissaoMenuModel permissaoMenuUsuario = getPermissaoMenu( "Usuário", "/user", "person", 4, false);
			permissaoMenuUsuario.setPerfil(perfilModel);
			permissaoMenuRepository.saveAndFlush(permissaoMenuUsuario);
			PermissaoMenuModel permissaoMenuMensagem = getPermissaoMenu( "Mensagem", "http://localhost:8000/app/index.html", "chat_bubble", 5, true);
			permissaoMenuMensagem.setPerfil(perfilModel);
			permissaoMenuRepository.saveAndFlush(permissaoMenuMensagem);
			
			
		}catch (Exception e) {
			 logger.error("{}", e);
			 throw e;
		}
	}
	
	public void geraPermissaoAdmin( PerfilModel perfilModel ) throws Exception{
		
		try {
			
			PermissaoMenuModel permissaoMenuMapa = getPermissaoMenu( "Mapa", "/maps", "location_on", 0 ,false);
			permissaoMenuMapa.setPerfil(perfilModel);
			permissaoMenuRepository.saveAndFlush(permissaoMenuMapa);
			PermissaoMenuModel permissaoMenuTrajeto = getPermissaoMenu( "Trajeto", "/trajeto", "navigation", 1 ,false);
			permissaoMenuTrajeto.setPerfil(perfilModel);
			permissaoMenuRepository.saveAndFlush(permissaoMenuTrajeto);
			PermissaoMenuModel permissaoMenuPontoMonitorado = getPermissaoMenu( "Ponto Monitorado", "/pontomonitorado", "directions_car", 2,false);
			permissaoMenuPontoMonitorado.setPerfil(perfilModel);
			permissaoMenuRepository.saveAndFlush(permissaoMenuPontoMonitorado);
			PermissaoMenuModel permissaoMenuDispositivo = getPermissaoMenu( "Dispositivo", "/dispositivo", "phonelink_ring", 3, false);
			permissaoMenuDispositivo.setPerfil(perfilModel);
			permissaoMenuRepository.saveAndFlush(permissaoMenuDispositivo);
			PermissaoMenuModel permissaoMenuTipoPontoMonitorado = getPermissaoMenu( "Tipo Ponto Monitorado", "/tipopontomonitorado", "rss_feed", 4, false);
			permissaoMenuTipoPontoMonitorado.setPerfil(perfilModel);
			permissaoMenuRepository.saveAndFlush(permissaoMenuTipoPontoMonitorado);
			PermissaoMenuModel permissaoMenuModelo = getPermissaoMenu( "Modelo", "/modelo", "view_module", 5, false);
			permissaoMenuModelo.setPerfil(perfilModel);
			permissaoMenuRepository.saveAndFlush(permissaoMenuModelo);
			PermissaoMenuModel permissaoMenuUsuario = getPermissaoMenu( "Usuário", "/user", "person", 6, false);
			permissaoMenuUsuario.setPerfil(perfilModel);
			permissaoMenuRepository.saveAndFlush(permissaoMenuUsuario);
			PermissaoMenuModel permissaoMenuMensagem = getPermissaoMenu( "Mensagem", "http://13.90.142.231:8088/#", "chat_bubble", 7, true);
			permissaoMenuMensagem.setPerfil(perfilModel);
			permissaoMenuRepository.saveAndFlush(permissaoMenuMensagem);
			
			
		}catch (Exception e) {
			 logger.error("{}", e);
			 throw e;
		}
	}
	
	private PermissaoMenuModel getPermissaoMenu(String titulo,  String link, String icone , Integer ordem ,Boolean blank) throws Exception{
		
		PermissaoMenuModel permissaoMenuModel = new PermissaoMenuModel();
		
		permissaoMenuModel.setTitulo(titulo);
		permissaoMenuModel.setLink(link);
		permissaoMenuModel.setIcone(icone);
		permissaoMenuModel.setOrdem(ordem);
		permissaoMenuModel.setBlank(blank);
		
		return permissaoMenuModel;
	}
	
	public ResponseVo salvaAtualiza(PermissaoMenuVo permissaoMenuVo) throws Exception{
		
		if(permissaoMenuVo.getIdPermissaoMenu() == null) {
			return salva(permissaoMenuVo);
		}
		return atualiza(permissaoMenuVo);
	}
	
	public ResponseVo salva(PermissaoMenuVo permissaoMenuVo) throws Exception{
		
		try { 
			
			PermissaoMenuModel permissaoMenuModel = new PermissaoMenuModel(permissaoMenuVo);
			
			PerfilModel perfilModel = perfilRepository.findById(permissaoMenuVo.getIdPerfil())
					.orElseThrow(() -> new NotFoundException("Não existe perdil para "+permissaoMenuVo.getIdPerfil())  );
			
			permissaoMenuModel.setPerfil(perfilModel);
			
			permissaoMenuRepository.saveAndFlush(permissaoMenuModel);
			
			return new ResponseVo("Dados salvos com sucesso");
			
		}catch (Exception e) {
			 logger.error("{}", e);
			 throw e;
		}
	}
	
	public ResponseVo atualiza(PermissaoMenuVo permissaoMenuVo) throws Exception{
		
		try { 
			
			PermissaoMenuModel permissaoMenuModel = permissaoMenuRepository.findById(permissaoMenuVo.getIdPermissaoMenu())
		 			.orElseThrow(() -> new NotFoundException("Não existe Permissão Menu para "+permissaoMenuVo.getIdPermissaoMenu())); 
			
			permissaoMenuModel.setTitulo(permissaoMenuVo.getTitulo());
			permissaoMenuModel.setLink(permissaoMenuVo.getLink());
			permissaoMenuModel.setIcone(permissaoMenuVo.getIcone());
			permissaoMenuModel.setOrdem(permissaoMenuVo.getOrdem());
			permissaoMenuModel.setBlank(permissaoMenuVo.getBlank());
			
			PerfilModel perfilModel = perfilRepository.findById(permissaoMenuVo.getIdPerfil())
					.orElseThrow(() -> new NotFoundException("Não existe perdil para "+permissaoMenuVo.getIdPerfil())  );
			
			permissaoMenuModel.setPerfil(perfilModel);
				
			permissaoMenuRepository.saveAndFlush(permissaoMenuModel);
			
			return new ResponseVo("Dados atualizado com sucesso");
			
		}catch (Exception e) {
			 logger.error("{}", e);
			 throw e;
		}
	}
	
	public List<PermissaoMenuVo> findAll(Integer idPerfil) throws Exception{
		
		try {
			return permissaoMenuRepository.findByPerfil_IdPerfil(idPerfil)
				.stream().map(  PermissaoMenuVo :: new ).collect(Collectors.toList());
		}catch (Exception e) {
			logger.error("{}", e);
			 throw e;
		}
	}
}
