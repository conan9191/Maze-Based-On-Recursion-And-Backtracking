/**
 * Name: Yiqun Peng
 */
package Maze;

import java.util.*;

/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    
    /* PROBLEM 1 */
    public boolean findMazePath(int x, int y) {
        /* The grid falls outside. */
    	if(y<0||y>=this.maze.getNRows()||x<0||x>=this.maze.getNCols())
    		return false;
    	/* The grid is background. */
    	else if(this.maze.getColor(x, y)==GridColors.BACKGROUND)
    		return false;
    	/* The grid is on exit */
    	else if(y==this.maze.getNRows()-1 && x==this.maze.getNCols()-1) {
    		this.maze.recolor(x, y, GridColors.PATH);
    		return true;
    	}
    	/* The grid has been visited. */
    	else if(this.maze.getColor(x, y)==GridColors.PATH) {
    		return false;
    	}
    	/* Otherwise, visiting next point */
    	else {	
    		this.maze.recolor(x, y, GridColors.PATH);
    		/* Adjacent points that meet the requirements are accessed */
    		if(findMazePath(x-1, y)|| //First,left
    		findMazePath(x+1, y)|| //Second,right
    		findMazePath(x, y-1)|| //Third,up
    		findMazePath(x, y+1)) { //Finally, down
    			return true;
    		}else { /* set it to the TEMPORARY path */
    			this.maze.recolor(x, y, GridColors.TEMPORARY);
    			return false;
    		}
    	}
    }
    
    /* PROBLEM 2 */
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y){
    	ArrayList <ArrayList <PairInt >> result = new ArrayList <>();
    	Stack<PairInt> trace = new Stack<>(); 
    	/* Call the path-finding method */
    	findMazePathStackBased(x,y,result, trace);
    	return result;
    }
    
    private void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
    	/* The grid falls outside. */
    	if(y<0||y>=this.maze.getNRows()||x<0||x>=this.maze.getNCols())
    		return;
    	/* The grid is background. */
    	else if(this.maze.getColor(x, y)==GridColors.BACKGROUND)
    		return;
    	/* The grid is on exit */
    	else if(y==this.maze.getNRows()-1 && x==this.maze.getNCols()-1) {
    		/* Use the stack to record the coordinates accessed */
    		trace.push(new PairInt(x,y));
    		/* Use list to record points of a successful path */
    		ArrayList<PairInt> a = new ArrayList<PairInt>();
    		a.addAll(trace);
    		/* Use list to record successful paths */
    		result.add(a);
    		/* Restore to facilitate other path access */
    		trace.pop();
    		return;
    	}
    	/* The grid has been visited. */
    	else if(this.maze.getColor(x, y)==GridColors.PATH) {
    		return;
    	}
    	/* Otherwise，visiting the neighbors */
    	else {	
    		/* Use the color to void Repeat visits */
    		this.maze.recolor(x, y, GridColors.PATH);
    		/* Use the stack to record the coordinates accessed */
    		trace.push(new PairInt(x,y));
    		findMazePathStackBased(x-1,y,result,trace);
    		findMazePathStackBased(x+1,y,result,trace);
    		findMazePathStackBased(x,y-1,result,trace);
    		findMazePathStackBased(x,y+1,result,trace);
    		this.maze.recolor(x, y, GridColors.NON_BACKGROUND);
    		/* Restore to facilitate other path access */
    		trace.pop();
    		return;
    		}
	}

	/* PROBLEM 3 */
    public ArrayList<PairInt> findMazePathMin(int x, int y){
    	Maze m = new Maze(maze);
    	/* Use the method in PROBLEM 2 to find all paths */
    	ArrayList<ArrayList<PairInt>> a = m.findAllMazePaths(x, y);
    	ArrayList<PairInt> minList = new ArrayList<PairInt>();
    	if(a.size()!=0) {
    		/* Record the index of the shortest path */
	    	int min = 0;
	    	minList = a.get(min);
	    	/*Compare and find the shortest path */
	    	for (int i = 0; i < a.size(); i++) {
	        	if(a.get(i).size()<a.get(min).size()) {
	        		minList=a.get(i);
	        		min=i;
	        	}
	        }
    	}
    	/* This method only returns one of the multiple shortest paths. 
    	 * If you want to return multiple paths, you need to change the 
    	 * return type of value of the function given in the problem */
    	return minList;
    }

    

    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/
