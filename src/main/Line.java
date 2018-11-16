package main;

import java.util.HashMap;

import processing.core.PApplet;

public class Line
{
	static PApplet p;
	Node A; // the node the calling node wants information from
	Node B; // The node calling this line

	float weight; // the weight of this line

	Line(Line old)
	{
		this.A = old.A;
		this.B = old.B;
		this.weight = old.weight;
	}

	Line(Node a, Node b)
	{
		A = a;
		B = b;
		weight = Functions.makeWeight();
	}

	Line(Node a, Node b, float wght)
	{
		A = a;
		B = b;
		weight = wght;
	}

	float calc()
	{
		return B.val * weight;
	}

	void doDraw()
	{
		p.stroke(255 - (weight + 1) * 128, (weight + 1) * 128, 0);
		p.line(A.x, A.y, B.x, B.y);
	}

	void fixLines(HashMap<Node, Node> hm)
	{
		this.A = hm.get(A);
		this.B = hm.get(B);
	}

	void evolveLine()
	{
		weight = Functions.makeWeight();

	}

	void destroyLine()
	{
		A = null;
		B = null;
	}
}
