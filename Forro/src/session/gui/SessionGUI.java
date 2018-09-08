/* file name  : SessionGUI.java
 * authors    : Ricardo Corr�a (correa@lia.ufc.br)
 * created    : Feb 14, 2005
 * copyright  : 
 *
 * modifications:
 *
 */
package session.gui;

//import femoop_test.FemoopEmulator;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.tree.DefaultMutableTreeNode;

import forrocore.SessionDriver;

import model.BindingProperties;
import model.Emulated;
import model.ModelEmulator;


/**
 *    It creates and shows the user dialog frame of the <i>Forr�</i> framework. This frame allow the user
 * to configure and control a simulation session.
 *  
 */
public final class SessionGUI {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 3834024766700008249L;
	static private final String newline = "\n";
	
	// Default locations and models
	/**
	 * Locations started by default
	 */
	protected static final String[] defaultLocations = {System.getProperty("java.rmi.server.hostname")};
//	private static final String[] defaultLocations = new String[] {"localhost", "oros", "precabura", "castanhao"};
	/**
	 * Name of the workspace started by default
	 */
	protected static final String defaultWorkspace = "Main Workspace";
	/**
	 * <tt>WorkspaceDriver</tt> implementation for the default workspace
	 */
	protected static final String defaultMiddlewareImpl = "session.workspace.rmi.RMIMiddleware";
	protected static final String defaultWorkspaceImpl = "session.workspace.NonBlockingWorkspaceDriver";
	/**
	 * Java's package contatining the model implementations
	 */
	protected static final String modelsPackage = System.getProperty("forro.model.packagename");
	/**
	 * Default models
	 */
	protected static final String[] models = {modelsPackage + "." + "Analytical", 
			modelsPackage + "." + "Discrete", modelsPackage + "." + "Numerical", modelsPackage + "." + "Algebraic"};
	
    // Premade convenience dimensions, for use wherever you need 'em.
    public static Dimension HGAP2 = new Dimension(2,1);
    public static Dimension VGAP2 = new Dimension(1,2);

    public static Dimension HGAP5 = new Dimension(5,1);
    public static Dimension VGAP5 = new Dimension(1,5);

    public static Dimension HGAP10 = new Dimension(10,1);
    public static Dimension VGAP10 = new Dimension(1,10);
    
    public static Dimension HGAP15 = new Dimension(15,1);
    public static Dimension VGAP15 = new Dimension(1,15);
    
    public static Dimension HGAP20 = new Dimension(20,1);
    public static Dimension VGAP20 = new Dimension(1,20);
    
    public static Dimension HGAP25 = new Dimension(25,1);
    public static Dimension VGAP25 = new Dimension(1,25);
    
    public static Dimension HGAP30 = new Dimension(30,1);
    public static Dimension VGAP30 = new Dimension(1,30);
    
    /**
	 * @uml.property  name="sessionDriver"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
    protected SessionDriver sessionDriver;
    
	/**
	 * 
	 */
	public SessionGUI(SessionDriver handler) {
		super();
		this.sessionDriver = handler;
	}
	
    public void showFrame() {
    	JFrame frame;
    	ModelsPane modelsPane;
    	
    	JFrame.setDefaultLookAndFeelDecorated(true);
    	JDialog.setDefaultLookAndFeelDecorated(true);
    	
    	frame = new JFrame("Remote Models Launcher");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	modelsPane = new ModelsPane(frame);
    	frame.setContentPane(modelsPane);
    	
    	frame.pack();
    	frame.setVisible(true);
    }
    
    JTree createLaunchedTree(boolean providesPort) {
		JTree   tree;

		DefaultMutableTreeNode top = new DefaultMutableTreeNode(sessionDriver.getSessionName());
		
		String[] modelImplStr;
		String[] portStr;
		String locStr;
		DefaultMutableTreeNode implNode;
		DefaultMutableTreeNode modNode;
		DefaultMutableTreeNode locNode;
		DefaultMutableTreeNode portNode;
		
		for (Iterator locIt = sessionDriver.locationSet().iterator(); locIt.hasNext();) {
			locStr = (String) locIt.next();
			locNode = new DefaultMutableTreeNode(locStr);
			top.add(locNode);
			
			boolean[] active;
			for (int i = 0; i < models.length; i++) {
				modNode = new DefaultMutableTreeNode(models[i]);
				locNode.add(modNode);
				
				modelImplStr = sessionDriver.implList(locStr, new String[] { models[i] });
				active = sessionDriver.probe(locStr, modelImplStr);
				
				for (int k = 0; k < modelImplStr.length; k++) {
					if (active[k]) {
						implNode = new DefaultMutableTreeNode(modelImplStr[k]);
						modNode.add(implNode);
						
						if (providesPort) portStr = sessionDriver.providesPortList(locStr, modelImplStr[k]);
						else portStr = sessionDriver.usesPortList(locStr, modelImplStr[k]);
						
						for (int j = 0; j < portStr.length; j++) {
							portNode = new DefaultMutableTreeNode(portStr[j]);
							implNode.add(portNode);
						}
					}
				}
			}
		}
		
		tree = new JTree(top);
		
	    return tree;
	}


	/**
	 * @author  gisele
	 */
	private class ModelsPane extends JPanel implements ActionListener {
		/**
		 * The main frame.
		 */
		private JFrame modelsFrame;

//		private Configuration config;
		
		private int activeLocation;
		private int activeModel;
		
		private ModelEmulator modelEmu = null;
		
		private JScrollPane implScrollPane;
		
		private JDialog launchedDialog = null;

		private  JComboBox locCB;
		private  JList implList;
		private  JComboBox coordCB;
		private  JComboBox modCB;
		private  JButton launchButton;
		private  JButton cancelButton;
		private  JButton addButton;
		private  JButton seeButton;

		private JButton bindingButton;
		
		public ModelsPane(JFrame f) {
			// the map is initialized with the default locations
			for (int i = 0; i < defaultLocations.length; i++) {
				sessionDriver.startLocation(defaultLocations[i]);
				sessionDriver.startWorkspace(defaultLocations[i], defaultWorkspace, defaultWorkspaceImpl, defaultMiddlewareImpl);
				sessionDriver.startLink(defaultWorkspace+".Loop", defaultLocations[i], defaultLocations[i]);
			}
			
			// Creates GUI
			modelsFrame = f;
			
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			setOpaque(true);

			// Create panels
			JPanel mainPanel = new JPanel(); 
		 	mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); 
		 	add(Box.createRigidArea(HGAP10)); 
		 	add(mainPanel, BorderLayout.CENTER); 
		 	add(Box.createRigidArea(HGAP10)); 
		 	
			JPanel locPanel = new JPanel(); 
		 	locPanel.setLayout(new BoxLayout(locPanel, BoxLayout.X_AXIS));
		 	mainPanel.add(Box.createRigidArea(VGAP10)); 
		 	mainPanel.add(locPanel, BorderLayout.CENTER); 

		 	JPanel centerPanel = new JPanel(); 
		 	centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS)); 
		 	mainPanel.add(Box.createRigidArea(VGAP10)); 
		 	mainPanel.add(centerPanel, BorderLayout.CENTER); 
		 	
		 	JPanel coordPanel = new JPanel(); 
		 	coordPanel.setLayout(new BoxLayout(coordPanel, BoxLayout.X_AXIS)); 
		 	mainPanel.add(Box.createRigidArea(VGAP10)); 
		 	mainPanel.add(coordPanel, BorderLayout.CENTER); 
		 	
		 	JPanel modelPanel = new JPanel(); 
		 	modelPanel.setLayout(new BoxLayout(modelPanel, BoxLayout.X_AXIS)); 
		 	mainPanel.add(Box.createRigidArea(VGAP10)); 
		 	mainPanel.add(modelPanel, BorderLayout.CENTER); 
		 	
		 	JPanel buttonPanel = new JPanel(); 
		 	buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS)); 
		 	mainPanel.add(Box.createRigidArea(VGAP30)); 
		 	mainPanel.add(buttonPanel, BorderLayout.EAST); 
		 	
		 	mainPanel.add(Box.createRigidArea(VGAP10)); 

		 	// Location panel
		 	JLabel l = (JLabel) locPanel.add(new JLabel("Location:"));   
		 	l.setAlignmentX(JLabel.LEFT_ALIGNMENT); 
		 	locPanel.add(Box.createRigidArea(HGAP10)); 
		 	locCB = (JComboBox) locPanel.add(createComboBox(defaultLocations));  
		 	locCB.setAlignmentX(JComboBox.LEFT_ALIGNMENT); 
		 	l.setLabelFor(locCB); 
		 	locPanel.add(Box.createRigidArea(HGAP30)); 
		 	addButton = (JButton) locPanel.add(createButton(this, "Add", "Add a new location"));
		 	locPanel.add(Box.createRigidArea(HGAP10)); 
		 	seeButton = (JButton) locPanel.add(createButton(this, "See", "See launched implementations"));

		 	// Center panel 
			implList = new JList();
			activeLocation = 0;
			showActiveLocImplementations();
			implScrollPane = new JScrollPane(implList);
		 	centerPanel.add(Box.createRigidArea(HGAP10)); 
		 	centerPanel.add(implScrollPane); 
		 	centerPanel.add(Box.createRigidArea(HGAP10)); 

		 	// Model panel
		 	l = (JLabel) modelPanel.add(new JLabel("Model:"));   
		 	l.setAlignmentX(JLabel.LEFT_ALIGNMENT); 
		 	modelPanel.add(Box.createRigidArea(HGAP10));
		 	String[] modStrArray = new String[models.length + 1];
		 	modStrArray[0] = "All models";
		 	for (int i = 0; i < models.length; i++)
		 		modStrArray[i + 1] = models[i];
		 	modCB = (JComboBox) modelPanel.add(createComboBox(modStrArray));  
		 	modCB.setAlignmentX(JComboBox.LEFT_ALIGNMENT);
		 	activeModel = 0;
		 	l.setLabelFor(modCB); 

		 	// Button panel 
			launchButton = (JButton) buttonPanel.add(createButton(this, "Launch", null)); 
		 	launchButton.setAlignmentX(JButton.RIGHT_ALIGNMENT); 
		 	buttonPanel.add(Box.createRigidArea(HGAP10)); 	
		 	bindingButton = (JButton) buttonPanel.add(createButton(this, "Binding", null)); 
		 	bindingButton.setAlignmentX(JButton.RIGHT_ALIGNMENT); 		 	
		 	buttonPanel.add(Box.createRigidArea(HGAP10));
			cancelButton = (JButton) buttonPanel.add(createButton(this, "Cancel", null)); 
		 	cancelButton.setAlignmentX(JButton.RIGHT_ALIGNMENT); 
		 	
		 	
		}

		/**
		 * Creates a combo box with the options described by the specified array of strings.
		 * 
		 * @param str array of strings
		 * @return the combo box
		 */
		JComboBox createComboBox(String[] str) { 
			JComboBox cb = new JComboBox();
			for (int i = 0; i < str.length; i++)
				cb.addItem(str[i]); 
			cb.addActionListener(this); 
			return cb; 
		}  
		
		private JDialog createAndShowNewLocationDialog(Component parent) {
			JDialog newLocationDialog;
			
	        Frame superFrame = parent instanceof Frame ? (Frame) parent
	                : (Frame)SwingUtilities.getAncestorOfClass(Frame.class, parent);

	        newLocationDialog = new JDialog(superFrame, "Add new location", true);
			
			newLocationDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			newLocationDialog.setContentPane(new NewLocationPane(newLocationDialog, locCB));
			
			newLocationDialog.pack();
			newLocationDialog.setLocationRelativeTo(parent);
			newLocationDialog.show();
			
			return newLocationDialog;
		}

		private JDialog createAndShowLaunchedDialog(Component parent) {
			JDialog lD;
			
	        Frame superFrame = parent instanceof Frame ? (Frame) parent
	                : (Frame)SwingUtilities.getAncestorOfClass(Frame.class, parent);

	        lD = new JDialog(superFrame, "Launched Implementations", false);
			
			lD.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
		    JScrollPane treeView = new JScrollPane(createLaunchedTree());
			lD.setContentPane(treeView);
			
			lD.pack();
			lD.setLocationRelativeTo(parent);
			lD.show();
			
			return lD;
		}
		
		private JTree createLaunchedTree() {
			return SessionGUI.this.createLaunchedTree(false);
		
		}
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			int[] selectedImpl;
			Object s = e.getSource();
			
			if (s == locCB) {
				// activate a new location
				activeLocation = locCB.getSelectedIndex();
				showActiveLocImplementations();
			} else if (s == modCB) {
				activeModel = modCB.getSelectedIndex();
				showActiveLocImplementations();
			} else if (s == addButton) {
				createAndShowNewLocationDialog(this);
			} else if (s == seeButton) {
				if (launchedDialog == null) {
					seeButton.setText("Don't see");
					validate();
					launchedDialog = createAndShowLaunchedDialog(this);
				} else {
					launchedDialog.dispose();
					launchedDialog = null;
					seeButton.setText("See");
					validate();
				}
			} else if (s == launchButton) {
				selectedImpl = implList.getSelectedIndices();
				String implStr;
				String locStr = (String) locCB.getItemAt(activeLocation);
				
				for (int i = 0; i < selectedImpl.length; i++) {
					implStr = (String) implList.getModel().getElementAt(selectedImpl[i]);
					try {
						Class[] inter = Class.forName(implStr).getInterfaces();
						boolean isEmulated = false;
						for (int k = 0; k < inter.length && !isEmulated; k++)
							isEmulated = inter[k].getName().equals(Emulated.name);
						if (isEmulated) {
							if (modelEmu == null) {
								modelEmu = new FemoopEmulator();	
								new Thread(new Runnable() {
									public void run() {
										modelEmu.show();
									}
								}).run();
							}
							sessionDriver.startModel(locStr, implStr, modelEmu);
						}
						else {
							sessionDriver.startModel(locStr, implStr, null);
						}
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				implList.clearSelection();
			} else if (s == bindingButton) {
				BindingPane bindGUI = new BindingPane();
				bindGUI.show();
			} else if (s == cancelButton) {
				if (modelEmu != null) modelEmu.dispose();
				modelsFrame.dispose();
			}
			
		}
		

		/**
		 * 
		 */
		private void showActiveLocImplementations() {
			String[] selectedModels;
			if (activeModel == 0) selectedModels = models;
			else selectedModels = new String[] { models[activeModel - 1] };
			
			String[] selectedImpl= null;
			selectedImpl = sessionDriver.implList((String) locCB.getItemAt(activeLocation), selectedModels);
			
			// select the models
//			config.putModel((String) locCB.getItemAt(activeLocation), models[activeModel], selectedImpl);
			
			implList.setListData(selectedImpl);
			implList.validate();
		}	      
	}
	
	/**
	 * Creates a button with a specified text and a specified tool tip.
	 * 
	 * @param txt button text
	 * @param tip tool tip
	 * @return the button
	 */
	JButton createButton(ActionListener a, String txt, String tip) { 
		JButton b = new JButton(txt);
	 	b.setToolTipText(tip);
		b.addActionListener(a); 
		return b; 
	}  
	
    private final class NewLocationPane extends JPanel implements ActionListener {
    	
    	private JPanel mainPanel;
    	private JPanel locPanel;
    	private JPanel buttonPanel;
    	private JTextField locTXT;
    	private JButton OKButton;
    	private JButton cancelButton;
    	private String newLocationText;
    	private JDialog newLocationDialog;
		private JComboBox locCB;
//		private Configuration config;
		
    	
    	public NewLocationPane(JDialog d, JComboBox cb) {
    		newLocationDialog = d;
    		locCB = cb;
//    		config = cf;
    		
			setOpaque(true);
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

			// Create panels
			mainPanel = new JPanel(); 
		 	mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); 
		 	add(Box.createRigidArea(HGAP10)); 
		 	add(mainPanel, BorderLayout.CENTER); 
		 	add(Box.createRigidArea(HGAP10)); 
		 	
			locPanel = new JPanel(); 
		 	locPanel.setLayout(new BoxLayout(locPanel, BoxLayout.X_AXIS));
		 	mainPanel.add(Box.createRigidArea(VGAP10)); 
		 	mainPanel.add(locPanel, BorderLayout.CENTER); 

		 	buttonPanel = new JPanel(); 
		 	buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS)); 
		 	mainPanel.add(Box.createRigidArea(VGAP30)); 
		 	mainPanel.add(buttonPanel, BorderLayout.EAST); 
		 	
		 	mainPanel.add(Box.createRigidArea(VGAP10)); 

		 	// Location panel
		 	JLabel l = (JLabel) locPanel.add(new JLabel("New Location:"));   
		 	l.setAlignmentX(JLabel.LEFT_ALIGNMENT); 
		 	locPanel.add(Box.createRigidArea(HGAP10)); 
		 	locTXT = (JTextField) locPanel.add(new JTextField(50));
		 	locTXT.addActionListener(this);
		 	locTXT.setAlignmentX(JTextField.LEFT_ALIGNMENT); 
		 	l.setLabelFor(locTXT); 
		 	locPanel.add(Box.createRigidArea(HGAP30)); 

		 	// Button panel 
			OKButton = (JButton) buttonPanel.add(createButton(this, "OK", null)); 
		 	OKButton.setAlignmentX(JButton.RIGHT_ALIGNMENT); 
		 	buttonPanel.add(Box.createRigidArea(HGAP10)); 
			cancelButton = (JButton) buttonPanel.add(createButton(this, "Cancel", null)); 
		 	cancelButton.setAlignmentX(JButton.RIGHT_ALIGNMENT);
		 	
		 	
		 	newLocationText = null;
		}

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			Object s = e.getSource();
			
			if (s == cancelButton) {
				newLocationText = null;
				newLocationDialog.dispose();
				newLocationDialog = null;
			} else {
				newLocationText = locTXT.getText().trim();
				if (newLocationText.length() > 0) {
					int i;
					for (i = 0; i < locCB.getItemCount() && !locCB.getItemAt(i).equals(newLocationText); i++);
					if (i == locCB.getItemCount()) {
						locCB.addItem(newLocationText);
						locCB.validate();
//						config.putLocation(newLocationText, models);
						sessionDriver.startLocation(newLocationText);
						sessionDriver.startWorkspace(newLocationText, defaultWorkspace, defaultWorkspaceImpl, defaultMiddlewareImpl);
						for (int k = 0; k < defaultLocations.length; k++)
							sessionDriver.startLink(newLocationText+defaultLocations[k], newLocationText, defaultLocations[k]);
					}
					newLocationText = null;
				}
				
				newLocationDialog.dispose();
				newLocationDialog = null;
			}
		}
	}
    
    /**
	 * @author  cfreire  TODO To change the template for this generated type comment go to  Window - Preferences - Java - Code Style - Code Templates
	 */
    private class BindingPane extends JFrame {

    	private javax.swing.JPanel jContentPane = null;

    	private JPanel bindPanel = null;
    	private JButton bindButton = null;
    	private JPanel portsPanel = null;
    	private JTree providesPortTree = null;
    	private JTree usesPortTree = null;
    	private JScrollPane providesTreeScrollPane = null;
    	private JScrollPane usesTreeScrollPane = null;
    	/**
    	 * This is the default constructor
    	 */
    	public BindingPane() {
    		super();
    		initialize();
    	}
    	/**
    	 * This method initializes this
    	 * 
    	 * @return void
    	 */
    	private void initialize() {
    		this.setSize(590, 326);
    		this.setContentPane(getJContentPane());
    		this.setTitle("Binding");
    	}
    	/**
		 * This method initializes jContentPane
		 * @return  javax.swing.JPanel
		 * @uml.property  name="jContentPane"
		 */
    	private javax.swing.JPanel getJContentPane() {
    		if(jContentPane == null) {
    			jContentPane = new javax.swing.JPanel();
    			jContentPane.setLayout(new java.awt.BorderLayout());
    			jContentPane.add(getBindPanel(), java.awt.BorderLayout.SOUTH);
    			jContentPane.add(getPortsPanel(), java.awt.BorderLayout.CENTER);
    		}
    		return jContentPane;
    	}
    	/**
		 * This method initializes jPanel	
		 * @return  javax.swing.JPanel
		 * @uml.property  name="bindPanel"
		 */    
    	private JPanel getBindPanel() {
    		if (bindPanel == null) {
    			GridLayout gridLayout1 = new GridLayout();
    			bindPanel = new JPanel();
    			bindPanel.setLayout(gridLayout1);
    			gridLayout1.setRows(1);
    			bindPanel.add(getBindButton(), null);
    		}
    		return bindPanel;
    	}
    	/**
		 * This method initializes jButton	
		 * @return  javax.swing.JButton
		 * @uml.property  name="bindButton"
		 */    
    	private JButton getBindButton() {
    		if (bindButton == null) {
    			bindButton = new JButton();
    			bindButton.setName("jButton");
    			bindButton.setText("Bind");
				bindButton.addActionListener(new java.awt.event.ActionListener() { 
					public void actionPerformed(java.awt.event.ActionEvent e) {
						DefaultMutableTreeNode tNode;
						
						System.out.print("Binding ");
						tNode = (DefaultMutableTreeNode) usesPortTree.getSelectionPath().getLastPathComponent();
						
						String usesPortName = (String) tNode.getUserObject();
						tNode = (DefaultMutableTreeNode) tNode.getParent();
						String usesModelName = (String) tNode.getUserObject();
						tNode = (DefaultMutableTreeNode) tNode.getParent().getParent();
						String usesLocationName = (String) tNode.getUserObject();
						System.out.print(usesLocationName + "." + usesModelName + "." + usesPortName);
						
						System.out.print(" to ");
						
						tNode = (DefaultMutableTreeNode) providesPortTree.getSelectionPath().getLastPathComponent(); 
						String providesPortName = (String) tNode.getUserObject();
						tNode = (DefaultMutableTreeNode) tNode.getParent();
						String providesModelName = (String) tNode.getUserObject();
						tNode = (DefaultMutableTreeNode) tNode.getParent().getParent();
						String providesLocationName = (String) tNode.getUserObject();
						System.out.print(providesLocationName + "." + providesModelName + "." + providesPortName);
						
						String linkName = providesLocationName.equals(usesLocationName) ? defaultWorkspace+".Loop" : defaultWorkspace+"."+usesLocationName+providesLocationName;

						System.out.print(" through " + linkName + "...");

						if (sessionDriver.bindPorts(providesLocationName + "." + providesModelName + "." + providesPortName, usesLocationName + "." + usesModelName + "." + usesPortName, linkName, new BindingProperties(1, true)))
							System.out.println("Done.");
						else
							System.out.println("Failed.");
					}
				});
    		}
    		return bindButton;
    	}
    	/**
		 * This method initializes jPanel1	
		 * @return  javax.swing.JPanel
		 * @uml.property  name="portsPanel"
		 */    
    	private JPanel getPortsPanel() {
    		if (portsPanel == null) {
    			GridLayout portsPanelLayout = new GridLayout();
    			portsPanel = new JPanel();
    			portsPanel.setLayout(portsPanelLayout);
    			portsPanelLayout.setRows(1);
    			portsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.LOWERED), "Active implementations", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
    			portsPanel.add(getUsesTreeScrollPane(), null);
    			portsPanel.add(getProvidesTreeScrollPane(), null);
    		}
    		return portsPanel;
    	}
    	/**
    	 * This method initializes jTree	
    	 * 	
    	 * @return javax.swing.JTree	
    	 */    
    	private JTree getProvidesTree() {    		   			
    		return providesPortTree = createLaunchedTree(true);
    	}
    	/**
    	 * This method initializes jTree1	
    	 * 	
    	 * @return javax.swing.JTree	
    	 */    
    	private JTree getUsesTree() {
    		return usesPortTree = createLaunchedTree(false);
    	}
    	/**
		 * This method initializes jScrollPane	
		 * @return  javax.swing.JScrollPane
		 * @uml.property  name="providesTreeScrollPane"
		 */    
    	private JScrollPane getProvidesTreeScrollPane() {
    		if (providesTreeScrollPane == null) {
    			providesTreeScrollPane = new JScrollPane();
    			providesTreeScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Select a provides port"));
    			providesTreeScrollPane.setViewportView(getProvidesTree());
    		}
    		return providesTreeScrollPane;
    	}
    	/**
		 * This method initializes jScrollPane1	
		 * @return  javax.swing.JScrollPane
		 * @uml.property  name="usesTreeScrollPane"
		 */    
    	private JScrollPane getUsesTreeScrollPane() {
    		if (usesTreeScrollPane == null) {
    			usesTreeScrollPane = new JScrollPane();
    			usesTreeScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Select a uses port"));
    			usesTreeScrollPane.setViewportView(getUsesTree());
    		}
    		return usesTreeScrollPane;
    	}
    }  //  @jve:decl-index=0:visual-constraint="96,74"

}	
