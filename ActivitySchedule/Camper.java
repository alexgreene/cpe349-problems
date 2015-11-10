public class Camper {
	
	private int id;
	private int swimTime;
	private int bikeTime;
	private int runTime;

	public Camper() {};

	public Camper(int id, int swimTime, int bikeTime, int runTime) {
		this.id = id;
		this.swimTime = swimTime;
		this.bikeTime = bikeTime;
		this.runTime = runTime;
	}

	// getters

	public int getId() {
		return id;
	}

	public int getSwimTime() {
		return swimTime;
	}

	public int getBikeTime() {
		return bikeTime;
	}

	public int getRunTime() {
		return runTime;
	}

	// setters

	public void setId(int id) {
		this.id = id;
	}

	public void setSwimTime(int swimTime) {
		this.swimTime = swimTime;
	}

	public void setBikeTime(int bikeTime) {
		this.bikeTime = bikeTime;
	}

	public void setRunTime(int runTime) {
		this.runTime = runTime;
	}

	public String toString(){
		return "[" + id + ", " + swimTime + ", " + bikeTime + ", " + runTime + "]";
	}
}