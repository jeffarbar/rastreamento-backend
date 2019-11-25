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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "perfil")
public class PerfilModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_perfil", nullable = false )
	private Integer idPerfil;
	
	@Column(name = "descricao", nullable = false , unique = true )
	private String descricao;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "perfil")
	private Set<PermissaoMenuModel> permissoesMenu;
	
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

	public Set<PermissaoMenuModel> getPermissoesMenu() {
		return permissoesMenu;
	}

	public void setPermissoesMenu(Set<PermissaoMenuModel> permissoesMenu) {
		this.permissoesMenu = permissoesMenu;
	}
	
}
