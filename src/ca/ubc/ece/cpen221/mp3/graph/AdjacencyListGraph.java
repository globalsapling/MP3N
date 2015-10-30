package ca.ubc.ece.cpen221.mp3.graph;
import java.util.*;

import ca.ubc.ece.cpen221.mp3.staff. *;

public class AdjacencyListGraph implements Graph {
	
   private Map<Vertex, ArrayList<Vertex>> graph = new HashMap<Vertex, ArrayList<Vertex>>( );
   
	/**
	 * Adds a vertex to the graph.
	 *
	 * Precondition: v is not already a vertex in the graph
	 */
	public void addVertex(Vertex v) {
		
			graph.put(v, new ArrayList<Vertex>()); //creates the given vertex as a key and assigns a new ArrayList as a Value
						
	}

	/**
	 * Adds an edge from v1 to v2.
	 *
	 * Precondition: v1 and v2 are vertices in the graph
	 */
	public void addEdge(Vertex v1, Vertex v2){
		
		graph.get(v1).add(v2); //Adds relation by getting corresponding ArrayList of v1 and adding v2 as an downstream Vertex.
		
	}

	/**
	 * Check if there is an edge from v1 to v2.
	 *
	 * Precondition: v1 and v2 are vertices in the graph Postcondition: return
	 * true iff an edge from v1 connects to v2
	 */
	public boolean edgeExists(Vertex v1, Vertex v2){
		
		return graph.get(v1).contains(v2); //Checks if the corresponding ArrayList of v1 has, v2 as it's member
	}

	/**
	 * Get an array containing all downstream vertices adjacent to v.
	 *
	 * Precondition: v is a vertex in the graph
	 * 
	 * Postcondition: returns a list containing each vertex w such that there is
	 * an edge from v to w. The size of the list must be as small as possible
	 * (No trailing null elements). This method should return a list of size 0
	 * iff v has no downstream neighbors.
	 */
	public List<Vertex> getDownstreamNeighbors(Vertex v){
		
		return graph.get(v); //returns an ArrayList that corresponds to vertex v in the HashMap, since we are storing only downstream members as a convention.
		
	}

	/**
	 * Get an array containing all upstream vertices adjacent to v.
	 *
	 * Precondition: v is a vertex in the graph
	 * 
	 * Postcondition: returns a list containing each vertex u such that there is
	 * an edge from u to v. The size of the list must be as small as possible
	 * (No trailing null elements). This method should return a list of size 0
	 * iff v has no upstream neighbors.
	 */
	public List<Vertex> getUpstreamNeighbors(Vertex v){
		
		List<Vertex> upstreamVertices = new ArrayList<Vertex>(); //This arrayList will keep track of all the Upstream Neighbors found, and be returned as needed.
		
		for ( Vertex potentialUpstreamNeighbor : graph.keySet()) {
			
			if (graph.get(potentialUpstreamNeighbor).contains(v) ) {
				upstreamVertices.add(v);
			}
		}
		return upstreamVertices;	
			
	}
	
	/**
	 * Get all vertices in the graph.
	 *
	 * Postcondition: returns a list containing all vertices in the graph. This
	 * method should return a list of size 0 iff the graph has no vertices.
	 */
	public List<Vertex> getVertices() {
		
		List<Vertex> allVertices = new ArrayList<Vertex>(); 
		allVertices.addAll(graph.keySet()); //All the vertices are simply all the keys of the HashMap, so the keyset is converted to type List and returned.
		return allVertices;	
		
	}
	
}
