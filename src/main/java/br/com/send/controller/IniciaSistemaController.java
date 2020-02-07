package br.com.send.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.send.service.DispositivoService;
import br.com.send.service.IniciaSistemaService;
import br.com.send.vo.DispositivoVo;
import br.com.send.vo.ResponseVo;

@RestController
@RequestMapping(path = "/iniciaSistema")
public class IniciaSistemaController extends Controller {

	@Autowired
	private IniciaSistemaService iniciaSistemaService;
	
	@PostMapping(path="/gera", produces = {MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<ResponseVo> salva() {
		try {
			return ResponseEntity.ok().body(iniciaSistemaService.inicia()); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
	}
}
