# JumpNN Eclipse
This is an improvment of JumpNN after transitioning to Eclipse.

This project was an experiment to applying a simple neural network to a player to achieve a simple goal. Each player must jump over the obstacles to survive. If a player touches an obstacle, the player is killed. When all players are killed a new generation will be created.

To create a new generation and ensure the players will continue to evolve toward the goal, the best performing players will be chosen. The fittest players are given another chance and will be bred 9 times (single parent) for a total of 10 players per fittest player. This will ensure that a bad mutation will not ruin the chances of breading a successful generation.

## Getting Started
This project relies on Processing with the Java plugin.  
https://processing.org/tutorials/eclipse/  
A compiled jar is provided in the root of the folder. JumpNN.jar

## Visuals Explained  
![jumpNN.png](imgs/jumpNN.png)  
A-E have been removed in this version and replaced with a control panel  
F - The visual representation of the brain (inputs on the left, outputs on the right)  
G - The players  
H - The obstacles

## Demo
![preview.gif](imgs/preview.gif)  
![demo.gif](imgs/demo.gif)
![NNdemo.gif](imgs/NNDemo.gif)  
