package main;

class obstacle
{
	float x, y, speed;
	float sizex = 20;
	float sizey;

	obstacle(float x, float y, float speed, float h)
	{
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.sizey = h;
	}

	void doWork()
	{
		x += speed;
	}

	void doDraw()
	{
		Brain.p.fill(255);
		Brain.p.rect(x, y - sizey + 10, sizex, sizey);

	}

	public String toString()
	{
		return "(" + x + "," + y + "," + speed + ")" + " [" + x + "," + (y - sizey + 10) + "," + sizex + "," + sizey
				+ "]";
	}
}