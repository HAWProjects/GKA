package eu.haw.gkaprojects.duc.robert;

public interface Vertex {
	/**
	 * Searching status 
	 */
	public static final int EXPLORED = 1;
	public static final int UNEXPLORED = 0;
	public static final int EXPLORING = 2;
	
	
	/**
	 * Return the label of the vertex
	 */
	public String getLabel();
	
	/**
	 * Return Attribute of the Vertex
	 */
	public int getAttribut();
	
	/**
	 * 	Return level of the Vertex in Breadth-first search
	 */
	public int getLevel();
	
	/**
	 * Set level for the Vertex in Breadth-first search
	 */
	public void setLevel(int level);
	
	/**
	 * Sheck search status
	 * 	Vertex.EXPLORED : The Vertex is already explored
	 *  Vertex.EXPLORING: The Vertex is in the exploring process
	 *  Vertex.UNEXPLORED: The Vertex is untouched	
	 */
	public int getSearchStatus();
	
	/**
	 * Set search status
	 * 	Vertex.EXPLORED : The Vertex is already explored
	 *  Vertex.EXPLORING: The Vertex is in the exploring process
	 *  Vertex.UNEXPLORED: The Vertex is untouched	
	 */
	public void setSearchStatus(int status);
	
	/**
	 * Get Predecessor
	 */
	public Vertex getPredecessor();
	
	/**
	 * Set Predecessor
	 */
	public void setPredecessor(Vertex pre);
	
	/**
	 * 
	 * @return
	 */
	public String getColor();
	
	/**
	 * 
	 * @param color
	 */
	public void setColor(String color);
}
