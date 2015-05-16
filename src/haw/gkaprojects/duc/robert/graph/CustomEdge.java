package haw.gkaprojects.duc.robert.graph;

import org.jgrapht.graph.DefaultWeightedEdge;

public class CustomEdge extends DefaultWeightedEdge implements Comparable<CustomEdge> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String _color;

	public CustomEdge() {
		super();
		_color = "";
	}

	/**
	 * 
	 * @return
	 */
	public String getColor() {
		return _color;
	}

	/**
	 * 
	 * @param color
	 */
	public void setColor(String color) {
		_color = color;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		boolean result = false;
		if(obj instanceof CustomEdge)
		{
			CustomEdge otherEdge = (CustomEdge)obj;
			result = this.getlabel().equals(otherEdge.getlabel());
		}
			
			return result;
	}
	
	
	
	public int hashCode()
	{
		return this.getlabel().hashCode();
	}
	
	public String getlabel(){
		return "("+this.getSource() + ":" + this.getTarget()+")";
	}
	
	@Override
	public String toString(){
		return Double.toString(getWeight());
	}

	@Override
	public int compareTo(CustomEdge otherEdge)
	{
		return (int)(this.getWeight() - otherEdge.getWeight());
	}
}
