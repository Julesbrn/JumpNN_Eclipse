package main;

class player
{
  boolean alive = true;
  
  int inputNodes = 1;
  int outputNodes = 1;

  int xPadding = 50;
  int yPadding = 50;
  
  Thread thread;
  Runnable run;
  Brain br;
  
  Node[] inputLayer;
  Node[] outputLayer;
  
  float posy, posx, velx, vely, jumpSpeed, walkSpeed, up;
  //float jumpspeed = 100;
  float sizex = 10;
  float sizey = 20;
  float r, g, b;
  
  String name;
  
  
  player(String name, float posx, float posy, float velx, float vely, float r, float g, float b, int numNodes, int numLayers)
  {
    br = new Brain(numLayers, numNodes, inputNodes, outputNodes, Functions.p.width, Functions.p.height/2, xPadding, yPadding, 1);
    this.name = name;
    this.posx = posx;
    this.posy = posy;
    this.velx = velx;
    this.vely = vely;
    this.r = r;
    this.g = g;
    this.b = b;
    inputLayer = br.getInput();
    outputLayer = br.getOutput();
    this.createThread();
  }
  
  player(player old, String newName)
  {
    this.name = newName;
    this.alive = true;
    this.br = old.br.deepCopy();
    this.br.evolve();
    this.posx = old.posx;
    this.posy = old.posy;
    this.velx = old.velx;
    this.vely = old.vely;
    this.r = old.r;
    this.g = old.g;
    this.b = old.b;
    
    inputLayer = br.getInput();
    outputLayer = br.getOutput();
    this.createThread();
  }
  

void createThread()
  {
    run = new PlayerRunnable(this); //create the runnable and pass in the player reference
    thread = new Thread(run); //make it a threads
    thread.start(); //star the thread 
  }
  
  void doDeepCopy()
  {
    Brain br2 = br.deepCopy();
    br = br2;
  }

  void setNNInput(float[] inputs)
  {
    if (inputs.length == 0 ) return;
    Node[] inputLayer = br.getInput();
    for (int i = 0; i < inputLayer.length; i ++)
    {
      inputLayer[i].val = inputs[i];
    }
  }
  
  boolean shouldJump()
  {
    if (br.getOutput()[0].val >= 0.5) return true; //if the output node is >= 0.5 jump
    else return false;
  }
  
  void die()
  {
   this.alive = false;
   this.thread.interrupt();
  }
  
  void revive()
  {
   this.alive = true;
   this.createThread();
  }
  
  void doDraw()
  {
	  Functions.p.fill(r,g,b);
	  Functions.p.rect(posx, posy, 10,10);
  }
  
  void doWork() //created from example code
  {
     // Only apply gravity if above ground (since y positive is down we use < ground)
    if (posy < Main.ground)
    {
      vely += Main.gravity;
    }
    else
    {
      vely = 0; 
    }
    
    // If on the ground and "jump" key is pressed set my upward velocity to the jump speed!
    if (posy >= Main.ground && up != 0)
    {
      vely = -10;
    }   
    
    // Check collision with edge of screen and don't move if at the edge
    float offset = 0;
    if (posy + vely > offset && posy + vely < (Functions.p.height - offset))
    {
      posy += vely;
    }  
  }
  
  protected void finalize() throws Throwable
  {
      destroyPlayer();
  }
  
  void destroyPlayer()
  {
    run = null;
    br.destroyBrain();
    br = null;
  }
}