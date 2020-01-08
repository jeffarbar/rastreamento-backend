package br.com.send.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.send.repository.PosicaoAtualPontoMonitoradoRepository;
import br.com.send.util.ConverteUtil;
import br.com.send.util.DataUtil;
import br.com.send.vo.PosicaoAtualPontoMonitoradoVo;

@Service
public class PosicaoAtualPontoMonitoradoService {

	private static final Logger logger = LogManager.getLogger(PosicaoAtualPontoMonitoradoService.class);
	
	@Autowired
	private PosicaoAtualPontoMonitoradoRepository posicaoAtualPontoMonitoradoRepository;
	
	@Autowired
	private ConverteUtil converteUtil;
	
	public PosicaoAtualPontoMonitoradoVo find(Long idPontoMonitorado) throws Exception{
		
		try {
			
			List<Object[]> result = posicaoAtualPontoMonitoradoRepository.findPosicaoAtualByIdPontoMonitorado(idPontoMonitorado);
			if( result != null && !result.isEmpty() ) {
				return converte(result.get(0));
			}
			return new PosicaoAtualPontoMonitoradoVo();
			
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	}
	
	/*
	public List<PosicaoAtualPontoMonitoradoVo> findByUsuario(Long idUsuario) throws Exception{
		
		try {
				
			return posicaoAtualPontoMonitoradoRepository.findPosicaoAtualByIdUsuario(idUsuario)
					.parallelStream().map( this :: converte ).collect(Collectors.toList());
			
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	}
	*/
	
	public List<PosicaoAtualPontoMonitoradoVo> findByEmpresa(Long idEmpresa) throws Exception{
		
		try {
				
			return posicaoAtualPontoMonitoradoRepository.findPosicaoAtualByIdEmpresa(idEmpresa)
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
