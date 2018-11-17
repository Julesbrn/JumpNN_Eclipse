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
			while (pl.alive)
			{

				synchronized (pl)
				{
					pl.doWork();
				}
				work();
				pl.isDone = true;

				// Thread.sleep(5);
				/*
				 * Main.sem.acquire(); Main.num -= 1; System.out.println("num is now " +
				 * Main.num); Main.sem.release();
				 */
				// Thread.sleep(0,1); //need this to make sure no deadlocks occur without sleep
				// if this part runs faster than the main thread can retake the lock a deadlock
				// occurs

				/*
				 * synchronized(Main.lock) { Main.lock.notifyAll(); }
				 */

				this.wait();
				pl.isDone = false;
			}
		}
		catch (InterruptedException ex)
		{
			/*
			 * Main.sem.acquire(); Main.num -= 1; System.out.println("num is now " +
			 * Main.num); Main.sem.release();
			 */
			// System.out.println("thread died, still decremented");
			pl = null; // Thread was told to stop, ensure garbage collection
						// will remove this object

		}
	}

	public synchronized void oldrun()
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