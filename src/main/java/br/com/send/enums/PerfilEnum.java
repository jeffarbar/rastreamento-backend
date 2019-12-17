package br.com.send.enums;

public enum PerfilEnum {

	 ADMIN("Administrador"),
	 USUARIO("Usuário");
	 
	 private String descricao;
	 
	 PerfilEnum(String descricao) {
        this.descricao = descricao;
     }
 
     public String getDescricao() {
        return descricao;
     }
}
