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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.BeanUtils;

import br.com.send.util.DataUtil;
import br.com.send.vo.EmpresaVo;


@Entity
@Table(name = "empresa")
public class EmpresaModel {

	public EmpresaModel() {}
	
	public EmpresaModel(EmpresaVo empresaVo){
		BeanUtils.copyProperties(empresaVo , this);
		this.setDtCadastro( DataUtil.getDataAtual() );
		this.setAtivo(Boolean.TRUE);
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_empresa", nullable = false )
	private Long idEmpresa;
	
	@Column(name = "razao_social", nullable = false )
	private String razaoSocial;
	
	@Column(name = "cnpj", nullable = false , unique = true )
	private String cnpj;
	
	
	@Column(name = "email_contato", nullable = false )
	private String emailContato;
	
	@Column(name = "telefone_contato", nullable = false )
	private String telefoneContato;
	
	@Column(name = "imagem")
	private byte[] imagem;
	
	@Column(name = "fg_ativo", columnDefinition = "boolean default true" , nullable = false )
	private Boolean ativo = Boolean.TRUE;
	
	@Column(name = "dt_cadastro", nullable = false )
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtCadastro;
	
	@Column(name = "dt_modificada")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtModificada;
	
	
	@OneToMany(mappedBy = "empresa", targetEntity = NotificacaoModel.class, 
			fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<NotificacaoModel> notificacoes;
	
	@ManyToMany(fetch = FetchType.LAZY, 
			cascade = { CascadeType.PERSIST, CascadeType.MERGE } , mappedBy = "empresas" )
	private Set<PontoMonitoradoModel> pontoMonitorados;

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmailContato() {
		return emailContato;
	}

	public void setEmailContato(String emailContato) {
		this.emailContato = emailContato;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public String getTelefoneContato() {
		return telefoneContato;
	}

	public void setTelefoneContato(String telefoneContato) {
		this.telefoneContato = telefoneContato;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
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

	public Set<PontoMonitoradoModel> getPontoMonitorados() {
		return pontoMonitorados;
	}

	public void setPontoMonitorados(Set<PontoMonitoradoModel> pontoMonitorados) {
		this.pontoMonitorados = pontoMonitorados;
	}
	
}
