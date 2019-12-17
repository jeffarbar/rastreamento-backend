package br.com.send.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.beans.BeanUtils;

import br.com.send.util.DataUtil;
import br.com.send.vo.DispositivoVo;
import br.com.send.vo.PontoMonitoradoVo;

@Entity
@Table(name = "dispositivo")
public class DispositivoModel {

	public DispositivoModel() {}
	
	public DispositivoModel(DispositivoVo dispositivoVo){
		BeanUtils.copyProperties(dispositivoVo , this);
		this.setDtCadastro(DataUtil.getDataAtual() );
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_dispositivo", nullable = false )
	private Long idDispositivo;
	
	@Column(name = "nome", nullable = false )
	private String nome;
	
	@Column(name = "identificador", nullable = false , unique = true )
	private String identificador;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_modelo" , nullable = false)
	private ModeloModel modelo;

	@Column(name = "dt_cadastro", nullable = false )
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtCadastro;
	
	@Column(name = "dt_modificada")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtModificada;
	
	@Column(name = "fg_ativo", columnDefinition = "boolean default true" , nullable = false )
	private Boolean ativo = Boolean.TRUE;

	public Long getIdDispositivo() {
		return idDispositivo;
	}

	public void setIdDispositivo(Long idDispositivo) {
		this.idDispositivo = idDispositivo;
	}

	public Date getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public Date getDtModificada() {
		return dtModificada;
	}

	public void setDtModificada(Date dtModificada) {
		this.dtModificada = dtModificada;
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

	public ModeloModel getModelo() {
		return modelo;
	}

	public void setModelo(ModeloModel modelo) {
		this.modelo = modelo;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

}
