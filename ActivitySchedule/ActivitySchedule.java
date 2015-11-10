import java.util.*;
import java.lang.*;
import java.io.*;

public class ActivitySchedule {

	public static ArrayList<Camper> allCampers = new ArrayList<Camper>();

	public static void main(String[] args) {

		readTimes();
		sortCampers();
		getScheduleResults();
	}

	public static void sortCampers() {
		Collections.sort(allCampers, new CamperCompare());
	}

	public static void getScheduleResults() {

		int totalTime = 0;
		int stragglerTime = 0;
		Camper temp;

		for (int i = 0; i < allCampers.size(); i++) {
			temp = allCampers.get(i);
			System.out.print(temp.getId());
			
			totalTime += temp.getSwimTime();
			stragglerTime -= temp.getSwimTime();

			if (stragglerTime < temp.getBikeTime() + temp.getRunTime()) {
				stragglerTime = temp.getBikeTime() + temp.getRunTime();
			}
			if (i != allCampers.size() - 1) {
				System.out.print(", ");
			}
		}

		totalTime += stragglerTime;
		System.out.println();
		System.out.println("Completion Time: " + totalTime);

	}

	public static void readTimes() {

		try {
			FileReader reader = new FileReader("test_c.txt");
	    	Scanner scanner = new Scanner(reader);
	    	String line;

		    while (scanner.hasNextLine()) {
		      line = scanner.nextLine();
		      Camper c = new Camper();
		      String[] nums = line.split(":|,");
		      
		      c.setId( Integer.parseInt( nums[0] ));
		      c.setSwimTime( Integer.parseInt( nums[1].split("\\(")[1] ));
		      c.setBikeTime( Integer.parseInt( nums[2] ));
		      c.setRunTime( Integer.parseInt( nums[3].split("\\)")[0] ));
		      allCampers.add(c);
		    }
		    reader.close();
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}


// >>> order participants by bike + run combined time, greatest to least