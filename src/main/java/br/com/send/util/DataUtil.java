package br.com.send.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.send.service.PontoMonitoradoService;


public class DataUtil {

	private static final Logger logger = LogManager.getLogger(DataUtil.class);
	
	private static TimeZone tz = TimeZone.getTimeZone("America/Sao_Paulo");
	
	private static SimpleDateFormat dateFormatddMMyyyyHHmmss = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	
	
	public static Date getDataAtual() {
		
		Calendar cal = Calendar.getInstance(tz);
		return cal.getTime();
	}
	
	public static String converteData(Date date) {
		return dateFormatddMMyyyyHHmmss.format(date); 
	}

	/**
	 * emtrada yyyy-MM-dd HH:mm:ss.SSS
	 * @param date
	 * @return
	 */
	public static String formataData(String date) {
		try {
			String[] dt = date.split(" ");
			String[] d = dt[0].split("-");
			String[] t = dt[1].split("[.]");
			return d[2] +"/"+ d[1] +"/"+ d[0] +" "+t[0];
		}catch (Exception e) {
			logger.error("Erro ao formatar data " + e);
			return null;
		}
	}
	
	public static void main(String[] args) {
		
		System.out.println( formataData("2019-11-18 02:11:15.621") );
	}
}
