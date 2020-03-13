package br.com.send.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.send.model.DispositivoModel;
import br.com.send.model.EmpresaModel;
import br.com.send.model.PontoMonitoradoModel;
import br.com.send.model.TipoPontoMonitoradoModel;
import br.com.send.model.UsuarioModel;
import br.com.send.repository.EmpresaRepository;
import br.com.send.repository.PontoMonitoradoRepository;
import br.com.send.repository.TipoPontoMonitoradoRepository;
import br.com.send.repository.UsuarioRepository;
import br.com.send.util.DataUtil;
import br.com.send.vo.PontoMonitoradoVo;
import br.com.send.vo.ResponseVo;
import br.com.send.vo.UsuarioVo;
import javassist.NotFoundException;

@Service
public class PontoMonitoradoService {

	private static final Logger logger = LogManager.getLogger(PontoMonitoradoService.class);
	
	@Autowired
	private PontoMonitoradoRepository pontoMonitoradoRepository;
	
	@Autowired
	private TipoPontoMonitoradoRepository tipoPontoMonitoradoRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	public List<PontoMonitoradoVo> findByEmpresa(Long idEmpresa) throws Exception{
		
		try {
			return pontoMonitoradoRepository.findByAtivoIsTrueAndEmpresas_IdEmpresa( idEmpresa ).stream().map(  PontoMonitoradoVo :: new )
					.collect(Collectors.toList());
		
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	}
	
	/*
	public List<PontoMonitoradoVo> findByUsuario(Long idUsuario) throws Exception{
		
		try {
			return pontoMonitoradoRepository.findByAtivoIsTrueAndUsuarios_IdUsuario( idUsuario ).stream().map(  PontoMonitoradoVo :: new )
					.collect(Collectors.toList());
		
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	}
	*/
	
	public PontoMonitoradoVo find(Long idPontoMonitorado) throws Exception{
		
		try {
			return new PontoMonitoradoVo( pontoMonitoradoRepository.findById(idPontoMonitorado)
					.orElseThrow(() -> new NotFoundException("Não existe ponto monitorado para "+idPontoMonitorado)  ));
		} catch (NotFoundException e) {
			logger.error("{}", e);
			throw e;
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	}
	
	public ResponseVo deletar(Long idPontoMonitorado) throws Exception{
		
		try {
			PontoMonitoradoModel pontoMonitoradoModel = pontoMonitoradoRepository.findById(idPontoMonitorado )
					.orElseThrow(() -> new NotFoundException("Não existe ponto monitorado para "+ idPontoMonitorado)  );

			pontoMonitoradoModel.setAtivo(Boolean.FALSE);
			pontoMonitoradoRepository.saveAndFlush(pontoMonitoradoModel);
			
			return new ResponseVo("Ponto de monitoria deletada");
		} catch (NotFoundException e) {
			logger.error("{}", e);
			throw e;
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	}
	
	public ResponseVo salvaAtualiza(PontoMonitoradoVo pontoMonitoradoVo) throws Exception{
		
		if(pontoMonitoradoVo.getIdPontoMonitorado() == null) {
			return salva(pontoMonitoradoVo);
		}
		return atualiza(pontoMonitoradoVo);
	}
	
	private ResponseVo atualiza(PontoMonitoradoVo pontoMonitoradoVo) throws Exception{
	
		try {
			
			PontoMonitoradoModel pontoMonitoradoModel = pontoMonitoradoRepository.findById(pontoMonitoradoVo.getIdPontoMonitorado() )
					.orElseThrow(() -> new NotFoundException("Não existe ponto monitorado para "+ pontoMonitoradoVo.getIdPontoMonitorado())  );

			TipoPontoMonitoradoModel tipoPontoMonitorado = tipoPontoMonitoradoRepository.findById(pontoMonitoradoVo.getIdTipoPontoMonitorado() )
					.orElseThrow(() -> new NotFoundException("Não existe tipo de ponto monitorado para "+ pontoMonitoradoVo.getIdTipoPontoMonitorado())  );
			
			pontoMonitoradoModel.setTipoPontoMonitorado(tipoPontoMonitorado);			
			pontoMonitoradoModel.setAtivo(pontoMonitoradoVo.isAtivo());
			pontoMonitoradoModel.setDtModificada(DataUtil.getDataAtual());
			pontoMonitoradoModel.setIdentificador(pontoMonitoradoVo.getIdentificador());
			pontoMonitoradoModel.setNome(pontoMonitoradoVo.getNome());
			
			pontoMonitoradoRepository.saveAndFlush( pontoMonitoradoModel );
			
			return new ResponseVo("Ponto monitorado atualizado");
			
		} catch (NotFoundException e) {
			logger.error("{}", e);
			throw e;
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	}
	
	private ResponseVo salva(PontoMonitoradoVo pontoMonitoradoVo) throws Exception{
		
		try {
			
			PontoMonitoradoModel pontoMonitoradoModel = pontoMonitoradoRepository.findByIdentificadorAndAtivoFalse(pontoMonitoradoVo.getIdentificador());
			
			if(pontoMonitoradoModel != null) {
				pontoMonitoradoModel.setAtivo(Boolean.TRUE);
				pontoMonitoradoModel.setDtCadastro( DataUtil.getDataAtual() );
			}else {
				pontoMonitoradoModel = new PontoMonitoradoModel(pontoMonitoradoVo);
			}

			TipoPontoMonitoradoModel tipoPontoMonitorado = tipoPontoMonitoradoRepository.findById(pontoMonitoradoVo.getIdTipoPontoMonitorado() )
					.orElseThrow(() -> new NotFoundException("Não existe tipo de ponto monitorado para "+ pontoMonitoradoVo.getIdTipoPontoMonitorado())  );
			
			EmpresaModel empresaModel = empresaRepository.findById(pontoMonitoradoVo.getIdEmpresa())
					.orElseThrow(() -> new NotFoundException("Não existe usuario para "+pontoMonitoradoVo.getIdEmpresa()) );
			
			pontoMonitoradoModel.setTipoPontoMonitorado( tipoPontoMonitorado );
			pontoMonitoradoModel.setEmpresas( Stream.of(empresaModel).collect(Collectors.toSet()));
			
			pontoMonitoradoRepository.saveAndFlush( pontoMonitoradoModel );
		
			return new ResponseVo("Ponto monitorado salvo");
			
		} catch (NotFoundException e) {
			logger.error("{}", e);
			throw e;
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	}
	
}
