import java.util.*;
import java.io.*;
import java.lang.*;

public class DivideConquer {

	public static void main(String[] args) throws IOException{

		// try {
		// 	FileWriter file = new FileWriter("output.csv");
		// 	BufferedWriter writer = new BufferedWriter(file);
		//
		// 	for(int x = 10000; x < 20000; x++){
		// 		int[] data = new int[x];
		// 		Random rand = new Random();
		// 		for(int i = 0; i < x; i++){
		// 			data[i] = rand.nextInt(20000) - 10000;
		// 		}
		// 		long startTime = System.currentTimeMillis();
		// 		int[] sorted = mergeSort(data);
		// 		long endTime = System.currentTimeMillis();
		// 		long time = endTime - startTime;
		// 		writer.write(x + ", " + time + "\n");
		// 	}
		// 	writer.close();
		// } catch (Exception e) {
		// 	System.out.println("ERROR YO!");
		// }

		ArrayList<Integer> lst = new ArrayList<Integer>();

		FileReader reader = new FileReader("test2.txt");
    Scanner scanner = new Scanner(reader);
    scanner.useDelimiter(", *");

		CharSequence leftBracket = "[";
		CharSequence rightBracket = "]";

    while (scanner.hasNext()) {
      if (scanner.hasNextInt()) {
				lst.add(scanner.nextInt());
      } else {
				String nxt = scanner.next();
				if (nxt.contains(leftBracket)){
					nxt = nxt.replace("[", "");
					lst.add(Integer.parseInt(nxt));
				}
				else if (nxt.contains(rightBracket)){
					nxt = nxt.replace("]", "");
					nxt = nxt.replace("\n", "");
					lst.add(Integer.parseInt(nxt));
				}
				else
        	break;
      }
    }
    reader.close();

		int[] data = new int[lst.size()];
		for(int c = 0; c < lst.size(); c++){
			data[c] = lst.get(c);
		}

		int[] mergeSorted = mergeSort(data);
		int[] selectionSorted = selectionSort(data);

		try {
			FileWriter file = new FileWriter("mergeSort_output.txt");
			FileWriter file2 = new FileWriter("selectionSort_output.txt");
			BufferedWriter writer = new BufferedWriter(file);
			BufferedWriter writer2 = new BufferedWriter(file2);

			for(int m = 0; m < mergeSorted.length; m++){
				writer.write(mergeSorted[m] + "\n");
			}

			for(int s = 0; s < selectionSorted.length; s++){
				writer2.write(selectionSorted[s] + "\n");
			}

			writer.close();
			writer2.close();
		} catch (Exception e) {
			System.out.println("ERROR YO!");
		}

	}

	public static int[] mergeSort(int[] arr){

		int[] firstHalf, secondHalf;
		int i;

		if(arr.length == 1){
			return arr;
		}
	        else {
			firstHalf = new int[arr.length/2];
			secondHalf = new int[arr.length - firstHalf.length];
			for(i = 0; i < firstHalf.length; i++){
				firstHalf[i] = arr[i];
			}

			for(int k = 0; k < secondHalf.length; i++, k++){
				secondHalf[k] = arr[i];
			}
		}

		int[] first = mergeSort(firstHalf);
		int[] second = mergeSort(secondHalf);

		int[] merged = merge(first, second);
		return merged;
	}

	public static int[] merge(int[] firstHalf, int[] secondHalf){

		int[] result = new int[firstHalf.length + secondHalf.length];
		int firstMarker = 0, secondMarker = 0;

		for(int i = 0; i < result.length; i++){
			if(firstMarker == firstHalf.length){
				result[i] = secondHalf[secondMarker++];
			}
			else if(secondMarker == secondHalf.length){
				result[i] = firstHalf[firstMarker++];
			}
			else {
				if(firstHalf[firstMarker] <= secondHalf[secondMarker]){
					result[i] = firstHalf[firstMarker++];
				}
				else if(firstHalf[firstMarker] > secondHalf[secondMarker]){
					result[i] = secondHalf[secondMarker++];
				}
			}
		}
		return result;
	}

	public static int[] selectionSort(int[] arr){
		int split = 0;
		int lowest, lowestIndex;
		while(split < arr.length-1){
		        lowest = arr[split];
			lowestIndex = split;
			for(int j = split; j < arr.length; j++){
				if(arr[j] < lowest){
					lowest = arr[j];
					lowestIndex = j;
				}
			}
			arr[lowestIndex] = arr[split];
			arr[split] = lowest;
			split++;
		}
		return arr;
	}
}
