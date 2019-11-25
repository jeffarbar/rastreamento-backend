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
import br.com.send.service.ModeloService;
import br.com.send.vo.ModeloVo;
import br.com.send.vo.ResponseVo;
import br.com.send.vo.TipoPontoMonitoradoVo;
import br.com.send.vo.UsuarioVo;

@RestController
@RequestMapping(path = "/modelo")
public class ModeloController extends Controller {

	@Autowired
	private ModeloService modeloService;
	
	@GetMapping(path="/{idModelo}", produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<ModeloVo> get(@PathVariable("idModelo") final Long idModelo){
		try {
			return ResponseEntity.ok().body(modeloService.find(idModelo)); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
    }
	
	@GetMapping(path="/", produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<List<ModeloVo>> get(){
		try {
	        return ResponseEntity.ok().body(modeloService.findAll()); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
    }
	
	@PostMapping(path="/", produces = {MediaType.APPLICATION_JSON_VALUE} , consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseVo> salva(@RequestBody ModeloVo modeloVo) {
		try {
			return ResponseEntity.ok().body(modeloService.salvaAtualiza(modeloVo)); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
	}
	
	@PutMapping(path="/", produces = {MediaType.APPLICATION_JSON_VALUE} , consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseVo> atualiza(@RequestBody ModeloVo modeloVo) {
		try {
			return ResponseEntity.ok().body(modeloService.salvaAtualiza(modeloVo)); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
	}
	
	@GetMapping(path="/cadastrarInicial", produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<ResponseVo> cadastrarInicial(){
		try {
			return ResponseEntity.ok().body(modeloService.cadastrarInicial()); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
    }
	
	@DeleteMapping(path="/{idModelo}", produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<ResponseVo> deletar(@PathVariable("idModelo") final Long idModelo){
		try {
			return ResponseEntity.ok().body(modeloService.desativar(idModelo) ); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
    }
}
