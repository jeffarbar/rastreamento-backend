package br.com.send.model;



import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.JoinColumn;

import org.springframework.beans.BeanUtils;

import br.com.send.util.DataUtil;
import br.com.send.vo.UsuarioVo;

@Entity
@Table(name = "usuario")
public class UsuarioModel {

	public UsuarioModel() {}
	
	public UsuarioModel(UsuarioVo usuarioVo){
		BeanUtils.copyProperties(usuarioVo , this);
		this.setDtCadastro( DataUtil.getDataAtual() );
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario", nullable = false )
	private Long idUsuario;

	@OneToMany(mappedBy = "usuario", targetEntity = NotificacaoModel.class, 
			fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<NotificacaoModel> notificacoes;
	
	@Column(name = "nome", nullable = false )
	private String nome;
	
	@Column(name = "email", unique = true, nullable = false )
	private String email;
	
	@Column(name = "chave_troca_senha", unique = true, nullable = false )
	private String chaveTrocaSenha;
	
	@Column(name = "profissao")
	private String profissao;

	@Column(name = "companhia")
	private String companhia;
	 
	@Column(name = "senha", nullable = false )
	private String senha;
	 
	@Column(name = "sobre" )
	private String sobre;
	 
	@Column(name = "latitude", nullable = false )
	private Double latitude;
	
	@Column(name = "longitude", nullable = false )
	private Double longitude;
	
	@Column(name = "zoom", nullable = false )
	private Integer zoom;
	
	@Column(name = "dt_cadastro", nullable = false )
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtCadastro;
	
	@Column(name = "dt_modificada")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtModificada;
	
	@ManyToMany(fetch = FetchType.LAZY, 
			cascade = { CascadeType.PERSIST, CascadeType.MERGE } , mappedBy = "usuarios" )
	private Set<PontoMonitoradoModel> pontoMonitorados;
	
	@Column(name = "fg_ativo", columnDefinition = "boolean default true" , nullable = false )
	private Boolean ativo = Boolean.TRUE;

	@ManyToOne
	@JoinColumn(name = "id_perfil")
	private PerfilModel perfil;
	
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Set<PontoMonitoradoModel> getPontoMonitorados() {
		return pontoMonitorados;
	}

	public void setPontoMonitorados(Set<PontoMonitoradoModel> pontoMonitorados) {
		this.pontoMonitorados = pontoMonitorados;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getCompanhia() {
		return companhia;
	}

	public void setCompanhia(String companhia) {
		this.companhia = companhia;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSobre() {
		return sobre;
	}

	public void setSobre(String sobre) {
		this.sobre = sobre;
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

	public List<NotificacaoModel> getNotificacoes() {
		return notificacoes;
	}

	public void setNotificacoes(List<NotificacaoModel> notificacoes) {
		this.notificacoes = notificacoes;
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

	public Integer getZoom() {
		return zoom;
	}

	public void setZoom(Integer zoom) {
		this.zoom = zoom;
	}

	public String getChaveTrocaSenha() {
		return chaveTrocaSenha;
	}

	public void setChaveTrocaSenha(String chaveTrocaSenha) {
		this.chaveTrocaSenha = chaveTrocaSenha;
	}

	public PerfilModel getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilModel perfil) {
		this.perfil = perfil;
	}

	
}
