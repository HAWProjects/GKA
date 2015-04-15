package haw.gkaprojects.duc.robert;

import java.util.ArrayList;
import java.util.List;

public class CreateGraph
{

	private List<String> _graphList;
	private boolean _attributed;
	private boolean _weighted;
	private boolean _directed;
	private boolean _undirected;
	
	public CreateGraph(List<List<String>> rowList){
		_attributed = false;
		_weighted = false;
		_directed = false;
		_undirected = false;
		
		List<String> headerlist = rowList.get(0);
		checkGraphType(headerlist);
		
		_graphList = rowList.remove(0);
	}

	private void checkGraphType(List<String> headerlist)
	{
		if(headerlist.contains("attributed") && !headerlist.contains("weighted") && !headerlist.contains("directed"))
		{
			_attributed = true;
		}
		else if(headerlist.contains("weighted") && !headerlist.contains("attributed")&& !headerlist.contains("directed"))
		{
			_weighted = true;
		}
		else if(headerlist.contains("directed") && !headerlist.contains("weighted") && !headerlist.contains("attributed"))
		{
			_directed = true;
			_undirected = false;
		}
		else if(headerlist.contains("attributed") && headerlist.contains("weighted") && !headerlist.contains("directed"))
		{
			_weighted = true;
			_attributed = true;
		}
		else
		{
			_undirected = true;
			_directed = false;
		}
		System.out.println(_attributed +" "+ _weighted+ " "+ _directed);
		
	}
}
