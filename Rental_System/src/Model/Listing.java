package Model;

import java.util.ArrayList;


public class Listing {

	private Landlord landlord;
	private Property property;
	
	public Listing(Landlord l, Property p) {
		property = p;
		landlord = l;
	}

	public Criteria getCriteria() {
		return property.getCriteria();
	}
	
	public Property getProperty() {
		return property;
	}
	
	public Landlord getLandlord() {
		return landlord;
	}

	public void display() {
		if (property.getState() == "Active") {
			property.toString();
			landlord.toString();
		}		
	}
}
