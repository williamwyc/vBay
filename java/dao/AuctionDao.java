package dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import model.Auction;
import model.Bid;
import model.Customer;
import model.Item;

public class AuctionDao {
//	public void createconnection(){
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection con = DriverManager.getConnection("jdbc:mysql://mysql.fall2018cse305.codyyarwood.com?useLegacyDatetimeCode=false&serverTimezone=America/New_York", "vbayadmin", "cse305rul3z");
//			Statement state = con.createStatement();
//			String sql = "SELECT * FROM vbaydb.Auction";
//			ResultSet rs = state.executeQuery(sql);
//			while(rs.next()) {
//				
//			}
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
	public List<Auction> getAllAuctions() {
		
		List<Auction> auctions = new ArrayList<Auction>();
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Each record is required to be encapsulated as a "Auction" class object and added to the "auctions" ArrayList
		 * Query to get data about all the auctions should be implemented
		 */
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql.fall2018cse305.codyyarwood.com?useLegacyDatetimeCode=false&serverTimezone=America/New_York", "vbayadmin", "cse305rul3z");
			Statement state = con.createStatement();
			String sql = "SELECT * FROM vbaydb.Auction";
			ResultSet rs = state.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("AuctionID");
				double increment = rs.getDouble("BidIncrement");
				double min = rs.getDouble("MinimumBid");
				int copies = rs.getInt("CopiesSold");
				int itemid = rs.getInt("ItemID");
				double closingbid = rs.getDouble("ClosingBid");
				double currentbid = rs.getDouble("CurrentBid");
				double currenthigh = rs.getDouble("CurrentHigh");
				double reserve = rs.getDouble("Reserve");
				Auction auction = new Auction();
				auction.setAuctionID(id);
				auction.setBidIncrement(increment);
				auction.setMinimumBid(min);
				auction.setCopiesSold(copies);
				auction.setItemID(itemid);
				auction.setClosingBid(closingbid);
				auction.setCurrentBid(currentbid);
				auction.setCurrentHighBid(currenthigh);
				auction.setReserve(reserve);
				auctions.add(auction);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return auctions;

	}

	public List<Auction> getAuctions(String customerID) {
		
		List<Auction> auctions = new ArrayList<Auction>();
		List<Integer> auctionsID = new ArrayList<Integer>();
		/*
		 * The students code to fetch data from the database will be written here
		 * Each record is required to be encapsulated as a "Auction" class object and added to the "auctions" ArrayList
		 * Query to get data about all the auctions in which a customer participated should be implemented
		 * customerID is the customer's primary key, given as method parameter
		 */
		try {
			int cusID = Integer.parseInt(customerID);
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql.fall2018cse305.codyyarwood.com?useLegacyDatetimeCode=false&serverTimezone=America/New_York", "vbayadmin", "cse305rul3z");
			Statement state = con.createStatement();
			String sql = "SELECT * FROM vbaydb.Bid";
			ResultSet rs = state.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("CustomerID");
				int aucID = rs.getInt("AuctionID");
				if(id == cusID && !auctionsID.contains(aucID)) {
					auctionsID.add(aucID);
				}
			}
			sql = "SELECT * FROM vbaydb.Auction";
			rs = state.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("AuctionID");
				double increment = rs.getDouble("BidIncrement");
				double min = rs.getDouble("MinimumBid");
				int copies = rs.getInt("CopiesSold");
				int itemid = rs.getInt("ItemID");
				double closingbid = rs.getDouble("ClosingBid");
				double currentbid = rs.getDouble("CurrentBid");
				double currenthigh = rs.getDouble("CurrentHigh");
				double reserve = rs.getDouble("Reserve");
				if(auctionsID.contains(id)) {
					Auction auction = new Auction();
					auction.setAuctionID(id);
					auction.setBidIncrement(increment);
					auction.setMinimumBid(min);
					auction.setCopiesSold(copies);
					auction.setItemID(itemid);
					auction.setClosingBid(closingbid);
					auction.setCurrentBid(currentbid);
					auction.setCurrentHighBid(currenthigh);
					auction.setReserve(reserve);
					auctions.add(auction);
				}
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		/*Sample data begins*/
//		for (int i = 0; i < 5; i++) {
//			Auction auction = new Auction();
//			auction.setAuctionID(1);
//			auction.setBidIncrement(new double(10));
//			auction.setMinimumBid(new double(10));
//			auction.setCopiesSold(12);
//			auction.setItemID(1234);
//			auction.setClosingBid(new double(10));
//			auction.setCurrentBid(new double(10));
//			auction.setCurrentHighBid(new double(10));
//			auction.setReserve(new double(10));
//			auctions.add(auction);
//		}
		/*Sample data ends*/
		
		return auctions;

	}

	public List<Auction> getOpenAuctions(String employeeEmail) {
		List<Auction> auctions = new ArrayList<Auction>();
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Each record is required to be encapsulated as a "Auction" class object and added to the "auctions" ArrayList
		 * Query to get data about all the open auctions monitored by a customer representative should be implemented
		 * employeeEmail is the email ID of the customer representative, which is given as method parameter
		 */
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql.fall2018cse305.codyyarwood.com?useLegacyDatetimeCode=false&serverTimezone=America/New_York", "vbayadmin", "cse305rul3z");
			Statement state = con.createStatement();
			String sql = "SELECT * FROM vbaydb.Auction WHERE Buyer is null";
			ResultSet rs = state.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("AuctionID");
				double increment = rs.getDouble("BidIncrement");
				double min = rs.getDouble("MinimumBid");
				int copies = rs.getInt("CopiesSold");
				int itemid = rs.getInt("ItemID");
				double closingbid = rs.getDouble("ClosingBid");
				double currentbid = rs.getDouble("CurrentBid");
				double currenthigh = rs.getDouble("CurrentHigh");
				double reserve = rs.getDouble("Reserve");
				Auction auction = new Auction();
				auction.setAuctionID(id);
				auction.setBidIncrement(increment);
				auction.setMinimumBid(min);
				auction.setCopiesSold(copies);
				auction.setItemID(itemid);
				auction.setClosingBid(closingbid);
				auction.setCurrentBid(currentbid);
				auction.setCurrentHighBid(currenthigh);
				auction.setReserve(reserve);
				auctions.add(auction);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return auctions;

		
		
	}

	public String recordSale(String auctionID) {
		/*
		 * The students code to update data in the database will be written here
		 * Query to record a sale, indicated by the auction ID, should be implemented
		 * auctionID is the Auction's ID, given as method parameter
		 * The method should return a "success" string if the update is successful, else return "failure"
		 */
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql.fall2018cse305.codyyarwood.com?useLegacyDatetimeCode=false&serverTimezone=America/New_York", "vbayadmin", "cse305rul3z");
			Statement state = con.createStatement();
			String sql = "SELECT * FROM vbaydb.Post WHERE AuctionID = "+ auctionID;
			ResultSet rs = state.executeQuery(sql);
			rs.next();
			Timestamp current = new Timestamp(System.currentTimeMillis());
			Timestamp auctionTime = rs.getTimestamp("ExpireDate");
			if(current.before(auctionTime)) {
				return "failure";
			}
			else {
				sql = "SELECT * FROM vbaydb.Auction WHERE AuctionID = "+auctionID;
				rs = state.executeQuery(sql);
				rs.next();
				double currentHigh = rs.getDouble("CurrentHigh");
				if(currentHigh>0) {
					sql = "SELECT * FROM vbaydb.Bid WHERE AuctionID = "+Integer.valueOf(auctionID)+"and BidPrice ="+currentHigh;
					rs = state.executeQuery(sql);
					rs.next();
					int buyer = rs.getInt("CustomerID");
					sql = "UPDATE vbaydb.Auction SET Buyer to "+Integer.valueOf(buyer)+" WHERE AuctionID = "+Integer.valueOf(auctionID);
					int i =state.executeUpdate(sql);
					if(i>0) {
						return "success";
					}
					else {
						return "failure";
					}
				}
			}
			return "failure";
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "failure";
	}

	public List getAuctionData(String auctionID, String itemID) {
		
		List output = new ArrayList();
		Item item = new Item();
		Bid bid = new Bid();
		Auction auction = new Auction();
		Customer customer = new Customer();
		
		/*
		 * The students code to fetch data from the database will be written here
		 * The item details are required to be encapsulated as a "Item" class object
		 * The bid details are required to be encapsulated as a "Bid" class object
		 * The auction details are required to be encapsulated as a "Auction" class object
		 * The customer details are required to be encapsulated as a "Customer" class object
		 * Query to get data about auction indicated by auctionID and itemID should be implemented
		 * auctionID is the Auction's ID, given as method parameter
		 * itemID is the Item's ID, given as method parameter
		 * The customer details must include details about the current winner of the auction
		 * The bid details must include details about the current highest bid
		 * The item details must include details about the item, indicated by itemID
		 * The auction details must include details about the item, indicated by auctionID
		 * All the objects must be added in the "output" list and returned
		 */
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql.fall2018cse305.codyyarwood.com?useLegacyDatetimeCode=false&serverTimezone=America/New_York", "vbayadmin", "cse305rul3z");
			Statement state = con.createStatement();
			String sql = "SELECT * FROM vbaydb.Auction A, vbaydb.Item I WHERE A.ItemID = I.ItemID and A.AuctionID = "+auctionID+" and I.ItemID = "+itemID;
			ResultSet rs = state.executeQuery(sql);
			rs.next();
			double currentHigh = rs.getDouble("CurrentHigh");
			item.setItemID(Integer.parseInt(itemID));
			item.setDescription(rs.getString("Description"));
			item.setType(rs.getString("Type"));
			item.setName(rs.getString("Name"));
			
			auction.setAuctionID(Integer.parseInt(auctionID));
			auction.setMinimumBid(rs.getDouble("MinimumBid"));
			auction.setBidIncrement(rs.getDouble("BidIncrement"));
			auction.setCurrentBid(rs.getDouble("CurrentBid"));
			auction.setCurrentHighBid(currentHigh);
			sql = "SELECT * FROM vbaydb.Bid B WHERE B.AuctionID = "+Integer.valueOf(auctionID) + " and B.BidPrice = "+currentHigh;
			rs = state.executeQuery(sql);
			int buyer=0;
			if (rs.next()) {
			buyer = rs.getInt("CustomerID");
			bid.setCustomerID(""+buyer);
			bid.setBidPrice(currentHigh);
			}
			
			sql = "SELECT * FROM vbaydb.Person WHERE SSN = "+buyer;
			if (rs.next()) {
			customer.setCustomerID(""+buyer);
			customer.setFirstName(rs.getString("Firstname"));
			customer.setLastName(rs.getString("Lastname"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		/*Sample data begins*/
		for (int i = 0; i < 4; i++) {
//			item.setItemID(123);
//			item.setDescription("sample description");
//			item.setType("BOOK");
//			item.setName("Sample Book");
//			
//			bid.setCustomerID("123-12-1234");
//			bid.setBidPrice(120);
//			
//			customer.setCustomerID("123-12-1234");
//			customer.setFirstName("Shiyong");
//			customer.setLastName("Lu");
//			
//			auction.setMinimumBid(100);
//			auction.setBidIncrement(10);
//			auction.setCurrentBid(110);
//			auction.setCurrentHighBid(115);
//			auction.setAuctionID(Integer.parseInt(auctionID));
		}
		/*Sample data ends*/
		
		output.add(item);
		output.add(bid);
		output.add(auction);
		output.add(customer);
		
		return output;

	}

	
}
