package br.ufc.lia.es.solar.rme;

import arcademis.ArcademisException;
import arcademis.Remote;
import br.ufc.lia.es.solar.model.AulaModel;
import br.ufc.lia.es.solar.model.DisciplinaModel;
import br.ufc.lia.es.solar.model.MensagemModel;
import br.ufc.lia.es.solar.model.UsuarioDisciplinaModel;
import br.ufc.lia.es.solar.model.UsuarioModel;
import br.ufc.lia.es.solar.util.MarshalableVector;

public interface ISolarServerService extends Remote{
	
	//metodo para login
	public UsuarioModel efetuarLogin(String login, String senha) throws ArcademisException;
	
	//listando disciplinas
	public MarshalableVector listarDisciplinasPorUsuario(String login) throws ArcademisException;
	
	//disciplina
	public DisciplinaModel retornaDisciplina(String codigo)throws ArcademisException;

	//retorna usuario
	public UsuarioModel retornaUsuario(String login) throws ArcademisException;
	
	//retorna participantes de um curso
	public MarshalableVector listarParticipantes(String codigo) throws ArcademisException;

	//atualiza número de acesso de um usuario
	public void incrementaAcessos(String login) throws ArcademisException;
	
	//envia msg ao fórum
	public void enviaMensagemForum(MensagemModel mensagemModel) throws ArcademisException;
	
	//lista msgs
	public MarshalableVector listarMensagemsForum(String disciplinaCodigo) throws ArcademisException;

	//envia msg ao email
	public void enviaMensagemEmail(MensagemModel mensagemModel, String emailDestino) throws ArcademisException;

	//listar aulas de uma determinada disciplina
	public MarshalableVector listarAulas(String disciplinaCodigo) throws ArcademisException;
	
	//inserir usuario
	public void inserirUsuario(UsuarioModel usuarioModel)throws ArcademisException;
	
	//inserir matricula
	public void inserirMatricula(UsuarioDisciplinaModel usuarioDisciplinaModel) throws ArcademisException;
	
	//inserir disciplina
	public void inserirDisciplina(DisciplinaModel disciplinaModel) throws ArcademisException;
	
	//lsitas apenas nome e codigo de cada disciplina
	public MarshalableVector listarDisciplinasLite() throws ArcademisException;
	
	//lista apenas logins
	public MarshalableVector listarUsuariosLite() throws ArcademisException;
	
	//lsitar professores
	public MarshalableVector listarProfessores() throws ArcademisException;
	
	//inserir aula
	public void inserirAula(AulaModel aulaModel) throws ArcademisException;
	
	//update usuario
	public void updateUsuario(UsuarioModel usuarioModel) throws ArcademisException ;
}
