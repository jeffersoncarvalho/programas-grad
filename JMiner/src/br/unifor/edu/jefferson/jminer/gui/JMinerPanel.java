/*
 * Created on 16/03/2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package br.unifor.edu.jefferson.jminer.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.unifor.edu.jefferson.jminer.painter.Painter;
import br.unifor.edu.jefferson.jminer.table.Table;

/**
 * @author kurumin
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JMinerPanel extends JPanel{
	private DrawPanel drawPanel;
	private Table table;
	public JMinerPanel()
	{
		this.setLayout(new BorderLayout());
//		alocando memória para os painéis
		drawPanel = new DrawPanel();
		
		
		//Tabuleiro controlador do jogo!
		table = new Table(this.drawPanel);
	
		//opa
		drawPanel.setTable(table);
		
		//controlPanel = new ControlPanel();
		//resultPanel = new ResultPanel();
  
		//Guaribando o drawPanel
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(drawPanel, BorderLayout.CENTER);	
		panel.setBorder(new EmptyBorder(1,1,1,1));
		panel.setBackground(Color.BLACK);
  
		//Adcionando Painéis ao Frame
		this.add(panel, BorderLayout.NORTH); // desenho
		this.add(new ControlPanel(this.table), BorderLayout.SOUTH);
		//c.add(controlPanel.getBar(),BorderLayout.NORTH);
		//c.add(resultPanel,BorderLayout.SOUTH);		
	}

}

class ControlPanel extends JPanel
{
	
	private JButton stop;
	public ControlPanel(final Table table)
	{
		
		this.setLayout(new FlowLayout());
		//----------------------
		this.add(new JLabel("Miners: "));
		final JTextField nMiners = new JTextField(2);
		nMiners.setText("3");
		this.add(nMiners);
		//----------------------
		this.add(new JLabel("Radar Range: "));
		final JTextField range = new JTextField(2);
		range.setText("10");
		this.add(range);
		
		//----------------------
		this.add(new JLabel("Delay (ms): "));
		final JTextField interval = new JTextField(4);
		interval.setText("1000");
		this.add(interval);
		//----------------------
		final JButton start = new JButton("Start");
		final JButton stop = new JButton("Stop");
		stop.setEnabled(false);
		start.addActionListener( new ActionListener(){
			 
			public void actionPerformed(ActionEvent e) {
						
						
						table.setInterval(Integer.parseInt(interval.getText().trim()));
						table.setNumMiners(Integer.parseInt(nMiners.getText().trim()));
						table.setRange(Integer.parseInt(range.getText().trim()));
						Painter.setRange(Integer.parseInt(range.getText().trim()));
						table.startTable();
						interval.setEnabled(false);
						nMiners.setEnabled(false);
						range.setEnabled(false);
						start.setEnabled(false);
						stop.setEnabled(true);
			}

		});
		this.add(start);
		
				stop.addActionListener( new ActionListener(){
			 
					public void actionPerformed(ActionEvent e) {
						
								table.stopTable();
								interval.setEnabled(true);
							    nMiners.setEnabled(true);
							    range.setEnabled(true);
								stop.setEnabled(false);
								start.setEnabled(true);
								  
					}

				});
		this.add(stop);
	}
}

