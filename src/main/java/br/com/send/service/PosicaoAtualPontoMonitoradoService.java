package br.com.send.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.send.model.PosicaoAtualPontoMonitoradoModel;
import br.com.send.repository.PosicaoAtualPontoMonitoradoRepository;
import br.com.send.util.ConverteUtil;
import br.com.send.util.DataUtil;
import br.com.send.vo.DispositivoVo;
import br.com.send.vo.PosicaoAtualPontoMonitoradoVo;
import br.com.send.vo.ResponseVo;
import br.com.send.vo.UsuarioVo;
import javassist.NotFoundException;

@Service
public class PosicaoAtualPontoMonitoradoService {

	private static final Logger logger = LogManager.getLogger(PosicaoAtualPontoMonitoradoService.class);
	
	@Autowired
	private PosicaoAtualPontoMonitoradoRepository posicaoAtualPontoMonitoradoRepository;
	
	@Autowired
	private ConverteUtil converteUtil;
	
	public ResponseVo salva(PosicaoAtualPontoMonitoradoVo posicaoAtualPontoMonitoradoVo) {
	
		try {
			posicaoAtualPontoMonitoradoRepository.deleteByIdentificadorDispositivo(
					posicaoAtualPontoMonitoradoVo.getIdentificadorDispositivo());
			
			PosicaoAtualPontoMonitoradoModel posicaoAtualDispositivoModel = new PosicaoAtualPontoMonitoradoModel(posicaoAtualPontoMonitoradoVo);
			posicaoAtualPontoMonitoradoRepository.saveAndFlush(posicaoAtualDispositivoModel);
			
			return new ResponseVo("Posição atual do dispositivo salva");
		
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	} 
	
	public PosicaoAtualPontoMonitoradoVo find(Long idPontoMonitorado) throws Exception{
		
		try {
			
			Object[] result = posicaoAtualPontoMonitoradoRepository.findPosicaoAtualByIdPontoMonitorado(idPontoMonitorado);
			if( result != null && result.length > 0 ) {
				return converte(result);
			}
			return new PosicaoAtualPontoMonitoradoVo();
			
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	}
	
	public List<PosicaoAtualPontoMonitoradoVo> findByUsuario(Long idUsuario) throws Exception{
		
		try {
				
			return posicaoAtualPontoMonitoradoRepository.findPosicaoAtualByIdUsuario(idUsuario)
					.parallelStream().map( this :: converte ).collect(Collectors.toList());
			
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	}
	
	private PosicaoAtualPontoMonitoradoVo converte(Object[] obj){
		
		PosicaoAtualPontoMonitoradoVo posicaoAtualPontoMonitoradoVo = new PosicaoAtualPontoMonitoradoVo();
		
		posicaoAtualPontoMonitoradoVo.setIdPosicaoAtualPontoMonitorado(Long.valueOf( converteUtil.converterString(obj[0]) ));
		posicaoAtualPontoMonitoradoVo.setDescricao(converteUtil.converterString(obj[1]));
		
		if(obj[2] != null) {
			posicaoAtualPontoMonitoradoVo.setLatitude(Double.valueOf( converteUtil.converterString(obj[2]) ));
		}
		if(obj[3] != null) {
			posicaoAtualPontoMonitoradoVo.setLongitude(Double.valueOf( converteUtil.converterString(obj[3]) ));
		}
		posicaoAtualPontoMonitoradoVo.setIdentificadorDispositivo(converteUtil.converterString(obj[4]));
		posicaoAtualPontoMonitoradoVo.setData( DataUtil.formataData( converteUtil.converterString(obj[5])) );
		
		return posicaoAtualPontoMonitoradoVo;
	}

}
