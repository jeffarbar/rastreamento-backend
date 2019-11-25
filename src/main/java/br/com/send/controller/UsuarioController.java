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
import org.springframework.web.multipart.MultipartFile;

import br.com.send.service.UsuarioService;
import br.com.send.vo.ResetSenhaVo;
import br.com.send.vo.ResponseVo;
import br.com.send.vo.UsuarioVo;

@RestController
@RequestMapping(path = "/usuario")
public class UsuarioController extends Controller {
	 
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping(path="/", produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<List<UsuarioVo>> get(){
		try {
			return ResponseEntity.ok().body(usuarioService.findAll()); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
    }
	
	@GetMapping(path="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<UsuarioVo> get(@PathVariable("id") final Long id){	
		try {
			return ResponseEntity.ok().body(usuarioService.find(id)); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
    }
	
	@PostMapping(path="/", produces = {MediaType.APPLICATION_JSON_VALUE} , consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseVo> salva(@RequestBody UsuarioVo userVo) {
		try {
			return ResponseEntity.ok().body(usuarioService.salva(userVo)); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
	}
	
	@PutMapping(path="/", produces = {MediaType.APPLICATION_JSON_VALUE} , consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseVo> atualiza(@RequestBody UsuarioVo userVo) {
		try {
			return ResponseEntity.ok().body(usuarioService.atualiza(userVo)); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
	}
	
	@PostMapping(path="/upload" , produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE} )
    public ResponseEntity<ResponseVo> upload(Long idUsuario, MultipartFile fotoPerfil) {
		
		try {
			return ResponseEntity.ok().body(usuarioService.upload(idUsuario, fotoPerfil)); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
    }
	
	@PostMapping(path="/alterarSenha" , produces = {MediaType.APPLICATION_JSON_VALUE} , consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseVo> alterarSenha(@RequestBody ResetSenhaVo resetSenhaVo) {
		
		try {
			return ResponseEntity.ok().body(usuarioService.alterarSenha(resetSenhaVo)); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
    }
}
