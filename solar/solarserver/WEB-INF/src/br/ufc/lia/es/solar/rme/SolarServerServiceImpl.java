package br.ufc.lia.es.solar.rme;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import rme.server.RmeRemoteObject;
import arcademis.ArcademisException;
import br.ufc.lia.es.solar.dao.AulaDAO;
import br.ufc.lia.es.solar.dao.DAO;
import br.ufc.lia.es.solar.dao.DAOFactory;
import br.ufc.lia.es.solar.dao.DisciplinaDAO;
import br.ufc.lia.es.solar.dao.MensagemDAO;
import br.ufc.lia.es.solar.dao.UsuarioDAO;
import br.ufc.lia.es.solar.dao.UsuarioDisciplinaDAO;
import br.ufc.lia.es.solar.model.AulaModel;
import br.ufc.lia.es.solar.model.DisciplinaModel;
import br.ufc.lia.es.solar.model.MensagemModel;
import br.ufc.lia.es.solar.model.UsuarioDisciplinaModel;
import br.ufc.lia.es.solar.model.UsuarioModel;
import br.ufc.lia.es.solar.util.CodesConstants;
import br.ufc.lia.es.solar.util.JavaMail;
import br.ufc.lia.es.solar.util.MarshalableVector;

public class SolarServerServiceImpl extends RmeRemoteObject implements ISolarServerService {

	private DataSource ds;
	private DAOFactory daoFactory;
	
	public SolarServerServiceImpl() {
		System.out.println("\nCreating Facade");
		try {
			this.ds = DAO.setupDataSource();
			this.daoFactory = new DAOFactory(this.ds);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public UsuarioModel efetuarLogin(String login, String senha) throws ArcademisException {
		UsuarioDAO uDAO = (UsuarioDAO)this.daoFactory.getDAO(DAOFactory.TIPO_USUARIO);
		UsuarioModel userRef = null;
	 
		userRef = uDAO.retrieveByLogin(login);
		 
		if(userRef!=null)
		{
			if(userRef.getSenha().equalsIgnoreCase(senha)){
				userRef.setCode(CodesConstants.LOGIN_OK);
				 
			}else{
				userRef.setCode(CodesConstants.LOGIN_NOK_BAD_PASSWORD);
				 
			}
				
			return userRef;
		}else
		{
			userRef = new UsuarioModel();
			userRef.setCode(CodesConstants.LOGIN_NOK_BAD_USER);
			 
			return userRef;
		}
		 
		 
	}

	public MarshalableVector listarDisciplinasPorUsuario(String login) throws ArcademisException {
		
		
		UsuarioDisciplinaDAO udDAO = (UsuarioDisciplinaDAO)this.daoFactory.getDAO(DAOFactory.TIPO_USUARIO_DISCIPLINA);
		List usuario_disciplinas = udDAO.listByUsuarioLogin(login);
		if(usuario_disciplinas!=null){
			DisciplinaDAO dDAO = (DisciplinaDAO)this.daoFactory.getDAO(DAOFactory.TIPO_DISCIPLINA);
			MarshalableVector  disciplinas = new MarshalableVector ();
			for(int i=0;i<usuario_disciplinas.size();i++){
				UsuarioDisciplinaModel udmodel = (UsuarioDisciplinaModel)usuario_disciplinas.get(i);
				DisciplinaModel dm = dDAO.retrieve(udmodel.getDisciplina_codigo());
				DisciplinaModel dmCompactada = new DisciplinaModel();
				dmCompactada.setNome(dm.getNome());
				dmCompactada.setCodigo(dm.getCodigo());
				disciplinas.add(dm);
			}
			return disciplinas;
		}else
		{
			
		}
		
		return null;
	}
	
	public DisciplinaModel retornaDisciplina(String codigo)throws ArcademisException{
		DisciplinaDAO ddao = (DisciplinaDAO)this.daoFactory.getDAO(DAOFactory.TIPO_DISCIPLINA);
		return ddao.retrieve(codigo);
	}

	public UsuarioModel retornaUsuario(String login) throws ArcademisException {
		 UsuarioDAO usuarioDAO = (UsuarioDAO)this.daoFactory.getDAO(DAOFactory.TIPO_USUARIO);
		 return usuarioDAO.retrieveByLogin(login);
		 
	}
	
	public MarshalableVector listarParticipantes(String codigo){
		MarshalableVector participantes = new MarshalableVector();
		UsuarioDisciplinaDAO usuarioDisciplinaDAO = (UsuarioDisciplinaDAO)this.daoFactory.getDAO(DAOFactory.TIPO_USUARIO_DISCIPLINA);
		List disciplinas = usuarioDisciplinaDAO.listByDisciplinaCodigo(codigo);
		UsuarioDAO usuarioDAO = new UsuarioDAO(this.ds);
		for(int i=0;i<disciplinas.size();i++){
			UsuarioDisciplinaModel udmodel = (UsuarioDisciplinaModel)disciplinas.get(i);
			UsuarioModel usuarioModel = usuarioDAO.retrieveByLogin(udmodel.getUsuario_login());
			UsuarioModel usuarioCompactado = new UsuarioModel();
			usuarioCompactado.setNome(usuarioModel.getNome());
			usuarioCompactado.setAcessos(usuarioModel.getAcessos());
			participantes.add(usuarioCompactado);
		}
		return participantes; 
	}

	public void incrementaAcessos(String login) throws ArcademisException {
		UsuarioDAO usuarioDAO = (UsuarioDAO)this.daoFactory.getDAO(DAOFactory.TIPO_USUARIO);
		UsuarioModel usuarioModel = usuarioDAO.retrieveByLogin(login);
		int soma = usuarioModel.getAcessos().intValue()+1;
		usuarioModel.setAcessos(new Integer(soma));
		try {
			usuarioDAO.updateAcessos(usuarioModel);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void enviaMensagemForum(MensagemModel mensagemModel) throws ArcademisException {
		MensagemDAO mensagemDAO = (MensagemDAO)this.daoFactory.getDAO(DAOFactory.TIPO_MENSAGEM);
		try {
			Date date = new Date();
			mensagemModel.setData(date.toLocaleString());
			mensagemDAO.insert(mensagemModel);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public MarshalableVector listarMensagemsForum(String disciplinaCodigo) throws ArcademisException {
		MarshalableVector mensagensToSend = new MarshalableVector();
		MensagemDAO mensagemDAO = (MensagemDAO)this.daoFactory.getDAO(DAOFactory.TIPO_MENSAGEM);
		List msgsList = mensagemDAO.listByDisciplinaCodigo(disciplinaCodigo);
		mensagensToSend.addAll(msgsList);
		return mensagensToSend;
	}

	public void enviaMensagemEmail(MensagemModel mensagemModel, String emailDestino) throws ArcademisException {
		 JavaMail jm = new JavaMail();
		  try {
			jm.sendSimpleMail(mensagemModel.getAutorNome(),emailDestino,mensagemModel.getConteudo());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public MarshalableVector listarAulas(String disciplinaCodigo) throws ArcademisException {
		MarshalableVector aulasToSend = new MarshalableVector();
		AulaDAO aulaDAO = (AulaDAO)this.daoFactory.getDAO(DAOFactory.TIPO_AULA);
		List aulasList = aulaDAO.listByDisciplinaCodigo(disciplinaCodigo);
		aulasToSend.addAll(aulasList);
		
		return aulasToSend;
	}

	public void inserirUsuario(UsuarioModel usuarioModel) throws ArcademisException {
		try {
			UsuarioDAO usuarioDAO = (UsuarioDAO)this.daoFactory.getDAO(DAOFactory.TIPO_USUARIO);
			usuarioDAO.insert(usuarioModel);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void inserirMatricula(UsuarioDisciplinaModel usuarioDisciplinaModel) throws ArcademisException {
		try {
			UsuarioDisciplinaDAO udDAO = (UsuarioDisciplinaDAO)this.daoFactory.getDAO(DAOFactory.TIPO_USUARIO_DISCIPLINA);
			udDAO.insert(usuarioDisciplinaModel);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void inserirDisciplina(DisciplinaModel disciplinaModel) throws ArcademisException {
		
		try {
			DisciplinaDAO dDAO = (DisciplinaDAO)this.daoFactory.getDAO(DAOFactory.TIPO_DISCIPLINA);
			int newId = 0;
			List disciplinas = dDAO.list();
			if(disciplinas!=null && disciplinas.size()!=0){
				String lastID = ((DisciplinaModel)disciplinas.get(disciplinas.size()-1)).getCodigo();
				int id = Integer.parseInt(lastID);
				newId = id+1;
			}
			
			disciplinaModel.setCodigo(newId+"");
			dDAO.insert(disciplinaModel);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UsuarioDisciplinaModel usuarioDisciplinaModel = new UsuarioDisciplinaModel();
		usuarioDisciplinaModel.setDisciplina_codigo(disciplinaModel.getCodigo());
		usuarioDisciplinaModel.setUsuario_login(disciplinaModel.getProfessor_login());
		
		try {
			UsuarioDisciplinaDAO udDAO = (UsuarioDisciplinaDAO)this.daoFactory.getDAO(DAOFactory.TIPO_USUARIO_DISCIPLINA);
			udDAO.insert(usuarioDisciplinaModel);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public MarshalableVector listarDisciplinasLite() throws ArcademisException {
		MarshalableVector cursosToSend = new MarshalableVector();
		DisciplinaDAO dDAO = (DisciplinaDAO)this.daoFactory.getDAO(DAOFactory.TIPO_DISCIPLINA);
		List list = dDAO.list();
		cursosToSend.addAll(list);
		return cursosToSend;
	}

	public MarshalableVector listarUsuariosLite() throws ArcademisException {
		MarshalableVector usersToSend = new MarshalableVector();
		UsuarioDAO usuarioDAO = (UsuarioDAO)this.daoFactory.getDAO(DAOFactory.TIPO_USUARIO);
		List list = usuarioDAO.listLogins();
		usersToSend.addAll(list);
		return usersToSend;
	}

	public MarshalableVector listarProfessores() throws ArcademisException {
		UsuarioDAO usuarioDAO = (UsuarioDAO)this.daoFactory.getDAO(DAOFactory.TIPO_USUARIO);
		List list = usuarioDAO.list();
		MarshalableVector professores = new MarshalableVector();
		for(int i=0;i<list.size();i++){
			UsuarioModel usuarioModel = (UsuarioModel)list.get(i);
			if(usuarioModel.getNivel().equals(CodesConstants.ADM_LEVEL))
			{
				UsuarioModel user = new UsuarioModel();
				user.setLogin(usuarioModel.getLogin());
				professores.add(user);
			}
				
		}
		return professores;
	}

	public void inserirAula(AulaModel aulaModel) throws ArcademisException {
		 
		try {
			AulaDAO aulaDAO = (AulaDAO)this.daoFactory.getDAO(DAOFactory.TIPO_AULA);
			aulaDAO.insert(aulaModel);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void updateUsuario(UsuarioModel usuarioModel) throws ArcademisException {

		
		UsuarioDAO usuarioDAO = (UsuarioDAO)this.daoFactory.getDAO(DAOFactory.TIPO_USUARIO);
		try {
			usuarioDAO.update(usuarioModel);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
