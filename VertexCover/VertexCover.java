import java.util.*;
import java.lang.*;
import java.io.*;

public class VertexCover {

	private static HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();

	public static void main(String[] args){

		readGraph();
		System.out.println( "Smart Algorithm: " + smartGreedyVertexCover() );
		System.out.println( "Basic Algorithm: " + basicGreedyVertexCover() );
		System.out.println( "Brute Force: " + bruteForceVertexCover() );
	}

	/*
	while there exists an edge in the graph
		choose v, the vertex in graph with highest degree
		remove v from graph
		add v to set
	*/
	public static int smartGreedyVertexCover() {

		HashMap<Integer, ArrayList<Integer>> set = new HashMap<Integer, ArrayList<Integer>>();
		HashMap<Integer, ArrayList<Integer>> graph = (HashMap<Integer, ArrayList<Integer>>) map.clone();

		while ( !graph.isEmpty() && ( hasAnEdge(graph)[0] != -1 ) ) {

			int v = -1;
			for (HashMap.Entry<Integer, ArrayList<Integer>> vertex : graph.entrySet()) {
    			if ( v == -1 || vertex.getValue().size() > graph.get(v).size() ) {
    				v = vertex.getKey();
    			}
			}
			removeEdgeReferences(graph, v);
			graph.remove(v);

			ArrayList<Integer> vEdges = map.get(v);
			set.put(v, vEdges);
		}

		return set.size();
	}

	/*
	while there exists an edge in the graph
		choose e, any edge in graph
		remove endpoints of e (u, v) from the graph
		add endpoints of e (u, v) to set
	*/
	public static int basicGreedyVertexCover() {

		HashMap<Integer, ArrayList<Integer>> set = new HashMap<Integer, ArrayList<Integer>>();
		HashMap<Integer, ArrayList<Integer>> graph = (HashMap<Integer, ArrayList<Integer>>) map.clone();

		int[] edge = hasAnEdge(graph);
		while ( edge[0] != -1 ) {

			removeEdgeReferences(graph, edge[0]);
			graph.remove(edge[0]);
			removeEdgeReferences(graph, edge[1]);
			graph.remove(edge[1]);

			ArrayList<Integer> edgeS = graph.get(edge[0]);
			set.put(edge[0], edgeS);

			ArrayList<Integer> edgeF = graph.get(edge[1]);
			set.put(edge[1], edgeF);

			edge = hasAnEdge(graph);
		}

		return set.size();
	}

	public static void removeEdgeReferences(HashMap<Integer, ArrayList<Integer>> g, int ref) {

		int otherVert;

		for ( int r = 0; r < g.get(ref).size(); r++ ) {
			otherVert = g.get(ref).get(r);
			ArrayList<Integer> toPut = new ArrayList<Integer>(g.get(otherVert));
			toPut.remove( (Integer) ref);
			g.replace(otherVert, toPut);
		}
	}

	public static int bruteForceVertexCover() {

		HashMap<Integer, ArrayList<Integer>> set = new HashMap<Integer, ArrayList<Integer>>();
		HashMap<Integer, ArrayList<Integer>> graph = (HashMap<Integer, ArrayList<Integer>>) map.clone();

		/*
		from n = 0 -> v number of vertices
			for each possible combination of n vertices
			determine if combination touches all edges
		*/
		LinkedHashSet<Integer> minimal = new LinkedHashSet<Integer>();

		ArrayList<Integer> keys = new ArrayList<Integer>(graph.keySet());
		LinkedHashSet<LinkedHashSet<Integer>> combos = generateCombinations( keys );
		for (LinkedHashSet<Integer> combo : combos) {
			if ( vertexCoverOrNah(combo) && (minimal.size() == 0 || combo.size() < minimal.size()) ) {
				minimal = combo;
			}
		}
		
		return minimal.size();
	}

	public static LinkedHashSet<LinkedHashSet<Integer>> generateCombinations(ArrayList<Integer> verts) {
		//return all possible combinations of n verts from vertices set (POWERSET)
		LinkedHashSet<LinkedHashSet<Integer>> toRet = new LinkedHashSet<LinkedHashSet<Integer>>();

		for ( int i = 0; i < (int) Math.pow(2, verts.size()); i++ ) {
			String binary = intToBinary(i, verts.size());
			LinkedHashSet<Integer> subset = new LinkedHashSet<Integer>();

			for (int j = 0; j < binary.length(); j++) {
            	if (binary.charAt(j) == '1') {
                	subset.add(verts.get(j));
            	}
           	}
           toRet.add(subset);
		}
		return toRet;
	}

	private static String intToBinary(int binary, int digits) {
     
       String temp = Integer.toBinaryString(binary);
       int foundDigits = temp.length();
       String returner = temp;
       for (int i = foundDigits; i < digits; i++) {
           returner = "0" + returner;
       }
     
       return returner;
   } 

	public static boolean vertexCoverOrNah(LinkedHashSet<Integer> verts) {

		boolean check = false;
		// determine if all edges are covered by this set of vertices
		for (HashMap.Entry<Integer, ArrayList<Integer>> vertex : map.entrySet()) {

			if ( !verts.contains( vertex.getKey() ) ) {
				for (int v : vertex.getValue()) {
					if ( !verts.contains(v) ) {
						return false;
					}
				}
			} 
		
		}
		return true;
	}

	public static int[] hasAnEdge(HashMap<Integer, ArrayList<Integer>> g) {

		int[] toRet = new int[2];
		toRet[0] = -1;
		for (HashMap.Entry<Integer, ArrayList<Integer>> vertex : g.entrySet()) {
			if ( vertex.getValue().size() > 0 ) {
				toRet[0] = vertex.getKey();
				toRet[1] = vertex.getValue().get(0);
				return toRet;
			}
		}
		return toRet;
	}

	public static void readGraph(){

		ArrayList<Integer> temp;
		int[] v = new int[2];

		try {
			FileReader reader = new FileReader("testB.txt");
	    	Scanner scanner = new Scanner(reader);
	    	String edge;

		    while (scanner.hasNextLine()) {
		      edge = scanner.nextLine();
		      Scanner parser = new Scanner(edge);
		      parser.useDelimiter(" ");

		      v[0] = Integer.parseInt(parser.next());
		      v[1] = Integer.parseInt(parser.next());

		   	  for(int i = -1; i < 1; i++){
			      temp = map.get(v[i+1]);
			      if(temp == null){
			      	temp = new ArrayList<Integer>();
			      } 
			      if(temp.indexOf(v[i*-1]) == -1){
			      		temp.add(v[i*-1]);
			      		map.put(v[i+1], temp);
			      }
			  }
		    }
		    reader.close();
		} catch(Exception e){
			System.out.println("ERROR YO!");
		}
	}

}