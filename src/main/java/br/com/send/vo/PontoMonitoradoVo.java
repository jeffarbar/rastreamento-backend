package br.com.send.vo;

import org.springframework.beans.BeanUtils;

import br.com.send.model.PontoMonitoradoModel;
import br.com.send.util.DataUtil;

public class PontoMonitoradoVo {

	public PontoMonitoradoVo() {}
	
	public PontoMonitoradoVo(PontoMonitoradoModel pontoMonitorado) {
		if( pontoMonitorado != null ){
			BeanUtils.copyProperties(pontoMonitorado,this);
			if(pontoMonitorado.getDtCadastro() != null) {
				this.setDtCadastro(DataUtil.converteData(pontoMonitorado.getDtCadastro()) );
			}
			if(pontoMonitorado.getTipoPontoMonitorado() != null) {
				this.setTipoPontoMonitorado(pontoMonitorado.getTipoPontoMonitorado().getDescricao());
				this.setIdTipoPontoMonitorado(pontoMonitorado.getTipoPontoMonitorado().getIdTipoPontoMonitorado());
			}
		}
	}
	
	
	private Long idPontoMonitorado;
	
	private String nome;

	private String identificador;
	
	private String tipoPontoMonitorado;
	
	private Long idTipoPontoMonitorado;
	
	private Long idEmpresa;
	
	private Boolean ativo;
	
	private String dtCadastro;

	public Boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Long getIdTipoPontoMonitorado() {
		return idTipoPontoMonitorado;
	}

	public void setIdTipoPontoMonitorado(Long idTipoPontoMonitorado) {
		this.idTipoPontoMonitorado = idTipoPontoMonitorado;
	}

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Long getIdPontoMonitorado() {
		return idPontoMonitorado;
	}

	public void setIdPontoMonitorado(Long idPontoMonitorado) {
		this.idPontoMonitorado = idPontoMonitorado;
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

	public String getTipoPontoMonitorado() {
		return tipoPontoMonitorado;
	}

	public void setTipoPontoMonitorado(String tipoPontoMonitorado) {
		this.tipoPontoMonitorado = tipoPontoMonitorado;
	}

	public String getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(String dtCadastro) {
		this.dtCadastro = dtCadastro;
	}
	
}
