package dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.Bid;
import model.Customer;
import model.Item;

public class BidDao {

	public List<Bid> getBidHistory(String auctionID) {
		
		List<Bid> bids = new ArrayList<Bid>();

		/*
		 * The students code to fetch data from the database
		 * Each record is required to be encapsulated as a "Bid" class object and added to the "bids" ArrayList
		 * auctionID, which is the Auction's ID, is given as method parameter
		 * Query to get the bid history of an auction, indicated by auctionID, must be implemented
		 */
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql.fall2018cse305.codyyarwood.com?useLegacyDatetimeCode=false&serverTimezone=America/New_York", "vbayadmin", "cse305rul3z");
			Statement state = con.createStatement();
			String sql = "SELECT * FROM vbaydb.Bid B WHERE B.AuctionId = " + Integer.valueOf(auctionID) + " ORDER BY B.BidTime asc";
			ResultSet rs = state.executeQuery(sql);
			while(rs.next()) {
				Bid bid = new Bid();
				bid.setAuctionID(rs.getInt("AuctionId"));
				bid.setCustomerID(rs.getString("CustomerId"));
				bid.setBidTime(rs.getDate("BidTime").toString());
				bid.setBidPrice(rs.getDouble("BidPrice"));
				bids.add(bid);	
			}
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}

		/*Sample data begins*/
//		for (int i = 0; i < 10; i++) {
//			Bid bid = new Bid();
//			bid.setAuctionID(123);
//			bid.setCustomerID("123-12-1234");
//			bid.setBidTime("2008-12-11");
//			bid.setBidPrice(100);
//			bids.add(bid);			
//		}
		/*Sample data ends*/
		
		return bids;
	}

	public List<Bid> getAuctionHistory(String customerID) {
		
		List<Bid> bids = new ArrayList<Bid>();

		/*
		 * The students code to fetch data from the database
		 * Each record is required to be encapsulated as a "Bid" class object and added to the "bids" ArrayList
		 * customerID, which is the Customer's ID, is given as method parameter
		 * Query to get the bid history of all the auctions in which a customer participated, indicated by customerID, must be implemented
		 */
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql.fall2018cse305.codyyarwood.com?useLegacyDatetimeCode=false&serverTimezone=America/New_York", "vbayadmin", "cse305rul3z");
			Statement state = con.createStatement();
			String sql = "SELECT * FROM vbaydb.Bid B WHERE B.CustomerId = " + Integer.valueOf(customerID) + " ORDER BY B.BidTime asc";
			ResultSet rs = state.executeQuery(sql);
			while(rs.next()) {
				Bid bid = new Bid();
				bid.setAuctionID(rs.getInt("AuctionId"));
				bid.setCustomerID(rs.getString("CustomerId"));
				bid.setBidTime(rs.getDate("BidTime").toString());
				bid.setBidPrice(rs.getDouble("BidPrice"));
				bids.add(bid);	
			}
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}

		/*Sample data begins*/
//		for (int i = 0; i < 10; i++) {
//			Bid bid = new Bid();
//			bid.setAuctionID(123);
//			bid.setCustomerID("123-12-1234");
//			bid.setBidTime("2008-12-11");
//			bid.setBidPrice(100);
//			bids.add(bid);			
//		}
		/*Sample data ends*/
		
		return bids;
	}

	public Bid submitBid(String auctionID, String itemID, Float currentBid, Float maxBid, String customerID) {
		
		Bid bid = new Bid();

		/*
		 * The students code to insert data in the database
		 * auctionID, which is the Auction's ID for which the bid is submitted, is given as method parameter
		 * itemID, which is the Item's ID for which the bid is submitted, is given as method parameter
		 * currentBid, which is the Customer's current bid, is given as method parameter
		 * maxBid, which is the Customer's maximum bid for the item, is given as method parameter
		 * customerID, which is the Customer's ID, is given as method parameter
		 * Query to submit a bid by a customer, indicated by customerID, must be implemented
		 * After inserting the bid data, return the bid details encapsulated in "bid" object
		 */
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql.fall2018cse305.codyyarwood.com?useLegacyDatetimeCode=false&serverTimezone=America/New_York", "vbayadmin", "cse305rul3z");
			Statement state = con.createStatement();
			Statement state2 = con.createStatement();
			String sql = "SELECT DISTINCT * FROM vbaydb.Bid B, vbaydb.Auction A WHERE A.AuctionID = B.AuctionID";
			ResultSet rs = state.executeQuery(sql);

			//Proxy Bidding
			while(rs.next()) {
				if(rs.getDouble("MaxBid") >= currentBid)
					while(currentBid < maxBid) {
						currentBid = (float) Math.min(maxBid, currentBid+rs.getDouble("BidIncrement"));
						if(rs.getDouble("MaxBid") < currentBid)
							break;
					}
				//If this bid still doesn't exceed the maxbid -> update the current winner
				if(rs.getDouble("MaxBid") >= currentBid) {
					sql = "UPDATE vbaydb.Bid B SET B.BidPrice = " + currentBid + " WHERE B.AuctionID = " + rs.getInt("AuctionID") + " AND B.CustomerID = " + rs.getInt("CustomerID") + " AND B.BidTime = \"" + rs.getDate("BidTime") +" "+rs.getTime("BidTime") + "\"";
					state2.executeUpdate(sql);		
				}
			}

			bid.setAuctionID(Integer.valueOf(auctionID));
			bid.setCustomerID(customerID);
			bid.setBidPrice(currentBid);
			bid.setMaxBid(maxBid);
			bid.setBidTime(LocalDateTime.now().getYear() + ":" + LocalDateTime.now().getMonthValue() + ":" + LocalDateTime.now().getDayOfMonth() + " " + LocalDateTime.now().getHour() + ":" 
			+ LocalDateTime.now().getMinute() + ":00");
			
			//AddBid
			sql = "INSERT INTO vbaydb.Bid(CustomerID, AuctionID, BidTime, BidPrice, MaxBid) VALUES("+ Integer.valueOf(bid.getCustomerID()) + ", " + Integer.valueOf(bid.getAuctionID()) + ", \"" + bid.getBidTime() + "\", " + bid.getBidPrice() + ", " + bid.getMaxBid() + ")";
			state.executeUpdate(sql);
			
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return bid;
	}

	public List<Bid> getSalesListing(String searchKeyword) {
		
		List<Bid> bids = new ArrayList<Bid>();

		/*
		 * The students code to fetch data from the database
		 * Each record is required to be encapsulated as a "Bid" class object and added to the "bids" ArrayList
		 * searchKeyword, which is the search parameter, is given as method parameter
		 * Query to  produce a list of sales by item name or by customer name must be implemented
		 * The item name or the customer name can be searched with the provided searchKeyword
		 */
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql.fall2018cse305.codyyarwood.com?useLegacyDatetimeCode=false&serverTimezone=America/New_York", "vbayadmin", "cse305rul3z");
			Statement state = con.createStatement();
			String sql = "SELECT DISTINCT B.* FROM vbaydb.Bid B, vbaydb.Auction A, vbaydb.Item I, vbaydb.Person C WHERE (B.CustomerID = C.SSN AND (C.FirstName LIKE \"" + searchKeyword + "\") OR (C.LastName LIKE \"" + searchKeyword + "\")) OR (A.ItemID = I.ItemID AND I.Name = \"" + searchKeyword + "\") ORDER BY B.BidTime asc";
			ResultSet rs = state.executeQuery(sql);
			while(rs.next()) {
				Bid bid = new Bid();
				bid.setAuctionID(rs.getInt("AuctionId"));
				bid.setCustomerID(rs.getString("CustomerId"));
				bid.setBidTime(rs.getDate("BidTime").toString());
				bid.setBidPrice(rs.getDouble("BidPrice"));
				bids.add(bid);	
			}
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}

		/*Sample data begins*/
//		for (int i = 0; i < 10; i++) {
//			Bid bid = new Bid();
//			bid.setAuctionID(123);
//			bid.setCustomerID("123-12-1234");
//			bid.setBidTime("2008-12-11");
//			bid.setBidPrice(100);
//			bids.add(bid);			
//		}
		/*Sample data ends*/
		
		return bids;
	}

}
