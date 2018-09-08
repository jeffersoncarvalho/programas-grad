package br.ufc.lia.es.solar.gui.factory;

import br.ufc.lia.es.solar.gui.AulaGUI;
import br.ufc.lia.es.solar.gui.DescricaoGUI;
import br.ufc.lia.es.solar.gui.EmailConteudoGUI;
import br.ufc.lia.es.solar.gui.EmailDestinoGUI;
import br.ufc.lia.es.solar.gui.ForumGUI;
import br.ufc.lia.es.solar.gui.HorarioGUI;
import br.ufc.lia.es.solar.gui.LoginGUI;
import br.ufc.lia.es.solar.gui.MensagemGUI;
import br.ufc.lia.es.solar.gui.MenuGUI;
import br.ufc.lia.es.solar.gui.ParticipantesGUI;
import br.ufc.lia.es.solar.gui.PerfilGUI;
import br.ufc.lia.es.solar.gui.ProfessorGUI;
import br.ufc.lia.es.solar.gui.adm.AtualizarUsuarioChoiceGUI;
import br.ufc.lia.es.solar.gui.adm.AtualizarUsuarioGUI;
import br.ufc.lia.es.solar.gui.adm.InserirAulaGUI;
import br.ufc.lia.es.solar.gui.adm.InserirDisciplinaGUI;
import br.ufc.lia.es.solar.gui.adm.InserirUsuarioGUI;
import br.ufc.lia.es.solar.gui.adm.MatricularUsuarioGUI;
import br.ufc.lia.es.solar.gui.adm.MenuADMGUI;
import br.ufc.lia.es.solar.main.SolarMEMIDlet;

public class GUIFactory {

	private SolarMEMIDlet parent;
	
	private LoginGUI loginGUI;
	private MenuGUI menuGUI;
	private PerfilGUI perfilGUI;
	private ProfessorGUI professorGUI;
	private ParticipantesGUI participantesGUI;
	private MensagemGUI mensagemGUI;
	private ForumGUI forumGUI;
	private EmailConteudoGUI emailConteudoGUI;
	private EmailDestinoGUI emDestinoGUI;
	private HorarioGUI horarioGUI;
	private DescricaoGUI descricaoGUI;
	private AulaGUI aulaGUI;
	
	//adm
	private MenuADMGUI  menuADMGUI;
	private InserirUsuarioGUI inserirUsuarioGUI;
	private MatricularUsuarioGUI matricularUsuarioGUI;
	private InserirDisciplinaGUI inserirDisciplinaGUI;
	private InserirAulaGUI inserirAulaGUI;
	private AtualizarUsuarioChoiceGUI atualizarUsuarioChoiceGUI;
	private AtualizarUsuarioGUI atualizarUsuarioGUI;

	

	public GUIFactory(SolarMEMIDlet parent) {
	
		this.parent = parent;
	}

	public LoginGUI getLoginGUI() {
		if(this.loginGUI==null)
			this.loginGUI = new LoginGUI(this.parent);
		return loginGUI;
	}


	public MenuGUI getMenuGUI() {
		if(this.menuGUI == null)
			this.menuGUI = new MenuGUI(this.parent);
		return menuGUI;
	}

 
	public PerfilGUI getPerfilGUI() {
		if(this.perfilGUI==null)
			this.perfilGUI = new PerfilGUI(this.parent);
		return perfilGUI;
	}


	public ProfessorGUI getProfessorGUI() {
		if(this.professorGUI==null)
			this.professorGUI = new ProfessorGUI(this.parent);
		return professorGUI;
	}
 
	public ParticipantesGUI getPartcipantesGUI() {
		if(this.participantesGUI == null)
			this.participantesGUI = new ParticipantesGUI(this.parent);
		return participantesGUI;
	}
	
	public MensagemGUI getMensagemGUI() {
		if(this.mensagemGUI ==null)
			this.mensagemGUI = new MensagemGUI(this.parent);
		return mensagemGUI;
	}
	
	public ForumGUI getForumGUI() {
		if(this.forumGUI==null)
			this.forumGUI = new ForumGUI(this.parent);
		return forumGUI;
	}

	public EmailConteudoGUI getEmailConteudoGUI() {
		if(this.emailConteudoGUI==null)
			this.emailConteudoGUI = new EmailConteudoGUI(this.parent);
		return emailConteudoGUI;
	}

	public EmailDestinoGUI getEmDestinoGUI() {
		if(this.emDestinoGUI==null)
			this.emDestinoGUI = new EmailDestinoGUI(this.parent); 
		return emDestinoGUI;
	}

	public HorarioGUI getHorarioGUI() {
		if(this.horarioGUI == null)
			this.horarioGUI = new HorarioGUI(this.parent);
		return horarioGUI;
	}

	public DescricaoGUI getDescricaoGUI() {
		if(this.descricaoGUI==null)
			this.descricaoGUI = new DescricaoGUI(this.parent);
		return descricaoGUI;
	}

	public AulaGUI getAulaGUI() {
		if(this.aulaGUI ==null)
			this.aulaGUI = new AulaGUI(this.parent);
		return aulaGUI;
	}
	
	public MenuADMGUI getMenuADMGUI() {
		if(this.menuADMGUI==null)
			this.menuADMGUI = new MenuADMGUI(this.parent);
		return menuADMGUI;
	}

	public InserirUsuarioGUI getInserirUsuarioGUI() {
		if(this.inserirUsuarioGUI == null)
			this.inserirUsuarioGUI = new InserirUsuarioGUI(this.parent);
		return inserirUsuarioGUI;
	}

	public MatricularUsuarioGUI getMatricularUsuarioGUI() {
		if(this.matricularUsuarioGUI==null)
			this.matricularUsuarioGUI = new MatricularUsuarioGUI(this.parent);
		return matricularUsuarioGUI;
	}

	public InserirDisciplinaGUI getInserirDisciplinaGUI() {
		if(this.inserirDisciplinaGUI == null)
			this.inserirDisciplinaGUI = new InserirDisciplinaGUI(this.parent);
		return inserirDisciplinaGUI;
	}
	
	public InserirAulaGUI getInserirAulaGUI() {
		if(this.inserirAulaGUI == null)
			this.inserirAulaGUI = new InserirAulaGUI(this.parent);
		return inserirAulaGUI;
	}

	public AtualizarUsuarioGUI getAtualizarUsuarioGUI() {
		if(this.atualizarUsuarioGUI == null)
			this.atualizarUsuarioGUI = new AtualizarUsuarioGUI(this.parent);
		return atualizarUsuarioGUI;
	}

	public AtualizarUsuarioChoiceGUI getAtualizarUsuarioChoiceGUI() {
		if(this.atualizarUsuarioChoiceGUI == null)
			this.atualizarUsuarioChoiceGUI = new AtualizarUsuarioChoiceGUI(this.parent);
		return atualizarUsuarioChoiceGUI;
	}
}
