package eu.haw.gkaprojects.duc.robert.test.algo;

import static org.junit.Assert.*;
import haw.gkaprojects.duc.robert.UndirectedEulerianGraphConstructor;
import haw.gkaprojects.duc.robert.EulerianCircuit.EulerUtil;
import haw.gkaprojects.duc.robert.EulerianCircuit.FleuryEulerian;
import haw.gkaprojects.duc.robert.EulerianCircuit.FleuryEulerianCircuit;
import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;

import java.util.List;
import java.util.Random;

import org.jgrapht.UndirectedGraph;
import org.junit.Test;

public class FleuryTest {
	
    private int testcount      = 200;
    private int minVertexcount = 50;
    
	@Test
	public void testSmallGraph() {
       
		int verticesAmount = 15;
        int edgesAmount = 30;
        for (int i = 0; i < testcount; i++) {
        	
            Random rand = new Random();

            do {

                  verticesAmount = rand.nextInt(100) + minVertexcount;
                  edgesAmount = rand.nextInt(300);
            }while(!UndirectedEulerianGraphConstructor.isArgumentStatisfied(verticesAmount,
                    edgesAmount));
            	
            UndirectedGraph<Vertex, CustomEdge> graph = (UndirectedGraph<Vertex, CustomEdge>) UndirectedEulerianGraphConstructor
                    .constructGraph(verticesAmount, edgesAmount);
            FleuryEulerian<Vertex, CustomEdge> eulerGraph = new FleuryEulerian<Vertex,CustomEdge>(graph);
            List<CustomEdge> circuit = eulerGraph.getEulerianCircuit();
            if(EulerUtil.isEulerianGraph(graph)){
            	assertTrue(EulerUtil.isEulerianCircuit(graph, circuit));
            }else{
            	 fail("No Euler Circuit");
            }
            	
            }
        }
		
	
	
	
	@Test
    public void testRandomizedEulerianGraph() {
      
		int verticesAmount = 0;
        int edgesAmount = 0;
        for (int i = 0; i < testcount; i++) {
            Random rand = new Random();
            do {
                  verticesAmount = rand.nextInt(10000) + minVertexcount;
                  edgesAmount = rand.nextInt(30000);

            } while (!UndirectedEulerianGraphConstructor.isArgumentStatisfied(verticesAmount,
                        edgesAmount));
            
            UndirectedGraph<Vertex, CustomEdge> graph = (UndirectedGraph<Vertex, CustomEdge>) UndirectedEulerianGraphConstructor
                    .constructGraph(verticesAmount, edgesAmount);
            FleuryEulerian<Vertex, CustomEdge> eulerGraph = new FleuryEulerian<Vertex,CustomEdge>(graph);
            List<CustomEdge> circuit = eulerGraph.getEulerianCircuit();
            if(EulerUtil.isEulerianGraph(graph)){
            	assertTrue(EulerUtil.isEulerianCircuit(graph, circuit));
            }else{
            	 fail("No Euler Circuit");
            }
        }
	}

}
