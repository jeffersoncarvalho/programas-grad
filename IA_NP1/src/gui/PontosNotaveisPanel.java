/*
 * PontosNotaveisPanel.java
 *
 * Created on 25 de Agosto de 2004, 09:16
 */

package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author  knoppix
 */
public class PontosNotaveisPanel extends JFrame{
    
    private PainelEscolha painelEscolha;
    /** Creates a new instance of PontosNotaveisPanel */
    public PontosNotaveisPanel() {
       
        setTitle("Contruindo Ambiente");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
				
			}
                });

        //alocando memória dos painéis
        this.painelEscolha = new PainelEscolha();
       
        //adcionando os painéis ao layout do conteiner
        Container c = getContentPane();
	c.setLayout(new BorderLayout());
        c.add(this.painelEscolha,BorderLayout.CENTER);
        
        //mostrando o frame
        this.setResizable(false);
        this.setLocation(200, 20);
        this.show();
        this.pack();
    }
    
}

//onde ficarãos pontos a escolher
class PainelEscolha extends JPanel
{
    public PainelEscolha()
    {
         this.setPreferredSize(new Dimension(100,100));
    }
    
    public void paint(Graphics g)
    {
        super.paint(g);
        g.drawString("Teste", 5, 5);
    }
}
