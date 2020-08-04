/**
 * Name: Yiqun Peng
 */
package Maze;

/* Pairs of integers that represent coordinates. */
public class PairInt {
	
	/* x-coordinate */
	private int x;
	/* y-coordinate */
	private int y;
	
	public PairInt(int x, int y) {
		this.x=x;
		this.y=y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	/* Determine if they are the same coordinate */
	public boolean equals(Object p) {
		PairInt pi = (PairInt)p;
		return this.x==pi.getX() && this.y==pi.getY();
	}
	
	/* Formatted output */
	public String toString() {
		return "("+x+","+y+")";
	}
	
	/* Copy the PairInt */
	public PairInt copy() {
		PairInt pi = new PairInt(this.getX(),this.getY());
		return pi;
	}
	
}
