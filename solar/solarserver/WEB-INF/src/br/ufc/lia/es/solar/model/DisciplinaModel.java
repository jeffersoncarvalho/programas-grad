package br.ufc.lia.es.solar.model;

import arcademis.MarshalException;
import arcademis.Stream;

public class DisciplinaModel extends Model{

	private String codigo;
	private String nome;
	private String horario;
	private String descricao;
	private String professor_login;
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getProfessor_login() {
		return professor_login;
	}

	public void setProfessor_login(String professor_login) {
		this.professor_login = professor_login;
	}

	public void marshal(Stream stream) throws MarshalException {
		super.marshal(stream);
		try {
			stream.write(this.codigo);
			stream.write(this.nome);
			stream.write(this.horario);
			stream.write(this.descricao);
			stream.write(this.professor_login);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public void unmarshal(Stream stream) throws MarshalException {
		super.unmarshal(stream);
		try {
			
			this.codigo = (String)stream.readObject();
			this.nome = (String)stream.readObject();
			this.horario = (String)stream.readObject();
			this.descricao = (String)stream.readObject();
			this.professor_login = (String)stream.readObject();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
