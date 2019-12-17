package br.com.send.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.send.service.PerfilService;
import br.com.send.vo.PerfilVo;
import br.com.send.vo.ResponseVo;

@RestController
@RequestMapping(path = "/perfil")
public class PerfilController extends Controller{

	@Autowired
	private PerfilService perfilService;
	
	@GetMapping(path="/geraPerfilInicial", produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<ResponseVo> geraPerfilInicial(){
		try {
			return ResponseEntity.ok().body(perfilService.geraPerfilInicial()); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
    }
	
	@GetMapping(path="/{idPerfil}", produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<PerfilVo> get(@PathVariable("idPerfil") final Integer idPerfil){
		try {
			return ResponseEntity.ok().body(perfilService.find(idPerfil)); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
    }
	
	@GetMapping(path="/", produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<List<PerfilVo>> get(){
		try {
			return ResponseEntity.ok().body(perfilService.findAll()); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
    }
	
	
	@PostMapping(path="/", produces = {MediaType.APPLICATION_JSON_VALUE} , consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseVo> salva(@RequestBody PerfilVo perfilVo) {
		try {
			return ResponseEntity.ok().body(perfilService.salvaAtualiza(perfilVo)); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
	}
	

	@PutMapping(path="/", produces = {MediaType.APPLICATION_JSON_VALUE} , consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseVo> atualiza(@RequestBody PerfilVo perfilVo) {
		try {
			return ResponseEntity.ok().body(perfilService.salvaAtualiza(perfilVo)); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
	}
	
}
