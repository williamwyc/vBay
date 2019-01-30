-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: mysql.fall2018cse305.codyyarwood.com
-- Generation Time: Dec 10, 2018 at 12:21 PM
-- Server version: 5.6.34-log
-- PHP Version: 7.1.22

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `vbaydb`
--

DELIMITER $$
--
-- Procedures
--
$$

$$

$$

$$

$$

$$

$$

$$

$$

$$

$$

$$

$$

$$

$$

$$

$$

$$

$$

$$

$$

$$

$$

$$

$$

$$

$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `Auction`
--

CREATE TABLE `Auction` (
  `AuctionID` int(9) NOT NULL DEFAULT '0',
  `BidIncrement` double DEFAULT NULL,
  `MinimumBid` double DEFAULT NULL,
  `Reserve` double DEFAULT NULL,
  `CurrentBid` double DEFAULT NULL,
  `Buyer` int(9) DEFAULT NULL,
  `Monitor` int(9) NOT NULL,
  `ItemID` int(9) NOT NULL,
  `CurrentHigh` double DEFAULT NULL,
  `ClosingBid` double DEFAULT NULL,
  `CopiesSold` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Auction`
--

INSERT INTO `Auction` (`AuctionID`, `BidIncrement`, `MinimumBid`, `Reserve`, `CurrentBid`, `Buyer`, `Monitor`, `ItemID`, `CurrentHigh`, `ClosingBid`, `CopiesSold`) VALUES
(101, 1, 5, 10, 11, 111111111, 123456789, 100000001, 15, 11, 1),
(102, 10, 1000, 2000, NULL, NULL, 123456789, 100000002, 0, 0, 0),
(103, 1, 5, 10, NULL, NULL, 123456789, 100000001, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `Bid`
--

CREATE TABLE `Bid` (
  `CustomerID` int(9) NOT NULL DEFAULT '0',
  `AuctionID` int(9) NOT NULL DEFAULT '0',
  `BidTime` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `BidPrice` double DEFAULT NULL,
  `MaxBid` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Bid`
--

INSERT INTO `Bid` (`CustomerID`, `AuctionID`, `BidTime`, `BidPrice`, `MaxBid`) VALUES
(111111111, 101, '2018-10-24 10:18:00', 10, 10),
(111111111, 101, '2018-10-24 10:20:42', 15, 15),
(222222222, 101, '2018-10-24 10:15:00', 10, 10),
(666666666, 103, '2018-10-25 09:30:37', 20, 20),
(666666666, 103, '2018-12-10 11:59:00', 20, 20);

-- --------------------------------------------------------

--
-- Table structure for table `Customer`
--

CREATE TABLE `Customer` (
  `Rating` int(2) DEFAULT NULL,
  `CreditCardNum` bigint(16) DEFAULT NULL,
  `ItemsSold` int(7) UNSIGNED NOT NULL DEFAULT '0',
  `ItemsBought` int(7) UNSIGNED NOT NULL DEFAULT '0',
  `CustomerID` int(9) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Customer`
--

INSERT INTO `Customer` (`Rating`, `CreditCardNum`, `ItemsSold`, `ItemsBought`, `CustomerID`) VALUES
(1, 1234567812345678, 0, 0, 111111111),
(2, 1234323456789876, 0, 0, 123456788),
(1, 5678123456781234, 0, 0, 222222222),
(1, 2345678923456789, 0, 0, 333333333),
(1, 6789234567892345, 0, 0, 444444444),
(4, 1234123412341234, 0, 0, 666666666);

-- --------------------------------------------------------

--
-- Table structure for table `Employee`
--

CREATE TABLE `Employee` (
  `StartDate` date DEFAULT NULL,
  `HourlyRate` decimal(4,2) UNSIGNED NOT NULL DEFAULT '10.00',
  `Level` int(1) UNSIGNED NOT NULL DEFAULT '0' COMMENT '0 = Employee, 1 = Manager, 2-9 = Undefined',
  `EmployeeID` int(9) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Employee`
--

INSERT INTO `Employee` (`StartDate`, `HourlyRate`, `Level`, `EmployeeID`) VALUES
('1998-11-01', '60.00', 0, 123456789),
('1998-06-02', '50.00', 0, 321688568),
('1999-02-02', '50.00', 1, 789123456);

-- --------------------------------------------------------

--
-- Table structure for table `Item`
--

CREATE TABLE `Item` (
  `ItemID` int(9) NOT NULL DEFAULT '0',
  `Description` text,
  `Name` varchar(255) DEFAULT NULL,
  `Type` varchar(32) DEFAULT NULL,
  `Year` year(4) NOT NULL DEFAULT '2000',
  `NumSold` int(9) NOT NULL DEFAULT '0',
  `NumAvailable` int(9) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Item`
--

INSERT INTO `Item` (`ItemID`, `Description`, `Name`, `Type`, `Year`, `NumSold`, `NumAvailable`) VALUES
(100000001, 'Wonderful classic movie dramatizing a horrible tragedy.', 'Titanic', 'DVD', 2005, 1, 3),
(100000002, 'Reliable car that costs less than the front row seats at Wrestle-mania.', 'Nissan Sentra', 'CAR', 2007, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `Person`
--

CREATE TABLE `Person` (
  `SSN` int(9) NOT NULL DEFAULT '0',
  `Username` varchar(9) NOT NULL DEFAULT '0',
  `LastName` varchar(20) NOT NULL,
  `FirstName` varchar(20) NOT NULL,
  `Address` varchar(255) DEFAULT NULL,
  `ZipCode` int(9) DEFAULT NULL,
  `Telephone` bigint(10) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Person`
--

INSERT INTO `Person` (`SSN`, `Username`, `LastName`, `FirstName`, `Address`, `ZipCode`, `Telephone`, `Email`) VALUES
(111111111, 'shiyong', 'Lu', 'Shiyong', '123 Success Street, Stony Brook, NY', 11790, 5166328959, 'shiyong@cs.sunysb.edu'),
(123456788, '0', 'sdsdss', 'sdss', '123 freedom rd, new york, NY', 11111, 1234567890, 'sdsd@sddsd'),
(123456789, 'smith', 'Smith', 'David', '123 College Road, Stony Brook, NY', 11790, 5162152345, 'dave@davesmith.com'),
(222222222, 'haixia', 'Du', 'Haixia', '456 Fortune Road, Stony Brook, NY', 11790, 5166324360, 'dhaixia@cs.sunysb.edu'),
(321688568, 'bjeans', 'BlueJeans', 'Barry', '15 Tangent Road, New York, NY', 11011, 7063218745, 'bbluejeans@taz.com'),
(333333333, 'john', 'Smith', 'John', '789 Peace Blvd., Los Angeles, CA', 12345, 4124434321, 'shlu@ic.sunysb.edu'),
(444444444, 'phil', 'Phil', 'Lewis', '135 Knowledge Lane, Stony Brook, NY', 11794, 5166668888, 'pml@cs.sunysb.edu'),
(666666666, 'mrmath', 'Math', 'Doug', '10, New, NY', 11011, 7061234567, 'dougmath@math.com'),
(789123456, 'warren', 'Warren', 'David', '456 Sunken Street, Stony Brook, NY', 11794, 5166329987, 'Warren@math.com');

-- --------------------------------------------------------

--
-- Table structure for table `Post`
--

CREATE TABLE `Post` (
  `ExpireDate` datetime DEFAULT NULL,
  `PostDate` datetime DEFAULT NULL,
  `CustomerID` int(9) NOT NULL DEFAULT '0',
  `AuctionID` int(9) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Post`
--

INSERT INTO `Post` (`ExpireDate`, `PostDate`, `CustomerID`, `AuctionID`) VALUES
('2008-12-16 13:00:00', '2018-10-22 00:00:00', 333333333, 102),
('2008-12-16 13:00:00', '2018-10-22 00:00:00', 444444444, 101),
('2018-10-25 00:00:00', '2018-10-25 00:00:00', 444444444, 103);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Auction`
--
ALTER TABLE `Auction`
  ADD PRIMARY KEY (`AuctionID`),
  ADD KEY `Buyer` (`Buyer`),
  ADD KEY `Monitor` (`Monitor`),
  ADD KEY `For` (`ItemID`);

--
-- Indexes for table `Bid`
--
ALTER TABLE `Bid`
  ADD PRIMARY KEY (`CustomerID`,`AuctionID`,`BidTime`),
  ADD KEY `AuctionID` (`AuctionID`);

--
-- Indexes for table `Customer`
--
ALTER TABLE `Customer`
  ADD PRIMARY KEY (`CustomerID`);

--
-- Indexes for table `Employee`
--
ALTER TABLE `Employee`
  ADD PRIMARY KEY (`EmployeeID`);

--
-- Indexes for table `Item`
--
ALTER TABLE `Item`
  ADD PRIMARY KEY (`ItemID`);

--
-- Indexes for table `Person`
--
ALTER TABLE `Person`
  ADD PRIMARY KEY (`SSN`);

--
-- Indexes for table `Post`
--
ALTER TABLE `Post`
  ADD PRIMARY KEY (`CustomerID`,`AuctionID`),
  ADD KEY `Auction` (`AuctionID`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Auction`
--
ALTER TABLE `Auction`
  ADD CONSTRAINT `Buyer` FOREIGN KEY (`Buyer`) REFERENCES `Customer` (`CustomerID`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `For` FOREIGN KEY (`ItemID`) REFERENCES `Item` (`ItemID`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `Monitor` FOREIGN KEY (`Monitor`) REFERENCES `Employee` (`EmployeeID`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `Bid`
--
ALTER TABLE `Bid`
  ADD CONSTRAINT `Bid_ibfk_1` FOREIGN KEY (`CustomerID`) REFERENCES `Customer` (`CustomerID`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `Bid_ibfk_2` FOREIGN KEY (`AuctionID`) REFERENCES `Auction` (`AuctionID`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `Customer`
--
ALTER TABLE `Customer`
  ADD CONSTRAINT `Customer_ibfk_1` FOREIGN KEY (`CustomerID`) REFERENCES `Person` (`SSN`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `Employee`
--
ALTER TABLE `Employee`
  ADD CONSTRAINT `Employee_ibfk_1` FOREIGN KEY (`EmployeeID`) REFERENCES `Person` (`SSN`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `Post`
--
ALTER TABLE `Post`
  ADD CONSTRAINT `Auction` FOREIGN KEY (`AuctionID`) REFERENCES `Auction` (`AuctionID`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `Seller` FOREIGN KEY (`CustomerID`) REFERENCES `Customer` (`CustomerID`) ON DELETE NO ACTION ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
