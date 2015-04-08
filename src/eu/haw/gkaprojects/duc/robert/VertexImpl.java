package eu.haw.gkaprojects.duc.robert;

public class VertexImpl implements Vertex{
	
	private int _searchStatus;
	private int _searchLevel;
	private String _label;
	private int _attribute;
	private Vertex _predecessor;
	private String _color;
	
	public VertexImpl(String label) {
		this(label,0);
	}
	
	public VertexImpl(String label, int attribute){
		_label = label;
		_attribute = attribute;
		_color = "";
		_searchLevel = Integer.MAX_VALUE;
		_searchStatus = Vertex.UNEXPLORED;
		_predecessor = null;
		
	}
	
	@Override
	public String getLabel() {
		return _label;
	}
	
	@Override
	public int getAttribut() {
		return _attribute;
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
        if (obj instanceof Vertex)
        {
            VertexImpl vergleichsVertex = (VertexImpl) obj;
            result = _label.equals(vergleichsVertex._label); //&& _attribute == vergleichsVertex._attribute ;
        }
        return result;
	}
	
	@Override
	public int hashCode() {
		return _label.hashCode() ;
	}
	
	@Override
	public String toString() {
		return _label;
	}

	@Override
	public int getLevel() {
		return _searchLevel;
	}

	@Override
	public int getSearchStatus() {
		return _searchStatus;
	}

	@Override
	public void setLevel(int level) {
		_searchLevel = level;
	}
	
	@Override
	public void setSearchStatus(int status) {
		_searchStatus = status;
	}

	@Override
	public Vertex getPredecessor() {
		return _predecessor;
	}

	@Override
	public void setPredecessor(Vertex pre) {
		_predecessor = pre;
	}
	
	@Override
	public String getColor() {
		return _color;
	}
	
	@Override
	public void setColor(String color) {
		_color = color;
	}
}
