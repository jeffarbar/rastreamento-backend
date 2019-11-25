package br.com.send.service;

import java.util.List;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.send.model.TipoPontoMonitoradoModel;
import br.com.send.repository.TipoPontoMonitoradoRepository;
import br.com.send.util.DataUtil;
import br.com.send.vo.ResponseVo;
import br.com.send.vo.TipoPontoMonitoradoVo;
import javassist.NotFoundException;

@Service
public class TipoPontoMonitoradoService {

	@Autowired
	private TipoPontoMonitoradoRepository tipoPontoMonitoradoRepository;
	
	private static final Logger logger = LogManager.getLogger(TipoPontoMonitoradoService.class);
	
	public ResponseVo desativar(Long id) throws Exception{
		
		try {
			TipoPontoMonitoradoModel tipoPontoMonitoradoModel = tipoPontoMonitoradoRepository.findById(id)
					.orElseThrow(() -> new NotFoundException("Não existe Tipo de Ponto Monitorado para "+id)  );
			
			tipoPontoMonitoradoModel.setDtModificada(DataUtil.getDataAtual());
			tipoPontoMonitoradoModel.setAtivo(Boolean.FALSE);
			tipoPontoMonitoradoRepository.saveAndFlush(tipoPontoMonitoradoModel);
			
			return new ResponseVo("Dados atualizado com sucesso");
			
		} catch (NotFoundException e) {
			logger.error("{}", e);
			throw e;
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	}
	
	public TipoPontoMonitoradoVo find(Long id) throws Exception{
		try {
			return new TipoPontoMonitoradoVo( tipoPontoMonitoradoRepository.findById(id)
					.orElseThrow(() -> new NotFoundException("Não existe Tipo Ponto Monitorado para "+id)  ));
		} catch (NotFoundException e) {
			logger.error("{}", e);
			throw e;
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	}
	
	public List<TipoPontoMonitoradoVo> findAll() throws Exception{
		
		try {
			return tipoPontoMonitoradoRepository.findAll()
					.parallelStream().filter( t -> Boolean.TRUE.equals( t.getAtivo() ))
					.map( TipoPontoMonitoradoVo :: new ).collect(Collectors.toList());
		
		}catch (Exception e) {
			 logger.error("{}", e);
			 throw e;
		}
	}
	
	public ResponseVo salvaAtualiza(TipoPontoMonitoradoVo tipoPontoMonitoradoVo) throws Exception{
		
		if(tipoPontoMonitoradoVo.getIdTipoPontoMonitorado() == null) {
			return salva(tipoPontoMonitoradoVo);
		}
		return atualiza(tipoPontoMonitoradoVo);
	}
	
	public ResponseVo salva(TipoPontoMonitoradoVo tipoPontoMonitoradoVo) throws Exception{
		
		TipoPontoMonitoradoModel tipoPontoMonitorado = new TipoPontoMonitoradoModel();
		tipoPontoMonitorado.setDescricao(tipoPontoMonitoradoVo.getDescricao());
		tipoPontoMonitorado.setDtCadastro(DataUtil.getDataAtual());
		tipoPontoMonitorado.setAtivo(Boolean.TRUE);
		tipoPontoMonitoradoRepository.saveAndFlush(tipoPontoMonitorado);
		
		return new ResponseVo("Dados salvo com sucesso");
	}
	
	public ResponseVo atualiza(TipoPontoMonitoradoVo tipoPontoMonitoradoVo) throws Exception{
		
		try {
			TipoPontoMonitoradoModel tipoPontoMonitoradoModel = tipoPontoMonitoradoRepository.findById(tipoPontoMonitoradoVo.getIdTipoPontoMonitorado())
					.orElseThrow(() -> new NotFoundException("Não existe Tipo de Ponto Monitorado para "+tipoPontoMonitoradoVo.getIdTipoPontoMonitorado())  );
			
			tipoPontoMonitoradoModel.setDescricao(tipoPontoMonitoradoVo.getDescricao());
			tipoPontoMonitoradoModel.setDtModificada(DataUtil.getDataAtual());
			tipoPontoMonitoradoModel.setAtivo(Boolean.TRUE);
			
			tipoPontoMonitoradoRepository.saveAndFlush(tipoPontoMonitoradoModel);
			
			return new ResponseVo("Dados atualizado com sucesso");
			
		} catch (NotFoundException e) {
			logger.error("{}", e);
			throw e;
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	}
	
	public ResponseVo cadastrarInicial() throws Exception{
		
		TipoPontoMonitoradoModel tipoPontoMonitorado = new TipoPontoMonitoradoModel();
		tipoPontoMonitorado.setDescricao("Equipe de Campo");
		
		tipoPontoMonitoradoRepository.saveAndFlush(tipoPontoMonitorado);
		
		return new ResponseVo("Dados salvo com sucesso");
	}
}
