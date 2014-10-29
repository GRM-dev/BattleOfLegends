package org.newdawn.slick.util.pathfinding.navmesh;

/**
 * A link between this space and another
 * 
 * @author kevin
 */
public class Link {
	/** The x coordinate of the joining point */
	private float px;
	/** The y coordinate of the joining point */
	private float py;
	/** The target space we'd be linking to */
	private Space target;
	
	/**
	 * Create a new link
	 * 
	 * @param px The x coordinate of the linking point
	 * @param py The y coordinate of the linking point
	 * @param target The target space we're linking to
	 */
	public Link(float px, float py, Space target) {
		this.px = px;
		this.py = py;
		this.target = target;
	}
	
	/**
	 * get the distance squared from this link to the given position
	 * 
	 * @param tx The x coordinate of the target location
	 * @param ty The y coordinate of the target location
	 * @return The distance squared from this link to the target
	 */
	public float distance2(float tx, float ty) {
		float dx = tx - px;
		float dy = ty - py;
		
		return ((dx*dx) + (dy*dy));
	}
	
	/**
	 * get the x coordinate of the link
	 * 
	 * @return The x coordinate of the link
	 */
	public float getX() {
		return px;
	}
	
	/**
	 * get the y coordinate of the link
	 * 
	 * @return The y coordinate of the link
	 */
	public float getY() {
		return py;
	}
	
	/**
	 * get the space this object links to
	 * 
	 * @return The space this object links to
	 */
	public Space getTarget() {
		return target;
	}
}