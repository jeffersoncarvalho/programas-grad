package br.ufc.lia.es.solar.model;

import arcademis.MarshalException;
import arcademis.Stream;

public class AulaModel extends Model{

	private String disciplinaCodigo;
	private String assunto;
	private String descricao;
	private String tarefa;
	private String data;
	
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getDisciplinaCodigo() {
		return disciplinaCodigo;
	}
	public void setDisciplinaCodigo(String disciplinaCodigo) {
		this.disciplinaCodigo = disciplinaCodigo;
	}
	public String getTarefa() {
		return tarefa;
	}
	public void setTarefa(String tarefa) {
		this.tarefa = tarefa;
	}
	
	public void marshal(Stream stream) throws MarshalException {
		super.marshal(stream);
		try {
			stream.write(this.disciplinaCodigo);
			stream.write(this.assunto);
			stream.write(this.descricao);
			stream.write(this.tarefa);
			stream.write(this.data);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public void unmarshal(Stream stream) throws MarshalException {
		super.unmarshal(stream);
		try {
			
			this.disciplinaCodigo = (String)stream.readObject();
			this.assunto = (String)stream.readObject();
			this.descricao = (String)stream.readObject();
			this.tarefa = (String)stream.readObject();
			this.data = (String)stream.readObject();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
