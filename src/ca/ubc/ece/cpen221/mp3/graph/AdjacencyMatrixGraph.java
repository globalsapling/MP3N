package ca.ubc.ece.cpen221.mp3.graph;
import java.util.*;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class AdjacencyMatrixGraph implements Graph {
    
	//This Boolean Arraylist-of-Arraylists will keep track of relationships, by assigning a unique arrayList to every Vertex.
	//This Unique boolean Arraylist has an element for every other Vertex on the graph, and a boolean state to show the existence  
	// of a downstream relationship
	
	List<List<Boolean>> graph = new ArrayList<List<Boolean>>(0); 
	
	//The following ArrayList will act as a address bar to check the indexes of the vertices, 
	// so the order in which the vertices are added does not matter
	List<Vertex> vertexAddress = new ArrayList<Vertex>(0);
	
		/**
		 * Adds a vertex to the graph.
		 *
		 * Precondition: v is not already a vertex in the graph
		 */
		public void addVertex(Vertex v){
			
			vertexAddress.add(v); //adds the vertex to an address book for further tracking and access
			graph.get(graph.size()-1).add(false);	
			for(int i =0; i<graph.size(); i++) {
				graph.get(i).add(false);
			}
		}

		/**
		 * Adds an edge from v1 to v2.
		 *
		 * Precondition: v1 and v2 are vertices in the graph
		 */
		public void addEdge(Vertex v1, Vertex v2){
			
			/* the following if-else statement accesses the place in the matrix that corresponds to the two given vertices and 
			   adds a relationship if there isn't any previously
			*/ 
			if  (graph.get(vertexAddress.indexOf(v1)).get(vertexAddress.indexOf(v2)) == false) { 
					graph.get(vertexAddress.indexOf(v1)).set(vertexAddress.indexOf(v2), true);
			}
		
		}

		
		/**
		 * Check if there is an edge from v1 to v2.
		 *
		 * Precondition: v1 and v2 are vertices in the graph Postcondition: return
		 * true iff an edge from v1 connects to v2
		 */
		public boolean edgeExists(Vertex v1, Vertex v2) {
			
			//the best part of a AdjecencyMatrixGraph is that you are essentially keeping track of edges, just a simple 
			//access to the element on the matrix corresponding to the two elements will suffice.
			return  (graph.get(vertexAddress.indexOf(v1)).get(vertexAddress.indexOf(v2)));
				
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
		public List<Vertex> getDownstreamNeighbors(Vertex v) {
			
			List<Vertex> downstreamNeighbors = new ArrayList<Vertex>();
			
			//If we imagine the list-of-lists (graph) as a two dimensional matrix, then the downstream neighbors are those
			//vertices in every row that register a "TRUE" state with the Vertex v. Here, we are just checking for vertices with
			//true states and adding them to an arrayList which we will return.
			
			for ( int i =0; i<vertexAddress.size(); i++ ) {	
				if ( graph.get(vertexAddress.indexOf(v)).get(i) == true) {
					downstreamNeighbors.add(vertexAddress.get(i));
				}
			}
			
			return downstreamNeighbors;
				
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
			
			List<Vertex> upstreamNeighbors = new ArrayList<Vertex>();
			int targetAddress = vertexAddress.indexOf(v);
			
			//Upstream neighbors are a tad bit more tricky than downstream neighbors. If we imagine the list-of-lists (graph) as a 2X2 
			// matrix, then the corresponding vertices to "TRUE" states in the columns give us our upstream neighbors.
			
			for (int i = 0; i<vertexAddress.size(); i++) {
				if (graph.get(i).get(targetAddress) == true){
					upstreamNeighbors.add(vertexAddress.get(i));
				}		
			}
			
			return upstreamNeighbors;

			
		}

		/**
		 * Get all vertices in the graph.
		 *
		 * Postcondition: returns a list containing all vertices in the graph. This
		 * method should return a list of size 0 iff the graph has no vertices.
		 */
		public List<Vertex> getVertices(){
			
			//every vertex ever added is first added into the ArrayList vertexAddress, so it's a ready reckonor to return.
			return vertexAddress;
		}

		

	  }
