package org.newdawn.slick.util.pathfinding;

/**
 * The context describing the current path finding state
 * 
 * @author kevin
 */
public interface PathFindingContext {
	/**
	 * get the object being moved along the path if any
	 * 
	 * @return The object being moved along the path
	 */
	public Mover getMover();
	
	/**
	 * get the x coordinate of the source location
	 * 
	 * @return The x coordinate of the source location
	 */
	public int getSourceX();

	/**
	 * get the y coordinate of the source location
	 * 
	 * @return The y coordinate of the source location
	 */
	public int getSourceY();
	
	/**
	 * get the distance that has been searched to reach this point
	 * 
	 * @return The distance that has been search to reach this point
	 */
	public int getSearchDistance();
}
