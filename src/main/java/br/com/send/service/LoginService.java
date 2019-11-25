package br.com.send.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.send.model.UsuarioModel;
import br.com.send.repository.UsuarioRepository;
import br.com.send.util.SenhaUtil;
import br.com.send.util.TemplateUtil;
import br.com.send.vo.LoginVo;
import br.com.send.vo.ResponseVo;
import br.com.send.vo.UsuarioVo;

@Service
public class LoginService {

	private static final Logger logger = LogManager.getLogger(LoginService.class);
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private TemplateUtil templateUtil;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private SenhaUtil senhaUtil;
	
	public UsuarioVo login(LoginVo loginVo) throws Exception{
		
		try {
			
			String senha = senhaUtil.geraSenha(loginVo.getSenha());
			
			UsuarioModel user = usuarioRepository.findUsuarioByEmailAndSenhaAndAtivoIsTrue(loginVo.getEmail() , senha);
			if( user == null || user.getIdUsuario() == null ) {
				 logger.error("{}", "Usuário não encontrato " + loginVo.getEmail());
				 throw new Exception("Usuário não encontrato " + loginVo.getEmail());
			}
			return new UsuarioVo(user);
		}catch (Exception e) {
			 logger.error("{}", e);
			 throw e;
		}
	}
	
	public ResponseVo esqueceuSenha(LoginVo loginVo) throws Exception{
		
		try {
			
			UsuarioModel user = usuarioRepository.findUsuarioByEmail(loginVo.getEmail());

			String chaveTrocaSenha = senhaUtil.geraChaveTrocaSenha(user.getEmail());
			
			user.setChaveTrocaSenha(chaveTrocaSenha);
			
			emailService.sendMail(user.getEmail(), "Alteração de Senha", templateUtil.getResetSenha(user.getNome(), chaveTrocaSenha));
			
			usuarioRepository.saveAndFlush(user);
			
			return new  ResponseVo("Senha alterada com sucesso");
			
		}catch (Exception e) {
			 logger.error("{}", e);
			 throw e;
		}
	}
	
/*	
	public static void main(String[] args) throws Exception {
		
		EmailService s = new EmailService();
		TemplateUtil t = new TemplateUtil();
		
		s.sendMail("jeffarbar@yahoo.com.br", "Alteração de Senha", t.getResetSenha("jeff", "32asdasdasd879879"));
		
	}
*/
}
