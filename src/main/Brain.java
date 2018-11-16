package main;

import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PApplet;

public class Brain
{
	static PApplet p;

	ArrayList<ArrayList<Node>> brain = new ArrayList<ArrayList<Node>>(); // 2d array of nodes, each layer followed by
																			// the nodes in that layer

	Brain()
	{
		brain = new ArrayList<ArrayList<Node>>();
	}

	Brain deepCopy()
	{
		Brain ret = new Brain();
		HashMap<Node, Node> hm = new HashMap<Node, Node>(); // prev -> new for lines
		for (int i = 0; i < brain.size(); i++)
		{
			ArrayList<Node> tmp = new ArrayList<Node>();
			ArrayList<Node> prev = brain.get(i);
			for (int j = 0; j < prev.size(); j++)
			{
				tmp.add(prev.get(j).clone(hm));
			}
			ret.brain.add(tmp);
		}

		for (int i = 0; i < brain.size(); i++)
		{
			ArrayList<Node> tmp = ret.brain.get(i);
			for (int j = 0; j < tmp.size(); j++)
			{
				tmp.get(j).finishClone(hm);
			}
		}
		return ret;
	}

	Node[] getInput()
	{
		ArrayList<Node> L = brain.get(0);
		Node[] tmp = new Node[L.size()];
		for (int i = 0; i < L.size(); i++)
		{
			tmp[i] = L.get(i);
		}
		return tmp;
	}

	Node[] getOutput()
	{
		ArrayList<Node> L = brain.get(brain.size() - 1);
		Node[] tmp = new Node[L.size()];
		for (int i = 0; i < L.size(); i++)
		{
			tmp[i] = L.get(i);
		}
		return tmp;
	}

	void destroyBrain()
	{
		for (ArrayList<Node> b : brain)
		{
			for (Node n : b)
			{
				n.destroyNode();
				n = null;
			}
			b = null;
		}
		brain = null;
	}

	Brain(int numLayers, int nodesPerLayer, int inputLayerNum, int outputNodesNum, float wdth, float hght, float xpos,
			float ypos, int x)
	{
		float inputHeight = hght / (inputLayerNum);
		float outputHeight = hght / (outputNodesNum);

		wdth /= (numLayers + 2);
		hght /= nodesPerLayer;

		// make the first layer
		ArrayList<Node> firstLayer = new ArrayList<Node>();
		for (int i = 0; i < inputLayerNum; i++)
		{
			Node abc = new Node(0 * wdth + xpos, (i) * inputHeight + inputHeight / 2);
			abc.setVal(0f);
			firstLayer.add(abc);
		}
		brain.add(firstLayer);

		// make the hidden layers
		for (int layer = 0; layer < numLayers; layer++)
		{
			ArrayList<Node> thisLayer = new ArrayList<Node>();
			for (int nNode = 0; nNode < nodesPerLayer; nNode++)
			{
				Node abc = new Node((layer + 1) * wdth + xpos, nNode * hght + ypos + hght / 2);
				float val = p.random(-1.0f, 1.0f);
				// println("val:" + val);
				abc.setVal(val);
				thisLayer.add(abc);
			}
			brain.add(thisLayer);

		}

		// Make the output Layer
		ArrayList<Node> outputLayer = new ArrayList<Node>();
		for (int i = 0; i < outputNodesNum; i++)
		{
			Node abc = new Node((numLayers + 1) * wdth + xpos, (i) * outputHeight + outputHeight / 2);
			abc.setVal(p.random(-1.0f, 1.0f));
			abc.override = 0;
			outputLayer.add(abc);
		}
		brain.add(outputLayer);

		// add all of the lines
		for (int i = 1; i < brain.size(); i++)// for each layer in brain
		{

			ArrayList<Node> thisLayer = brain.get(i);
			ArrayList<Node> prevLayer = brain.get(i - 1);
			for (int a = 0; a < thisLayer.size(); a++) // for each node in this layer
			{
				ArrayList<Line> lines = new ArrayList<Line>();
				for (int b = 0; b < prevLayer.size(); b++) // for nodes in previous layer
				{
					// create a link between these two
					lines.add(new Line(thisLayer.get(a), prevLayer.get(b)));
				}
				thisLayer.get(a).addLines(lines);
			}
		}
	}

	void evolve() //to evolve a brain we either evolve a node or a line. Nodes mutate their bias. Lines mutate their weight
	  {
		int item = (int) p.random(0f,1f)*(brain.size()-1);
		ArrayList<Node> tmp = brain.get(item+1);
		int item2 = (int) p.random(0f,1f)*tmp.size();
	   Node n = tmp.get(item2);
	   
	   if (p.random(0f,1f) > 0.5) 
	   {
	     n.evolveNode();
	     return;
	   }
	   int item3 = (int) p.random(0f,1f)*n.lines.size();
	   Line l = n.lines.get(item3);
	   l.evolveLine();
	  }

	void doDraw()
	{
		for (ArrayList<Node> layer : brain)
		{
			for (Node node : layer)
			{
				node.doDraw();
			}
		}
		for (ArrayList<Node> layer : brain)
		{
			for (Node node : layer)
			{
				node.doDraw2();
			}
		}

		boolean tmp = false;
		if (tmp)
		{
			ArrayList<Node> inLayer = brain.get(0);
			ArrayList<Node> outLayer = brain.get(brain.size() - 1);

			for (Node node : inLayer)
			{
				node.doDraw3();
			}
			for (Node node : outLayer)
			{
				node.doDraw3();
			}
		}
	}

	void doCalc()
	{
		for (int i = 1; i < brain.size(); i++)// first layer is the input, therefore should not be calculated.
		{
			for (int j = 0; j < brain.get(i).size(); j++)
			{
				brain.get(i).get(j).doCalc();
			}
		}
	}
}
