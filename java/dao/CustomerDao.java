package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Customer;

public class CustomerDao {
	/*
	 * This class handles all the database operations related to the customer table
	 */
	
	/**
	 * @param String searchKeyword
	 * @return ArrayList<Customer> object
	 */
	public List<Customer> getCustomers(String searchKeyword) {
		/*
		 * This method fetches one or more customers based on the searchKeyword and returns it as an ArrayList
		 */
		
		List<Customer> customers = new ArrayList<Customer>();

		/*
		 * The students code to fetch data from the database based on searchKeyword will be written here
		 * Each record is required to be encapsulated as a "Customer" class object and added to the "customers" List
		 */
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql.fall2018cse305.codyyarwood.com?useLegacyDatetimeCode=false&serverTimezone=America/New_York", "vbayadmin", "cse305rul3z");
			Statement state = con.createStatement();
			String sql = "SELECT * FROM vbaydb.Customer c, vbaydb.Person p "
					+ "WHERE p.SSN=c.CustomerID";
			ResultSet rs = state.executeQuery(sql);
			while(rs.next()) {
				Customer customer = new Customer();
				customer.setCustomerID(""+rs.getInt("SSN"));
				String[] address = rs.getString("Address").split(", ");
				customer.setAddress(address[0]);
				customer.setLastName(rs.getString("Lastname"));
				customer.setFirstName(rs.getString("Firstname"));
				customer.setCity(address[1]);
				customer.setState(address[2]);
				customer.setEmail(rs.getString("Email"));
				customer.setZipCode(rs.getInt("Zipcode"));
				customer.setTelephone(""+rs.getLong("Telephone"));
				customer.setCreditCard(""+rs.getLong("CreditCardNum"));
				customer.setRating(rs.getInt("Rating"));
				if(searchKeyword==null) {
					customers.add(customer);
				}
				else {
					if(customer.getFirstName().toLowerCase().contains(searchKeyword.toLowerCase())||customer.getLastName().toLowerCase().contains(searchKeyword.toLowerCase())) {
						customers.add(customer);
					}
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return customers;
	}


	public Customer getHighestRevenueCustomer() {
		/*
		 * This method fetches the customer who generated the highest total revenue and returns it
		 * The students code to fetch data from the database will be written here
		 * The customer record is required to be encapsulated as a "Customer" class object
		 */

		Customer customer = new Customer();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql.fall2018cse305.codyyarwood.com?useLegacyDatetimeCode=false&serverTimezone=America/New_York", "vbayadmin", "cse305rul3z");
			Statement state = con.createStatement();
			String sql ="SELECT CustomerID, SUM(CurrentBid) AS SUM\r\n" + 
					"        FROM (SELECT C.CustomerID, A.CurrentBid\r\n" + 
					"				FROM vbaydb.Customer C, vbaydb.Auction A, vbaydb.Post P\r\n" + 
					"				WHERE P.CustomerID = C.CustomerID and P.AuctionID = A.AuctionID\r\n" + 
					"			) AS Cu\r\n" + 
					"		GROUP BY CustomerID\r\n" + 
					"        ORDER BY SUM desc\r\n" + 
					"        LIMIT 1\r\n" + 
					"        ;";
			ResultSet rs = state.executeQuery(sql);
			rs.next();
			int customerID = rs.getInt("CustomerID");
			sql = "SELECT * FROM vbaydb.Person WHERE SSN ="+customerID;
			rs = state.executeQuery(sql);
			rs.next();
			customer.setCustomerID(""+customerID);
			customer.setLastName(rs.getString("LastName"));
			customer.setFirstName(rs.getString("FirstName"));
			customer.setEmail(rs.getString("Email"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return customer;
		
	}

	public List<Customer> getCustomerMailingList() {

		/*
		 * This method fetches the all customer mailing details and returns it
		 * The students code to fetch data from the database will be written here
		 * Each customer record is required to be encapsulated as a "Customer" class object and added to the "customers" List
		 */

		
		List<Customer> customers = new ArrayList<Customer>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql.fall2018cse305.codyyarwood.com?useLegacyDatetimeCode=false&serverTimezone=America/New_York", "vbayadmin", "cse305rul3z");
			Statement state = con.createStatement();
			String sql = "SELECT * FROM vbaydb.Customer c, vbaydb.Person p "
					+ "WHERE p.SSN=c.CustomerID";
			ResultSet rs = state.executeQuery(sql);
			while(rs.next()) {
				Customer customer = new Customer();
				customer.setCustomerID(""+rs.getInt("SSN"));
				String[] address = rs.getString("Address").split(", ");
				customer.setAddress(address[0]);
				customer.setLastName(rs.getString("Lastname"));
				customer.setFirstName(rs.getString("Firstname"));
				customer.setCity(address[1]);
				customer.setState(address[2]);
				customer.setEmail(rs.getString("Email"));
				customer.setZipCode(rs.getInt("Zipcode"));
				customer.setTelephone(""+rs.getLong("Telephone"));
				customers.add(customer);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return customers;
	}

	public Customer getCustomer(String customerID) {

		/*
		 * This method fetches the customer details and returns it
		 * customerID, which is the Customer's ID who's details have to be fetched, is given as method parameter
		 * The students code to fetch data from the database will be written here
		 * The customer record is required to be encapsulated as a "Customer" class object
		 */
		Customer customer = new Customer();
		int id = Integer.parseInt(customerID);
		int pos = 0, pos2 = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql.fall2018cse305.codyyarwood.com?useLegacyDatetimeCode=false&serverTimezone=America/New_York", "vbayadmin", "cse305rul3z");
			Statement state = con.createStatement();
			String sql = "SELECT * FROM vbaydb.Customer c, vbaydb.Person p "
					+ "WHERE p.SSN=c.CustomerID and p.SSN="+id;
			ResultSet rs = state.executeQuery(sql);
			while(rs.next()) {
				customer.setCustomerID(""+rs.getInt("SSN"));
				String[] address =  rs.getString("Address").split(", ");
				customer.setAddress(address[0]);
				System.out.println(address[0]);
				System.out.println(address[1]);
				System.out.println(address[2]);
				customer.setLastName(rs.getString("Lastname"));
				customer.setFirstName(rs.getString("Firstname"));
				customer.setCity(address[1]);
				customer.setState(address[2]);
				customer.setEmail(rs.getString("Email"));
				customer.setZipCode(rs.getInt("Zipcode"));
				customer.setTelephone(""+rs.getLong("Telephone"));
				customer.setCreditCard(""+rs.getLong("CreditCardNum"));
				customer.setRating(rs.getInt("Rating"));
				break;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customer;
		/*Sample data begins*/
//		Customer customer = new Customer();
//		customer.setCustomerID("111-11-1111");
//		customer.setAddress("123 Success Street");
//		customer.setLastName("Lu");
//		customer.setFirstName("Shiyong");
//		customer.setCity("Stony Brook");
//		customer.setState("NY");
//		customer.setEmail("shiyong@cs.sunysb.edu");
//		customer.setZipCode(11790);
//		customer.setTelephone("5166328959");
//		customer.setCreditCard("1234567812345678");
//		customer.setRating(1);
		/*Sample data ends*/
		
	}
	
	public String deleteCustomer(String customerID) {

		/*
		 * This method deletes a customer returns "success" string on success, else returns "failure"
		 * The students code to delete the data from the database will be written here
		 * customerID, which is the Customer's ID who's details have to be deleted, is given as method parameter
		 */
		int id = Integer.parseInt(customerID);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql.fall2018cse305.codyyarwood.com?useLegacyDatetimeCode=false&serverTimezone=America/New_York", "vbayadmin", "cse305rul3z");
			Statement state = con.createStatement();
			int i = state.executeUpdate("DELETE FROM vbaydb.Customer WHERE CustomerID = "+id);
			int j = state.executeUpdate("DELETE FROM vbaydb.Person WHERE SSN = "+id);
	        if(i>0&&j>0){
	              return "success";
	        }
	        else{
	             return "failure";

	        }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "failure";
		
	}


	public String getCustomerID(String username) {
		/*
		 * This method returns the Customer's ID based on the provided email address
		 * The students code to fetch data from the database will be written here
		 * username, which is the email address of the customer, who's ID has to be returned, is given as method parameter
		 * The Customer's ID is required to be returned as a String
		 */
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql.fall2018cse305.codyyarwood.com?useLegacyDatetimeCode=false&serverTimezone=America/New_York", "vbayadmin", "cse305rul3z");
			Statement state = con.createStatement();
			String sql = "SELECT * FROM vbaydb.Customer c, vbaydb.Person p "
					+ "WHERE p.SSN=c.CustomerID and p.Email=\""+username+"\"";
			ResultSet rs = state.executeQuery(sql);
			while(rs.next()) {
				return ""+rs.getInt("SSN");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}


	public List<Customer> getSellers() {
		
		/*
		 * This method fetches the all seller details and returns it
		 * The students code to fetch data from the database will be written here
		 * The seller (which is a customer) record is required to be encapsulated as a "Customer" class object and added to the "customers" List
		 */

		List<Customer> customers = new ArrayList<Customer>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql.fall2018cse305.codyyarwood.com?useLegacyDatetimeCode=false&serverTimezone=America/New_York", "vbayadmin", "cse305rul3z");
			Statement state = con.createStatement();
			String sql = "SELECT DISTINCT C.*, p.* FROM vbaydb.Customer C, vbaydb.Person p, vbaydb.Post ps "
					+ "WHERE p.SSN=C.CustomerID and ps.CustomerID = C.CustomerID";
			ResultSet rs = state.executeQuery(sql);
			while(rs.next()) {
				Customer customer = new Customer();
				customer.setCustomerID(""+rs.getInt("SSN"));
				String[] address = rs.getString("Address").split(",[ ]*");
				customer.setAddress(address[0]);
				customer.setLastName(rs.getString("Lastname"));
				customer.setFirstName(rs.getString("Firstname"));
				customer.setCity(address[1]);
				customer.setState(address[2]);
				customer.setEmail(rs.getString("Email"));
				customer.setZipCode(rs.getInt("Zipcode"));
				customer.setTelephone(""+rs.getLong("Telephone"));
				customers.add(customer);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		/*Sample data begins*/
//		for (int i = 0; i < 10; i++) {
//			Customer customer = new Customer();
//			customer.setCustomerID("111-11-1111");
//			customer.setAddress("123 Success Street");
//			customer.setLastName("Lu");
//			customer.setFirstName("Shiyong");
//			customer.setCity("Stony Brook");
//			customer.setState("NY");
//			customer.setEmail("shiyong@cs.sunysb.edu");
//			customer.setZipCode(11790);
//			customers.add(customer);			
//		}
		/*Sample data ends*/
		
		return customers;

	}


	public String addCustomer(Customer customer) {

		/*
		 * All the values of the add customer form are encapsulated in the customer object.
		 * These can be accessed by getter methods (see Customer class in model package).
		 * e.g. firstName can be accessed by customer.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database insertion of the customer details and return "success" or "failure" based on result of the database insertion.
		 */
		int id = Integer.parseInt(customer.getCustomerID());
		String address = customer.getAddress()+", "+customer.getCity()+", "+customer.getState();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql.fall2018cse305.codyyarwood.com?useLegacyDatetimeCode=false&serverTimezone=America/New_York", "vbayadmin", "cse305rul3z");
			Statement state = con.createStatement();
			String sql1 = "INSERT INTO vbaydb.Person (SSN,LastName,FirstName,Address,ZipCode,Telephone,Email) "+
						"VALUES("+id+",\""+customer.getLastName()+"\",\""+customer.getFirstName()+"\",\""+address+"\","+Integer.valueOf(customer.getZipCode())+","+Long.parseLong(customer.getTelephone())+",\""+customer.getEmail()+"\");";
			String sql2 = "INSERT INTO vbaydb.Customer (CustomerID,CreditCardNum,Rating)"+
						"VALUES("+id+","+Long.valueOf(customer.getCreditCard())+","+customer.getRating()+");";
			int i = state.executeUpdate(sql1);
			int j = state.executeUpdate(sql2);
	        if(i>0&&j>0){
	              return "success";
	        }
	        else{
	             return "failure";

	        }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "failure";

	}

	public String editCustomer(Customer customer) {
		/*
		 * All the values of the edit customer form are encapsulated in the customer object.
		 * These can be accessed by getter methods (see Customer class in model package).
		 * e.g. firstName can be accessed by customer.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database update and return "success" or "failure" based on result of the database update.
		 */
		int id = Integer.valueOf(customer.getCustomerID());
		String address = customer.getAddress()+", "+customer.getCity()+", "+customer.getState();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql.fall2018cse305.codyyarwood.com?useLegacyDatetimeCode=false&serverTimezone=America/New_York", "vbayadmin", "cse305rul3z");
			Statement state = con.createStatement();
			String sql1 = "UPDATE vbaydb.Person SET LastName = \""+customer.getLastName()+
					"\", FirstName = \""+customer.getFirstName()+
					"\", Address = \""+address+
					"\",ZipCode = "+customer.getZipCode()+
					",Telephone = "+Long.valueOf(customer.getTelephone())+
					",Email = \""+customer.getEmail()+
					"\" WHERE SSN = "+ id+";";
			String sql2 = "UPDATE vbaydb.Customer SET CreditCardNum = "+customer.getCreditCard()+
					", Rating = "+customer.getRating()+
					" WHERE CustomerID = "+id+";";
			int i = state.executeUpdate(sql1);
			int j = state.executeUpdate(sql2);
	        if(i>0&&j>0){
	              return "success";
	        }
	        else{
	             return "failure";

	        }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		/*Sample data begins*/
		return "failure";
		/*Sample data ends*/

	}

}
