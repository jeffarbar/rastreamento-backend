package br.com.send.vo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;

import br.com.send.model.EmpresaModel;
import br.com.send.model.PerfilModel;
import br.com.send.model.PermissaoMenuModel;
import br.com.send.service.PermissaoMenuService;

public class PerfilVo {

	public PerfilVo() {}
	
	public PerfilVo(PerfilModel perfilModel) {
		if( perfilModel != null ){
			BeanUtils.copyProperties(perfilModel,this);
			
			if(perfilModel.getPermissoesMenu() != null) {
					this.permissaoMenuVo = perfilModel.getPermissoesMenu().stream()
							.sorted((p1, p2) -> p1.getOrdem().compareTo(p2.getOrdem()))
							.map( PermissaoMenuVo :: new ).collect(Collectors.toList());
			}
		}
	}
	
	public PerfilVo(PerfilModel perfilModel,EmpresaModel empresaModel) {
		if( perfilModel != null ){
			BeanUtils.copyProperties(perfilModel,this);
			
			if(perfilModel.getPermissoesMenu() != null) {
				
				if(empresaModel == null) {
					this.permissaoMenuVo = perfilModel.getPermissoesMenu().stream().filter( p ->  PermissaoMenuService.LINK_PADRAO.equals( p.getLink() ) )
							.sorted((p1, p2) -> p1.getOrdem().compareTo(p2.getOrdem()))
							.map( PermissaoMenuVo :: new ).collect(Collectors.toList());
				}else {
					this.permissaoMenuVo = perfilModel.getPermissoesMenu().stream().sorted((p1, p2) -> p1.getOrdem().compareTo(p2.getOrdem()))
							.map( PermissaoMenuVo :: new ).collect(Collectors.toList());
				}
				
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
