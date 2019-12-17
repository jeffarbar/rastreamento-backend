package br.com.send.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class SenhaUtil {

	private Random random = new Random();
	
	public String geraSenha(String senha) throws Exception{

		MessageDigest algorithm = MessageDigest.getInstance("MD5");
		return String.format("%040x", new BigInteger(1, algorithm.digest(senha.getBytes("UTF-8"))));       
	}
	
	public String geraChaveTrocaSenha(String email) throws Exception{
		
		email += random.nextInt(1000);
	    MessageDigest md = MessageDigest.getInstance("MD5");
	    md.update(email.getBytes());
	    return String.format("%040x", new BigInteger(1, md.digest()));
	}
	
}
