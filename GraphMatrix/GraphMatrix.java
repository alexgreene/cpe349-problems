import java.util.*;
import java.lang.*;
import java.io.*;


public class GraphMatrix {

	private static HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
	private static boolean isBipartite = true;
	private static boolean isConnected = true;

	//COLOR MAPPING
	//0: WHITE (default)
	//1: RED
	//2: BLUE
	private static int[] colors;

	public static void main(String[] args){

		readGraph();
		depthFirstSearch();
	}

	public static void depthFirstSearch(){

		Set<Integer> keySet = map.keySet();
		Integer[] keys = keySet.toArray(new Integer[keySet.size()]);

		colors = new int[3000];
		//map.size() + 1

		for(int k = 0; k < keys.length; k++){
			if (colors[keys[k]] == 0) {
				if (k > 0)
					isConnected = false;
				colors[keys[k]] = 1;
				explore(keys[k], 2);
			}
		}

		// for(int a = 0; a < colors.length; a++){
		// 	String color = (colors[a] == 0) ? "?" : ((colors[a] == 1) ? "RED": "BLUE");
		// 	System.out.println(a + ": " + color);
		// }

		System.out.println("Bipartite? ... " + isBipartite);
		System.out.println("Connected? ... " + isConnected);
	}

	public static void explore(int curNode, int colorPassed){
		int colorToPass = (colorPassed == 1) ? 2 : 1; //alternate color

		//System.out.println("colors[" + curNode + "]: " + colors[curNode] + ", colorPassed: " + colorPassed);

		ArrayList<Integer> friends = map.get(curNode);
		for (int i = 0; i < friends.size(); i++){
			int f = friends.get(i);

			if(colors[curNode] == colors[f]){
				isBipartite = false;
			} else if(colors[f] == 0){
				colors[f] = colorPassed;
				explore(f, colorToPass);
			}
		}
	}

	public static void readGraph(){

		ArrayList<Integer> temp;
		int[] v = new int[2];

		try {
			FileReader reader = new FileReader("testH.txt");
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
		    //System.out.println(map.toString());
		    reader.close();
		} catch(Exception e){
			System.out.println("ERROR YO!");
		}
	}

}