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
import br.com.send.service.PontoMonitoradoService;
import br.com.send.vo.PontoMonitoradoVo;
import br.com.send.vo.ResponseVo;
import br.com.send.vo.UsuarioVo;

@RestController
@RequestMapping(path = "/pontoMonitorado")
public class PontoMonitoradoController extends Controller {
		
	@Autowired
	private PontoMonitoradoService pontoMonitoradoService;
	
	@GetMapping(path="/empresa/{idEmpresa}", produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<List<PontoMonitoradoVo>> getByEmpresa(@PathVariable("idEmpresa") final Long idEmpresa){
		try {
			return ResponseEntity.ok().body(pontoMonitoradoService.findByEmpresa(idEmpresa)); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
    }
	
	@GetMapping(path="/{idPontoMonitorado}", produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<PontoMonitoradoVo> get(@PathVariable("idPontoMonitorado") final Long idPontoMonitorado){
		try {
			return ResponseEntity.ok().body(pontoMonitoradoService.find(idPontoMonitorado) ); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
    }
	
	@DeleteMapping(path="/{idPontoMonitorado}", produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<ResponseVo> deletar(@PathVariable("idPontoMonitorado") final Long idPontoMonitorado){
		try {
			return ResponseEntity.ok().body(pontoMonitoradoService.deletar(idPontoMonitorado) ); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
    }

	@PostMapping(path="/", produces = {MediaType.APPLICATION_JSON_VALUE} , consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseVo> salva(@RequestBody PontoMonitoradoVo pontoMonitoradoVo) {
		try {
			return ResponseEntity.ok().body(pontoMonitoradoService.salvaAtualiza(pontoMonitoradoVo)); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
	}
	
	@PutMapping(path="/", produces = {MediaType.APPLICATION_JSON_VALUE} , consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseVo> atualiza(@RequestBody PontoMonitoradoVo pontoMonitoradoVo) {
		try {
			return ResponseEntity.ok().body(pontoMonitoradoService.salvaAtualiza(pontoMonitoradoVo)); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
	}
}
