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

import br.com.send.service.TipoPontoMonitoradoService;
import br.com.send.vo.ModeloVo;
import br.com.send.vo.ResponseVo;
import br.com.send.vo.TipoPontoMonitoradoVo;


@RestController
@RequestMapping(path = "/tipoPontoMonitorado")
public class TipoPontoMonitoradoController extends Controller{

	@Autowired
	private TipoPontoMonitoradoService tipoPontoMonitoradoService;
	
	@GetMapping(path="/", produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<List<TipoPontoMonitoradoVo>> get(){
		try {
			return ResponseEntity.ok().body(tipoPontoMonitoradoService.findAll()); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
    }
	
	@GetMapping(path="/{idTipoPontoMonitorado}", produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<TipoPontoMonitoradoVo> get(@PathVariable("idTipoPontoMonitorado") final Long idTipoPontoMonitorado){
		try {
			return ResponseEntity.ok().body(tipoPontoMonitoradoService.find(idTipoPontoMonitorado)); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
    }
	
	@PostMapping(path="/", produces = {MediaType.APPLICATION_JSON_VALUE} , consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseVo> salva(@RequestBody TipoPontoMonitoradoVo tipoPontoMonitoradoVo) {
		try {
			return ResponseEntity.ok().body(tipoPontoMonitoradoService.salvaAtualiza(tipoPontoMonitoradoVo)); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
	}
	
	@PutMapping(path="/", produces = {MediaType.APPLICATION_JSON_VALUE} , consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseVo> atualiza(@RequestBody TipoPontoMonitoradoVo tipoPontoMonitoradoVo) {
		try {
			return ResponseEntity.ok().body(tipoPontoMonitoradoService.salvaAtualiza(tipoPontoMonitoradoVo)); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
	}
	
	@GetMapping(path="/cadastrarInicial", produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<ResponseVo> cadastrarInicial(){
		try {
			return ResponseEntity.ok().body(tipoPontoMonitoradoService.cadastrarInicial()); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
    }
	
	@DeleteMapping(path="/{idTipoPontoMonitorado}", produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<ResponseVo> deletar(@PathVariable("idTipoPontoMonitorado") final Long idTipoPontoMonitorado){
		try {
			return ResponseEntity.ok().body(tipoPontoMonitoradoService.desativar(idTipoPontoMonitorado) ); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
    }
}
