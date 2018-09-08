package br.ufc.lia.jeffersoncarvalho.primivasgeometricas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import br.ufc.lia.jeffersoncarvalho.ordenacao.Element;
import br.ufc.lia.jeffersoncarvalho.ordenacao.SortingMethods;

public class LinhaPoligonal extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector pontos = new Vector();
	private boolean resolvido = false;
	
	public boolean isResolvido() {
		return resolvido;
	}

	public void setResolvido(boolean resolvido) {
		this.resolvido = resolvido;
	}

	public void addPonto(Point ponto){
		this.pontos.add(ponto);
	}
	
	public Vector getPontos() {
		return this.pontos;
	}
	
	public void resolve()
	{
		if(this.pontos.size()==0)
			return;
		//transformar em vetor de Elements
		Element[] elements = new Element[pontos.size()];
		for(int i=0;i<pontos.size();i++){
			Point p = (Point)pontos.get(i);
			Element element = new Element(p,p.getX());
			elements[i] = element;
		}
		
		//sorting
		SortingMethods.mergeSort(elements,elements.length);
		
		//voltando para pontos...
		pontos.clear();
		for(int i=0;i<elements.length;i++){
			this.addPonto((Point)elements[i].getContent());
		}
		
		this.setResolvido(true);
		
	}
	
	//***GUI***GUI***GUI
	public LinhaPoligonal(){
		
		 
		super("Linha Poligonal");
 	 
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		this.setResizable(false);
		PainelDeDesenho pd = new PainelDeDesenho(this);
		c.add(pd,BorderLayout.CENTER);
		c.add(new PainelDeControle(this,pd),BorderLayout.SOUTH);
		this.setVisible(true);
		pack();
	
		 
		
		 
  
		//Fechando Janela
		addWindowListener(
				new WindowAdapter(){
					public void windowClosing(WindowEvent e)
					{
						
						System.exit(0);
					}
				}  
			);
	}

	public static void main(String[] args) {
		new LinhaPoligonal();
	}



	
}

class PainelDeDesenho extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LinhaPoligonal myLp = null;
	public PainelDeDesenho(LinhaPoligonal lp)
	{
		super.setPreferredSize(new Dimension(400,400));
		setBackground(Color.white);
		myLp = lp;
		addMouseListener(
				new MouseAdapter(){
					public void mouseClicked(MouseEvent e) {
						if(myLp.isResolvido())
							return;
						  myLp.addPonto(new Point(e.getX(),e.getY()));
						  //System.out.println(myLp.getPontos().size());
						  repaint();
					}
				}
		);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		//Anti Aliasing 
   	  	Graphics2D g2d = (Graphics2D)g;
   	  	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
   	  	
		Vector pontos = myLp.getPontos();
		int[] xValues = null;
		int[] yValues = null;
		if(myLp.isResolvido())
		{
			xValues = new int[pontos.size()];
			yValues = new int[pontos.size()];
		}
			
		for(int i=0;i<pontos.size();i++){
			Point p = (Point)pontos.get(i);
			g2d.fillOval((int)p.getX()-2,(int)p.getY()-2,5,5);
			if(xValues!=null)
			{
				xValues[i] = (int)p.getX();
				yValues[i] = (int)p.getY();
			}
		}
		
		if(xValues!=null)
			g2d.drawPolyline(xValues,yValues,xValues.length);
		 
	}
}
class PainelDeControle extends JPanel
{
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PainelDeControle(final LinhaPoligonal lp, final PainelDeDesenho pd)
	{
		
		this.setLayout(new FlowLayout());
		 
		 
	 
		final JButton desenha = new JButton("Desenha");
		final JButton apaga = new JButton("Apaga");
		apaga.setEnabled(false);
		desenha.addActionListener( new ActionListener(){
			 
			public void actionPerformed(ActionEvent e) {
				//desenha
				if(lp.getPontos().isEmpty())
					return;
				 
				 lp.resolve();		 
				 pd.repaint();
				 apaga.setEnabled(true);
				 desenha.setEnabled(false);
			}

		});
		
		this.add(desenha);
		
		apaga.addActionListener( new ActionListener(){
			 
			public void actionPerformed(ActionEvent e) {
				lp.setResolvido(false);
				lp.getPontos().clear();
				pd.repaint();
				apaga.setEnabled(false);
				desenha.setEnabled(true);
			}

		});
		this.add(apaga);
	}
}