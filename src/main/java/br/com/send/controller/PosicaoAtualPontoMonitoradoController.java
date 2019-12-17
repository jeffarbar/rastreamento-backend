package br.com.send.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.send.service.PosicaoAtualPontoMonitoradoService;
import br.com.send.vo.PontoMonitoradoVo;
import br.com.send.vo.PosicaoAtualPontoMonitoradoVo;
import br.com.send.vo.ResponseVo;

@RestController
@RequestMapping(path = "/posicaoAtualPontoMonitorado")
public class PosicaoAtualPontoMonitoradoController extends Controller {

	@Autowired
	public PosicaoAtualPontoMonitoradoService posicaoAtualPontoMonitoradoService;
	
	
	@GetMapping(path="/all/{idUsuario}", produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<List<PosicaoAtualPontoMonitoradoVo>> getAll(@PathVariable("idUsuario") final Long idUsuario){
		try {
			return ResponseEntity.ok().body(posicaoAtualPontoMonitoradoService.findByUsuario(idUsuario) ); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
    }
	
	@GetMapping(path="/{idPontoMonitorado}", produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<PosicaoAtualPontoMonitoradoVo> get(@PathVariable("idPontoMonitorado") final Long idPontoMonitorado){
		try {
			return ResponseEntity.ok().body(posicaoAtualPontoMonitoradoService.find(idPontoMonitorado) ); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
    }
}
