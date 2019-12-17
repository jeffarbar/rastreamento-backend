package br.com.send.vo;

import org.springframework.beans.BeanUtils;

import br.com.send.model.DispositivoModel;
import br.com.send.model.ModeloModel;
import br.com.send.util.DataUtil;

public class DispositivoVo {

	public DispositivoVo() {}
	
	public DispositivoVo(DispositivoModel dispositivo, Long idPontoMonitorado) {
		
		this.setIdPontoMonitorado(idPontoMonitorado);
		if( dispositivo != null ){
			BeanUtils.copyProperties(dispositivo,this);
			if(dispositivo.getDtCadastro() != null) {
				this.setDtCadastroDispositivo( DataUtil.converteData(dispositivo.getDtCadastro()) );
			}
			ModeloModel model = dispositivo.getModelo();
			if(model != null) {
				this.setIdModelo(model.getIdModelo());
				this.setModelo(model.getDescricao() );
			}
		}
	}
	
	private Long idDispositivo;
	
	private Long idPontoMonitorado;
	
	private String nome;

	private String identificador;

	private String modelo;
	
	private Long idModelo;
	
	private String dtCadastroDispositivo;
	

	
	public Long getIdModelo() {
		return idModelo;
	}

	public void setIdModelo(Long idModelo) {
		this.idModelo = idModelo;
	}

	public String getDtCadastroDispositivo() {
		return dtCadastroDispositivo;
	}

	public void setDtCadastroDispositivo(String dtCadastroDispositivo) {
		this.dtCadastroDispositivo = dtCadastroDispositivo;
	}

	public Long getIdDispositivo() {
		return idDispositivo;
	}

	public void setIdDispositivo(Long idDispositivo) {
		this.idDispositivo = idDispositivo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	public Long getIdPontoMonitorado() {
		return idPontoMonitorado;
	}

	public void setIdPontoMonitorado(Long idPontoMonitorado) {
		this.idPontoMonitorado = idPontoMonitorado;
	}

	

}
