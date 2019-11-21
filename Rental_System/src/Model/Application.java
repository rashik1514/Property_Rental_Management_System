package Model;

import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Random;

public class Application {

	ListingArrayListSubject theListings;
	Landlord theLandlord;
	Manager theManager;
	RegularRenter regRenter;
	ArrayList<RegisteredRenter> registeredRenters;

	public Application() {
		theListings = new ListingArrayListSubject();
		regRenter = new RegularRenter();
		registeredRenters = new ArrayList<RegisteredRenter>();

		Name n = new Name("big", "papa");
		Landlord l = new Landlord(n, "momfker69@gmail.com", new ArrayList<Property>());

		Criteria c1 = new Criteria("apartment", 2, 2, 2, "SW");
		Criteria c2 = new Criteria("town house", 2, 2, 2, "SW");
		Criteria c3 = new Criteria("basement suite", 2, 2, 2, "NW");

		Property p = new Property(1, 10, 20, "Strathearn Gdns", c1);
		Property q = new Property(2, 10, 20, "Big gay way", c2);
		Property r = new Property(3, 10, 20, "Yeet st", c3);

		l.registerProperty(p);
		l.registerProperty(q);
		l.registerProperty(r);

		l.payFees(p, theListings);
		l.payFees(q, theListings);
		l.payFees(r, theListings);
	}

	public boolean checkManagerExist(String username, String password) {
		// TODO CHECK IF MANAGER EXISTS
		return true;
	}

	public void addNewManager(String userName, String password) {
		// TODO Auto-generated method stub

	}

	public void addNewRegRenter(String username, String password) {

		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ensf480", "root",
					"centralasia");

			String query = "INSERT INTO registeredrenters (Username, Password) VALUES (?, ?)";
			PreparedStatement pStat = myConn.prepareStatement(query);
			pStat.setString(1, username);
			pStat.setString(2, password);
			pStat.executeUpdate();

			System.out.printf("Creating new regRenter w/ user: %s and pass: %s\n", username, password);
			RegisteredRenter rr = new RegisteredRenter(username, password, new ArrayList<Criteria>(), theListings);
			registeredRenters.add(rr);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public boolean checkRegRenterList(String username, String password) {

		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ensf480", "root",
					"centralasia");

			String query = "SELECT * FROM registeredrenters WHERE Username = ? AND Password = ?";
			PreparedStatement pStat = myConn.prepareStatement(query);
			pStat.setString(1, username);
			pStat.setString(2, password);
			ResultSet rs = pStat.executeQuery();

			if (!rs.next())
				return false;

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return true;

	}

	public String searchProperty(String info) {
		String[] split = info.split(" ");
		System.out.println(info);
		Criteria cr = getCriteria(split);
		ArrayList<Listing> filteredListings = regRenter.search(theListings.getListings(), cr);

		String list = "";
		for (int i = 0; i < filteredListings.size(); i++) {
			System.out.println(filteredListings.get(i).getProperty().getID());
			list += filteredListings.get(i).getProperty().toString();
		}
		return list;
	}

	private Criteria getCriteria(String[] split) {
		String h = null;
		int bed = 0;
		int bath = 0;
		int furn = 0;
		String quad = null;

		for (int i = 0; i < split.length; i++) {
			if (i == 0) {
				if (!split[0].matches("[a-zA-Z0-9]+"))
					h = null;
				else
					h = split[0].trim();
			}

			if (i == 1) {
				if (!split[1].matches("[a-zA-Z0-9]+"))
					bed = 0;
				else
					bed = Integer.parseInt(split[1]);

			}
			if (i == 2) {
				if (!split[2].matches("[a-zA-Z0-9]+"))
					bath = 0;
				else
					bath = Integer.parseInt(split[2]);

			}
			if (i == 3) {
				if (!split[3].matches("[a-zA-Z0-9]+"))
					furn = 0;
				else if (split[3].compareTo("Y") == 0)
					furn = 2;
				else if (split[3].compareTo("N") == 0)
					furn = 1;

			}
			if (i == 4) {
				if (!split[4].matches("[a-zA-Z0-9]+"))
					quad = null;
				else
					quad = split[4].trim();

			}
		}

		Criteria newCrit = new Criteria(h, bed, bath, furn, quad);
		return newCrit;

	}

	public String getAllListings() {
		String list = "";
		for (int i = 0; i < theListings.getListings().size(); i++) {
			list += theListings.getListings().get(i).getProperty().toString();
		}
		return list;
	}

	public boolean checkID(String id) {
		if (!id.matches("-?\\d+(\\.\\d+)?"))
			return false;
		int theID = Integer.parseInt(id);
		for (int i = 0; i < theListings.getListings().size(); i++) {
			if (theID == theListings.getListings().get(i).getProperty().getID())
				return true;
		}
		return false;
	}

	public void sendMessage(int id, String message) {
		System.out.println(id + message);

	}

	public boolean rent(String id) {
		int theID = Integer.parseInt(id);
		for (int i = 0; i < theListings.getListings().size(); i++) {
			if (theID == theListings.getListings().get(i).getProperty().getID()
					&& theListings.getListings().get(i).getProperty().getState() == "Active") {
				theListings.getListings().get(i).getProperty().changeState("Rented");
				return true;
			}
		}
		return false;
	}

	public void newLandlord(String fname, String lname, String email) {
		theLandlord = new Landlord(new Name(fname, lname), email, new ArrayList<Property>());
		System.out.println(fname + " " + lname + " " + email);
	}

	public int registerNewProperty(String id, String address, Criteria c) {

		try {

			if (!id.matches("-?\\d+(\\.\\d+)?"))
				return 0;

			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ensf480", "root",
					"centralasia");

			// String query = "INSERT INTO registeredrenters (Username, Password) VALUES (?,
			// ?)";

			String query = "INSERT INTO listing (ID, fee, period, address, type, bed, bath, furnished, quadrant) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			// String query = "INSERT INTO 'property' ('ID, fee, period, address,
			// house-type) VALUES (?, ?, ?, ?, ?)";
			Random r = new Random();
			int fee = r.nextInt((1000 - 100) + 1) + 100;
			r = new Random();
			int period = r.nextInt((24 - 1) + 1) + 1;

			boolean check = checkID(id);
			int ID = Integer.parseInt(id);
			if (check == true)
				return 1;

			Property newProperty = new Property(ID, fee, period, address, c);

			theLandlord.registerProperty(newProperty);

			PreparedStatement pStat = myConn.prepareStatement(query);
			pStat.setInt(1, ID);
			pStat.setInt(2, fee);
			pStat.setInt(3, period);
			pStat.setString(4, address);
			pStat.setString(5, c.getType());
			pStat.setInt(6, c.getBed());
			pStat.setInt(7, c.getBath());
			pStat.setInt(8, c.getFurnished());
			pStat.setString(9, c.getQuad());

			pStat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 2;
	}

	public String getLandlordListings() {
		String list = "";
		System.out.println(theLandlord.getPropertyList().size());
		for (int i = 0; i < theLandlord.getPropertyList().size(); i++) {
			list += theLandlord.getPropertyList().get(i).getInfo();
		}
		System.out.println(list);
		return list;
	}

	public boolean pay(String id) {

		try {

			if (!id.matches("-?\\d+(\\.\\d+)?"))
				return false;

			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ensf480", "root",
					"centralasia");

			String query = "UPDATE listing SET state = ?, fname = ?, lname = ?, email = ? WHERE ID = ?";

			PreparedStatement pStat = myConn.prepareStatement(query);

			int ID = Integer.parseInt(id);
			for (int i = 0; i < theLandlord.getPropertyList().size(); i++) {
				if (theLandlord.getPropertyList().get(i).getID() == ID
						&& theLandlord.getPropertyList().get(i).getState() != "Active") {
					theLandlord.payFees(theLandlord.getPropertyList().get(i), theListings);
					pStat.setInt(5, ID);
					pStat.setInt(2, ID);
					pStat.setString(1, "Active");
					pStat.setString(2, theLandlord.getName().getFname());
					pStat.setString(3, theLandlord.getName().getLname());
					pStat.setString(4, theLandlord.getEmail());
					pStat.executeUpdate();

					return true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
