package main;

import java.util.ArrayList;

import processing.core.PApplet;

public class Functions
{
	public static PApplet p;
	public static float activation(float input, int override)
	{
		int type = 0;
		if (override >= 0)
			type = override;
		if (type == 0) // sigmoid
		{
			return 1 / (1 + PApplet.exp(-1 * input));
		}
		if (type == 1)
		{
			if (input < 0)
				return 0;
			else
				return input;
		}
		else
		{
			System.out.println("Something is wrong. input: " + input + " override: " + override);
			return 0;
		}
	}

	public static float makeWeight()
	{
		int type = 0;
		if (type == 0)
		{
			return p.random(-1f, 1f);
		}
		if (type == 1)
		{
			return p.random(0f, 1f);
		}
		else
		{
			System.out.println("Something is wrong. makeWeight");
			return 0;
		}
	}

	public static void drawType(float x)
	{
		p.fill(255);
		p.text("shi", x, 210);
	}

	public static void drawText(String text, float x, float y)
	{
		p.fill(255);
		p.text(text, x, y);
	}
	static boolean checkCollision(player p, obstacle o)
	{
	  if (p == null || o == null) return false; //just in case to prevent any errors. If either one does not exist, it cant collide.
	  if (o.x <= p.posx && p.posx <= o.x + o.sizex) //left side
	  {
	    if (o.y <= p.posy && p.posy <= o.y + o.sizey) return true; //top
	    if (o.y <= p.posy + p.sizey && p.posy + p.sizey <= o.y + o.sizey) return true; //bottom
	  }
	  
	  if (o.x <= p.posx + p.sizex && p.posx + p.sizex <= o.x + o.sizex) //right side
	  {
	    if (o.y <= p.posy && p.posy <= o.y + o.sizey) return true; //top
	    if (o.y <= p.posy + p.sizey && p.posy + p.sizey <= o.y + o.sizey) return true; //bottom
	  }
	  
	  //check for obstacle in player
	  
	  if (p.posx <= o.x && o.x <= p.posx + p.sizex) //left side
	  {
	    if (p.posy <= o.y && o.y <= p.posy + p.sizey) return true;
	    if (p.posy <= o.y + o.sizey && o.y + o.sizey <= p.posy + p.sizey) return true;
	  }
	  
	  if (p.posx <= o.x + o.sizex && o.x + o.sizex <= p.posx + p.sizex) //right side
	  {
	    if (p.posy <= o.y && o.y <= p.posy + p.sizey) return true;
	    if (p.posy <= o.y + o.sizey && o.y + o.sizey <= p.posy + p.sizey) return true;
	  }

	  return false;
	}

	static float calcDist(player p, obstacle o)
	{
	  return  o.x - p.posx;
	}
	static void doGeneration_nograveyard()
	{
	  
	  if (Main.population.size() == 0) return;
	  
	  int alive = Main.population.size();
	  int tmp = (Main.numPlayers - alive) / Main.population.size();
	  
	  for (int i = 0; i < alive; i ++)
	  {
	    player p = Main.population.get(i);
	    for (int j = 0; j < tmp; j++)
	    {
	    	Main.population.add(new player(p, (Main.counter2++ + "")));
	    }
	  }
	  Main.graveYard = new ArrayList<player>(); //delete anything in the graveyard.
	}

	void doGeneration_somegraveyard()
	{
	  for (int i = Main.population.size(); i < 10; i++) //
	  {
	    int end = Main.graveYard.size();
	    player tmp = Main.graveYard.get(end);
	    tmp.revive();
	    Main.population.add(tmp);
	  }
	  
	  for (int i = 0; i < Main.population.size(); i++)
	  {
	    player tmp = Main.population.get(i);
	    for (int j = 0; j < 9; j++)
	    {
	    	Main.population.add(new player(tmp, Main.counter2++ + ""));
	    }
	  }
	}

	static void doGeneration()
	{
		Main.obstacles.remove(0); //Remove the obstacle so the players dont die immediently
		Main.generation++; //we are breeding, so we increment the generation count
	  
	  for (int i = Main.graveYard.size()-1; i > Main.graveYard.size() - Main.topPlayers - 1; i--)
	  {
	    player tmp = Main.graveYard.get(i); 
	    tmp.revive();
	    Main.population.add(tmp); //we keep the top 10 fittest
	    
	    int remaining = (Main.numPlayers - Main.topPlayers) / Main.topPlayers;
	    for (int j = 0; j < remaining; j++) //Then we create 9 additional players mutated from the original
	    {
	    	Main.population.add(new player(tmp, "" + Main.counter2++ ));
	    }
	  } 
	  for (player g: Main.graveYard)
	  {
	    g.alive = false;
	    synchronized(g.run)
	    {
	      g.run.notify();
	    }
	      
	    if (g.thread.isAlive()) 
	    {
	      try
	      {
	        g.thread.join();
	      }
	      catch (InterruptedException ex)
	       {
	          System.err.println(ex);
	       }
	      
	    }
	    g.thread.interrupt(); //kill the thread
	  }
	  
	  for (int i = Main.graveYard.size()-1; i >= 0; i-- )
	  {
		  Main.graveYard.remove(i); 
	  }
	  for (player g: Main.graveYard)
	  {
	    g.destroyPlayer();
	  }
	  Main.graveYard = new ArrayList<player>();
	  System.gc();
	}
}
