package br.com.send.vo;

import org.springframework.beans.BeanUtils;

import br.com.send.model.TipoPontoMonitoradoModel;
import br.com.send.util.DataUtil;

public class TipoPontoMonitoradoVo {

	public TipoPontoMonitoradoVo() {}
	
	public TipoPontoMonitoradoVo(TipoPontoMonitoradoModel tipoPontoMonitoradoModel) {
		if( tipoPontoMonitoradoModel != null ){
			BeanUtils.copyProperties(tipoPontoMonitoradoModel,this);
			if(tipoPontoMonitoradoModel.getDtCadastro() != null) {
				this.setDtCadastro( DataUtil.converteData(tipoPontoMonitoradoModel.getDtCadastro()) );
			}
		}
	}
	
	
	private Long idTipoPontoMonitorado;
	
	private String descricao;
	
	private String dtCadastro;

	public Long getIdTipoPontoMonitorado() {
		return idTipoPontoMonitorado;
	}

	public void setIdTipoPontoMonitorado(Long idTipoPontoMonitorado) {
		this.idTipoPontoMonitorado = idTipoPontoMonitorado;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(String dtCadastro) {
		this.dtCadastro = dtCadastro;
	}
	
}
