package br.com.send.vo;

import org.springframework.beans.BeanUtils;
import br.com.send.model.ModeloModel;
import br.com.send.util.DataUtil;

public class ModeloVo {

	public ModeloVo() {}
	
	public ModeloVo(ModeloModel modeloModel) {
		if( modeloModel != null ){
			BeanUtils.copyProperties(modeloModel,this);
			if(modeloModel.getDtCadastro() != null) {
				this.setDtCadastro( DataUtil.converterDataGMT3(modeloModel.getDtCadastro()) );
			}
		}
	}
	
	private Long idModelo;
	
	private String descricao;
	
	private String dtCadastro;

	public String getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(String dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public Long getIdModelo() {
		return idModelo;
	}

	public void setIdModelo(Long idModelo) {
		this.idModelo = idModelo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
