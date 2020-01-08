package br.com.send.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.beans.BeanUtils;
import br.com.send.util.DataUtil;
import br.com.send.vo.NotificacaoVo;


@Entity
@Table(name = "notificacao")
public class NotificacaoModel {

	public NotificacaoModel() {}
	
	public NotificacaoModel(NotificacaoVo notificacaoVo){
		BeanUtils.copyProperties(notificacaoVo , this);
		this.setDtCadastro( DataUtil.getDataAtual() );
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_notificacao", nullable = false )
	private Long idNotificacao;
	
	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private EmpresaModel empresa;

	@Column(name = "descricao", nullable = false )
	private String descricao;
	
	@Column(name = "dt_cadastro", nullable = false )
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtCadastro;
	
	@Column(name = "dt_modificada" )
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtModificada;
	
	@Column(name = "fg_lida", columnDefinition = "boolean default false" , nullable = false )
	private Boolean lida = Boolean.FALSE;

	public Boolean isLida() {
		return lida;
	}

	public void setLida(Boolean lida) {
		this.lida = lida;
	}

	public Long getIdNotificacao() {
		return idNotificacao;
	}

	public void setIdNotificacao(Long idNotificacao) {
		this.idNotificacao = idNotificacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	public EmpresaModel getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaModel empresa) {
		this.empresa = empresa;
	}

	
}
