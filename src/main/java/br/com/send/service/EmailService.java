package br.com.send.service;

import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

@Service
public class EmailService {

	private static final Logger logger = LogManager.getLogger(EmailService.class);
	
	@Value("${email.chave.api}") 
	private String chaveApiEMail;
	
	@Value("${email.remetente}") 
	private String remetente;
	
	@Value("${email.endpoint}") 
	private String endPoint;
	
	@Value("${email.content}") 
	private String contentEmail;
	
	//@Autowired 
	//private JavaMailSender mailSender;
	  
	/*
	public void sendMail(String destino, String assunto, String conteudoHtml) throws Exception{

	    MimeMessage mail = mailSender.createMimeMessage();
	
	    MimeMessageHelper helper = new MimeMessageHelper( mail );
	    helper.setTo( destino );
	    helper.setSubject( assunto );
	    helper.setText(conteudoHtml, true);
	    mailSender.send(mail);
		
    }
	*/
	
	public void sendMail(String destino, String assunto, String conteudoHtml) throws Exception{
		
		try {
		
			Email from = new Email(remetente);
		    Email to = new Email(destino);
		    Content content = new Content( contentEmail , conteudoHtml);
		    Mail mail = new Mail(from, assunto, to, content);
	
		    SendGrid sg = new SendGrid(chaveApiEMail);
		    Request request = new Request();
	
	        request.setMethod(Method.POST);
	        request.setEndpoint(endPoint);
	        request.setBody(mail.build());
	        Response response = sg.api(request);
        
	        logger.info("Envio de email por API, codigo: "+response.getStatusCode()+ ". headers: "+response.getHeaders());
	        
		}catch (Exception e) {
			 logger.error("{}", e);
			 throw e;
		}
	 }
}
