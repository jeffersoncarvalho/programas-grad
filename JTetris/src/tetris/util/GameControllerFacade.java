package tetris.util;
 

import tetris.table.Table;

public class GameControllerFacade {
	
	private Player player;
	private Table table;
	
	public GameControllerFacade(Table table)
	{
		 this.table = table;
		 this.createNewPlayer("Skywalker");
		 this.table.getDrawPaneForNext().setPlayer(this.player);
		 this.table.setPlayer(this.player);
	}
	
	public void createNewPlayer(String name)
	{
		player = new Player(name,0,0);
	}
	
	public void startGame()
	{
		table.run();
	}
	
	public void stopGame()
	{
		
	}
	
	public void pauseGame()
	{
		
	}
	
	public void unpauseGame()
	{
		
	}
		
	/**
	 * @return
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @param player
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

}
