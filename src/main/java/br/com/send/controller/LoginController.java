package br.com.send.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.send.service.LoginService;
import br.com.send.vo.LoginVo;
import br.com.send.vo.ResetSenhaVo;
import br.com.send.vo.ResponseVo;
import br.com.send.vo.UsuarioVo;

@RestController
@RequestMapping(path = "/login")
public class LoginController extends Controller {

	@Autowired
	public LoginService loginService;
	
	@PostMapping(path="/session", produces = {MediaType.APPLICATION_JSON_VALUE} , 
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UsuarioVo> loggin(@RequestBody LoginVo loginVo) {
		try {
			return ResponseEntity.ok(loginService.login(loginVo));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
	}
	
	@PostMapping(path="/esqueceuSenha",
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseVo> esqueceuSenha(@RequestBody LoginVo loginVo) {
		try {
			return ResponseEntity.ok(loginService.esqueceuSenha(loginVo));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
	}
}