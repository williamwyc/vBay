package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Employee;
import model.Item;
import model.Post;

public class PostDao {

	
	public List<Item> getSalesReport(Post post) {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Each record is required to be encapsulated as a "Item" class object and added to the "items" List
		 * Query to get sales report for a particular month must be implemented
		 * post, which has details about the month and year for which the sales report is to be generated, is given as method parameter
		 * The month and year are in the format "month-year", e.g. "10-2018" and stored in the expireDate attribute of post object
		 * The month and year can be accessed by getter method, i.e., post.getExpireDate()
		 */

		List<Item> items = new ArrayList<Item>();
		
		String expMY[] = (post.getExpireDate()).split("-");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql.fall2018cse305.codyyarwood.com?useLegacyDatetimeCode=false&serverTimezone=America/New_York", "vbayadmin", "cse305rul3z");
			Statement state = con.createStatement();
			String sql = "SELECT DISTINCT I.Name, A.closingBid FROM vbaydb.Auction A, vbaydb.Post P, vbaydb.Bid B, vbaydb.Item I WHERE A.AuctionID = P.AuctionID AND MONTH(P.ExpireDate) = " + Integer.valueOf(expMY[0]) +" AND YEAR(P.ExpireDate) = " + Integer.valueOf(expMY[1]);
			ResultSet rs = state.executeQuery(sql);
			while(rs.next()) {
				Item item = new Item();
				item.setName(rs.getString("Name"));
				item.setSoldPrice(rs.getInt("ClosingBid"));
				items.add(item);
			}
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
				
		/*Sample data begins*/
//		for (int i = 0; i < 10; i++) {
//			Item item = new Item();
//			item.setName("Sample item");
//			item.setSoldPrice(100);
//			items.add(item);
//		}
		/*Sample data ends*/
		

		return items;
		
	}
}
