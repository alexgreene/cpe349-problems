import java.util.*;

public class LongestSubsequence {

	public static void main(String[] args) {
		
		int[] seq = { 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15 };
		//ArrayList<Integer> result = longestSub(seq);

		ArrayList<Integer> result = longestSub(seq);

		System.out.println("----------------");
		System.out.println("Longest Increasing Subsequence: " + result.toString());
		System.out.println("----------------");
	}

	public static ArrayList<Integer> longestSub(int[] seq) {

		ArrayList<Integer> toRet = new ArrayList<Integer>();

		int[] table = new int[seq.length];
		int[] pointerTable = new int[seq.length];

		int maxPrevLength = 0;
		int maxPrevIndex = -1;

		//for each element, a-sub-i in the sequence
		for ( int i = 0; i < seq.length; i++ ) {

			maxPrevLength = 0;
			maxPrevIndex = -1;

			// iterate from a-sub-j to a-sub-i
			for ( int j = 0; j < i; j++ ) {
				if ( table[j] > maxPrevLength && seq[i] > seq[j]) {
					maxPrevLength = table[j];
					maxPrevIndex = j;
				}
			}
			
			int temp = maxPrevLength > 0 ? maxPrevLength + 1 : 1;
			table[i] = temp;
			pointerTable[i] = maxPrevIndex;
			
		}

		int maxLength = 0;	
		int maxIndex = 0;
		// find max value in the table
		for( int k = 0; k < table.length; k++ ) {
			if ( table[k] > maxLength ) {
				maxLength = table[k];
				maxIndex = k;
			}
		}

		// backtrack to collect the actual subsequence
		// end of sequence is element at index 'maxIndex'
		int cursor = seq[maxIndex];
		toRet.add(0, cursor);
		while ( pointerTable[cursor] != -1 ) {
			toRet.add(0, pointerTable[cursor]);
			cursor = pointerTable[cursor];
		}

		return toRet;
	}
}