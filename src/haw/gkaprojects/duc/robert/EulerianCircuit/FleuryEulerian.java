package haw.gkaprojects.duc.robert.EulerianCircuit;

import java.util.List;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.EulerianCircuit;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.Pseudograph;

public class FleuryEulerian<V,E> {
	
	private List<E> eulerianCircuit;
	
	public FleuryEulerian(UndirectedGraph<V, E> graph)
	{
		if (isEulerian(graph))
		{
			
			UndirectedGraph<V, E> newGraph = (UndirectedGraph<V, E>)((AbstractBaseGraph<V, E>)graph).clone();
			createEuler(newGraph);
		}
	}
	
	private void createEuler(UndirectedGraph<V, E> graph) {
		// bei einem Knoten starten
		
		//  wähle einen kante 
		
	}
	
	
	
	

	/**
	 * returns the eulerian path
	 * @return List of Edges - The eulerian path 
	 */
	public List<E> getEulerianCircuit()
	{
		return eulerianCircuit;
	}
	
	/**
	 * Checks if graph is an eulerian graph
	 * @param graph
	 * @return boolean
	 */
	public boolean isEulerian(UndirectedGraph<V, E> graph)
	{
		return EulerianCircuit.isEulerian(graph);
	}

}
