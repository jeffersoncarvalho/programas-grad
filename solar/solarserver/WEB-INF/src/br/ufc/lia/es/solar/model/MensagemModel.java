package br.ufc.lia.es.solar.model;

import arcademis.MarshalException;
import arcademis.Stream;

public class MensagemModel extends Model{

	private Integer id;
	private String disciplinaCodigo;
	private String autorLogin;
	private String autorNome;
	private String conteudo;
	private String data;
	
	public String getAutorLogin() {
		return autorLogin;
	}
	public void setAutorLogin(String autorLogin) {
		this.autorLogin = autorLogin;
	}
	public String getAutorNome() {
		return autorNome;
	}
	public void setAutorNome(String autorNome) {
		this.autorNome = autorNome;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getDisciplinaCodigo() {
		return disciplinaCodigo;
	}
	public void setDisciplinaCodigo(String disciplinaCodigo) {
		this.disciplinaCodigo = disciplinaCodigo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void marshal(Stream stream) throws MarshalException {
		super.marshal(stream);
		try {
			stream.write(this.id.intValue());
			stream.write(this.disciplinaCodigo);
			stream.write(this.autorLogin);
			stream.write(this.autorNome);
			stream.write(this.conteudo);
			stream.write(this.data);
			
			 
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public void unmarshal(Stream stream) throws MarshalException {
		super.unmarshal(stream);
		try {
			
			this.id = new Integer(stream.readInt());
			this.disciplinaCodigo = (String)stream.readObject();
			this.autorLogin = (String)stream.readObject();
			this.autorNome = (String)stream.readObject();
			this.conteudo = (String)stream.readObject();
			this.data = (String)stream.readObject();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	 
}
