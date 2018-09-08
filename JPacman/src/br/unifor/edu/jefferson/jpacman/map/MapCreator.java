/*
 * Created on 09/02/2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package br.unifor.edu.jefferson.jpacman.map;

import java.io.RandomAccessFile;
import java.io.File;

import br.unifor.edu.jefferson.jpacman.util.JPacmanConstants;

/**
 * @author jefferson
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class MapCreator {
 
	public static int[][] createMap(String filename)
	{
		
		int mat[][] = new int [JPacmanConstants.gameDimension.height][JPacmanConstants.gameDimension.width];
		try{
			 
			String path = MapCreator.formatString( ClassLoader.getSystemResource("maps"+File.separator+filename).getFile());
			RandomAccessFile raf = new RandomAccessFile( path ,"r");
			String line ;
			int j=0;
			while( (line = raf.readLine())!=null )
			{
				for(int i=0;i<20;i++)
				{
					try{
						char letra = line.charAt(i);
						 
						switch (letra)
						{
							case 'X':
								mat[j][i] = JPacmanConstants.WALL;
							break;
							case 'F':
								mat[j][i] = JPacmanConstants.FOOD;
							break; 
						}//switch
					}
					catch(Exception e)
					{
						System.out.println("Furou:" + e.toString());
					}
				}//for
				//System.out.println("");
				j++;          
			}//while
			raf.close();
		}
		catch(Exception e)
		{
            
			System.out.println(e.toString());
		}
        
		return mat;

	}
	
	
	public static void main(String args[])
	{
		
		 
		int map[][] = MapCreator.createMap("testmap.map");
		for(int i=0;i<map.length;i++)
		{
			for(int j=0;j<map[0].length;j++)
			System.out.print(map[i][j]);
			System.out.println();
		}
		 	
	}
	
	private static String formatString(String in)
	{
		String out = in.replaceAll("%20"," ");
		out = out.replaceAll("%5","\\");
		return out;
	}

}
