package model;

public class Auction {

	/*
	 * This class is a representation of the auction table in the database
	 * Each instance variable has a corresponding getter and setter
	 */

	private int auctionID;
	private double bidIncrement;
	private double minimumBid;
	private int copiesSold;
	private int monitor;
	private int itemID;
	private double closingBid;
	private double currentBid;
	private double currentHighBid;
	private double reserve;
	private int employeeID;
	public int getAuctionID() {
		return auctionID;
	}
	public void setAuctionID(int auctionID) {
		this.auctionID = auctionID;
	}
	public double getBidIncrement() {
		return bidIncrement;
	}
	public void setBidIncrement(double bidIncrement) {
		this.bidIncrement = bidIncrement;
	}
	public double getMinimumBid() {
		return minimumBid;
	}
	public void setMinimumBid(double minimumBid) {
		this.minimumBid = minimumBid;
	}
	public int getCopiesSold() {
		return copiesSold;
	}
	public void setCopiesSold(int copiesSold) {
		this.copiesSold = copiesSold;
	}
	public int getMonitor() {
		return monitor;
	}
	public void setMonitor(int monitor) {
		this.monitor = monitor;
	}
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	public double getClosingBid() {
		return closingBid;
	}
	public void setClosingBid(double closingBid) {
		this.closingBid = closingBid;
	}
	public double getCurrentBid() {
		return currentBid;
	}
	public void setCurrentBid(double currentBid) {
		this.currentBid = currentBid;
	}
	public double getCurrentHighBid() {
		return currentHighBid;
	}
	public void setCurrentHighBid(double currentHighBid) {
		this.currentHighBid = currentHighBid;
	}
	public double getReserve() {
		return reserve;
	}
	public void setReserve(double reserve) {
		this.reserve = reserve;
	}
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	
}
