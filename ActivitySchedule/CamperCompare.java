import java.util.*;
import java.lang.*;
import java.io.*;

public class CamperCompare implements Comparator<Camper> {
	
	@Override
	public int compare(Camper a, Camper b) {
		Integer aSum = new Integer(a.getBikeTime() + a.getRunTime());
		Integer bSum = new Integer(b.getBikeTime() + b.getRunTime());

		return bSum.compareTo(aSum);
	}
}