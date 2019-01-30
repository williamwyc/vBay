package model;

public class Bid {
	
	/*
	 * This class is a representation of the bid table in the database
	 * Each instance variable has a corresponding getter and setter
	 */

	private String customerID;
	private int auctionID;
	private String bidTime;
	private double bidPrice;
	private double maxBid;
	public double getMaxBid() {
		return maxBid;
	}
	public void setMaxBid(double maxBid) {
		this.maxBid = maxBid;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public int getAuctionID() {
		return auctionID;
	}
	public void setAuctionID(int auctionID) {
		this.auctionID = auctionID;
	}
	public String getBidTime() {
		return bidTime;
	}
	public void setBidTime(String bidTime) {
		this.bidTime = bidTime;
	}
	public double getBidPrice() {
		return bidPrice;
	}
	public void setBidPrice(double currentHigh) {
		this.bidPrice = currentHigh;
	}
	

}
