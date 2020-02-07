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
import org.springframework.web.multipart.MultipartFile;

import br.com.send.service.EmpresaService;
import br.com.send.vo.EmpresaVo;
import br.com.send.vo.ImagemVo;
import br.com.send.vo.ResponseVo;


@RestController
@RequestMapping(path = "/empresa")
public class EmpresaController extends Controller {

	@Autowired
	private EmpresaService empresaService;
	
	@GetMapping(path="/", produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<List<EmpresaVo>> get(){
		try {
			return ResponseEntity.ok().body(empresaService.findAll()); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
    }
	
	@GetMapping(path="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<EmpresaVo> get(@PathVariable("id") final Long id){	
		try {
			return ResponseEntity.ok().body(empresaService.find(id)); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
    }
	
	@GetMapping(path="/usuario/{idUsuario}", produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<List<EmpresaVo>> getUsuario(@PathVariable("idUsuario") final Long idUsuario){	
		try {
			return ResponseEntity.ok().body(empresaService.findUsuario(idUsuario)); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
    }
	
	@PostMapping(path="/", produces = {MediaType.APPLICATION_JSON_VALUE} , consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseVo> salva(@RequestBody EmpresaVo empresaVo) {
		try {
			return ResponseEntity.ok().body(empresaService.salva(empresaVo)); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
	}
	
	
	@PutMapping(path="/", produces = {MediaType.APPLICATION_JSON_VALUE} , consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseVo> atualiza(@RequestBody EmpresaVo empresaVo) {
		try {
			return ResponseEntity.ok().body(empresaService.atualiza(empresaVo)); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
	}
	
	@DeleteMapping(path="/{idEmpresa}", produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<ResponseVo> deletar(@PathVariable("idEmpresa") final Long idEmpresa){
		try {
			return ResponseEntity.ok().body(empresaService.desativar(idEmpresa) ); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
    }
	
	@PostMapping(path="/upload" , produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE} )
    public ResponseEntity<ResponseVo> upload(Long idEmpresa, MultipartFile fotoEmpresa) {
		
		try {
			return ResponseEntity.ok().body(empresaService.upload(idEmpresa, fotoEmpresa)); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
    }
	
	@GetMapping(path="/download/{idEmpresa}" ,  produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<ImagemVo> download(@PathVariable("idEmpresa") final Long idEmpresa) {
		
		try {
			return ResponseEntity.ok().body(empresaService.download(idEmpresa)); 
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
    }
}
