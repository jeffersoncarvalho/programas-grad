package br.ufc.lia.es.solar.model;

import arcademis.MarshalException;
import arcademis.Stream;

public class UsuarioDisciplinaModel extends Model{

	private String usuario_login;
	private String disciplina_codigo;
	 
	public String getUsuario_login() {
		return usuario_login;
	}
	public void setUsuario_login(String usuario_login) {
		this.usuario_login = usuario_login;
	}
	public String getDisciplina_codigo() {
		return disciplina_codigo;
	}
	public void setDisciplina_codigo(String disciplina_codigo) {
		this.disciplina_codigo = disciplina_codigo;
	}
	
	 
	public void marshal(Stream stream) throws MarshalException {
		 
		super.marshal(stream);
		try {
			stream.write(this.usuario_login);
			stream.write(this.disciplina_codigo);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	 
	public void unmarshal(Stream stream) throws MarshalException {
		 
		super.unmarshal(stream);
		try {
			
			this.usuario_login = (String)stream.readObject();
			this.disciplina_codigo = (String)stream.readObject();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
