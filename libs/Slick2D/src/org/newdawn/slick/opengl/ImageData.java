package org.newdawn.slick.opengl;

import java.nio.ByteBuffer;

/**
 * A description of any class providing ImageData in a form suitable for OpenGL texture
 * creation.
 * 
 * @author kevin
 */
public interface ImageData {

	/**
	 * get the last bit depth read from a TGA
	 * 
	 * @return The last bit depth read
	 */
	public int getDepth();

	/**
	 * get the last width read from a TGA
	 * 
	 * @return get the last width in pixels fread from a TGA
	 */
	public int getWidth();

	/**
	 * get the last height read from a TGA
	 * 
	 * @return get the last height in pixels fread from a TGA
	 */
	public int getHeight();

	/**
	 * get the last required texture width for a loaded image
	 * 
	 * @return get the ast required texture width for a loaded image
	 */
	public int getTexWidth();

	/**
	 * get the ast required texture height for a loaded image
	 * 
	 * @return get the ast required texture height for a loaded image
	 */
	public int getTexHeight();
	
	/**
	 * get the store image
	 * 
	 * @return The stored image
	 */
	public ByteBuffer getImageBufferData();

}