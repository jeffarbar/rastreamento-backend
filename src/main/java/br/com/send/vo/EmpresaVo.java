package br.com.send.vo;

import org.springframework.beans.BeanUtils;

import br.com.send.model.EmpresaModel;
import br.com.send.model.UsuarioModel;
import br.com.send.util.DataUtil;

public class EmpresaVo {

	public EmpresaVo() {}
	
	public EmpresaVo(EmpresaModel empresaModel  )  {
		if( empresaModel != null ){
			BeanUtils.copyProperties(empresaModel,this);
			
			if(empresaModel.getDtCadastro() != null) {
				this.setDtCadastro(  DataUtil.converterDataGMT3(empresaModel.getDtCadastro()) );
			}
			if(empresaModel.getDtModificada() != null) {
				this.setDtModificada( DataUtil.converterDataGMT3(empresaModel.getDtModificada()) );
			}
			
		}
	}
	
	private Long idEmpresa;

	private String razaoSocial;

	private String cnpj;
	
	private String emailContato;

	private String telefoneContato;
	
	private Boolean ativo;
	
	private String dtCadastro;
	
	private String dtModificada;

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

	public String getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(String dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public String getDtModificada() {
		return dtModificada;
	}

	public void setDtModificada(String dtModificada) {
		this.dtModificada = dtModificada;
	}
	
	
}
