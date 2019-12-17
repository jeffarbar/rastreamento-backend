package br.com.send.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.send.controller.DispositivoController;
import br.com.send.model.DispositivoModel;
import br.com.send.model.ModeloModel;
import br.com.send.model.PontoMonitoradoDispositivoModel;
import br.com.send.model.PontoMonitoradoModel;
import br.com.send.repository.DispositivoRepository;
import br.com.send.repository.ModeloRepository;
import br.com.send.repository.PontoMonitoradoDispositivoRepository;
import br.com.send.repository.PontoMonitoradoRepository;
import br.com.send.util.ConverteUtil;
import br.com.send.util.DataUtil;
import br.com.send.vo.DispositivoVo;
import br.com.send.vo.PontoMonitoradoVo;
import br.com.send.vo.ResponseVo;
import javassist.NotFoundException;

@Service
public class DispositivoService {
	
	private static final Logger logger = LogManager.getLogger(DispositivoController.class);
	
	@Autowired
	private ModeloRepository modeloRepository;
	
	@Autowired
	private PontoMonitoradoDispositivoRepository pontoMonitoradoDispositivoRepository;
	
	@Autowired
	private PontoMonitoradoRepository pontoMonitoradoRepository;
	
	@Autowired
	private DispositivoRepository dispositivoRepository;
	
	@Autowired
	private ConverteUtil converteUtil;
	
	
	public ResponseVo atualiza(DispositivoVo dispositivoVo) throws Exception{
		
		try {
		
			DispositivoModel dispositivoModel = dispositivoRepository.findById(dispositivoVo.getIdDispositivo() )
					.orElseThrow(() -> new NotFoundException("Não existe dispositivo para "+dispositivoVo.getIdDispositivo()));
			
			dispositivoModel.setDtModificada(DataUtil.getDataAtual() );
			
			ModeloModel modelo = modeloRepository.findById( dispositivoVo.getIdModelo() )
					.orElseThrow(() -> new NotFoundException("Não existe modelo para "+ dispositivoVo.getIdModelo() )  );
			
			PontoMonitoradoModel pontoMonitorado = pontoMonitoradoRepository.findById( dispositivoVo.getIdPontoMonitorado() )
					.orElseThrow(() -> new NotFoundException("Não existe ponto monitorado para "+ dispositivoVo.getIdPontoMonitorado() )  );
			
			
			dispositivoModel.setModelo( modelo );
			dispositivoModel.setNome(dispositivoVo.getNome());
			dispositivoModel.setIdentificador(dispositivoVo.getIdentificador());
			dispositivoModel = dispositivoRepository.saveAndFlush(dispositivoModel);
			
			PontoMonitoradoDispositivoModel pontoMonitoradoDispositivoModel = pontoMonitoradoDispositivoRepository
					.findByPontoMonitoradoDispositivo(dispositivoVo.getIdPontoMonitorado(), dispositivoVo.getIdDispositivo());
			if( pontoMonitoradoDispositivoModel == null) {
				this.desativaPontoMonitoradoDispositivo(dispositivoVo.getIdDispositivo());
				pontoMonitoradoDispositivoModel = new PontoMonitoradoDispositivoModel();
			}
			
			pontoMonitoradoDispositivoModel.setPontoMonitorado( pontoMonitorado );
			pontoMonitoradoDispositivoModel.setDispositivo(dispositivoModel);
			pontoMonitoradoDispositivoModel.setAtivo(Boolean.TRUE);
			pontoMonitoradoDispositivoModel.setDtDesativada(null);
			pontoMonitoradoDispositivoRepository.saveAndFlush(pontoMonitoradoDispositivoModel);
			
			return new ResponseVo("Dispositivo atualizado");
			
		} catch (NotFoundException e) {
			logger.error("{}", e);
			throw e;
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	}

	private void desativaPontoMonitoradoDispositivo(Long idDispositivo) {
		pontoMonitoradoDispositivoRepository.findByDispositivo(idDispositivo).forEach( pmd ->{
			pmd.setAtivo(Boolean.FALSE);
			pmd.setDtDesativada(DataUtil.getDataAtual());
			pontoMonitoradoDispositivoRepository.saveAndFlush(pmd);
		} );
	}
	
	private ResponseVo salva(DispositivoVo dispositivoVo) throws Exception{
		
		try {
		
			DispositivoModel dispositivoModel = new DispositivoModel(dispositivoVo);
			ModeloModel modelo = modeloRepository.findById( dispositivoVo.getIdModelo() )
					.orElseThrow(() -> new NotFoundException("Não existe modelo para "+ dispositivoVo.getIdModelo() )  );
			
			dispositivoModel.setModelo( modelo );
			dispositivoModel.setNome(dispositivoVo.getNome());
			dispositivoModel.setIdentificador(dispositivoVo.getIdentificador());
			dispositivoModel = dispositivoRepository.saveAndFlush(dispositivoModel);
			
			PontoMonitoradoDispositivoModel pontoMonitoradoDispositivoModel = new PontoMonitoradoDispositivoModel();
			
			PontoMonitoradoModel pontoMonitorado = pontoMonitoradoRepository.findById( dispositivoVo.getIdPontoMonitorado() )
					.orElseThrow(() -> new NotFoundException("Não existe ponto monitorado para "+ dispositivoVo.getIdPontoMonitorado() )  );
			
			pontoMonitoradoDispositivoModel.setPontoMonitorado( pontoMonitorado );
			pontoMonitoradoDispositivoModel.setDispositivo(dispositivoModel);
			pontoMonitoradoDispositivoRepository.saveAndFlush(pontoMonitoradoDispositivoModel);
			return new ResponseVo("Dispositivo salvo");
			
		} catch (NotFoundException e) {
			logger.error("{}", e);
			throw e;	
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	}
	
	public ResponseVo salvaAtualiza(DispositivoVo dispositivoVo) throws Exception{
		
		if(dispositivoVo.getIdDispositivo() == null) {
			return salva(dispositivoVo);
		}
		return atualiza(dispositivoVo);
	}
	
	
	public DispositivoVo find(Long idDispositivo, Long idPontoMonitorado) throws Exception{
		
		try {
			return new DispositivoVo( dispositivoRepository.findById(idDispositivo)
					.orElseThrow(() -> new NotFoundException("Não existe dispositivo para "+idDispositivo)  ), idPontoMonitorado);
		} catch (NotFoundException e) {
			logger.error("{}", e);
			throw e;
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	}
	
	@Transactional
	public ResponseVo deletar(Long idDispositivo, Long idPontoMonitorado) throws Exception{
		
		try {
			DispositivoModel dispositivoModel = dispositivoRepository.findById(idDispositivo )
					.orElseThrow(() -> new NotFoundException("Não existe dispositivo para "+ idDispositivo)  );

			dispositivoModel.setAtivo(Boolean.FALSE);
			dispositivoRepository.save(dispositivoModel);
			
			PontoMonitoradoDispositivoModel pontoMonitoradoDispositivoModel = pontoMonitoradoDispositivoRepository
					.findByPontoMonitoradoDispositivo(idPontoMonitorado, idDispositivo);
			
			pontoMonitoradoDispositivoModel.setAtivo(Boolean.FALSE);
			pontoMonitoradoDispositivoModel.setDtDesativada(DataUtil.getDataAtual());
			pontoMonitoradoDispositivoRepository.save(pontoMonitoradoDispositivoModel);
			
			return new ResponseVo("Ponto de monitoria deletada");
		} catch (NotFoundException e) {
			logger.error("{}", e);
			throw e;
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	}
	
	public List<DispositivoVo> findAllByPontoMonitorado(Long idPontoMonitorado) throws Exception{
		
		try {
			return pontoMonitoradoDispositivoRepository.findDispositivosByIdPontoMonitorado(idPontoMonitorado)
					.parallelStream().map( this :: converte ).collect(Collectors.toList());
		
		}catch (Exception e) {
			 logger.error("{}", e);
			 throw e;
		}
	}
	
	public List<DispositivoVo> findAllByUsuario(Long idUsuario) throws Exception{
		
		try {
			return pontoMonitoradoDispositivoRepository.findDispositivosByIdUsuario(idUsuario)
					.parallelStream().map( this :: converte ).collect(Collectors.toList());
		
		}catch (Exception e) {
			 logger.error("{}", e);
			 throw e;
		}
	}
	
	private DispositivoVo converte(Object[] obj){
	
		DispositivoVo dispositivoVo = new DispositivoVo();
		
		dispositivoVo.setIdDispositivo( Long.valueOf( converteUtil.converterString(obj[0]) ) );
		dispositivoVo.setNome( converteUtil.converterString(obj[1]));
		dispositivoVo.setIdentificador( converteUtil.converterString(obj[2]) );
		dispositivoVo.setDtCadastroDispositivo( DataUtil.formataData( converteUtil.converterString(obj[3]))) ;
		dispositivoVo.setModelo( converteUtil.converterString(obj[4]));
		dispositivoVo.setIdPontoMonitorado( Long.valueOf(converteUtil.converterString(obj[5])));
		
		return dispositivoVo;
	}

}
