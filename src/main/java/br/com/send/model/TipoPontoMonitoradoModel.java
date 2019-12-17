package br.com.send.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.send.util.DataUtil;

@Entity
@Table(name = "tipo_ponto_monitorado")
public class TipoPontoMonitoradoModel {

	public TipoPontoMonitoradoModel() {
		this.setDtCadastro( DataUtil.getDataAtual() );
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_ponto_monitorado", nullable = false )
	private Long idTipoPontoMonitorado;
	
	@Column(name = "descricao", nullable = false )
	private String descricao;

	@Column(name = "dt_cadastro", nullable = false )
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtCadastro;
	
	@Column(name = "dt_modificada")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtModificada;
	
	@Column(name = "fg_ativo", columnDefinition = "boolean default true" , nullable = false )
	private Boolean ativo = Boolean.TRUE;

	public Long getIdTipoPontoMonitorado() {
		return idTipoPontoMonitorado;
	}

	public void setIdTipoPontoMonitorado(Long idTipoPontoMonitorado) {
		this.idTipoPontoMonitorado = idTipoPontoMonitorado;
	}

	public Date getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Date getDtModificada() {
		return dtModificada;
	}

	public void setDtModificada(Date dtModificada) {
		this.dtModificada = dtModificada;
	}
	
}
