package br.com.send.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import br.com.send.util.DataUtil;
import br.com.send.vo.PermissaoMenuVo;
import br.com.send.vo.UsuarioVo;

@Entity
@Table(name = "permissao_menu")
public class PermissaoMenuModel {

	public PermissaoMenuModel() {}
	
	public PermissaoMenuModel(PermissaoMenuVo permissaoMenuVo){
		BeanUtils.copyProperties(permissaoMenuVo , this);
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_permissao_menu", nullable = false )
	private Long idPermissaoMenu;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_perfil", nullable = false)
	private PerfilModel perfil;
	
	@Column(name = "titulo", nullable = false )
	private String titulo;
	
	@Column(name = "link", nullable = false )
	private String link;
	
	@Column(name = "icone", nullable = false )
	private String icone;
	
	@Column(name = "ordem", nullable = false )
	private Integer ordem;
	
	@Column(name = "fg_blank", columnDefinition = "boolean default false" , nullable = false )
	private Boolean blank = Boolean.FALSE;

	
	public String getIcone() {
		return icone;
	}

	public void setIcone(String icone) {
		this.icone = icone;
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

	public Boolean getBlank() {
		return blank;
	}

	public void setBlank(Boolean blank) {
		this.blank = blank;
	}

	

	public Integer getOrdem() {
		return ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

	public PerfilModel getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilModel perfil) {
		this.perfil = perfil;
	}

		
}
