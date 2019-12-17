package br.com.send.vo;


import org.springframework.beans.BeanUtils;

import br.com.send.model.UsuarioModel;


public class UsuarioVo {
	
	public UsuarioVo() {}
	
	public UsuarioVo(UsuarioModel userModel) {
		if( userModel != null ){
			BeanUtils.copyProperties(userModel,this);
			this.setConfirmarSenha(userModel.getSenha());
			
			if( userModel.getPerfil() != null ) {
				this.perfilVo = new PerfilVo(userModel.getPerfil());
			}
		}
	}
	
	private Long idUsuario;
	
	private String nome;

	private String email;
	
	private String profissao;

	private String companhia;
	 
	private String senha;
	
	private String confirmarSenha;

	private String sobre;

	private Double latitude;
	
	private Double longitude;
	
	private Integer zoom;
	
	private PerfilVo perfilVo;
	
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

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
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

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

	public String getSobre() {
		return sobre;
	}

	public void setSobre(String sobre) {
		this.sobre = sobre;
	}

	public PerfilVo getPerfilVo() {
		return perfilVo;
	}

	public void setPerfilVo(PerfilVo perfilVo) {
		this.perfilVo = perfilVo;
	}
	  
	
}
