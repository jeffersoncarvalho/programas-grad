/*
 * Created on 04/08/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package br.jefferson.jinvaders.util;

/**
 * @author Usuario
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SingletonPlayer {
	
	public static String name = "SkyWalker";
	public static int points = 0;
	public static int lifes = 3;
	public static int level = 1;
	
	public static void increasePontuation(int alienType)
	{
		SingletonPlayer.points+= SingletonPlayer.level*10;
	}
	
	public static void decreaseLife()
	{
		SingletonPlayer.lifes = SingletonPlayer.lifes-1>=0?SingletonPlayer.lifes-1:0;
	}

}
