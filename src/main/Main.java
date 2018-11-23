package main;

import java.util.ArrayList;

import processing.core.PApplet;

public class Main extends PApplet
{
	// begin modifiable values
	public static int numPlayers = 100; // number of players per generation to spawn
	public static int topPlayers = 10; // number of players to resurrect from the grave and breed from

	public static int minLayers = 1;
	public static int maxLayers = 3;
	public static int minNodes = 1;
	public static int maxNodes = 3;
	// end modifiable values

	// variables below this line should not be changed
	public static ArrayList<player> population = new ArrayList<player>();
	public static ArrayList<player> graveYard = new ArrayList<player>();
	public static ArrayList<obstacle> obstacles = new ArrayList<obstacle>();

	int counter = 0;
	public static int fr = 60; // math is frame based. Increasing this from 60 will not create smoother
	// animation, it will only run faster
	public static int generation = 1;
	int next = 0;
	public static float minNext = 30f;
	public static float maxNext = 40f;

	public static boolean show = false;
	public static boolean turbo = false;
	public static boolean showOb = true;
	public static boolean showPl = true;
	public static boolean showT = true;
	public static boolean isSlow = false;

	public final static float gravity = .5f; // half a pixel per frame gravity.
	public final static float ground = 785; // Y coordinate of ground for collision

	public static int counter2 = 0;

	// public static Semaphore sem;
	// public static Object lock;
	// public static int num = 0;

	public static boolean doGraveyard = false;
	public static boolean doOffspring = false;

	public static Window window;

	public void run()
	{
		PApplet.main("main.Main");
		// Main.sem = new Semaphore(1);
		// Main.lock = new Object();
		window = new Window();

	}

	public void settings()
	{
		size(800, 800);
	}

	public void setup()
	{
		Functions.p = this;
		Brain.p = this;
		Line.p = this;
		Node.p = this;

		frameRate(fr);

		for (int i = 0; i < numPlayers; i++)
		{
			player tmp = new player("" + i, 10.0f, 700.0f, -1.0f, 0.0f, random(0, 255), random(0, 255), random(0, 255),
					(int) random(minNodes, maxNodes), (int) random(minLayers, maxLayers));
			population.add(tmp);
		}
	}

	public void draw()
	{
		if (window != null) window.setLabels(population.size() + "", generation + "", fr + "", ((int) frameRate) + "");
		counter++;
		if (counter >= next)
		{
			counter = 0;
			//next = (int) random(60f, 80f);
			next = (int) random(minNext, maxNext);
			obstacles.add(new obstacle(width, height - 20, -4f, 50f));
		}

		background(0);
		
		/*if (showT)
			Functions.drawText(generation + "", 50, 50);
		if (showT)
			Functions.drawText(fr + "", 100, 50);
		Functions.drawText(frameRate + "", 200, 50);
		if (showT)
			Functions.drawText(turbo + "", 10, 50);
		if (showT)
			Functions.drawText(population.size() + "", 25, 25);
			*/

		fill(0, 0, 255);
		rect(0, 790, 800, 10); // draw the ground
		fill(255);

		for (player pl : population)// draw loop
		{
			if (showPl)
				pl.doDraw();
			// if (show) pl.br.doDraw(); //comment this out if you want to see all of the
			// brains overlapped
		}
		if (show)
			population.get(0).br.doDraw(); // only draw a single brain

		for (int i = population.size() - 1; i >= 0; i--) // check each living player
		{
			player tmp = population.get(i);
			for (obstacle o : obstacles)
			{
				if (Functions.checkCollision(tmp, o) || !tmp.alive) // check if the player has died
				{
					tmp.die();
					graveYard.add(tmp);
					population.remove(i);
					/*
					 * synchronized (tmp.run) { tmp.run.notify(); }
					 */
					break;
				}
			}
		}

		if (population.size() == 0) // if all players are dead, we need to breed new players
		{
			Functions.doGeneration();
		}

		for (int i = obstacles.size() - 1; i >= 0; i--) // need to move all obstacles
		{
			obstacle tmp = obstacles.get(i);
			tmp.doWork();
			if (showOb)
				tmp.doDraw();
			if (tmp.x <= 0)
				obstacles.remove(i); // if this obstacle is off the screen, it does not matter
		}

		// end of draw function
		try
		{

			while (true)
			{
				/*
				 * synchronized (Main.lock) { Main.lock.wait(); }
				 */
				Thread.sleep(0, 100); // my original solution was to use locks, but unfortunately notifys are not
										// caught if a lock is not waiting, resulting in a deadlock. Each thread takes a
										// few milliseconds to run, so waiting a millisecond does not adversely affect
										// performance

				boolean tmp = true;
				for (player p : population)
				{
					if (p.alive && p.thread.isAlive() && p.isDone) // if player is alive, the thread is alive, and the
																	// player is done, we are done
					{
						// do nothing
					}
					else
					{
						if (!p.alive || !p.thread.isAlive())
						{
							// do nothing
						}
						else
						{
							tmp = false;
							break;
						}

					}
				}
				if (tmp)
					break;

				/*
				 * Main.sem.acquire(); if (num <= 0) { Main.sem.release(); break;
				 * 
				 * } Main.sem.release();
				 */

			}

			/*
			 * Main.sem.acquire(); Main.num = population.size(); Main.sem.release();
			 */
			for (player p : population)
			{
				synchronized (p.run)
				{
					p.run.notify();
				}
			}

		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		if (doGraveyard)
		{
			doGraveyard = false;
			Functions.doGeneration_nograveyard();
		}
		if (doOffspring)
		{
			doOffspring = false;
			for (int i = 0; i < 10; i++)
			{
				player tmp = new player(population.get(0), str(counter2++));
				population.add(tmp);
			}
		}
	}

	public void keyPressed_old() // migrated these functions to a jframe
	{
		if (key == 'p') // show/hide drawing the brain
		{
			show = !show;
		}
		if (key == 'q') // up the framerate
		{
			if (fr >= 300)
				fr += 100;
			else
				fr += 10;
			if (!turbo)
				frameRate(fr);
		}
		if (key == 'a') // lower the framerate
		{
			if (fr - 10 <= 0)
				return;
			if (fr >= 300)
				fr -= 100;
			else
				fr -= 10;
			if (!turbo)
				frameRate(fr);
		}
		if (key == 'e') // toggle turbomode
		{
			turbo = !turbo;
			if (turbo)
				frameRate(999999);
			else
				frameRate(fr);
		}
		if (key == 'o') // breed the top player, only creates 1 more player
		{
			player tmp = new player(population.get(0), str(counter2++));
			population.add(tmp);
		}
		if (key == 'j') // breed the top player, only creates 1 more player
		{
			for (int i = 0; i < 10; i++)
			{
				player tmp = new player(population.get(0), str(counter2++));
				population.add(tmp);
			}
		}
		if (key == 'd')
		{
			print("debug");
		}
		if (key == 'l') // toggles drawing the obstacles
		{
			showOb = !showOb;
			showPl = !showPl;
			// showT = !showT;
		}
		if (key == 'k') // toggle slowmode
		{
			if (isSlow)
			{
				isSlow = false;
				frameRate(fr);
			}
			else
			{
				isSlow = true;
				frameRate(1);
			}
		}
		if (key == 'n') // force breeding from remaining players (will only run if num players <= 50)
		{
			Functions.doGeneration_nograveyard();
		}
	}

}
