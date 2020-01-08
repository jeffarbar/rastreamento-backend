package br.com.send.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.send.model.EmpresaModel;
import br.com.send.model.NotificacaoModel;
import br.com.send.model.UsuarioModel;
import br.com.send.repository.EmpresaRepository;
import br.com.send.repository.NotificacaoRepository;
import br.com.send.repository.UsuarioRepository;
import br.com.send.util.DataUtil;
import br.com.send.vo.NotificacaoVo;
import br.com.send.vo.ResponseVo;
import javassist.NotFoundException;


@Service
public class NotificacaoService {

	private static final Logger logger = LogManager.getLogger(NotificacaoService.class);
	
	@Autowired
	private NotificacaoRepository notificacaoRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	public ResponseVo salva(NotificacaoVo notificacaoVo)  throws Exception{
		
		try {
		
			NotificacaoModel notificacaoModel = new NotificacaoModel( notificacaoVo );
			this.salvaAtualiza(notificacaoModel, notificacaoVo);
		
			return new ResponseVo("Notificação salva");
			
		} catch (NotFoundException e) {
			logger.error("{}", e);
			throw e;
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	}
	
	public ResponseVo atualiza(NotificacaoVo notificacaoVo) throws Exception{
		
		try {
			
			NotificacaoModel notificacaoModel = notificacaoRepository.findById(notificacaoVo.getIdNotificacao())
					.orElseThrow(() -> new NotFoundException("Não existe notificação para "+notificacaoVo.getIdNotificacao()));
			
			notificacaoModel.setDtModificada(DataUtil.getDataAtual());
			
			this.salvaAtualiza(notificacaoModel, notificacaoVo);
		
			return new ResponseVo("Notificação atualizada");
			
		} catch (NotFoundException e) {
			logger.error("{}", e);
			throw e;
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	}
	
	private void salvaAtualiza(NotificacaoModel notificacaoModel , NotificacaoVo notificacaoVo) throws Exception{
		 
		EmpresaModel empresa = empresaRepository.findById(notificacaoVo.getIdEmpresa())
			.orElseThrow(() -> new NotFoundException("Não existe usuario para "+notificacaoVo.getIdEmpresa()));
		
		notificacaoModel.setEmpresa(empresa);
		notificacaoModel.setDescricao(notificacaoVo.getDescricao());
		notificacaoRepository.saveAndFlush( notificacaoModel );
	}
	
	public List<NotificacaoVo> findAll(Long idEmpresa) throws Exception{
		
		try {
			return notificacaoRepository.findNotificacaoByEmpresaIdEmpresaAndLidaIsFalse(idEmpresa)
					.stream().map(  NotificacaoVo :: new ).collect(Collectors.toList());
		
		}catch (Exception e) {
			 logger.error("{}", e);
			 throw e;
		}
	}
	
	/*
	
	public List<NotificacaoVo> findAll(Long idUsuario){
		
		List<NotificacaoVo> list = new ArrayList();
		
		NotificacaoVo n1 = new NotificacaoVo();
		n1.setId(1l);
		n1.setDescricao("Erro um");
		
		NotificacaoVo n2 = new NotificacaoVo();
		n2.setId(2l);
		n2.setDescricao("Erro dois");
		
		NotificacaoVo n3 = new NotificacaoVo();
		n3.setId(3l);
		n3.setDescricao("Erro tres");
		
		list.add(n1);
		list.add(n2);
		list.add(n3);
		
		return list;
	}
	*/
}
