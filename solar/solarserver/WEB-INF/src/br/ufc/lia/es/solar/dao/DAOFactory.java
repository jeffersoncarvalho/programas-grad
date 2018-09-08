package br.ufc.lia.es.solar.dao;

import javax.sql.DataSource;

public class DAOFactory {

	private DisciplinaDAO disciplinaDAO = null;
	private MensagemDAO mensagemDAO = null;
	private UsuarioDAO usuarioDAO = null;
	private UsuarioDisciplinaDAO usuarioDisciplinaDAO = null;
	private AulaDAO aulaDAO = null;
	
	public static final int TIPO_DISCIPLINA = 0;
	public static final int TIPO_MENSAGEM = 1;
	public static final int TIPO_USUARIO = 2;
	public static final int TIPO_USUARIO_DISCIPLINA = 3;
	public static final int TIPO_AULA = 4;
	
	private DataSource ds = null;
	
	public DAOFactory(DataSource ds){
		this.ds = ds;
	}
	
	public DAO getDAO(int tipo){
		
		switch (tipo) {
			case DAOFactory.TIPO_DISCIPLINA:
				return this.getDisciplinaDAO();
			case DAOFactory.TIPO_MENSAGEM:
				return this.getMensagemDAO();
			case DAOFactory.TIPO_USUARIO:
				return this.getUsuarioDAO();
			case DAOFactory.TIPO_USUARIO_DISCIPLINA:
				return this.getUsuarioDisciplinaDAO();
			case DAOFactory.TIPO_AULA:
				return this.getAulaDAO();
			
		 
		}
		return null;
	}
	
	private DisciplinaDAO getDisciplinaDAO() {
		if(this.disciplinaDAO==null)
			this.disciplinaDAO = new DisciplinaDAO(this.ds);
		return disciplinaDAO;
	}
	private MensagemDAO getMensagemDAO() {
		if(this.mensagemDAO ==null)
			this.mensagemDAO = new MensagemDAO(this.ds);
		return mensagemDAO;
	}
	private UsuarioDAO getUsuarioDAO() {
		if(this.usuarioDAO == null)
			this.usuarioDAO = new UsuarioDAO(this.ds);
		return usuarioDAO;
	}
	private UsuarioDisciplinaDAO getUsuarioDisciplinaDAO() {
		if(this.usuarioDisciplinaDAO ==null)
			this.usuarioDisciplinaDAO = new UsuarioDisciplinaDAO(this.ds);
		return usuarioDisciplinaDAO;
	}

	private AulaDAO getAulaDAO() {
		if(this.aulaDAO == null)
			this.aulaDAO = new AulaDAO(this.ds);
		return aulaDAO;
	}
}
