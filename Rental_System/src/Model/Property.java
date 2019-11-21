package Model;

public class Property {
	private int fee;
	private String state;
	private int period;
	private String address;
	private int ID;
	private Criteria info;

	Property(int id, int f, int p, String a, Criteria c) {

		ID = id;
		address = a;
		fee = f;
		period = p;
		info = c;

		state = "Inactive";

	}

	public String getState() {
		return state;
	}

	public int getID() {
		return ID;
	}

	public void changeState(String state2) {
		state = state2;
	}

	public void changePeriod(int period) {
		this.period = period;
	}

	public Criteria getCriteria() {
		return info;
	}

	public String toString() {
		if (state == "Active") {
			return "Item ID: " + ID + ", Address: " + address + ", " + "State: " + state + ", " + info.toString() + "*";
		}
		return "";
	}

	public String getInfo() {
		return "Item ID: " + ID + ", Address: " + address + ", " + "State: " + "Fee: " + fee + ", " + "Period: "
				+ period + ", " + state + ", " + info.toString() + "*";
	}
}
