package br.com.send.vo;

import org.springframework.beans.BeanUtils;

import br.com.send.model.NotificacaoModel;

public class NotificacaoVo {

	public NotificacaoVo() {}
	
	public NotificacaoVo(NotificacaoModel notificacaoModel) {
		if( notificacaoModel != null ){
			BeanUtils.copyProperties(notificacaoModel,this);
		}
	}
	
	private Long idNotificacao;
	
	private Long idEmpresa;
	
	private String descricao;

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

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

}
