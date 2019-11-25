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
import br.com.send.service.PermissaoMenuService;
import br.com.send.vo.PermissaoMenuVo;
import br.com.send.vo.ResponseVo;

@RestController
@RequestMapping(path = "/permissaoMenux")
public class PermissaoMenuController extends Controller{

	@Autowired
	private PermissaoMenuService permissaoMenuService;
	
	@GetMapping(path="/{idPerfil}", produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<List<PermissaoMenuVo>> get(@PathVariable("idPerfil") final Integer idPerfil){
		try {
			return ResponseEntity.ok().body(permissaoMenuService.findAll(idPerfil)); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
    }
	
	
	@PostMapping(path="/", produces = {MediaType.APPLICATION_JSON_VALUE} , consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseVo> salva(@RequestBody PermissaoMenuVo permissaoMenuVo) {
		try {
			return ResponseEntity.ok().body(permissaoMenuService.salvaAtualiza(permissaoMenuVo)); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
	}
	

	@PutMapping(path="/", produces = {MediaType.APPLICATION_JSON_VALUE} , consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseVo> atualiza(@RequestBody PermissaoMenuVo permissaoMenuVo) {
		try {
			return ResponseEntity.ok().body(permissaoMenuService.salvaAtualiza(permissaoMenuVo)); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
	}
	
}
