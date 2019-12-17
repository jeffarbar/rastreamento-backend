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
import br.com.send.service.NotificacaoService;
import br.com.send.vo.NotificacaoVo;
import br.com.send.vo.ResponseVo;

@RestController
@RequestMapping(path = "/notificacao")
public class NotificacaoController extends Controller {

	@Autowired
	private NotificacaoService notificacaoService;
	
	@GetMapping(path="/{idUsuario}", produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<List<NotificacaoVo>> get(@PathVariable("idUsuario") final Long idUsuario){
		try {
	        return ResponseEntity.ok().body(notificacaoService.findAll(idUsuario)); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
    }
	
	@PostMapping(path="/", produces = {MediaType.APPLICATION_JSON_VALUE} , consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseVo> salva(@RequestBody NotificacaoVo notificacaoVo) {
		try {
			return ResponseEntity.ok().body(notificacaoService.salva(notificacaoVo)); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
	}
	
	@PutMapping(path="/", produces = {MediaType.APPLICATION_JSON_VALUE} , consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseVo> atualiza(@RequestBody NotificacaoVo notificacaoVo) {
		try {
			return ResponseEntity.ok().body(notificacaoService.atualiza(notificacaoVo)); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
	}
}
