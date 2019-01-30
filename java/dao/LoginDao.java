package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import model.Bid;
import model.Login;

public class LoginDao {
	/*
	 * This class handles all the database operations related to login functionality
	 */
	
	private static List<Login> logins;
	
	
	public Login login(String username, String password) {
		/*
		 * Return a Login object with role as "manager", "customerRepresentative" or "customer" if successful login
		 * Else, return null
		 * The role depends on the type of the user, which has to be handled in the database
		 * username, which is the email address of the user, is given as method parameter
		 * password, which is the password of the user, is given as method parameter
		 * Query to verify the username and password and fetch the role of the user, must be implemented
		 * # No password information was required in previous assignments. Password verification left out for debugging ease.
		 */
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql.fall2018cse305.codyyarwood.com?useLegacyDatetimeCode=false&serverTimezone=America/New_York", "vbayadmin", "cse305rul3z");
			Statement state = con.createStatement();
			String sql = "SELECT * FROM vbaydb.Person P WHERE P.Email= \"" + username + "\"";
			ResultSet rs = state.executeQuery(sql);
			int ssn = 0;
			if(rs.next()) {
				ssn = rs.getInt("SSN");
				
				Login login = new Login();
				login.setUsername(username);
				
				sql = "SELECT * FROM vbaydb.Customer C WHERE C.CustomerID= " + ssn;
				ResultSet rs1 = state.executeQuery(sql);
				
				if(rs1.next()) {
					login.setRole("customer");
				}
				else {
					sql = "SELECT * FROM vbaydb.Employee E WHERE E.EmployeeID= " + ssn;
					rs1 = state.executeQuery(sql);
					rs1.next();
					login.setRole(rs1.getInt("Level") == 1 ? "manager" : "customerRepresentative");
					
				}
				
				return login;
			}
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
//		//Temporary change role code
//		Login login = new Login();
//		if(username.compareToIgnoreCase("manager") == 0)
//			login.setRole("manager");
//		else if (username.compareToIgnoreCase("CustRep") == 0)
//			login.setRole("customerRepresentative");
//		else
//			login.setRole("customer");
//		
//		
//		return login;
		
	}
	
	public String addUser(Login login) {
		/*
		 * Query to insert a new record for user login must be implemented
		 * login, which is the "Login" Class object containing username and password for the new user, is given as method parameter
		 * The username and password from login can get accessed using getter methods in the "Login" model
		 * e.g. getUsername() method will return the username encapsulated in login object
		 * Return "success" on successful insertion of a new user
		 * Return "failure" for an unsuccessful database operation
		 */
		
		//logins.add(login);
		
		return "success";
	}

}
