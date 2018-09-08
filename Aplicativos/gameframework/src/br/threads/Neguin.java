/*
 * Created on 20/07/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package br.threads;

import br.gui.PainelDeDesenho;
import br.table.Table;

/**
 * @author Usuario
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Neguin extends Thread{

	private Table table;
	private PainelDeDesenho pd;
	int x = 0;
	int y = 0;
	
	public Neguin(Table table,PainelDeDesenho pd,int x,int y)
	{
		this.table = table;
		this.pd = pd;
		this.x = x;
		this.y = y;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			while(true){
				//System.out.println("teste");
				
				this.table.pintar(x,y,0);
				//substitui por heurística
				x++;
				y++;
				this.table.pintar(x,y,1);
				this.pd.repaint();
				Thread.sleep(1000);
				
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void moverParaEsquerda()
	{
		//tratamentos de colisão no 4 métodos!
		this.table.pintar(this.x,y,0);
		this.x = this.x-1;
		this.table.pintar(this.x,y,1);
		this.pd.repaint();
	}
	
	public void moverParaDireita()
	{
		this.table.pintar(this.x,y,0);
		this.x = this.x+1;
		this.table.pintar(this.x,y,1);
		this.pd.repaint(); 
	}
	
	public void moverParaCima()
	{
		this.table.pintar(this.x,y,0);
		this.y = this.y-1;
		this.table.pintar(this.x,y,1);
		this.pd.repaint();
	}
	
	public void moverParaBaixo()
	{
		this.table.pintar(this.x,y,0);
		this.y = this.y+1;
		this.table.pintar(this.x,y,1);
		this.pd.repaint();
	}
}
