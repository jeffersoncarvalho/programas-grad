package br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho.gui.applet;
/**
 * Applet allowing interactive investigation of hull algorithms
 */
import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho.Embrulho;
import br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho.Util3D;
import br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho.Vertice;
public class AppletHull extends Applet3d{

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
Panel mainPanel;
  Choice modelChoice=null;
  Choice dataChoice;
  Choice dataChoice2d;
  Choice dataChoice3d;
  Choice dimensionChoice;
  TextField dataSize;
  Button moreButton;
  String moreMessage;
  Axes axes = new Axes();
  Points3d points3d = new Points3d();
  private Point3dObject3d[] points = new Point3dObject3d[32];
  ArrayList vertices = new ArrayList(32);
  //private JFileChooser fileChooser = new JFileChooser();
  int runTime = 1;

  Viewer makeViewer(){
    Viewer v = new Viewer(this);
    try {
      String view = getParameter("view");
      if (view != null){
	View3dInfo home = View3dInfo.fromString(view);
	v.setHome(home);
	v.setView(home);
      }
    } catch (NumberFormatException e) {
    }
    Object3dAdaptor.normalColor=v.addColor("Normal","normal objects",getBoolParameter("Normal"),Color.green,true);
    Triangle3d.backFaceColor=v.addColor(Color.blue);
    Object3dAdaptor.addColor=v.addColor("Added","added objects",getBoolParameter("Added"),Color.red,true);
    Object3dAdaptor.deleteColor=v.addColor("Deleted","deleted objects",getBoolParameter("Deleted"),Color.yellow,true);
    Axes.color=v.addColor("Axes","coordinate axes",getBoolParameter("Axes"),Color.black,true);
    Points3d.color=v.addColor("Points","input points",getBoolParameter("Points"),Color.black,true);
    //DivideAndConquer.leftColor=v.addColor("Left Hull","left convex hull",getBoolParameter("Left Hull"),Color.cyan,false);
    //DivideAndConquer.rightColor=v.addColor("Right Hull","right convex hull",getBoolParameter("Right Hull"),Color.magenta,false);
    Object3dAdaptor.selectColor=v.addColor("Selected","selected object",getBoolParameter("Selected"),Color.cyan,false);

    dataChoice.select(getParameter("distribution"));
    dataSize.setText(getParameter("npoints"));
    setDistribution();
    return v;
  }

  public void init(){
	this.setSize(new Dimension(800,600));
    mainPanel = new Panel();
    dimensionChoice = new Choice();
    dimensionChoice.addItem("2D");
    dimensionChoice.addItem("3D");
    dimensionChoice.setVisible(false);
    mainPanel.add(dimensionChoice);
    dataChoice3d = new Choice();
     
    dataChoice3d.addItem("In Sphere");
    dataChoice3d.addItem("On Sphere");
    dataChoice3d.addItem("On Paraboloid");
    dataChoice3d.addItem("In Cube");
    dataChoice3d.addItem("Gaussian");
    dataChoice3d.addItem("Wedge Block");
    //dataChoice3d.addItem("From File");
    dataChoice2d = new Choice();    
    dataChoice2d.addItem("In Circle");
    dataChoice2d.addItem("On Circle");
    dataChoice2d.addItem("In Square");
    dataChoice = dataChoice3d;
    dataSize = new TextField(Integer.toString(points.length),3);
    super.init();
    mainPanel.add(createModelChoice());
    selectDimension();
    mainPanel.add(dataSize);
    mainPanel.add(new Label("Points"));
    moreButton = new Button(moreMessage="More Controls");
     
    if (!getBoolParameter("vrml")){
      mainPanel.add(moreButton);
    }

    if (getBoolParameter("controls")){
      add("South",mainPanel);
    }

    points3d.setLabels(getIntVectorParameter("labels"));
    int frame = getIntParameter("frame",-1);
    if (frame!=-1){
      viewer.stop();
      viewer.setFrameNo(frame);
    }   
    if (getParameter("Points")!=null) {
      viewer.setColorVisibility(Points3d.color,getBoolParameter("Points"));
    }
  }

  public void setDistribution(){
    int n;
    String choice =dataChoice.getSelectedItem();
    if (choice.equals("Wedge Block")) {
      n = wedgeBlockData.length;
    } else {
      try {
	n = Math.max(Integer.parseInt(dataSize.getText()),3);
      } catch (NumberFormatException e) {
	n = 32;
      }
    }
    Point3d[] pts = new Point3d[n];
    
    Point3d.setSeed(getIntParameter("seed",(int)System.currentTimeMillis()));
    if (choice.equals("Wedge Block")) {
      wedgeBlock(pts);
    } else if (choice.equals("In Sphere")) {
      randomInSphere(pts);
    } else if (choice.equals("On Sphere")) {
      randomOnSphere(pts);
    } else if (choice.equals("On Paraboloid")) {
      randomOnParaboloid(pts);
    } else if (choice.equals("In Cube")) {
      randomInCube(pts);
    } else if (choice.equals("Gaussian")) {
      randomGaussian(pts);
    } else if (choice.equals("On Circle")) {
      randomOnCircle(pts);
    } else if (choice.equals("In Circle")) {
      randomInCircle(pts);
    } else if (choice.equals("In Square")) {
      randomInSquare(pts);
    }/*else if (choice.equals("From File")) {
    	File temp = retornaArquivo();
        if(temp!=null){
        	 pts = Util3D.gerarPoint3DDeArquivo(temp.toString());
        }
    	 
    }*/
    
    dataSize.setText(Integer.toString(pts.length));
    points = new Point3dObject3d[pts.length];
    for (int i=0; i <pts.length; i++) {
    	//System.out.println(pts[i].x()+" " +pts[i].y()+" "+pts[i].z());
    	points[i] = new Point3dObject3d(pts[i]);
    	  Vertice v = new Vertice(pts[i].x(),pts[i].y(),pts[i].z());
         this.vertices.add(v);
    }
  }

  public boolean action(Event evt, Object arg) {
    //Timer.printall();
    if (evt.target.equals(dataChoice)||evt.target.equals(dataSize)) {
      setDistribution();
      viewer.setModel(selectModel(modelChoice.getSelectedItem()));
      return true;
    } else if (evt.target == moreButton){
      viewer.moreControls(moreButton);
      return true;
    } else if (evt.target == dimensionChoice){
      selectDimension();
      setDistribution();
      viewer.setModel(selectModel(modelChoice.getSelectedItem()));
      return true;
    } else {
      return super.action(evt,arg);
    }
  }
       
  
  private void randomInSphere(Point3d[] pts) {
    for (int i=0; i <pts.length; i++) {
      pts[i] = Point3d.randomInSphere().scale(0.75);
    }
  }      

  private void randomOnSphere(Point3d[] pts) {
    for (int i=0; i <pts.length; i++) {
      pts[i] = Point3d.randomOnSphere().scale(0.75);
    }
  }      

  private void randomOnCircle(Point3d[] pts) {
    for (int i=0; i <pts.length; i++) {
      pts[i] = Point3d.randomOnCircle().scale(0.75);
    }
  }      

  private void randomInCircle(Point3d[] pts) {
    for (int i=0; i <pts.length; i++) {
      pts[i] = Point3d.randomInCircle().scale(0.75);
    }
  }      

  private void randomInCube(Point3d[] pts) {
    for (int i=0; i <pts.length; i++) {
      pts[i] = Point3d.random().add(new Point3d(-0.5,-0.5,-0.5)).scale(1.25);
    }
  }      

  private void randomInSquare(Point3d[] pts) {
    for (int i=0; i <pts.length; i++) {
      pts[i] = Point3d.random().add(new Point3d(-0.5,-0.5,-0.5)).scale(1.25,1.25,0);
    }
  }      

  private void randomOnParaboloid(Point3d[] pts) {
    for (int i=0; i <pts.length; i++) {
      Point3d temp = Point3d.random().add(new Point3d(-0.5,-0.5,-0.5)).scale(2,2,0);
      pts[i] = temp.add(new Point3d(0,0,temp.dot(temp)-0.5)).scale(0.625);
    }
  }      

  private void randomGaussian(Point3d[] pts) {
    for (int i=0; i <pts.length; i++) {
      pts[i] = Point3d.randomGaussian().scale(0.3);
    }
  }      

  

  private static double[][] wedgeBlockData = {
    {-2,1,.5},{-2,2,.5},{-.5,1,.5},{-.5,2,.5},{-.5,1,.8},{-.5,2,.8},//wedge
    {-1,1.5,.6},{-1.5,1.5,.6},//extra points inside wedge
    {1,1.8,2},{2,1.8,2},{2,1.2,2},{1,1.2,2},{1,1.8,-1}, //block
    {2,1.8,-1},{2,1.2,-1},{1,1.2,-1}};                 //block
  private void wedgeBlock(Point3d[] pts){
    for (int i=0; i < wedgeBlockData.length; i++){
      pts[i] = new Point3d(wedgeBlockData[i][0],
			   wedgeBlockData[i][1],
			   wedgeBlockData[i][2]);
      pts[i]=pts[i].add(new Point3d(0,-1.5,-.5)).scale(0.375).add(Point3d.randomInSphere().scale(0.003));//add a bit of noise
    }
  }

  public boolean mouseEnter(Event e, int x, int y){
    if (dimensionChoice.getSelectedItem().equals("3D")) {
      //showStatus("Hold the mouse button down and move the mouse to rotate the hull");
    }
    return true;
  }

  public Choice createModelChoice() {
    if (modelChoice == null) {
      modelChoice = new Choice();
      modelChoice.addItem("Incremental");
      modelChoice.addItem("Gift Wrap");
      modelChoice.addItem("Divide and Conquer");
      modelChoice.addItem("QuickHull");
    }
    modelChoice.setVisible(false);
    return modelChoice;
  }

 
  public Object3dList selectModel(String choice) {
	   Object3dList model ;
	  
	      
	    Embrulho embrulho = new Embrulho(null);
	    long inicio = System.currentTimeMillis();
	    model = embrulho.resolveByWE(vertices);
	    long fim = System.currentTimeMillis();
	    System.out.println("Tempo Decorrido(ms): " + (fim-inicio));
	    vertices.clear();
	    viewer.extraColors(new int[0]);
	    model.addElement(points3d.set(points));

	    //hack to render applet view of Incremental algorithm
	    /*if (!getBoolParameter("vrml")&&choice.equals("Incremental")){
	      for (int i = 0; i < points.length; i++){
		if (points[i].getSelectFrame()>=0){
		  model.addElement(new Point3dObject3d(points[i],points[i].getSelectFrame()));
		}
	      }
	    }*/
	    
	    model.addElement(axes);
	    return model;
	  }
  
   public void selectDimension(){
    
      viewer.setViewLock(false);
      viewer.setView("Home");
      viewer.setColorVisibility(Points3d.color,false);
      mainPanel.remove(dataChoice);
      mainPanel.add(dataChoice3d,2);
      dataChoice = dataChoice3d;
      validate();
    
  } 

  /** defaultModel to display */
  public Object3dList defaultModel() {
    dimensionChoice.select(getParameter("dimension"));
    modelChoice.select(getParameter("model"));
    return selectModel(getParameter("model"));
  }

  public String[][] getDefaultParameters() {
    String[][] defaults = {
      // Array of arrays of strings describing each parameter.
      // Format: parameter name, value
      {"bgcolor", "ffffff"},
      {"controls", "on"},
      {"dimension", "3D"},
      {"model","Incremental"},
      {"distribution","In Sphere"},
      {"npoints","32"},
      {"Normal","on"},
      {"Added","on"},
      {"Deleted","on"},
      {"Axes","on"},
      {"Left Hull","on"},
      {"Right Hull","on"},
      {"Selected","on"}
    };
    return defaults;
  }

   
  
  // Return information suitable for display in an About dialog box.
  public String getAppletInfo() {
    return "3D Hull Applet v1.13\nWritten by Tim Lambert.";
  }

   
 
  /*public File retornaArquivo()
  {
      int result = this.fileChooser.showOpenDialog(null);
      if(result == JFileChooser.CANCEL_OPTION)
          return null;
      return fileChooser.getSelectedFile();
  }*/
  
  
  
}
