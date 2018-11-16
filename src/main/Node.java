package main;

import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PApplet;

public class Node
{
	static PApplet p;
	ArrayList<Line> lines = new ArrayList<Line>(); // these are lines which connect to all node connected to this one.
													// In a deep, fully connected neural network, this is the entire
													// previous layer
	float x, y; // the location of this node, calculated by brain
	float val; // this will be set once the calculation is finished
	float rawVal;
	float bias;
	int override = -1;
	float size = 25;

	Node(float x, float y, float val)
	{
		this.x = x;
		this.y = y;
		this.val = val;
	}

	Node(float x, float y)
	{
		this.x = x;
		this.y = y;
		bias = makeBias();
	}

	Node clone(HashMap<Node, Node> hm)
	{
		Node ret = new Node(x, y, val);
		for (int i = 0; i < lines.size(); i++)
		{
			Line tmp = new Line(this.lines.get(i));
			ret.lines.add(tmp);
		}
		hm.put(this, ret);

		return ret;
	}

	void finishClone(HashMap<Node, Node> hm)
	{
		for (int i = 0; i < lines.size(); i++)
		{
			lines.get(i).fixLines(hm);
		}
	}

	void evolveNode()
	{
		bias = makeBias();
	}

	float makeBias()
	{
		return Functions.p.random(-0.1f, 0.1f);
	}

	void addLines(ArrayList<Line> lines)
	{
		this.lines = lines;
	}

	void setVal(float v)
	{
		val = v;
		size = ((val + 1) / 2) * 15 + 10;
		if (size > 50)
			size = 50;
		if (size < 0)
			size = 10;
	}

	void changeVal(float v)
	{
		val += v;

		float act = Functions.activation(val, override);
		size = 10 + ((act + 1) / 2) * 15;
	}

	void doCalc()
	{
		val = bias;
		for (Line line : lines)
		{
			val += line.calc();
		}
		rawVal = val;

		val = Functions.activation(val, 0);

		size = 10 + val * 15;
		if (size > 50)
			size = 50;
		if (size < 0)
			size = 10;

	}

	void doDraw()
	{
		for (Line line : lines)
		{
			line.doDraw();
		}
	}

	void doDraw2()
	{
		p.stroke(255);
		float c = ((bias + 1) / 2) * 255;
		p.fill(255 - c, c, 0);
		p.ellipse(x, y, size, size);
		doDraw3();
	}

	void doDraw3()
	{
		Functions.drawText(val + "", x, y);
		Functions.drawText(bias + "", x, y + 25);
	}

	void destroyNode()
	{
		for (Line l : lines)
		{
			l.destroyLine();
		}
		lines = null;
	}
}
