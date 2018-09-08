import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class TesteInterface extends JFrame
{
	private JPanel buttonPanel;
	private JButton buttons[];
	private PainelDesenho pd;
	
	public TesteInterface()
	{
		super("Teste com Swing");
		Container c=getContentPane();
		buttonPanel = new JPanel();
		buttons = new JButton[5];
		
		pd = new PainelDesenho();
		
		GridLayout grid = new GridLayout(1,buttons.length);
		buttonPanel.setLayout(grid);//meu painel é do tipo grid.
		
		for(int i=0;i<buttons.length;i++)
		{
			buttons[i] = new JButton("Botão "+(i+1));
			buttonPanel.add(buttons[i]);
		}
		
		c.add(buttonPanel,BorderLayout.SOUTH);
		c.add(pd,BorderLayout.NORTH);
		
		setSize(425,150);
		show();
		
	}//construtor
	
	public static void main(String args[])
	{
		TesteInterface ti = new TesteInterface();
		
		ti.addWindowListener(
			new WindowAdapter(){
				public void windowClosing(WindowEvent e)
				{
					System.exit(0);
				}
			}
		);
	}//main
	
}//class

class PainelDesenho extends JPanel
{
	private JButton botao;
	
	public PainelDesenho()
	{
		
		setLayout(new FlowLayout());
		
		botao = new JButton("Teste");
		add(botao);	
	}//constutor
	
	public void paint(Graphics g)
	{
		g.drawString("Jefferson",10,30);
	}
	
}

