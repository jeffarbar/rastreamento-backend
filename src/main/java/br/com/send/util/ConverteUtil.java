package br.com.send.util;

import org.springframework.stereotype.Service;

@Service
public class ConverteUtil {

	public String converterString(Object obj) {
		  
		if( obj == null ) {
			return null;
		}
		return obj.toString();
	}
}
