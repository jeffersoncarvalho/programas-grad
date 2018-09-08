package br.ufc.lia.es.solar.model;

import arcademis.MarshalException;
import arcademis.Stream;
 
 

public class UsuarioModel  extends Model {

	 
	 
	private String login;
	private String senha;
	private String nome;
	private String email;
	private String site;
	private String nivel;
	private String area;
	private Integer acessos = new Integer(0);
	
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	 
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	 
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public Integer getAcessos() {
		return acessos;
	}
	public void setAcessos(Integer acessos) {
		this.acessos = acessos;
	}
	
	public void marshal(Stream stream) throws MarshalException {
		super.marshal(stream);
		try {
			stream.write(this.login);
			stream.write(this.nome);
			stream.write(this.senha);
			stream.write(this.nivel);
			stream.write(this.email);
			stream.write(this.site);
			stream.write(this.area);
			stream.write(this.acessos.intValue());
			 
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public void unmarshal(Stream stream) throws MarshalException {
		super.unmarshal(stream);
		try {
			
			this.login = (String)stream.readObject();
			this.nome = (String)stream.readObject();
			this.senha = (String)stream.readObject();
			this.nivel = (String)stream.readObject();
			this.email = (String)stream.readObject();
			this.site = (String)stream.readObject();
			this.area =  (String)stream.readObject();
			this.acessos = new Integer(stream.readInt());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	 
}
