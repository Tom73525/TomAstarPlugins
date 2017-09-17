/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar.model;

import astar.plugin.IModel;
import static astar.util.Helper.tracksWall;
import astar.util.Node;

/**
 *
 * @author Tom
 */
public class Pretty implements IModel {
    
    protected char[][] tileMap = null;

    @Override
    public void init(char[][] tileMap) {
        this.tileMap = tileMap; 
    }

    @Override
    public double shape(double heuristic, Node curNode, Node adjNode) {
        System.out.println(curNode.getSteps());
        
        if(curNode.getSteps()<=2)
            return heuristic;    
        
        Node child = new Node(curNode);
        Node parent = new Node(curNode.getParent());
        Node grandparent = new Node(curNode.getParent().getParent());
        
        if(( grandparent.getCol() - parent.getCol() ) != ( parent.getCol() - child.getCol()) 
                || (grandparent.getRow() - parent.getRow()) != ( parent.getRow() - child.getRow() ) 
                && ( ( tracksWall( tileMap , curNode ) && tracksWall( tileMap, adjNode ) ) ) ){
            return heuristic+=13;
            
        }
        
        if(( ( tracksWall( tileMap , curNode.getParent() ) 
                || ( tracksWall( tileMap , curNode ) || ( tracksWall( tileMap, adjNode.getParent()) 
                || (tracksWall( tileMap, adjNode.getParent() ) ) ) ) ) ) ) {
            return heuristic+=10;
        }  
        
       
        if(( grandparent.getCol() - parent.getCol() ) != ( parent.getCol() - child.getCol()) 
                || (grandparent.getRow() - parent.getRow()) != ( parent.getRow() - child.getRow() ))
        {
            return heuristic+=2;
            
        }
        
        return heuristic;
    }

    @Override
    public void complete(Node curNode) {
        
    }
    
}
