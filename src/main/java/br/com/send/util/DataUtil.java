package br.com.send.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DataUtil {

	private static final Logger logger = LogManager.getLogger(DataUtil.class);
	
	private static TimeZone tz = TimeZone.getTimeZone("GMT-3");
	
	private static int GMT = -3;
	
	private static SimpleDateFormat dateFormatddMMyyyyHHmmss = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	
	private static SimpleDateFormat dateFormatyyyyMMddHHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	
	public static Date getDataAtual() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}
	
	/*
	public static String converteData(Date date) {
		if(date == null)return null;

		return dateFormatddMMyyyyHHmmss.format(date); 
	}
	*/

	/**
	 * emtrada yyyy-MM-dd HH:mm:ss.SSS
	 * @param date
	 * @return
	 */
	public static String formataData(String date) {
		try {
			return converterDataGMT3( dateFormatyyyyMMddHHmmss.parse(date) );
		}catch (Exception e) {
			logger.error("Erro ao formatar data " + e);
			return null;
		}
	}
	
	
	
	public static String converterDataGMT3(Date data) {
		if(data == null)return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		cal.add(Calendar.HOUR_OF_DAY, GMT );
		return dateFormatddMMyyyyHHmmss.format(cal.getTime()); 
	}
	
	public static void main(String[] args) {
		
		System.out.println( formataData("2020-01-16 01:10:09.054") );
	}
}
