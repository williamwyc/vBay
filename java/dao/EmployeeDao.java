package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Employee;

public class EmployeeDao {
	/*
	 * This class handles all the database operations related to the employee table
	 */
	
	public String addEmployee(Employee employee) {

		/*
		 * All the values of the add employee form are encapsulated in the employee object.
		 * These can be accessed by getter methods (see Employee class in model package).
		 * e.g. firstName can be accessed by employee.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database insertion of the employee details and return "success" or "failure" based on result of the database insertion.
		 */
		int id = Integer.parseInt(employee.getEmployeeID());
		String address = employee.getAddress()+", "+employee.getCity()+", "+employee.getState();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql.fall2018cse305.codyyarwood.com?useLegacyDatetimeCode=false&serverTimezone=America/New_York", "vbayadmin", "cse305rul3z");
			Statement state = con.createStatement();
			String sql1 = "INSERT INTO vbaydb.Person (SSN,LastName,FirstName,Address,ZipCode,Telephone,Email) "+
						"VALUES("+id+",\""+employee.getLastName()+"\",\""+employee.getFirstName()+"\",\""+address+"\","+Integer.valueOf(employee.getZipCode())+","+Long.parseLong(employee.getTelephone())+",\""+employee.getEmail()+"\");";
			String sql2 = "INSERT INTO vbaydb.Employee (EmployeeID,Level,StartDate,HourlyRate)"+
						"VALUES("+id+","+(employee.getLevel().equalsIgnoreCase("manager") ? 1 : 0)+","+java.sql.Date.valueOf(employee.getStartDate())+","+employee.getHourlyRate()+");";
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

	public String editEmployee(Employee employee) {
		/*
		 * All the values of the edit employee form are encapsulated in the employee object.
		 * These can be accessed by getter methods (see Employee class in model package).
		 * e.g. firstName can be accessed by employee.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database update and return "success" or "failure" based on result of the database update.
		 */
		int id = Integer.parseInt(employee.getEmployeeID());
		String address = employee.getAddress()+", "+employee.getCity()+", "+employee.getState();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql.fall2018cse305.codyyarwood.com?useLegacyDatetimeCode=false&serverTimezone=America/New_York", "vbayadmin", "cse305rul3z");
			Statement state = con.createStatement();
			String sql1 = "UPDATE vbaydb.Person SET LastName = \""+employee.getLastName()+
					"\", FirstName = \""+employee.getFirstName()+
					"\", Address = \""+address+
					"\" ,ZipCode ="+employee.getZipCode()+
					",Telephone = "+Long.parseLong(employee.getTelephone())+
					",Email = \""+employee.getEmail()+
					"\" WHERE SSN = "+ id+";";
			String sql2 = "UPDATE vbaydb.Employee SET Level = "+(employee.getLevel().equalsIgnoreCase("manager") ? 1 : 0)+
					", StartDate = "+java.sql.Date.valueOf(employee.getStartDate())+
					", HourlyRate = "+employee.getHourlyRate()+
					" WHERE EmployeeID = "+id+";";
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

	public String deleteEmployee(String employeeID) {
		/*
		 * employeeID, which is the Employee's ID which has to be deleted, is given as method parameter
		 * The sample code returns "success" by default.
		 * You need to handle the database deletion and return "success" or "failure" based on result of the database deletion.
		 */
		int id = Integer.parseInt(employeeID);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql.fall2018cse305.codyyarwood.com?useLegacyDatetimeCode=false&serverTimezone=America/New_York", "vbayadmin", "cse305rul3z");
			Statement state = con.createStatement();
			int i = state.executeUpdate("DELETE FROM vbaydb.Employee WHERE EmployeeID = "+id);
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

	
	public List<Employee> getEmployees() {

		/*
		 * The students code to fetch data from the database will be written here
		 * Query to return details about all the employees must be implemented
		 * Each record is required to be encapsulated as a "Employee" class object and added to the "employees" List
		 */

		List<Employee> employees = new ArrayList<Employee>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql.fall2018cse305.codyyarwood.com?useLegacyDatetimeCode=false&serverTimezone=America/New_York", "vbayadmin", "cse305rul3z");
			Statement state = con.createStatement();
			String sql = "SELECT * FROM vbaydb.Employee e, vbaydb.Person p "
					+ "WHERE p.SSN=e.EmployeeID";
			ResultSet rs = state.executeQuery(sql);
			while(rs.next()) {
				Employee employee = new Employee();
				employee.setEmployeeID(""+rs.getInt("SSN"));
				String[] address = rs.getString("Address").split(", ");
				employee.setAddress(address[0]);
				employee.setLastName(rs.getString("Lastname"));
				employee.setFirstName(rs.getString("Firstname"));
				employee.setCity(address[1]);
				employee.setState(address[2]);
				employee.setEmail(rs.getString("Email"));
				employee.setZipCode(rs.getInt("Zipcode"));
				employee.setStartDate(rs.getDate("StartDate").toString());
				employee.setHourlyRate(rs.getDouble("HourlyRate"));
				employees.add(employee);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		/*Sample data begins*/
//		for (int i = 0; i < 10; i++) {
//			Employee employee = new Employee();
//			employee.setEmail("shiyong@cs.sunysb.edu");
//			employee.setFirstName("Shiyong");
//			employee.setLastName("Lu");
//			employee.setAddress("123 Success Street");
//			employee.setCity("Stony Brook");
//			employee.setStartDate("2006-10-17");
//			employee.setState("NY");
//			employee.setZipCode(11790);
//			employee.setTelephone("5166328959");
//			employee.setEmployeeID("631-413-5555");
//			employee.setHourlyRate(100);
//			employees.add(employee);
//		}
		/*Sample data ends*/
		
		return employees;
	}

	public Employee getEmployee(String employeeID) {

		/*
		 * The students code to fetch data from the database based on "employeeID" will be written here
		 * employeeID, which is the Employee's ID who's details have to be fetched, is given as method parameter
		 * The record is required to be encapsulated as a "Employee" class object
		 */

		Employee employee = new Employee();
		int id = Integer.parseInt(employeeID);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql.fall2018cse305.codyyarwood.com?useLegacyDatetimeCode=false&serverTimezone=America/New_York", "vbayadmin", "cse305rul3z");
			Statement state = con.createStatement();
			String sql = "SELECT * FROM vbaydb.Employee e, vbaydb.Person p "
					+ "WHERE p.SSN=e.EmployeeID and e.EmployeeID = "+ id;
			ResultSet rs = state.executeQuery(sql);
			rs.next();
			employee.setEmployeeID(employeeID);
			String[] address = rs.getString("Address").split(", ");
			employee.setAddress(address[0]);
			employee.setLastName(rs.getString("Lastname"));
			employee.setFirstName(rs.getString("Firstname"));
			employee.setCity(address[1]);
			employee.setState(address[2]);
			employee.setEmail(rs.getString("Email"));
			employee.setZipCode(rs.getInt("Zipcode"));
			employee.setStartDate(rs.getDate("StartDate").toString());
			employee.setHourlyRate(rs.getDouble("HourlyRate"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		/*Sample data begins*/
//		employee.setEmail("shiyong@cs.sunysb.edu");
//		employee.setFirstName("Shiyong");
//		employee.setLastName("Lu");
//		employee.setAddress("123 Success Street");
//		employee.setCity("Stony Brook");
//		employee.setStartDate("2006-10-17");
//		employee.setState("NY");
//		employee.setZipCode(11790);
//		employee.setTelephone("5166328959");
//		employee.setEmployeeID("631-413-5555");
//		employee.setHourlyRate(100);
		/*Sample data ends*/
		
		return employee;
	}
	
	public Employee getHighestRevenueEmployee() {
		
		/*
		 * The students code to fetch employee data who generated the highest revenue will be written here
		 * The record is required to be encapsulated as a "Employee" class object
		 */
		
		Employee employee = new Employee();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql.fall2018cse305.codyyarwood.com?useLegacyDatetimeCode=false&serverTimezone=America/New_York", "vbayadmin", "cse305rul3z");
			Statement state = con.createStatement();
			String sql ="SELECT EmployeeID, SUM(CurrentBid) AS SUM\r\n" + 
					"        FROM (SELECT E.EmployeeID, A.CurrentBid\r\n" + 
					"				FROM vbaydb.Employee E, vbaydb.Auction A\r\n" + 
					"				WHERE E.EmployeeID = A.Monitor\r\n" + 
					"			) AS Em\r\n" + 
					"		GROUP BY EmployeeID\r\n" + 
					"        ORDER BY SUM desc\r\n" + 
					"        LIMIT 1\r\n" + 
					"        ;";
			ResultSet rs = state.executeQuery(sql);
			rs.next();
			int employeeID = rs.getInt("EmployeeID");
			sql = "SELECT * FROM vbaydb.Person WHERE SSN ="+employeeID;
			rs = state.executeQuery(sql);
			rs.next();
			employee.setEmployeeID(""+employeeID);
			employee.setLastName(rs.getString("LastName"));
			employee.setFirstName(rs.getString("FirstName"));
			employee.setEmail(rs.getString("Email"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		/*Sample data begins*/
//		employee.setEmail("shiyong@cs.sunysb.edu");
//		employee.setFirstName("Shiyong");
//		employee.setLastName("Lu");
//		employee.setEmployeeID("631-413-5555");
		/*Sample data ends*/
		
		return employee;
	}

	public String getEmployeeID(String username) {
		/*
		 * The students code to fetch data from the database based on "username" will be written here
		 * username, which is the Employee's email address who's Employee ID has to be fetched, is given as method parameter
		 * The Employee ID is required to be returned as a String
		 */
		int id = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql.fall2018cse305.codyyarwood.com?useLegacyDatetimeCode=false&serverTimezone=America/New_York", "vbayadmin", "cse305rul3z");
			Statement state = con.createStatement();
			String sql = "SELECT * FROM vbaydb.Employee e, vbaydb.Person p "
					+ "WHERE p.SSN=e.EmployeeID and p.Email = \""+username+"\"";
			ResultSet rs = state.executeQuery(sql);
			rs.next();
			id = rs.getInt("EmployeeID");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ""+id;
	}

}
