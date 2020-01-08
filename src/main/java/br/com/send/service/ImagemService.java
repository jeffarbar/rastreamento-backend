package br.com.send.service;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URI;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ImagemService {

	private static final String caminho = "http://localhost:8082/rastreamento/resources/imagens/";
	
	private static final Logger logger = LogManager.getLogger(ImagemService.class);
	
	private String geraNome() {
		return java.util.UUID.randomUUID().toString();
	}
	
	private String getExtensao(String nomeArquivo) {
		return  FilenameUtils.getExtension(nomeArquivo);
	}
	
	public String salvar(byte[] conteudo , String nomeImagem) throws Exception {

		BufferedImage img = null;
		
		try {
			
			String extensao = getExtensao(nomeImagem);
			String nome = geraNome() + "." + extensao;
			
			URI uri = new URI(caminho + nome);
			 
			img = ImageIO.read(new ByteArrayInputStream(conteudo));
			ImageIO.write(img, extensao , new File(uri) );
			
			return nome;
			
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	}
}
