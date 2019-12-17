package br.com.send.model;


import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.BeanUtils;

import br.com.send.util.DataUtil;
import br.com.send.vo.PontoMonitoradoVo;
import br.com.send.vo.UsuarioVo;

@Entity
@Table(name = "ponto_monitorado")
public class PontoMonitoradoModel {

	public PontoMonitoradoModel() {}
	
	public PontoMonitoradoModel(PontoMonitoradoVo pontoMonitoradoVo){
		BeanUtils.copyProperties(pontoMonitoradoVo , this);
		this.setDtCadastro( DataUtil.getDataAtual() );
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ponto_monitorado", nullable = false )
	private Long idPontoMonitorado;
	
	@Column(name = "nome", nullable = false )
	private String nome;
	
	@Column(name = "identificador", nullable = false , unique = true )
	private String identificador;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_tipo_ponto_monitorado" , nullable = false)
	private TipoPontoMonitoradoModel tipoPontoMonitorado;
	
	@ManyToMany(fetch = FetchType.LAZY , cascade = { CascadeType.MERGE , CascadeType.PERSIST })
	@JoinTable(
			name = "usuario_ponto_monitorado",
			joinColumns = @JoinColumn(name = "id_ponto_monitorado"), 
			inverseJoinColumns = @JoinColumn(name = "id_usuario"))
	private Set<UsuarioModel> usuarios;
	
	@Column(name = "dt_cadastro", nullable = false )
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtCadastro;
	
	@Column(name = "dt_modificada")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtModificada;
	
	@Column(name = "fg_ativo", columnDefinition = "boolean default true" , nullable = false )
	private Boolean ativo = Boolean.TRUE;

	

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

	public TipoPontoMonitoradoModel getTipoPontoMonitorado() {
		return tipoPontoMonitorado;
	}

	public void setTipoPontoMonitorado(TipoPontoMonitoradoModel tipoPontoMonitorado) {
		this.tipoPontoMonitorado = tipoPontoMonitorado;
	}

	public Set<UsuarioModel> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<UsuarioModel> usuarios) {
		this.usuarios = usuarios;
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

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
}
