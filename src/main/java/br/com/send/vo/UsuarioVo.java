package br.com.send.vo;


import org.springframework.beans.BeanUtils;

import br.com.send.enums.PerfilEnum;
import br.com.send.model.UsuarioModel;
import br.com.send.util.DataUtil;


public class UsuarioVo {
	
	public UsuarioVo() {}
	
	public UsuarioVo(UsuarioModel userModel) {
		if( userModel != null ){
			BeanUtils.copyProperties(userModel,this);
			this.setConfirmarSenha(userModel.getSenha());
			if( userModel.getDtCadastro() != null ) {
				this.setDtCadastro( DataUtil.converteData(userModel.getDtCadastro()) );
			}
			
			if( userModel.getPerfil() != null ) {
				this.perfilVo = new PerfilVo(userModel.getPerfil(), userModel.getEmpresa());
				this.setIdPerfil(this.perfilVo.getIdPerfil());
				this.setDescricao(this.perfilVo.getDescricao());
				this.setAdmin( PerfilEnum.ADMIN.getDescricao().equals( this.perfilVo.getDescricao() ) );
				
			}
			if( userModel.getEmpresa() != null ) {
				this.setIdEmpresa(userModel.getEmpresa().getIdEmpresa());
				this.setRazaoSocialEmpresa(userModel.getEmpresa().getRazaoSocial());
			}
		}
	}
	
	private Long idEmpresa;
	
	private String razaoSocialEmpresa;
	
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
	
	private Integer idPerfil;
    
	private String descricao;
     
	private boolean isAdmin;
	
	private String dtCadastro;
	
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

	public String getRazaoSocialEmpresa() {
		return razaoSocialEmpresa;
	}

	public void setRazaoSocialEmpresa(String razaoSocialEmpresa) {
		this.razaoSocialEmpresa = razaoSocialEmpresa;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfissao() {
		return profissao;
	}

	public String getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(String dtCadastro) {
		this.dtCadastro = dtCadastro;
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

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Integer getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
}
