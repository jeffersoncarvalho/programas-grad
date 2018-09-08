package br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.ArrayList;

import javax.swing.JPanel;

import jgl.GL;
import jgl.GLU;
import jgl.GLUT;
import br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho.Embrulho;
import br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho.Face;
import br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho.Vertice;
import br.ufc.lia.jeffersoncarvalho.aplicacoes.tetraedralizacao.Tetraedralizacao;
import br.ufc.lia.jeffersoncarvalho.aplicacoes.tetraedralizacao.Tetraedro;
import br.ufc.lia.jeffersoncarvalho.aplicacoes.tetraedralizacao.tema.FechoDaMorte;

public class EmbrulhoPainel extends JPanel {

	float angleX = 0f;

	float angleY = 0f;

	float angleZ = 0f;

	float zoom = -13f;

	float xPos = -1.8f;

	float yPos = -0.8f;

	boolean tdisparada = false;

	protected long tsleep = 1000;

	private boolean colorir = false;
	private boolean explodir = false;
	 
	/*
	 * float mat_specular[] = { 1.0f, 1.0f, 1.0f, 1.0f }; float mat_shininess[] = {
	 * 25.0f }; float light_position[] = { 10.0f, 1.0f, 1.0f, 0.0f };
	 */
	private Cor cor = new Cor(0f, 0f, 0f);

	private Dimension resolucao;

	private int tiraPontos = 0;
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	GL myGL = new GL();

	GLU myGLU = new GLU(myGL);

	GLUT myUT = new GLUT(myGL);

	Hashtable linhas = new Hashtable();

	ArrayList triangulos = new ArrayList();
	ArrayList tetraedros = new ArrayList();
    int tetraedroDidatico=-1;

	ArrayList pontos = new ArrayList();

	Triangulo trianguloDidatico;

	private boolean apagaLinhas;

	private boolean apagaEixo;
	
	private ArrayList facesDaMorte;
	private Face faceDaMorte ;
	private float xDaMorte = 0;
	private int contagemDamorte = 70;
	private boolean laserMorte[] = new boolean[4];
	 
	 

	public void limpa(){
		 linhas = new Hashtable();

		 triangulos = new ArrayList();
		 tetraedros = new ArrayList();

		 pontos = new ArrayList();
		  
		 tsleep = 1000;
		 
		 facesDaMorte = null;
		 faceDaMorte = null ;
		 xDaMorte = 0;
		 contagemDamorte = 70;
		 for(int i=0;i<4;i++)
			 laserMorte[i]=false;
		 tiraPontos = 0;
		 explodir = false;
		 tetraedroDidatico=-1;
	}
	
	private void cena1(){
	    
	    this.xPos = 0.40000015f; 
	    this.yPos = -0.6f;
	    this.zoom = -21.5f;
	    this.angleX = 0.0f;
	    this.angleY = 32.0f;
	    this.angleZ = 0.0f;
	}
	
	private void cena2(){
	    
	    this.xPos = -4.9999995f; 
	    this.yPos = -0.6f;
	    this.zoom = -1.5f;
	    this.angleX = 0.0f;
	    this.angleY = 32.0f;
	    this.angleZ = 0.0f;
	}
	
	public EmbrulhoPainel(Dimension resolucao) {
		this.resolucao = resolucao;
		super.setPreferredSize(resolucao);
		this.principal();
	}

	public void componentResized(ComponentEvent e) {
		myReshape(getSize().width, getSize().height);
		display();
		repaint();
	}

	private void myReshape(int w, int h) {
		myGL.glViewport(0, 0, w, h);
		myGL.glClearColor(1, 1, 1, 1);
		myGL.glMatrixMode(GL.GL_PROJECTION);
		myGL.glLoadIdentity();
		myGLU.gluPerspective(60.0, (double) w / (double) h, 1.0, 60.0);
		myGL.glMatrixMode(GL.GL_MODELVIEW);
		myGL.glLoadIdentity();

		myGL.glTranslatef(0.0f, 0.0f, -5.0f);
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void paint(Graphics g) {
		myUT.glutSwapBuffers(g, this);
	}

	public  synchronized void display() {
		 
 
		myGL.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		 
		/* clear the matrix */
		myGL.glLoadIdentity();

		myGL.glDepthRange(-100, 100);

		/* viewing transformation */
		myGL.glTranslatef(this.xPos, this.yPos, this.zoom);

		myGL.glRotatef(angleX, 1.0f, 0.0f, 0.0f); // x
		myGL.glRotatef(angleY, 0.0f, 1.0f, 0.0f); // y
		myGL.glRotatef(angleZ, 0.0f, 0.0f, 1.0f); // z

		/* modeling transformation */
		myGL.glScalef(.135f, .135f, .135f);
		
		desenhaTetraedroDidatico();

		if(facesDaMorte!=null && facesDaMorte.size()>0)
			this.desenhaFacesDaMorte();
		
		if(!apagaEixo)
			eixos();
		
		if(!explodir){
			if (colorir)
				desenhaTriangulos();
			
			desenhaTrianguloDidatico();
			
			if (!apagaLinhas)  
			{
				//myGL.glEnable(GL.GL_DEPTH_TEST);
				desenhaLinhas();
				//myGL.glDisable(GL.GL_DEPTH_TEST);
			}
				
			
			if (tiraPontos!=0)
				desenhaPontos();

			
		}else
			this.desenhaTetraedros();
		
		
		
		
		myGL.glFlush();
	}

	 
	
	// Devolve a chave da linha no Hash.
	private String linha2String(Linha l) {
		Ponto3D p1, p2;
		p1 = l.getP1();
		p2 = l.getP2();
		String s = Float.toString(p1.getX()) + "&" + Float.toString(p1.getY())
				+ "&" + Float.toString(p1.getZ()) + "->"
				+ Float.toString(p2.getX()) + "&" + Float.toString(p2.getY())
				+ "&" + Float.toString(p2.getZ());

		return s;
	}

	public void insereTriangulo(Triangulo triangulo) {

		this.triangulos.add(triangulo);
	}

	public void desenhaTetraedros(){
		
		for(int i=0;i<tetraedros.size();i++){
			Tetraedro t = (Tetraedro)tetraedros.get(i);
			 
			myGL.glBegin(GL.GL_LINES);
				myGL.glColor3f(1.0f,0,0);
				myGL.glVertex3f((float)t.getV1().getX(), (float)t.getV1().getY(), (float)t.getV1().getZ()); 
				myGL.glVertex3f((float)t.getV2().getX(), (float)t.getV2().getY(), (float)t.getV2().getZ());
				
				myGL.glVertex3f((float)t.getV1().getX(), (float)t.getV1().getY(), (float)t.getV1().getZ()); 
				myGL.glVertex3f((float)t.getV3().getX(), (float)t.getV3().getY(), (float)t.getV3().getZ());
				
				myGL.glVertex3f((float)t.getV1().getX(), (float)t.getV1().getY(), (float)t.getV1().getZ()); 
				myGL.glVertex3f((float)t.getV4().getX(), (float)t.getV4().getY(), (float)t.getV4().getZ());
				
				myGL.glVertex3f((float)t.getV2().getX(), (float)t.getV2().getY(), (float)t.getV2().getZ()); 
				myGL.glVertex3f((float)t.getV3().getX(), (float)t.getV3().getY(), (float)t.getV3().getZ());
				 
				myGL.glVertex3f((float)t.getV3().getX(), (float)t.getV3().getY(), (float)t.getV3().getZ());
				myGL.glVertex3f((float)t.getV4().getX(), (float)t.getV4().getY(), (float)t.getV4().getZ());
				
				myGL.glVertex3f((float)t.getV4().getX(), (float)t.getV4().getY(), (float)t.getV4().getZ());
				myGL.glVertex3f((float)t.getV2().getX(), (float)t.getV2().getY(), (float)t.getV2().getZ());
		   myGL.glEnd();
		}
	}
	
	public void desenhaTetraedroDidatico() {

		if (this.tetraedros!=null && this.tetraedros.size()!=0) {
			if(this.tetraedroDidatico>=0 && this.tetraedroDidatico<this.tetraedros.size()){
			
				Tetraedro tetraedro = (Tetraedro)this.tetraedros.get(tetraedroDidatico);
				Face[] faces = tetraedro.getFaces();
				myGL.glEnable(GL.GL_DEPTH_TEST);
				myGL.glBegin(GL.GL_TRIANGLES);
				
				 
					
					//0
					myGL.glColor3f(09f, 0.0f, 0f);
					myGL.glVertex3f((float)faces[0].getV1().getX(), (float)faces[0].getV1().getY(), (float)faces[0].getV1().getZ());

					myGL.glColor3f(0.8f, 0f, 0f);
					myGL.glVertex3f((float)faces[0].getV2().getX(), (float)faces[0].getV2().getY(), (float)faces[0].getV2().getZ());

					myGL.glColor3f(0.7f, 0.0f, 0f);
					myGL.glVertex3f((float)faces[0].getV3().getX(), (float)faces[0].getV3().getY(), (float)faces[0].getV3().getZ());
					
					//1
					myGL.glColor3f(0f, 0.9f, 0f);
					myGL.glVertex3f((float)faces[1].getV1().getX(), (float)faces[1].getV1().getY(), (float)faces[1].getV1().getZ());

					myGL.glColor3f(0f, 0.8f, 0f);
					myGL.glVertex3f((float)faces[1].getV2().getX(), (float)faces[1].getV2().getY(), (float)faces[1].getV2().getZ());

					myGL.glColor3f(0f, 0.7f, 0f);
					myGL.glVertex3f((float)faces[1].getV3().getX(), (float)faces[1].getV3().getY(), (float)faces[1].getV3().getZ());
					
					//2
					myGL.glColor3f(0f, 0.0f, 0.9f);
					myGL.glVertex3f((float)faces[2].getV1().getX(), (float)faces[2].getV1().getY(), (float)faces[2].getV1().getZ());

					myGL.glColor3f(0f, 0f, 0.8f);
					myGL.glVertex3f((float)faces[2].getV2().getX(), (float)faces[2].getV2().getY(), (float)faces[2].getV2().getZ());

					myGL.glColor3f(0f, 0.0f, 0.7f);
					myGL.glVertex3f((float)faces[2].getV3().getX(), (float)faces[2].getV3().getY(), (float)faces[2].getV3().getZ());
					
					//3
					myGL.glColor3f(0.9f, 0.9f, 0f);
					myGL.glVertex3f((float)faces[3].getV1().getX(), (float)faces[3].getV1().getY(), (float)faces[3].getV1().getZ());

					myGL.glColor3f(0.8f, 0.8f, 0f);
					myGL.glVertex3f((float)faces[3].getV2().getX(), (float)faces[3].getV2().getY(), (float)faces[3].getV2().getZ());

					myGL.glColor3f(0.7f, 0.7f, 0f);
					myGL.glVertex3f((float)faces[3].getV3().getX(), (float)faces[3].getV3().getY(), (float)faces[3].getV3().getZ());
					
				 
				myGL.glEnd();
				myGL.glDisable(GL.GL_DEPTH_TEST);
						
			}
			
		}
	}
	
	public void desenhaTrianguloDidatico() {

		if (this.trianguloDidatico != null) {
			myGL.glBegin(GL.GL_TRIANGLES);
			myGL.glColor3f(0.9f, 0.0f, 0f);
			myGL.glVertex3f(trianguloDidatico.getP1().getX(), trianguloDidatico
					.getP1().getY(), trianguloDidatico.getP1().getZ());

			myGL.glColor3f(0.5f, 0f, 0f);
			myGL.glVertex3f(trianguloDidatico.getP2().getX(), trianguloDidatico
					.getP2().getY(), trianguloDidatico.getP2().getZ());

			myGL.glColor3f(0.4f, 0.0f, 0f);
			myGL.glVertex3f(trianguloDidatico.getP3().getX(), trianguloDidatico
					.getP3().getY(), trianguloDidatico.getP3().getZ());
			myGL.glEnd();
		}
	}

	public void desenhaQuadrado(float rX, float rY, float rZ, float rSize) {
		myGL.glBegin(GL.GL_LINES);
			myGL.glColor3f(1.0f,0,0);
			myGL.glVertex3f(rX-rSize, rY+rSize, rZ); 
			myGL.glVertex3f(rX+rSize, rY+rSize, rZ);
			
			myGL.glVertex3f(rX+rSize, rY+rSize, rZ); 
			myGL.glVertex3f(rX+rSize, rY-rSize, rZ);
			
			myGL.glVertex3f(rX+rSize, rY-rSize, rZ); 
			myGL.glVertex3f(rX-rSize, rY-rSize, rZ);
			
			myGL.glVertex3f(rX-rSize, rY-rSize, rZ);
			myGL.glVertex3f(rX-rSize, rY+rSize, rZ);
			
		myGL.glEnd();
		
	}

	
	public void desenhaCubo(float rX, float rY, float rZ, float rSize) {

		float rS2 = rSize / 2;
		/*
		 * { glVertex3f(rX-rS2, rY+rS2, rZ-rS2); // A glVertex3f(rX+rS2, rY+rS2,
		 * rZ-rS2); // B glVertex3f(rX+rS2, rY-rS2, rZ-rS2); // C
		 * glVertex3f(rX-rS2, rY-rS2, rZ-rS2); // D glVertex3f(rX-rS2, rY+rS2,
		 * rZ+rS2); // E glVertex3f(rX-rS2, rY-rS2, rZ+rS2); // F
		 * glVertex3f(rX+rS2, rY+rS2, rZ+rS2); // H glVertex3f(rX+rS2, rY-rS2,
		 * rZ+rS2); // G }
		 */

		// 6
		myGL.glBegin(GL.GL_QUADS);
		myGL.glColor3f(1f, 0f, 0f);
		myGL.glVertex3f(rX - rS2, rY - rS2, rZ - rS2); // D
		myGL.glVertex3f(rX + rS2, rY - rS2, rZ - rS2); // C
		myGL.glVertex3f(rX + rS2, rY - rS2, rZ + rS2); // G
		myGL.glVertex3f(rX - rS2, rY - rS2, rZ + rS2); // F

		// glVertex3f(rX-rS2, rY+rS2, rZ-rS2);
		myGL.glEnd();

		// 5
		myGL.glBegin(GL.GL_QUADS);
		myGL.glColor3f(1f, 0f, 0f);
		myGL.glVertex3f(rX - rS2, rY + rS2, rZ - rS2); // A
		myGL.glVertex3f(rX - rS2, rY + rS2, rZ + rS2); // E
		myGL.glVertex3f(rX + rS2, rY + rS2, rZ + rS2); // H
		myGL.glVertex3f(rX + rS2, rY + rS2, rZ - rS2); // B
		// glVertex3f(rX-rS2, rY+rS2, rZ-rS2);
		myGL.glEnd();

		// 1
		myGL.glBegin(GL.GL_QUADS);
		myGL.glColor3f(0f, 0f, 1f);
		myGL.glVertex3f(rX - rS2, rY + rS2, rZ - rS2); // A
		myGL.glVertex3f(rX - rS2, rY - rS2, rZ - rS2); // D
		myGL.glVertex3f(rX - rS2, rY - rS2, rZ + rS2); // F
		myGL.glVertex3f(rX - rS2, rY + rS2, rZ + rS2); // E

		// glVertex3f(rX-rS2, rY+rS2, rZ-rS2);
		myGL.glEnd();

		// 2
		myGL.glBegin(GL.GL_QUADS);
		myGL.glColor3f(0f, 1f, 0f);
		myGL.glVertex3f(rX - rS2, rY + rS2, rZ - rS2); // A
		myGL.glVertex3f(rX + rS2, rY + rS2, rZ - rS2); // B
		myGL.glVertex3f(rX + rS2, rY - rS2, rZ - rS2); // C
		myGL.glVertex3f(rX - rS2, rY - rS2, rZ - rS2); // D
		// glVertex3f(rX-rS2, rY+rS2, rZ-rS2);
		myGL.glEnd();

		// 3
		myGL.glBegin(GL.GL_QUADS);
		myGL.glColor3f(0f, 0f, 1f);
		myGL.glVertex3f(rX + rS2, rY - rS2, rZ - rS2); // C
		myGL.glVertex3f(rX + rS2, rY + rS2, rZ - rS2); // B
		myGL.glVertex3f(rX + rS2, rY + rS2, rZ + rS2); // H
		myGL.glVertex3f(rX + rS2, rY - rS2, rZ + rS2); // G
		// glVertex3f(rX+rS2, rY-rS2, rZ-rS2);
		myGL.glEnd();

		// 4
		myGL.glBegin(GL.GL_QUADS);
		myGL.glColor3f(0f, 1f, 0f);
		myGL.glVertex3f(rX - rS2, rY + rS2, rZ + rS2); // E
		myGL.glVertex3f(rX - rS2, rY - rS2, rZ + rS2); // F
		myGL.glVertex3f(rX + rS2, rY - rS2, rZ + rS2); // G
		myGL.glVertex3f(rX + rS2, rY + rS2, rZ + rS2); // H

		// glVertex3f(rX+rS2, rY-rS2, rZ-rS2);
		myGL.glEnd();

	}

	public void desenhaTriangulos() {

		myGL.glBegin(GL.GL_TRIANGLES);
		for (int i = 0; i < this.triangulos.size(); i++) {
			Triangulo triangulo = (Triangulo) triangulos.get(i);

			myGL.glColor3f(0f, 0f, 0.9f);
			myGL.glVertex3f(triangulo.getP1().getX(), triangulo.getP1().getY(),
					triangulo.getP1().getZ());

			myGL.glColor3f(0f, 0f, 0.7f);
			myGL.glVertex3f(triangulo.getP2().getX(), triangulo.getP2().getY(),
					triangulo.getP2().getZ());

			myGL.glColor3f(0f, 0f, 0.4f);
			myGL.glVertex3f(triangulo.getP3().getX(), triangulo.getP3().getY(),
					triangulo.getP3().getZ());

		}
		myGL.glEnd();

	}

	public void insereLinha(Linha l) {
		// Soh devo colocar se já não tiver esta linha com os pontos
		// invertidos. Se for a mesma linha, será substituída.
		Linha tmp;

		Cor atual;

		String s = linha2String(l);

		// Verifica se esta no hash no formato dado
		if (linhas.containsKey(s)) {
			// Só preciso colocar a cor atual como anterior e a cor da
			// nova linha como atual.
			tmp = (Linha) linhas.get(s);

			atual = tmp.getCor();
			tmp.setCor(l.getCor());
			tmp.getCor().setAnterior(atual);
		} else { // Se nao esta no formato dado, ou esta no invertido ou
			// nao esta. Podemos colocar esta nova linha no invertido,
			// substituindo alguma possivel anterior.
			Linha l2 = new Linha(l.getP2(), l.getP1(), l.getCor());
			s = linha2String(l2);

			if (linhas.containsKey(s)) {
				// Só preciso colocar a cor atual como anterior e a cor da
				// nova linha como atual.
				tmp = (Linha) linhas.get(s);

				atual = tmp.getCor();
				tmp.setCor(l2.getCor());
				tmp.getCor().setAnterior(atual);
			} else {
				// Preciso adicionar a linha no hash
				linhas.put(s, l2);
			}
		}

	}

	private void desenhaLinhas() {
		Enumeration conjunto;
		Linha l;
		
		Cor c;

		conjunto = linhas.elements();

		myGL.glBegin(GL.GL_LINES);
	
			while (conjunto.hasMoreElements()) {
				l = (Linha) conjunto.nextElement();
				c = l.getCor();
				myGL.glColor3f(c.getR(), c.getG(), c.getB());
				myGL.glVertex3f(l.getP1().getX(), l.getP1().getY(), l.getP1()
						.getZ());
				myGL.glVertex3f(l.getP2().getX(), l.getP2().getY(), l.getP2()
						.getZ());
			}
		 
		
		myGL.glEnd();
		
		
	}

	private void desenhaPontos() {
			Ponto3D p;
			/*double xM=0;
			double yM=0;
			double zM=0;*/
			 
			
			
			myGL.glEnable (GL.GL_DEPTH_TEST);
		 
			for (int i = 0; i < pontos.size(); i++) {
				p = (Ponto3D) pontos.get(i);
				if(tiraPontos==1)
					this.desenhaCubo(p.getX(),p.getY(),p.getZ(),1.3f);
				else if(tiraPontos==2)
					this.desenhaQuadrado(p.getX(),p.getY(),p.getZ(),1.3f/2);
				
				/*xM+=p.getX();
				yM+=p.getY();
				zM+=p.getZ();*/
			}
			//this.desenhaQuadrado((float)(xM/4),(float)(yM/4),(float)(zM/4),1.3f/2);
			myGL.glDisable(GL.GL_DEPTH_TEST);
	}
	
	public void inserePonto(Ponto3D p, Cor c) {
		 
		Ponto3D p1 = new Ponto3D(p.getX()  , p.getY()  , p.getZ()
				 );
		 

		this.pontos.add(p1);
		 
	}

	public void principal() {
		myUT.glutInitWindowSize(this.resolucao.width, this.resolucao.height);
		myUT.glutInitWindowPosition(0, 0);
		myUT.glutCreateWindow(this);

		myGL.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		myGL.glShadeModel(GL.GL_SMOOTH);

		// as call glutReshapeFunc(reshape)
		// addComponentListener (this);
		myReshape(getSize().width, getSize().height);
		// call display as call glutDisplayFunc(display)
		display();
		// eixos();

		// rodaEmbrulho();
	}

	public void rotacionaTela() {
		display();
		repaint();
		/*
		 * Thread t = new Thread(){ public void run() {
		 * 
		 * while(true){ try { Thread.sleep(150); } catch (InterruptedException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 * 
		 * 
		 * 
		 * angleX += 1f; angleY +=2f ; angleZ +=3f ;
		 * 
		 * 
		 * display(); repaint(); //System.out.println("teste");
		 *  } } }; t.setPriority(Thread.MAX_PRIORITY); t.start();
		 */
	}

	public void eixos() {

		myGL.glBegin(GL.GL_LINES);
		myGL.glColor3f(0, 0, 1);
		
		myGL.glVertex3f(0, 0, 0);
		myGL.glVertex3f(0, 0, 50);
		
		myGL.glColor3f(0, 0, 1);
		myGL.glVertex3f(0, 0, 51);
		myGL.glVertex3f(0, 0, 52);
		myGL.glVertex3f(0, 0, 52);
		myGL.glVertex3f(0, 1, 51);
		myGL.glVertex3f(0, 1, 51);
		myGL.glVertex3f(0, 1, 52);

		myGL.glColor3f(0, 0, 1);
		myGL.glVertex3f(0, 0, 0);
		myGL.glVertex3f(0, 50, 0);
		
		 

		

		myGL.glColor3f(0, 0, 1);
		myGL.glVertex3f(0, 0, 0);
		myGL.glVertex3f(50, 0, 0);
		
		myGL.glColor3f(0, 0, 1);
		myGL.glVertex3f(52, 0, 0);
		myGL.glVertex3f(51, 1, 0);
		myGL.glVertex3f(52, 1, 0);
		myGL.glVertex3f(51, 0, 0);

		myGL.glEnd();
	}

	public ArrayList rodaEmbrulho(final ArrayList vertices) {

		final Embrulho embrulho = new Embrulho(null);
		 
		ArrayList res = null;
		long ini = System.currentTimeMillis();
		// embrulho.resolveByListaDeFaces(vertices);
		res = embrulho.resolveByWESemApplet(vertices);

		// embrulho.resolveByWEThread(vertices);
		long fim = System.currentTimeMillis();
		System.out.println((fim - ini) + " milisegundos");
		trianguloDidatico = null;
		 
		return res;
		 

	}

	public void rodaTetraedralizacao(final ArrayList vertices) {

		final Tetraedralizacao tetra = new Tetraedralizacao(this);
		Thread t = new Thread() {
			public void run() {

				long ini = System.currentTimeMillis();
				// embrulho.resolveByListaDeFaces(vertices);
				tetra.tetraedralizar(vertices);

				// embrulho.resolveByWEThread(vertices);
				long fim = System.currentTimeMillis();
				System.out.println((fim - ini) + " milisegundos");
				trianguloDidatico = null;
			}
		};
		t.setPriority(Thread.MAX_PRIORITY);
		t.start();

	}

	public void desenhaFace(Face f1) {

		Ponto3D p1 = f1.getV1().toPonto3D();
		Ponto3D p2 = f1.getV2().toPonto3D();
		Ponto3D p3 = f1.getV3().toPonto3D();

		Triangulo triangulo = new Triangulo(p1, p2, p3);

		trianguloDidatico = triangulo;

		try {
			Thread.sleep(tsleep);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// this.triangulos.clear();

		Linha l1 = new Linha(p1, p2, cor);
		Linha l2 = new Linha(p2, p3, cor);
		Linha l3 = new Linha(p3, p1, cor);

		this.insereLinha(l1);
		this.insereLinha(l2);
		this.insereLinha(l3);

		this.insereTriangulo(triangulo);

		// ver depois
		if(tsleep!=0){
			display();
			repaint();
		}
		

	}
	
	public void desenhaFacesDaMorte() {

		 
		 for(int i=0;i<facesDaMorte.size();i++){
			  
				 Face f = (Face)this.facesDaMorte.get(i);
				 myGL.glBegin(GL.GL_LINES);
					myGL.glColor3f(0, 0, 0);
					
					myGL.glVertex3f((float)f.getV1().getX()-xDaMorte, (float)f.getV1().getY(), (float)f.getV1().getZ());
					myGL.glVertex3f((float)f.getV2().getX()-xDaMorte, (float)f.getV2().getY(), (float)f.getV2().getZ());
					
					myGL.glVertex3f((float)f.getV2().getX()-xDaMorte, (float)f.getV2().getY(), (float)f.getV2().getZ());
					myGL.glVertex3f((float)f.getV3().getX()-xDaMorte, (float)f.getV3().getY(), (float)f.getV3().getZ());
					
					myGL.glVertex3f((float)f.getV3().getX()-xDaMorte, (float)f.getV3().getY(), (float)f.getV3().getZ());
					myGL.glVertex3f((float)f.getV1().getX()-xDaMorte, (float)f.getV1().getY(), (float)f.getV1().getZ());
	
				myGL.glEnd();
			  
		 }
		
		 if(faceDaMorte!=null){
			 myGL.glBegin(GL.GL_TRIANGLES);
				 
					 

					myGL.glColor3f(0f, 0f, 0.9f);
					myGL.glVertex3f((float)faceDaMorte.getV1().getX()-xDaMorte, (float)faceDaMorte.getV1().getY(),
							(float)faceDaMorte.getV1().getZ());

					myGL.glColor3f(0f, 0f, 0.7f);
					myGL.glVertex3f((float)faceDaMorte.getV2().getX()-xDaMorte, (float)faceDaMorte.getV2().getY(),
							(float)faceDaMorte.getV2().getZ());

					myGL.glColor3f(0f, 0f, 0.4f);
					myGL.glVertex3f((float)faceDaMorte.getV3().getX()-xDaMorte, (float)faceDaMorte.getV3().getY(),
							(float)faceDaMorte.getV3().getZ());

				 
				myGL.glEnd();
		 }
		
		if(contagemDamorte==0){
			myGL.glBegin(GL.GL_LINES);
				myGL.glColor3f(0, 1, 0);
				
				if(laserMorte[0]==true){
					
					myGL.glVertex3f((float)faceDaMorte.getV1().getX()-xDaMorte, (float)faceDaMorte.getV1().getY(), (float)faceDaMorte.getV1().getZ());
					myGL.glVertex3f((float)faceDaMorte.getV1().getX()-xDaMorte-9, 4, 0);
				}
				
				if(laserMorte[1]==true){
					myGL.glVertex3f((float)faceDaMorte.getV2().getX()-xDaMorte, (float)faceDaMorte.getV2().getY(), (float)faceDaMorte.getV2().getZ());
					myGL.glVertex3f((float)faceDaMorte.getV1().getX()-xDaMorte-9, 4, 0);
				}
				
				if(laserMorte[2]==true){
					myGL.glVertex3f((float)faceDaMorte.getV3().getX()-xDaMorte, (float)faceDaMorte.getV3().getY(), (float)faceDaMorte.getV3().getZ());
					myGL.glVertex3f((float)faceDaMorte.getV1().getX()-xDaMorte-9, 4, 0);
				}
				
				if(laserMorte[3]==true){
					myGL.glVertex3f((float)faceDaMorte.getV1().getX()-xDaMorte-9, 4, 0);
					myGL.glVertex3f(0, 0, 0);
				}
				
			
			myGL.glEnd();
			
		}
			
	}
	
	
	
	public void explodirTetraedros(){
		Thread t = new Thread(){
			public void run() {
				  
				while(true){
					 
					for(int i=0;i<tetraedros.size();i++)
					 {
						 
							 Tetraedro t = (Tetraedro)tetraedros.get(i);
							 t.move();	
							
						 
					 }
					 
					 display();
					 repaint();
					 try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				 
				 
			};
		};
		t.setPriority(Thread.MAX_PRIORITY)	;
		t.start();
	}

	public void zoomIn() {
		this.zoom -= 0.5;
	}

	public void zoomOut() {
		this.zoom += 0.5;
	}

	public void moveLeft() {
		this.xPos -= 0.2;
	}

	public void moveRight() {
		this.xPos += 0.2;
	}

	public void moveUp() {
		this.yPos += 0.2;
	}

	public void moveDown() {
		this.yPos -= 0.2;
	}

	public void faster() {
		
		if (this.tsleep != 0) {
			tsleep -= 50;
		}
	}

	public void slower() {
		if (this.tsleep != 0) {
			tsleep += 50;
		}
	}

	public void mudarColorir() {
		colorir = !colorir;
	}

	public void mudarApagaLinha() {
		apagaLinhas = !apagaLinhas;
	}
	
	public void mudarApagaEixo() {
		apagaEixo = !apagaEixo;
	}

	public void tiraPontos() {
		this.tiraPontos++;
		this.tiraPontos %=3;

	}

	public long getTsleep() {
		return tsleep;
	}

	public void setTsleep(long tsleep) {
		this.tsleep = tsleep;
	}

	public float getAngleX() {
		return angleX;
	}

	public void setAngleX(float angleX) {
		this.angleX = angleX;
	}

	public float getAngleY() {
		return angleY;
	}

	public void setAngleY(float angleY) {
		this.angleY = angleY;
	}

	public float getAngleZ() {
		return angleZ;
	}

	public void setAngleZ(float angleZ) {
		this.angleZ = angleZ;
	}

	public ArrayList getTetraedros() {
		return tetraedros;
	}

	public void setTetraedros(ArrayList tetraedros) {
		for(int i=0;i<tetraedros.size();i++){
			Tetraedro t = (Tetraedro)tetraedros.get(i);
			Vertice v1 = new Vertice(t.getV1().getX(),t.getV1().getY(),t.getV1().getZ());
			Vertice v2 = new Vertice(t.getV2().getX(),t.getV2().getY(),t.getV2().getZ());
			Vertice v3 = new Vertice(t.getV3().getX(),t.getV3().getY(),t.getV3().getZ());
			Vertice v4 = new Vertice(t.getV4().getX(),t.getV4().getY(),t.getV4().getZ());
			this.tetraedros.add(new Tetraedro(v1,v2,v3,v4));
		}
		 
	}

	public boolean isExplodir() {
		return explodir;
	}

	public void setExplodir(boolean explodir) {
		this.explodir = explodir;
		this.explodirTetraedros();
	}
	
	public void rodarAnimacaoFechoDaMorte(){
		
		if(tetraedros==null || tetraedros.size()==0)
			return;
		final FechoDaMorte fechoDaMorte = new FechoDaMorte();
		this.facesDaMorte = fechoDaMorte.getFacesDaMorte();
		this.faceDaMorte = fechoDaMorte.getFaceDaMorte(); 
		this.apagaEixo = true;
		this.tiraPontos = 0; 
		
		 
		this.display();
		this.repaint();
		
		//movendo a estrela da morte
		 Thread t = new Thread(){
			public void run() {
				  
				 while(contagemDamorte!=0){
					xDaMorte+=0.2;
					 
					try {
						Thread.sleep(250);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 display();
					 repaint();
					 contagemDamorte--;
				  }
				 
				 
				//habilitando o laser da morte
				 cena2();
				 display();
				 repaint();
				 
				 for(int i=0;i<laserMorte.length;i++){
					 laserMorte[i]=true;
					 display();
					 repaint();
					 try {
							Thread.sleep(600);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				 }
				 
				//explodindo os tetraedros
				 cena1();
				 display();
				 repaint();
				 try {
						Thread.sleep(600);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				 }
				 if(tetraedros!=null && tetraedros.size()!=0){
					 explodir = true;
					 int count = 0;
					 while(count!=150){
						 
						 	if(count==6){
						 	   
						 		for(int i=0;i<laserMorte.length;i++)
						 			laserMorte[i] = false;
						 	}
						 
						 	//zoom-=0.1;
							for(int i=0;i<tetraedros.size();i++)
							 {
								 
									 Tetraedro t = (Tetraedro)tetraedros.get(i);
									 t.move();	
									
								 
							 }
							 
							 display();
							 repaint();
							 try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							count++;
					}
				 }//if
				
				 while(tetraedros.size()!=0)
				 {

						tetraedros.remove(0);
						display();
						repaint();
						 try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				 }
				
				 
			};
		};
		t.setPriority(Thread.MAX_PRIORITY)	;
		t.start() ;
		
		 
		
		
	}
		
		
		 
}