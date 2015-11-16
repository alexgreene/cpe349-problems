import java.util.*;
import java.io.*;

public class FewestLockers {

	private static int numLockers;
	private static int numBalls;
	private static int numKeys;
	private static int[] keys;
	private static int[] balls;

	private static int[] table;
	
	public static void main(String[] args) {

		getInputData();

		int ret = findFewestLockers();
		System.out.println("min lockers: " + ret);
		System.out.println(Arrays.toString(table));
	}

	public static int findFewestLockers() {

		int lastBallLoc = -1;
		int lastBallKey = -1;
		int closestKey;
		table = new int[numLockers];
		table[0] = keys[0] + 1;
		if (balls[0] == 0) {
			lastBallLoc = 0;
		}
		int curKey = keys[0];
		
		for (int i = 1; i < table.length; i++) {
			closestKey = findClosestKey(i);

			if (closestKey < i) {
				table[i] = table[i-1] + 1;
			}
			else {
				if (lastBallKey == closestKey) {
					table[i] = table[i-1];
				}
				else {
					int secondOption = distance(closestKey, i);
					if ( !checkFilling(lastBallKey, i) || contains(balls, lastBallKey)) {
						
						secondOption += 1;
					}
					if (lastBallLoc >= 0) {
						int firstOption = distance(lastBallLoc, i);
						firstOption += table[lastBallLoc];
						secondOption += table[lastBallLoc];

						if (secondOption < firstOption) {
							curKey = closestKey;
						}
						table[i] = Math.min(firstOption, secondOption);
					} 
					else { // a ball has not been seen yet
						curKey = closestKey;
						table[i] = secondOption;
					}
					
				}
			}
			if ( contains(balls, i) ) {
				lastBallLoc = i;
				lastBallKey = curKey;
			}
		}

		return table[lastBallLoc];
	}

// 0				 1					 2					 3					 4					 5				 6
// 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0
// X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X 
//               K K   K       K K			 K K   K K   K	   K   K     K K   K         K     K   K     K   K K       K
//   B     B  	       B B B B     B B B                 B B       B B B         B B B B   B   B B B B   B     B   B B   B
// 8 7 7 7 7 7 7 7 8 9 8 9 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 3 3 3 3 3 3 3 3 3 3 3
//						   0 1 2 2 3 4 5 6 7 6 6 7 6 6 7 6 7 8 8 9 8 9 0 1 1 2 1 2 3 4 5 5 6 7 7 8 9 0 1 1 2 2 2 3 4 5 5 6


	public static int findClosestKey(int i) {
		int leftMarker = 0;
		int rightMarker = 0;

		while(leftMarker < numKeys && keys[leftMarker] < i ) {
			leftMarker++;
		}
		leftMarker--;
		if (leftMarker <  0) {
			leftMarker = 0;
		}
		if (leftMarker + 1 < keys.length) {
			rightMarker = leftMarker + 1;
			if (i - keys[leftMarker] > keys[rightMarker] - i) {
				return keys[rightMarker];
			}
		}

		return keys[leftMarker];
	}

	public static boolean checkFilling(int a, int b) {
		if (b-a <= 1) {
			return false;
		}
		for(int c = a + 1; c < b; c++) {
			if( !contains(balls, c) ) {
				return false;
			}
		}
		return true;
	}

	public static int distance(int a, int b) {
		int toRet = a - b;
		if (toRet < 0) { 
			toRet = -toRet;
		}
		return toRet;
	}

	public static boolean contains(int[] arr, int c) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == c) {
				return true;
			}
		}
		return false;
	}

	public static void getInputData() {

		try {
			FileReader reader = new FileReader("test2.txt");
	    	Scanner scanner = new Scanner(reader);
	    	String line;
	    	String[] temp;

		    line = scanner.nextLine();
		    temp = line.split("\\s+");
		      
		    numLockers = Integer.parseInt( temp[0] );
		    numKeys = Integer.parseInt( temp[1] );
		    numBalls = Integer.parseInt( temp[2] );

		    balls = new int[numBalls];
			keys = new int[numKeys];

		    line = scanner.nextLine();
		    temp = line.split(" ");
		    for (int k = 0; k < temp.length; k++) {
		    	keys[k] = Integer.parseInt( temp[k] ) - 1;
		    }

		    line = scanner.nextLine();
		    temp = line.split(" ");
		    for (int j = 0; j < temp.length; j++) {
		    	balls[j] = Integer.parseInt( temp[j] ) - 1;
		    }
		    
		    Arrays.sort(balls);
		    Arrays.sort(keys);

		    reader.close();
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}


// X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X
//       K   K K K     K K   K     K K   K                       K                               K         K K   K             K
//               B       B       B   B B   B B                   B           B   B                     B B B                         B
// 4 3 2 1 2 1 1 1 2 3 2 2 3 3 4 4 4 5 6 

// X X X X X X X 
// K         K
//   B B B B   

// X X X X X X X X X X X X
//   K       K         K
//   B         B B B B   B
// 2 1 2 3 3 2