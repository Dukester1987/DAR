-- phpMyAdmin SQL Dump
-- version 4.3.8
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Sep 22, 2016 at 06:29 PM
-- Server version: 5.6.29-76.2
-- PHP Version: 5.4.31


--
-- Database: `sopsioco_DAR`
--

-- --------------------------------------------------------

--
-- Table structure for table `AFAllocation`
--

CREATE TABLE IF NOT EXISTS `AFAllocation` (
  `ID` int(11) NOT NULL,
  `SiteID` int(11) NOT NULL,
  `VehicleID` varchar(20)  NOT NULL,
  `Description` varchar(80)  NOT NULL,
  `StartDate` date NOT NULL,
  `EndDate` date NOT NULL
);

-- --------------------------------------------------------

--
-- Table structure for table `AFFuel`
--

CREATE TABLE IF NOT EXISTS `AFFuel` (
  `ID` int(11) NOT NULL,
  `AFAllocationID` int(11) NOT NULL,
  `Amount` double(18) NOT NULL,
  `DateFor` date NOT NULL
);

-- --------------------------------------------------------

--
-- Table structure for table `ChangeLog`
--

CREATE TABLE IF NOT EXISTS `ChangeLog` (
  `ID` int(11) NOT NULL,
  `AffectedTable` varchar(80)  NOT NULL,
  `RowID` varchar(5)  NOT NULL,
  `Operation` varchar(10)  NOT NULL,
  `NewValue` text  NOT NULL,
  `LoginID` int(4) NOT NULL,
  `Time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UID` varchar(36)  NOT NULL
);


-- --------------------------------------------------------

--
-- Table structure for table `EPA`
--

CREATE TABLE IF NOT EXISTS `EPA` (
  `ID` int(11) NOT NULL,
  `Rate` double NOT NULL,
  `DateFor` date NOT NULL
);

-- --------------------------------------------------------

--
-- Table structure for table `LaborAllocation`
--

CREATE TABLE IF NOT EXISTS `LaborAllocation` (
  `ID` int(11) NOT NULL,
  `LaborID` int(11) NOT NULL,
  `SiteID` int(11) NOT NULL,
  `StartDate` date NOT NULL,
  `EndDate` date DEFAULT NULL
) ;


-- --------------------------------------------------------

--
-- Table structure for table `LaborFunctions`
--

CREATE TABLE IF NOT EXISTS `LaborFunctions` (
  `ID` int(11) NOT NULL,
  `Function` varchar(80)  NOT NULL
)   ;

--
-- Dumping data for table `LaborFunctions`
--



-- --------------------------------------------------------

--
-- Table structure for table `LaborList`
--

CREATE TABLE IF NOT EXISTS `LaborList` (
  `ID` int(11) NOT NULL,
  `LaborName` varchar(80)  NOT NULL,
  `LaborFunction` int(11) NOT NULL,
  `LaborRate` double NOT NULL
) ;


-- --------------------------------------------------------

--
-- Table structure for table `LaborStatus`
--

CREATE TABLE IF NOT EXISTS `LaborStatus` (
  `ID` int(11) NOT NULL,
  `Status` varchar(50)  NOT NULL
);

--
-- Dumping data for table `LaborStatus`
--



-- --------------------------------------------------------

--
-- Table structure for table `LaborUtilization`
--

CREATE TABLE IF NOT EXISTS `LaborUtilization` (
  `ID` int(11) NOT NULL,
  `LaborAllocationID` int(11) NOT NULL,
  `PlantID` varchar(5)  NOT NULL,
  `Hours` decimal(10,2) NOT NULL,
  `Status` int(5) NOT NULL,
  `Notes` varchar(80)  NOT NULL,
  `DateFor` date NOT NULL
);

-- --------------------------------------------------------

--
-- Table structure for table `Login`
--

CREATE TABLE IF NOT EXISTS `Login` (
  `ID` int(11) NOT NULL,
  `LoginName` varchar(80)  NOT NULL,
  `Password` varchar(32)  NOT NULL,
  `Rights` varchar(80)  NOT NULL,
  `Status` int(11) NOT NULL,
  `LastUpload` timestamp NULL DEFAULT NULL,
  `LastDownload` timestamp NULL DEFAULT NULL
);




-- --------------------------------------------------------

--
-- Table structure for table `PlantAllocation`
--

CREATE TABLE IF NOT EXISTS `PlantAllocation` (
  `ID` int(11) NOT NULL,
  `PlantID` varchar(20)  NOT NULL,
  `SiteID` int(11) NOT NULL,
  `StartDate` date NOT NULL,
  `EndDate` date DEFAULT NULL
);


-- --------------------------------------------------------

--
-- Table structure for table `PlantList`
--

CREATE TABLE IF NOT EXISTS `PlantList` (
  `ID` varchar(20)  NOT NULL,
  `PlantDesc` varchar(80)  NOT NULL,
  `Rate` decimal(10,2) NOT NULL,
  `UnitOfMeasurement` varchar(5)  NOT NULL
);

--
-- Dumping data for table `PlantList`
--



-- --------------------------------------------------------

--
-- Table structure for table `PlantUtilization`
--

CREATE TABLE IF NOT EXISTS `PlantUtilization` (
  `ID` int(11) NOT NULL,
  `PlantAllocationID` int(11) NOT NULL,
  `StartHours` int(11) NOT NULL,
  `EndHours` int(11) NOT NULL,
  `DateFor` date NOT NULL,
  `Fuel` decimal(10,2) NOT NULL,
  `Notes` varchar(80)  NOT NULL
);


--
-- Table structure for table `ProductAllocation`
--

CREATE TABLE IF NOT EXISTS `ProductAllocation` (
  `ID` int(11) NOT NULL,
  `ProductID` int(11) NOT NULL,
  `SiteID` int(11) NOT NULL,
  `StartDate` date NOT NULL,
  `EndDate` date DEFAULT NULL
);

--
-- Dumping data for table `ProductAllocation`
--


-- --------------------------------------------------------

--
-- Table structure for table `ProductGroups`
--

CREATE TABLE IF NOT EXISTS `ProductGroups` (
  `ID` int(11) NOT NULL,
  `GroupName` varchar(80)  NOT NULL
);


-- --------------------------------------------------------

--
-- Table structure for table `ProductUtilization`
--

CREATE TABLE IF NOT EXISTS `ProductUtilization` (
  `ID` int(11) NOT NULL,
  `ProductAllocationID` int(11) NOT NULL,
  `Qty` decimal(10,2) NOT NULL,
  `Notes` varchar(80)  NOT NULL,
  `TransactionType` int(11) NOT NULL,
  `DateFor` date NOT NULL
);


-- --------------------------------------------------------

--
-- Table structure for table `Products`
--

CREATE TABLE IF NOT EXISTS `Products` (
  `ID` int(11) NOT NULL,
  `ProductName` varchar(80)  NOT NULL,
  `ProductVal` decimal(10,0) NOT NULL,
  `Type` int(2) NOT NULL,
  `EPA` int(11) NOT NULL,
  `GroupID` int(11) NOT NULL,
  `UOM` varchar(2)  NOT NULL
)   ;

--
-- Dumping data for table `Products`
--



-- --------------------------------------------------------

--
-- Table structure for table `Recipe`
--

CREATE TABLE IF NOT EXISTS `Recipe` (
  `ID` int(11) NOT NULL,
  `ProductAllocationID` int(11) NOT NULL,
  `SiteID` int(11) NOT NULL,
  `RecName` varchar(80)  NOT NULL,
  `status` tinyint(1) NOT NULL
)   ;


-- --------------------------------------------------------

--
-- Table structure for table `RecipeRel`
--

CREATE TABLE IF NOT EXISTS `RecipeRel` (
  `ID` int(11) NOT NULL,
  `RecID` int(11) NOT NULL,
  `ProductAllocationID` int(11) NOT NULL,
  `Used` double NOT NULL
)   ;


-- --------------------------------------------------------

--
-- Table structure for table `SiteList`
--

CREATE TABLE IF NOT EXISTS `SiteList` (
  `ID` int(11) NOT NULL,
  `SiteName` varchar(40)  NOT NULL,
  `SiteDesc` varchar(80)  NOT NULL
)   ;

--
-- Dumping data for table `SiteList`
--



-- --------------------------------------------------------

--
-- Table structure for table `SiteNotes`
--

CREATE TABLE IF NOT EXISTS `SiteNotes` (
  `ID` int(11) NOT NULL,
  `SiteID` int(11) NOT NULL,
  `Notes` text  NOT NULL,
  `DateFor` date NOT NULL
);

-- --------------------------------------------------------

--
-- Table structure for table `TransactionTypes`
--

CREATE TABLE IF NOT EXISTS `TransactionTypes` (
  `ID` int(11) NOT NULL,
  `Description` varchar(80)  NOT NULL
)   ;

--
-- Dumping data for table `TransactionTypes`
--


-- --------------------------------------------------------

--
-- Table structure for table `UpdateLog`
--

CREATE TABLE IF NOT EXISTS `UpdateLog` (
  `ID` int(11) NOT NULL,
  `Start` timestamp NULL DEFAULT NULL,
  `End` timestamp NULL DEFAULT NULL,
  `type` int(1) NOT NULL
);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `AFAllocation`
--
ALTER TABLE `AFAllocation`
  ADD PRIMARY KEY (`ID`,`SiteID`);

--
-- Indexes for table `AFFuel`
--
ALTER TABLE `AFFuel`
  ADD PRIMARY KEY (`ID`,`AFAllocationID`);

--
-- Indexes for table `ChangeLog`
--
ALTER TABLE `ChangeLog`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `EPA`
--
ALTER TABLE `EPA`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `LaborAllocation`
--
ALTER TABLE `LaborAllocation`
  ADD PRIMARY KEY (`ID`,`SiteID`);

--
-- Indexes for table `LaborFunctions`
--
ALTER TABLE `LaborFunctions`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `LaborList`
--
ALTER TABLE `LaborList`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `LaborStatus`
--
ALTER TABLE `LaborStatus`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `LaborUtilization`
--
ALTER TABLE `LaborUtilization`
  ADD PRIMARY KEY (`ID`,`LaborAllocationID`);

--
-- Indexes for table `Login`
--
ALTER TABLE `Login`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `PlantAllocation`
--
ALTER TABLE `PlantAllocation`
  ADD PRIMARY KEY (`ID`,`SiteID`);

--
-- Indexes for table `PlantUtilization`
--
ALTER TABLE `PlantUtilization`
  ADD PRIMARY KEY (`ID`,`PlantAllocationID`);

--
-- Indexes for table `ProductAllocation`
--
ALTER TABLE `ProductAllocation`
  ADD PRIMARY KEY (`ID`,`SiteID`);

--
-- Indexes for table `ProductGroups`
--
ALTER TABLE `ProductGroups`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `ProductUtilization`
--
ALTER TABLE `ProductUtilization`
  ADD PRIMARY KEY (`ID`,`ProductAllocationID`);

--
-- Indexes for table `Products`
--
ALTER TABLE `Products`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `Recipe`
--
ALTER TABLE `Recipe`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `RecipeRel`
--
ALTER TABLE `RecipeRel`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `SiteList`
--
ALTER TABLE `SiteList`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `SiteNotes`
--
ALTER TABLE `SiteNotes`
  ADD PRIMARY KEY (`ID`,`SiteID`);

--
-- Indexes for table `TransactionTypes`
--
ALTER TABLE `TransactionTypes`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `UpdateLog`
--
ALTER TABLE `UpdateLog`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `AFAllocation`
--
ALTER TABLE `AFAllocation`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `AFFuel`
--
ALTER TABLE `AFFuel`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `ChangeLog`
--
ALTER TABLE `ChangeLog`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `EPA`
--
ALTER TABLE `EPA`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `LaborAllocation`
--
ALTER TABLE `LaborAllocation`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `LaborFunctions`
--
ALTER TABLE `LaborFunctions`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `LaborList`
--
ALTER TABLE `LaborList`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `LaborStatus`
--
ALTER TABLE `LaborStatus`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `LaborUtilization`
--
ALTER TABLE `LaborUtilization`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `Login`
--
ALTER TABLE `Login`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `PlantAllocation`
--
ALTER TABLE `PlantAllocation`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `PlantUtilization`
--
ALTER TABLE `PlantUtilization`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `ProductAllocation`
--
ALTER TABLE `ProductAllocation`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `ProductGroups`
--
ALTER TABLE `ProductGroups`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `ProductUtilization`
--
ALTER TABLE `ProductUtilization`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `Products`
--
ALTER TABLE `Products`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `Recipe`
--
ALTER TABLE `Recipe`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `RecipeRel`
--
ALTER TABLE `RecipeRel`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `SiteList`
--
ALTER TABLE `SiteList`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `SiteNotes`
--
ALTER TABLE `SiteNotes`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `TransactionTypes`
--
ALTER TABLE `TransactionTypes`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `UpdateLog`
--
ALTER TABLE `UpdateLog`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
-- Database: `sopsioco_DAR`
--
INSERT INTO `TransactionTypes` (`ID`, `Description`) VALUES
(1, 'Ingoing'),
(2, 'Outgoing'),
(3, 'Production'),
(4, 'Used in Production');

INSERT INTO `LaborFunctions` (`ID`, `Function`) VALUES
(1, 'Operator'),
(2, 'Weighbridge operator'),
(3, 'Site Manager'),
(4, 'Labour Hire');

INSERT INTO `LaborStatus` (`ID`, `Status`) VALUES
(1, 'IN'),
(2, 'Sick Leave'),
(3, 'RDO'),
(4, 'Annual Leave'),
(5, 'Unpaid Leave'),
(6, 'Public Holiday'),
(7, 'Bereavement Leave');

INSERT INTO `Login` (`ID`,`LoginName`,`Password`,`Rights`,`Status`,`LastUpload`,`LastDownload`) VALUES
(1,'Menangle','4ecd9f4c37e9d72cae76daa239892693','{"SiteID":1,"Rights":1}',1,null,null),
(2,'Wallacia','2fae7330fe63aa996902e0606caa2bc0','{"SiteID":2,"Rights":1}',1,null,null),
(3,'Dukester','2fae7330fe63aa996902e0606caa2bc0','{"SiteID":1,"Rights":1}',1,null,null); 

INSERT INTO `PlantList` (`ID`, `PlantDesc`, `Rate`, `UnitOfMeasurement`) VALUES
('P01', 'Caterpillar 936 Loader Sand & Soil Yard', '297.50', 'h'),
('U50', 'Mitsubishi 4T Single Axle Tipper Rego BI36EY', '94.50', 'h'),
('U51', 'Mitsubishi 3T Single Axle Tipper Rego BZ32DN', '94.50', 'h'),
('U52', 'Mitsubishi 3T Single Axle Tipper Rego BZ33DN', '94.50', 'h'),
('U150', 'Feightliner Twin Steer Tipper Rego CD60ZZ', '294.00', 'h'),
('U154', 'Freightliner Twin Steer Tipper AH83RP', '294.00', 'h'),
('U153', 'Freightliner Twin Steer Tipper AH04RQ', '294.00', 'h'),
('U53', 'Mistubishi 9T Single Axle Tipper Rego BZ60NI', '168.00', 'h'),
('U54', 'Mistubishi 6T Single Axle  RegoCA93DI Skip Bin Truck', '200.00', 'h'),
('U159', '12T Sterling CH50RS', '277.20', 'h'),
('U160', '12T Sterling CI97DG', '277.20', 'h'),
('U157', 'Sterling Bogie  Tipper AK86OE Check Utilization', '277.20', 'h'),
('U158', 'Sterling Bogie  Tipper CG80XG', '277.20', 'h'),
('P20', 'Leader Watercart', '277.20', 'h'),
('U31', 'Sterling Bogie  Tipper C73PT Retired to KC Yard', '277.20', 'h'),
('P157', 'Volvo EC460BL Exc', '619.50', 'h'),
('P158', 'Volvo EC460BL Exc', '619.50', 'h'),
('P173', 'Extec S-6 Screen', '1120.00', 'h'),
('P195', 'Komatsu D375A-5 Dozer', '1400.00', 'h'),
('P200', 'Komatsu WA 470-6 Loader', '385.00', 'h'),
('P201', 'Acco Watercart Kilometer', '277.20', 'h'),
('P205', 'Komatsu WF550T-3 Compactor', '802.90', 'h'),
('P209', 'Komatsu WA470-6 Loader', '385.00', 'h'),
('P216', 'UD Watercart Bogie', '277.20', 'h'),
('P217', 'UD Watercart', '277.20', 'h'),
('P234', 'Metso Nordberg LT 1213S Impact Crusher', '1960.00', 'h'),
('P238', 'Komatsu WA 470-6 Loader', '385.00', 'h'),
('P239', 'Komatsu WA 470-6 Loader', '385.00', 'h'),
('P249', 'Komatsu WA 500-6 Loader', '620.90', 'h'),
('P250', 'Volvo A40F Dump Truck', '626.50', 'h'),
('P251', 'Volvo A40F Dump Truck', '626.50', 'h'),
('P252', 'Caterpillar D8R Dozer', '700.00', 'h'),
('P255', 'Komatsu WA 500-6 Loader', '620.90', 'h'),
('P258', 'Komatsu D475A-5 Dozer', '2100.00', 'h'),
('P264', 'Caterpillar CS563 Smooth Drum 12T Roller', '182.00', 'h'),
('P265', 'Caterpillar CP533 Padfoot 12T Roller', '182.00', 'h'),
('P268', 'Hyundai R5.5-9 Exc', '202.30', 'h'),
('P269', 'Hyundai R80CR-9 Exc', '224.00', 'h'),
('P270', 'Hyundai R145CDR-9 Exc', '281.40', 'h'),
('P273', 'Caterpillar  140M Grader Fitted For Gps', '420.00', 'h'),
('P274', 'Caterpillar 12H Grader', '347.90', 'h'),
('P275', 'Caterpillar CP533 Padfoot 12T Roller', '182.00', 'h'),
('P277', 'Caterpillar CB14 Tandem Smooth Drum 1.4T Roller', '87.50', 'h'),
('P278', 'Bobcat S590 ', '154.70', 'h'),
('P279', 'Bobcat S650', '175.00', 'h'),
('P280', 'Bobcat T650 Pozzi Track', '201.60', 'h'),
('P282', 'Mitsubishi Watercart Single Axle Standpipe YREE0008', '217.00', 'h'),
('P286', 'Caterpillar CB22  BK Tandem Smooth Drum 2.5T Roller', '87.50', 'h'),
('P287', 'Caterpillar 345 Exc', '619.50', 'h'),
('P290', 'Metso Locotrack LT 3054 Crusher', '1960.00', 'h'),
('P293', 'Komatsu HM 400-2 Dump Truck Tail Gate', '626.50', 'h'),
('P299', 'Komatsu PC200 LC-8 Exc', '329.00', 'h'),
('P303', 'Keestrack Frontier Screen', '840.00', 'h'),
('P305', 'Keestrack Frontier Screen', '840.00', 'h'),
('P306', 'Keestrack Frontier Screen', '840.00', 'h'),
('P312', 'Caterpillar  CB22B Dual Smooth Drum Roller 2.5T', '87.50', 'h'),
('P314', 'Bobcat T650 Pozzi Track', '201.60', 'h'),
('P316', 'Komatsu WA480- Loader', '441.00', 'h'),
('P320', 'Isuzu FVZ1400 13Kl Watercart Rego CH52RS', '277.20', 'h'),
('P325', 'Caterpillar 980G Loader', '620.90', 'h'),
('P327', 'Komatsu WA470-6', '385.00', 'h'),
('P328', 'Isuzu  FVZ1400 13Kl Watercart Rego CF00YI', '277.20', 'h'),
('P329', 'Caterpillar  IT62G Tool Carrier, Bucket,Man Basket,Forks & Jib', '297.50', 'h'),
('P186', 'Extec S-5 Screen', '1008.00', 'h'),
('P206', 'Komatsu WA 470-6 Loader', '385.00', 'h'),
('P212', 'Komatsu WA 380-6 Loader Traded for P327 27/11/15', '350.00', 'h'),
('P297', 'Komatsu PC200 LC-8 Exc Grabs, Dig, B/Bucket, Sieve Bucket', '329.00', 'h'),
('P309', 'Komatsu PC450LC-8 Case Drain', '619.50', 'h'),
('P317', 'Keestrack Frontier Scalper Purchased from Sceenmaster 28/9/15', '840.00', 'h'),
('P332', 'Keestrack Screen Purchased from Sceenmaster 23/4/16', '840.00', 'h'),
('T07', 'Single Axle Tag Along Trailer Rego Z93797', '140.00', 'h'),
('T08', 'Single Axle Tag Along Trailer Rego TA03UQ', '140.00', 'h'),
('U57', 'Spare Site Clean Bogie - STEVE SEE CRYSTAL', '277.20', 'h'),
('P133', 'Volvo A30D  Dump Truck', '461.30', 'h'),
('P148', 'Metso Nordberg NP 1213-m Impactor Crusher', '1960.00', 'h'),
('P150', 'Volvo A30D Dump Truck', '461.30', 'h'),
('P184', 'Extec S-5 Screen  ( In Grave Yard )', '1008.00', 'h'),
('P199', 'Adelaide Chieftain 1400 Screen', '1008.00', 'h'),
('P221', 'Finlay 7702002 Trommel', '1120.00', 'h'),
('P266', 'Caterpillar 825H Compactor', '802.90', 'h'),
('P267', 'Hyundai R5.5-9 Exc', '202.30', 'h'),
('P271', 'Hyundai R235LCR-9 Exc', '359.80', 'h'),
('P272', 'Caterpillar 140M Grader', '420.00', 'h'),
('P276', 'Cat CS573 Smooth Drum Roller 14T', '182.00', 'h'),
('P281', 'Mitsubishi Watercart Single Axle Standpipe YREE0007', '217.00', 'h'),
('P284', 'Hyundai 320LC-9 Exc', '525.00', 'h'),
('P291', 'Komatsu HM 400-2 Dump Truck NoTail Gate', '626.50', 'h'),
('P292', 'Komatsu HM 400-2 Dump Truck Tail Gate', '626.50', 'h'),
('P295', 'Komatsu PC138-8 US Exc', '281.40', 'h'),
('P301', 'Caterpillar D10N Dozer', '1400.00', 'h'),
('P308', 'Komatsu PC450LC-8', '619.50', 'h'),
('P310', 'Komatsu GD655 Grader Fitted For Gps', '347.90', 'h'),
('P318', 'Volvo EC480DL Excavator Fitted For Gps', '619.50', 'h'),
('P326', 'Caterpiller D11T Dozer', '2100.00', 'h'),
('Allcott Hire', '6" Pump, 200m Delivery Line, 12m Suction Line C/W Foot Valve', '0.00', 'h'),
('P126', 'Hitachi ZX 480 Exc', '619.50', 'h'),
('P181', 'Chieftain EG 2100 Screen', '1080.00', 'h'),
('P190', 'Komatsu PC450-8 LC Exc', '619.50', 'h'),
('P207', 'Komatsu WA 470-6 Loader', '385.00', 'h'),
('P229', 'Metso LT7150 Barmac Crusher Sold/Traded', '1960.00', 'h'),
('P237', 'Komatsu WA 470-6 Loader', '385.00', 'h'),
('P246', 'Screenmaster Frontier Scalper (Keestrac) Screen', '840.00', 'h'),
('P289', 'Metso Nordberg LT 1315S Impact Crusher', '1960.00', 'h'),
('P298', 'Komatsu PC270 LC-8 Exc Dig & Batter Buckets Remeadiation', '386.40', 'h'),
('P331', 'Keestrack 1313S Impact Crusher', '2800.00', 'h'),
('P26', '5140 Case Tractor', '0.00', 'h'),
('P153', '4 Cyl GM Pump 150mm Self Priming', '0.00', 'h'),
('P155', 'Komatsu D85 Dozer', '595.00', 'h'),
('P176', 'Perkins & 100mm Water Pump', '0.00', 'h'),
('P190A', 'Yanmar Diesel Flex Drive Pump', '0.00', 'h'),
('P210', 'Komatsu PC450-7 Excavator', '619.50', 'h'),
('P219', 'Sline Water Cart', '277.20', 'h'),
('P232', 'Deutz 100mm Water Pump', '0.00', 'h'),
('U274', 'Caterpillar 12H Grader', '347.90', 'h'),
('P302', 'Aston Atomiser 1600', '616.00', 'h'),
('P315', 'Caterpillar 836H Trash Compactor', '802.90', 'h'),
('U221', 'Service Truck 4 x 4 C/W Tools (Tools List In Plant Folder W''shop Office)', '0.00', 'h'),
('P143', 'Weighbridge Gen Set (in Container 25KVA)', '0.00', 'h'),
('P142', 'Diesel 6" Pump House Pump', '0.00', 'h'),
('P178', 'MT 1440 Manitou Tele Handler Forklift & Bucket', '277.20', 'h'),
('P179', 'Washery Gen Set 1135 KVA', '0.00', 'h'),
('P182', 'Caterpillar Water Pump 150mm x 125mm', '0.00', 'h'),
('P123', 'Ford 900 Watercart', '277.20', 'h'),
('P228', '4 Cyl Ford Motor & Grundfos Water Pump', '0.00', 'h'),
('P253', 'Tesab Jaw Crusher', '1400.00', 'h'),
('Attach', 'Dig Bucket & Ripper For P287', '0.00', 'h'),
('P288', 'Caterpillar 815F Compactor', '452.20', 'h'),
('P135', 'Genset #2 Runs P151  Wash Pant', '0.00', 'h'),
('P144', 'TQS Mobile Radial Stacker S-62685 Conveyor (Fixed Plant Item)', '0.00', 'h'),
('P162', 'Diesel Generator', '0.00', 'h'),
('P163', 'Cummins 100 KVA Genset & 6" Submersable Pump', '0.00', 'h'),
('P208', 'Komatsu WA 470-6 Loader', '385.00', 'h'),
('P222', 'Komatsu PC710-5 Exc Long Reach', '1820.00', 'h'),
('P240', 'Komatsu PC800-6 Exc', '1225.00', 'h'),
('P241', 'Track Stacker Conveyor', '420.00', 'h'),
('P245', 'VSI 2000 (Blown Motor) Crusher', '0.00', 'h'),
('P283', 'Iveco Watercart Kilometer', '277.20', 'h'),
('P294', 'Caterpillar D11R Dozer', '2100.00', 'h'),
('P300', 'Caterpillar 325 Exc', '359.80', 'h'),
('P304', ' Gipo Mag 2700 Vertical Shaft Impactor (Crusher)', '2800.00', 'h'),
('P319', 'Keestrack 230 Track Stacker', '420.00', 'h'),
('P12', 'Perkins Generator Weighbridge', '0.00', 'h'),
('P39', 'Powerscreen M100 Mobile Screen', '1008.00', 'h'),
('P79', 'Dredge 4Cyl Caterpillar & Goodwin Sand Pump', '0.00', 'h'),
('P89', 'Powerscreen 620 Trommel', '0.00', 'h'),
('P93', 'Stationary Trommel', '0.00', 'h'),
('P99', 'Water Pump 471 Detroit Pump', '0.00', 'h'),
('P140', 'Komatsu WA 500-3H Loader', '620.90', 'h'),
('P147', 'Komatsu PC650-3 Exc', '1050.00', 'h'),
('P174', 'Hazemag Crushing Plant Crusher', '0.00', 'h'),
('P177', 'MT 1440 Manitou Telehandler Forklift & Bucket', '277.20', 'h'),
('P183', 'Nirox Mobile Trommel', '840.00', 'h'),
('P198', 'Sand Wash Plant', '0.00', 'h'),
('P215', 'L/P Stacker Conveyor', '420.00', 'h'),
('P220', 'Komatsu D155A-6 Dozer', '700.00', 'h'),
('P242', 'Power Screen 624 Trommel', '840.00', 'h'),
('P243', 'Caterpillar 12H Grader', '347.90', 'h'),
('P248', 'Komatsu WA 500-6 Loader', '620.90', 'h'),
('P260', 'Komatsu PC850-8 Exc', '1365.00', 'h'),
('P285', 'Hyundai 320LC-9 Exc Fitted For GPS Complete Set', '525.00', 'h'),
('P307', 'Keestrack Screen S166', '840.00', 'h'),
('P311', 'Caterpillar 773D Watercart', '840.00', 'h'),
('P333', 'Metso LT300HP Cone Crusher 43T', '2800.00', 'h'),
('Au1', 'Auger Drive 450mm, 750mm, 2 x 2.5m Extensions', '168.00', 'h');

INSERT INTO `Products` (`ID`, `ProductName`, `ProductVal`, `Type`, `EPA`, `GroupID`, `UOM`) VALUES
(1, 'B TURF UNDERLAY / REC SOIL', '0', 2, 0, 15, 't'),
(2, 'B TURF UNDERLAY / INTERCOMPANY', '0', 2, 0, 15, 't'),
(5, 'B ENM', '0', 1, 0, 8, 't'),
(6, 'B GSW (BUILDING & DEMO)', '0', 1, 0, 9, 't'),
(7, 'B GSW - RECYCLABLE', '0', 1, 0, 9, 't'),
(8, 'B13 TIMBER WASTE', '0', 1, 0, 11, 't'),
(9, 'B12 GREEN WASTE', '0', 1, 0, 11, 't'),
(10, 'B VENM', '0', 1, 0, 8, 't'),
(13, 'T/Underlay screened', '0', 3, 0, 15, 't'),
(14, 'Garden Mix Screened', '0', 3, 0, 15, 't'),
(17, 'Screen Sand', '0', 4, 0, 14, 't'),
(18, 'Garden Mix Blended', '0', 4, 0, 15, 't'),
(19, 'T/Underlay Blend', '0', 4, 0, 15, 't'),
(31, 'Shale Cake', '0', 3, 0, 6, 't'),
(33, 'Pink Shale', '0', 4, 0, 6, 't'),
(34, 'Brown Shale', '0', 4, 0, 6, 't'),
(35, 'Blue Shale', '0', 4, 0, 6, 't'),
(36, 'DGB Roadbase', '0', 4, 0, 13, 't'),
(40, 'Bedding Sand', '0', 2, 0, 14, 't'),
(61, 'Use of Hi Quality Weighbridge', '0', 2, 0, 12, 't'),
(62, 'Scrap Metal', '0', 2, 0, 12, 't'),
(64, 'Concrete - Nil Charge', '0', 1, 0, 7, 't'),
(71, '100mm minus - Cross Over', '0', 3, 0, 10, 't'),
(72, 'Blended Fill Sand (WS)', '0', 3, 0, 14, 't'),
(73, 'Concrete Sand', '0', 3, 0, 14, 't'),
(74, '40mm Crushed Sandstone', '0', 3, 0, 13, 't'),
(75, 'Recycled Brick-Concrete Sand', '0', 3, 0, 13, 't'),
(77, '6-12mm Sandstone Pebble', '0', 3, 0, 10, 't'),
(78, 'White Brick Sand', '0', 3, 0, 14, 't'),
(79, '150-300 Gabion', '0', 3, 0, 10, 't'),
(80, '300-500 Med', '0', 3, 0, 10, 't'),
(81, '500-800 Large', '0', 3, 0, 10, 't'),
(82, 'Armour Rock 800+', '0', 3, 0, 10, 't'),
(84, '10-20 Crushed River Gravel', '0', 3, 0, 10, 't'),
(85, '20-40mm Round River Gravel', '0', 3, 0, 10, 't'),
(86, '40mm+ round River Gravel', '0', 3, 0, 10, 't'),
(87, '20mm Quartz Pebble', '0', 3, 0, 10, 't'),
(88, '20mm Quartz Crushed', '0', 3, 0, 10, 't'),
(90, 'Pre Crushed (Sand)', '0', 3, 0, 14, 't'),
(91, '5-7mm Sandstone Pebble', '0', 3, 0, 10, 't'),
(92, 'Recycled agg', '0', 3, 0, 10, 't'),
(95, 'Washed Sand', '0', 4, 0, 14, 't'),
(96, '20mm Road Base', '0', 4, 0, 13, 't'),
(97, 'Raw Feed', '0', 4, 0, 14, 't'),
(98, 'Fine Raw Feed', '0', 4, 0, 14, 't'),
(99, 'Mix Brick Concrete Dirt', '0', 4, 0, 7, 't'),
(100, 'Concrete up to 1.5mtr', '0', 4, 0, 7, 't'),
(101, 'Concrete over 1.5mtr', '0', 4, 0, 7, 't'),
(102, 'Clean Bricks', '0', 4, 0, 7, 't'),
(103, 'Brick & Concrete', '0', 4, 0, 7, 't'),
(110, 'Gold Concentrate', '0', 2, 0, 12, 't'),
(111, '80-20 Propagating Sand', '0', 2, 0, 14, 't'),
(112, '3mm Sand', '0', 3, 0, 14, 't'),
(113, '3 - 8mm River Pebble', '0', 3, 0, 10, 't'),
(114, '8 - 16mm River Pebble (10-14)', '0', 3, 0, 10, 't'),
(115, '16 - 35mm River Pebble (20mm)', '0', 3, 0, 10, 't'),
(116, '35 - 50mm River Pebble', '0', 3, 0, 10, 't'),
(117, '50mm +  River Pebble', '0', 3, 0, 10, 't'),
(120, 'Pre-screened SAND Raw Feed', '0', 4, 0, 14, 't'),
(121, 'Pre-screened PEBBLES Raw Feed', '0', 4, 0, 10, 't'),
(135, 'USE OF HIQ WEIGHBRIDGE', '0', 2, 0, 12, 't'),
(136, 'STEEL TRANSFER', '0', 2, 0, 12, 't'),
(139, 'S CC MED 1 NO STEEL', '0', 1, 0, 7, 't'),
(140, 'S CC MED 2 LIGHT MESH STEEL', '0', 1, 0, 7, 't'),
(141, 'S CC LRG 3 MEDIUM STEEL', '0', 1, 0, 7, 't'),
(142, 'S GSW general solid waste', '0', 1, 0, 7, 't'),
(143, 'S BRK 5 clean bricks', '0', 1, 0, 7, 't'),
(144, 'S BRK 6 brick/concrete mix', '0', 1, 0, 7, 't'),
(145, 'S ROTAR 7 clean asphalt', '0', 1, 0, 7, 't'),
(146, 'S ROTAR 8 road base/asphalt mix', '0', 1, 0, 7, 't'),
(147, 'S SOIL/CLAY 9', '0', 1, 0, 9, 't'),
(148, 'S M/MASONRY 10 dirt/brick/concrete', '0', 1, 0, 7, 't'),
(149, 'S 11 Unrecycleable waste', '0', 1, 0, 11, 't'),
(150, 'S TIMBER/SML/12 green waste', '0', 1, 0, 11, 't'),
(151, 'S TIMBER/LRG/13 large trunks/timber', '0', 1, 0, 11, 't'),
(152, 'S CS/WASTE 14 dirt/brick/concrete', '0', 1, 0, 9, 't'),
(153, 'S CW/WASTE 15 council waste', '0', 1, 0, 9, 't'),
(154, 'S VENM', '0', 1, 0, 8, 't'),
(155, 'S ENM', '0', 1, 0, 8, 't'),
(158, 'S RAP recycled asphalt', '0', 3, 0, 13, 't'),
(159, 'S 30/70 recycled aggregate', '0', 3, 0, 10, 't'),
(160, 'S RSND recycled sand', '0', 3, 0, 14, 't'),
(161, 'Material Prep', '0', 3, 0, 8, 't'),
(162, 'S CSRS2%', '0', 3, 0, 13, 't'),
(163, 'S CSI', '0', 3, 0, 13, 't'),
(164, 'S TURF UNDERLAY', '0', 3, 0, 15, 't'),
(165, 'S GARDEN MIX', '0', 3, 0, 15, 't'),
(166, 'S 10mm recycled aggregate ', '0', 3, 0, 10, 't'),
(167, 'S 20mm recycled aggregate', '0', 3, 0, 10, 't'),
(168, 'Fill Soil', '0', 3, 0, 15, 't'),
(169, '70-30 blended soil', '0', 3, 0, 15, 't'),
(170, 'S 20 Recycled Road Base', '0', 4, 0, 13, 't'),
(171, 'S RDGB20 road base RTA spec', '0', 4, 0, 13, 't'),
(179, '40mm Graded Rubble ', '0', 2, 0, 10, 't'),
(193, '20mm Class 2', '0', 2, 0, 13, 't'),
(194, '14/10mm Aggregrate', '0', 2, 0, 10, 't'),
(197, '900mm Minus Landscape Rock', '0', 2, 0, 10, 't'),
(198, 'Shot rock 300 minus', '0', 2, 0, 10, 't'),
(199, 'Cat C contaminated Soil', '0', 1, 0, 5, 't'),
(200, 'Cat C contaminated Soil to recycling', '0', 1, 0, 4, 't'),
(201, 'Cat C Contaminated Soil & Asbestos', '0', 1, 0, 3, 't'),
(202, 'Cat C Contaminated Soil & PCBs', '0', 1, 0, 5, 't'),
(203, 'Cat C Sludges / Slurries', '0', 1, 0, 5, 't'),
(204, 'Solid Waste with Cat C Residues', '0', 1, 0, 4, 't'),
(205, 'Cat C Abated Asbestos', '0', 1, 0, 3, 't'),
(206, 'Asbestos', '0', 1, 0, 2, 't'),
(207, 'Asbestos Load Rate', '0', 1, 0, 2, 't'),
(208, 'Cleanfill with Asbestos', '0', 1, 0, 2, 't'),
(209, 'Ceramic Fibres', '0', 1, 0, 12, 't'),
(210, 'Acid Sulfate Soils', '0', 1, 0, 1, 't'),
(211, 'Cleanfill  ', '0', 1, 0, 8, 't'),
(212, 'Cleanfill with rubble', '0', 1, 0, 8, 't'),
(213, 'Cleanfill with odour', '0', 1, 0, 8, 't'),
(214, 'Cleanfill with odour and rubble', '0', 1, 0, 8, 't'),
(215, 'Wet Cleanfill  ', '0', 1, 0, 8, 't'),
(216, 'Wet Cleanfill with rubble', '0', 1, 0, 8, 't'),
(217, 'Wet Cleanfill with odour', '0', 1, 0, 8, 't'),
(218, 'Wet Cleanfill with odour and rubble', '0', 1, 0, 8, 't'),
(219, 'Clean Drilling Mud', '0', 1, 0, 8, 't'),
(220, 'Concrete', '0', 1, 0, 7, 't'),
(221, 'Basalt rock', '0', 1, 0, 7, 't'),
(222, 'Washed Construction Sand', '0', 3, 0, 14, 't'),
(223, 'Packing Sand', '0', 3, 0, 14, 't'),
(224, 'Menage Mix', '0', 3, 0, 14, 't'),
(225, 'Concrete Mix', '0', 3, 0, 14, 't'),
(226, '7mm Washed pebble', '0', 3, 0, 10, 't'),
(227, '14mm Washed Pebble', '0', 3, 0, 10, 't'),
(228, '25mm Washed pebble', '0', 3, 0, 10, 't'),
(229, '5mm Dust', '0', 3, 0, 13, 't'),
(230, '5mm Drainage Aggregate (washed)', '0', 3, 0, 10, 't'),
(231, 'Washed Basalt Sand', '0', 3, 0, 14, 't'),
(232, '7mm aggregate', '0', 3, 0, 10, 't'),
(233, '14mm Aggregate', '0', 3, 0, 10, 't'),
(234, '20mm Aggregate', '0', 3, 0, 10, 't'),
(235, '20-40 mm Aggregate', '0', 3, 0, 10, 't'),
(237, '20mm Class4', '0', 3, 0, 13, 't'),
(238, '60mm NDCR', '0', 3, 0, 13, 't'),
(239, '100mm Graded Rubble', '0', 3, 0, 10, 't'),
(240, 'Spalls 40mm - 100mm', '0', 3, 0, 10, 't'),
(241, 'Oversize shotrock 100mm - 350mm', '0', 3, 0, 10, 't'),
(242, 'Clay', '0', 3, 0, 6, 't'),
(243, 'Topsoil organic Blend', '0', 3, 0, 15, 't'),
(244, '7 mm Dust', '0', 3, 0, 13, 't'),
(245, '20mm Class3', '0', 4, 0, 13, 't'),
(257, 'RELOAD', '0', 2, 0, 12, 't'),
(258, 'WEIGHBRIDGE ONLY', '0', 2, 0, 12, 't'),
(260, 'VENM - SPADEABLE', '0', 1, 0, 8, 't'),
(261, 'VENM', '0', 1, 0, 8, 't'),
(262, 'ENM - SPADEABLE', '0', 1, 0, 8, 't'),
(263, 'ENM ', '0', 1, 0, 8, 't'),
(264, 'INTERNAL ENM / VENM', '0', 1, 0, 8, 't'),
(265, 'GBF', '0', 3, 0, 14, 't'),
(266, '100mm Crushed Sandstone', '0', 3, 0, 13, 't'),
(267, '75mm Crushed Sandstone', '0', 3, 0, 13, 't'),
(268, '50mm Crushed Sandstone', '0', 3, 0, 13, 't'),
(269, '75-150mm Sandstone Gabian', '0', 3, 0, 10, 't'),
(270, '150-300mm Sandstone Gabian', '0', 3, 0, 10, 't'),
(271, '300-500mm sandstone Boulder', '0', 3, 0, 10, 't'),
(272, '500-800mm Sandstone Boulder', '0', 3, 0, 10, 't'),
(273, '800mm+ Sandstone Armour Rock', '0', 3, 0, 10, 't'),
(274, 'Select Fill (Blue Shale)', '0', 3, 0, 6, 't'),
(275, 'Bulk Fill - Overburden', '0', 3, 0, 6, 't'),
(277, 'Ripped Sandstone to Stockpile', '0', 4, 0, 14, 't'),
(292, 'GSW', '0', 1, 0, 9, 't'),
(293, 'GSW C & D', '0', 1, 0, 9, 't'),
(294, 'Asbestos Sheet', '0', 1, 0, 2, 't'),
(295, 'Asbestos Soil', '0', 1, 0, 2, 't'),
(296, 'Asbestos Contaminated Demo Waste', '0', 1, 0, 2, 't'),
(297, 'Glass', '0', 1, 0, 9, 't'),
(298, 'White Clay', '0', 3, 0, 6, 't'),
(299, 'Blue Clay', '0', 3, 0, 6, 't');

INSERT INTO `SiteList` (`ID`, `SiteName`, `SiteDesc`) VALUES
(1, 'Menangle', 'Quarry'),
(2, 'Wallacia', 'Quarry');