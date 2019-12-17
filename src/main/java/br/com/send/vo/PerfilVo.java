package br.com.send.vo;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import br.com.send.model.PerfilModel;

public class PerfilVo {

	public PerfilVo() {}
	
	public PerfilVo(PerfilModel perfilModel) {
		if( perfilModel != null ){
			BeanUtils.copyProperties(perfilModel,this);
			
			if(perfilModel.getPermissoesMenu() != null) {
				this.permissaoMenuVo = perfilModel.getPermissoesMenu().parallelStream()
					.sorted((p1, p2) -> p1.getOrdem().compareTo(p2.getOrdem()))
					.map( PermissaoMenuVo :: new ).collect(Collectors.toList());
			}
		}
	}
	
	private Integer idPerfil;
	
	private String descricao;
	
	private List<PermissaoMenuVo> permissaoMenuVo;

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

	public List<PermissaoMenuVo> getPermissaoMenuVo() {
		return permissaoMenuVo;
	}

	public void setPermissaoMenuVo(List<PermissaoMenuVo> permissaoMenuVo) {
		this.permissaoMenuVo = permissaoMenuVo;
	}

}
