package main;

class obstacle
{
	float x, y, speed;
	float sizex = 20;
	float sizey;

	obstacle(float x, float y, float speed, float h)
	{
		//System.out.println("h:" + h);
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
		//System.out.println("abcdef");
		Brain.p.fill(255);
		Brain.p.rect(x, y - sizey + 10, sizex, sizey);
		//Brain.p.rect(x, y - 100, sizex, sizey);
		
	}

	public String toString()
	{
		return "(" + x + "," + y + "," + speed + ")" + " [" + x + "," + (y - sizey + 10) + "," + sizex + "," + sizey
				+ "]";
	}
}