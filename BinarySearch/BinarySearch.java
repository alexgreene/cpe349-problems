import java.util.*;

public class BinarySearch {

	public static void main(String args[]){
		
		double[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 8, 8, 9, 13, 15, 56, 67, 7777};

		int result = search(arr, 8, 0, arr.length-1);
		if (result == -1){
			System.out.println("Set does not contain this value");
		}	
		else {
			System.out.println(result);
		}
	}	

	public static int search(double[] A, double x, int min, int max){
		if(max < min){
			return -1;
		}
		else {
			if(x < A[(int)Math.floor((max+min)/2)]){
				return search(A, x, min, (int) Math.floor((max+min)/2)-1);
			} 
			else if(x > A[(int)Math.floor((max+min)/2)]){
				return search(A, x, (int) Math.floor((max+min)/2)+1, max);
			}
			else {
				return (max+min)/2;
			}
		}
	}

}
