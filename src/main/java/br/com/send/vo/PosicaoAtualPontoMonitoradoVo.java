package br.com.send.vo;

import org.springframework.beans.BeanUtils;

import br.com.send.model.PosicaoAtualPontoMonitoradoModel;
import br.com.send.model.UsuarioModel;

public class PosicaoAtualPontoMonitoradoVo {

	public PosicaoAtualPontoMonitoradoVo() {}
	
	public PosicaoAtualPontoMonitoradoVo(PosicaoAtualPontoMonitoradoModel posicaoAtualPontoMonitorado) {
		if( posicaoAtualPontoMonitorado != null ){
			BeanUtils.copyProperties(posicaoAtualPontoMonitorado,this);
		}
	}
	
	private Long idPosicaoAtualPontoMonitorado;
	
	private String descricao;

	private Double latitude;
	
	private Double longitude;
	
	private String identificadorDispositivo;
	
	private String data;

	public Long getIdPosicaoAtualPontoMonitorado() {
		return idPosicaoAtualPontoMonitorado;
	}

	public void setIdPosicaoAtualPontoMonitorado(Long idPosicaoAtualPontoMonitorado) {
		this.idPosicaoAtualPontoMonitorado = idPosicaoAtualPontoMonitorado;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getIdentificadorDispositivo() {
		return identificadorDispositivo;
	}

	public void setIdentificadorDispositivo(String identificadorDispositivo) {
		this.identificadorDispositivo = identificadorDispositivo;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	
}
