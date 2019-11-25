package br.com.send.vo;

import org.springframework.beans.BeanUtils;
import br.com.send.model.PermissaoMenuModel;

public class PermissaoMenuVo {

	public PermissaoMenuVo() {}
	
	public PermissaoMenuVo(PermissaoMenuModel permissaoMenuModel) {
		if( permissaoMenuModel != null ){
			BeanUtils.copyProperties(permissaoMenuModel,this);
		}
	}
	
	private Long idPermissaoMenu;

	private String titulo;

	private String link;
	
	private String icone;
	
	private Integer ordem;
	
	private Boolean blank ;

	private Integer idPerfil;
	
	
	
	public Integer getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}

	public Long getIdPermissaoMenu() {
		return idPermissaoMenu;
	}

	public void setIdPermissaoMenu(Long idPermissaoMenu) {
		this.idPermissaoMenu = idPermissaoMenu;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getIcone() {
		return icone;
	}

	public void setIcone(String icone) {
		this.icone = icone;
	}

	
	public Integer getOrdem() {
		return ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

	public Boolean getBlank() {
		return blank;
	}

	public void setBlank(Boolean blank) {
		this.blank = blank;
	}
	
}
