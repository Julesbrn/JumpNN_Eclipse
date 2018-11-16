package main;

public class PlayerRunnable implements Runnable
{
	player pl;

	public PlayerRunnable(player pl)
	{
		this.pl = pl;
	}
	
	public synchronized void work()
	{
		
		pl.doWork();

		float[] dists = new float[1]; // we only care about the closest obstacle, this is an array to make
										// extension easier
		if (Main.obstacles.size() != 0)
			dists[0] = Functions.calcDist(pl, Main.obstacles.get(0));

		pl.setNNInput(dists);
		pl.br.doCalc();

		if (pl.shouldJump())
			pl.up = -1;
		else
			pl.up = 0;

	}

	public synchronized void run()
	{
		try
		{
			while (pl != null && pl.alive)
			{
				if (pl == null)
					break;
				
				work();
				
				wait();

			}
		}
		catch (InterruptedException ex)
		{
			pl = null; // Thread was told to stop, ensure garbage collection will remove this object
		}
	}
}