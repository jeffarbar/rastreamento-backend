package br.com.send.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.send.model.ModeloModel;
import br.com.send.model.TipoPontoMonitoradoModel;
import br.com.send.repository.ModeloRepository;
import br.com.send.util.DataUtil;
import br.com.send.vo.ModeloVo;
import br.com.send.vo.ResponseVo;
import javassist.NotFoundException;


@Service
public class ModeloService {

	private static final Logger logger = LogManager.getLogger(ModeloService.class);
	
	@Autowired
	private ModeloRepository modeloRepository;
	
	public List<ModeloVo> findAll() throws Exception{
		
		try {
			return modeloRepository.findAll()
				.parallelStream().filter( t -> Boolean.TRUE.equals( t.getAtivo() ))
				.map(  ModeloVo :: new ).collect(Collectors.toList());
		}catch (Exception e) {
			logger.error("{}", e);
			 throw e;
		}
	}
	
	public ResponseVo salvaAtualiza(ModeloVo modeloVo) throws Exception{
		
		if(modeloVo.getIdModelo() == null) {
			return salva(modeloVo);
		}
		return atualiza(modeloVo);
	}
	
	public ResponseVo salva(ModeloVo modeloVo) throws Exception{
		
		ModeloModel modeloModel = new ModeloModel();
		modeloModel.setDescricao(modeloVo.getDescricao());
		modeloModel.setDtCadastro(DataUtil.getDataAtual());
		modeloModel.setAtivo(Boolean.TRUE);
		modeloRepository.saveAndFlush(modeloModel);
		
		return new ResponseVo("Dados salvo com sucesso");
	}

	public ResponseVo atualiza(ModeloVo modeloVo) throws Exception{
	
		try {
			ModeloModel modeloModel = modeloRepository.findById(modeloVo.getIdModelo())
					.orElseThrow(() -> new NotFoundException("Não existe modelo para "+modeloVo.getIdModelo())  );
			
			modeloModel.setDescricao(modeloVo.getDescricao());
			modeloModel.setDtModificada(DataUtil.getDataAtual());
			modeloModel.setAtivo(Boolean.TRUE);
			
			modeloRepository.saveAndFlush(modeloModel);
			
			return new ResponseVo("Dados atualizado com sucesso");
			
		} catch (NotFoundException e) {
			logger.error("{}", e);
			throw e;
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	}
	
	public ModeloVo find(Long id) throws Exception{
		try {
			return new ModeloVo( modeloRepository.findById(id)
					.orElseThrow(() -> new NotFoundException("Não existe modelo para "+id)  ));
		} catch (NotFoundException e) {
			logger.error("{}", e);
			throw e;
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	}
	
	public ResponseVo desativar(Long id) throws Exception{
		
		try {
			ModeloModel modelo = modeloRepository.findById(id)
					.orElseThrow(() -> new NotFoundException("Não existe modelo para "+id) );
			
			modelo.setDtModificada(DataUtil.getDataAtual());
			modelo.setAtivo(Boolean.FALSE);
			modeloRepository.saveAndFlush(modelo);
			
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
	
		ModeloModel modelo = new ModeloModel();
		modelo.setDescricao("Padrão");
		
		modeloRepository.saveAndFlush(modelo);
		
		return new ResponseVo("Dados salvo com sucesso");
	}
}
