package br.com.send.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.send.service.DispositivoService;
import br.com.send.vo.DispositivoVo;
import br.com.send.vo.PontoMonitoradoVo;
import br.com.send.vo.ResponseVo;

@RestController
@RequestMapping(path = "/dispositivo")
public class DispositivoController extends Controller {

	@Autowired
	private DispositivoService dispositivoSerice;
	
	@GetMapping(path="/pontoMonitorado/{idPontoMonitorado}", produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<List<DispositivoVo>> getPontoMonitorado(@PathVariable("idPontoMonitorado") final Long idPontoMonitorado){
		try {
			return ResponseEntity.ok().body(dispositivoSerice.findAllByPontoMonitorado(idPontoMonitorado)); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
    }

	@GetMapping(path="/usuario/{idUsuario}", produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<List<DispositivoVo>> getUsuario(@PathVariable("idUsuario") final Long idUsuario){
		try {
			return ResponseEntity.ok().body(dispositivoSerice.findAllByUsuario(idUsuario)); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
    }

	@GetMapping(path="/{idDispositivo}/{idPontoMonitorado}", produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<DispositivoVo> get(@PathVariable("idDispositivo") final Long idDispositivo,
    		@PathVariable("idPontoMonitorado") final Long idPontoMonitorado){
		try {
			return ResponseEntity.ok().body(dispositivoSerice.find(idDispositivo,idPontoMonitorado) ); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
    }
	
	@DeleteMapping(path="/{idDispositivo}/{idPontoMonitorado}", produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<ResponseVo> deletar(@PathVariable("idDispositivo") final Long idDispositivo,
    		@PathVariable("idPontoMonitorado") final Long idPontoMonitorado){
		try {
			return ResponseEntity.ok().body(dispositivoSerice.deletar(idDispositivo,idPontoMonitorado) ); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
    }
	
	@PostMapping(path="/", produces = {MediaType.APPLICATION_JSON_VALUE} , consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseVo> salva(@RequestBody DispositivoVo dispositivoVo) {
		try {
			return ResponseEntity.ok().body(dispositivoSerice.salvaAtualiza(dispositivoVo)); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
	}
	

	@PutMapping(path="/", produces = {MediaType.APPLICATION_JSON_VALUE} , consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseVo> atualiza(@RequestBody DispositivoVo dispositivoVo) {
		try {
			return ResponseEntity.ok().body(dispositivoSerice.salvaAtualiza(dispositivoVo)); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
	}
	
}
