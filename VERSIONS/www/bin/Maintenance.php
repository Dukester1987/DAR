<?php

class Maintenance {

  public $conn;
  private $notifyAdvance = 50;
  
  public function __construct($conn) {
    $this->conn = $conn;
  }
  
  public function getMaintenanceData() {
    $query = "SELECT mt.ID,
              mt.PlantID,
              pl.PlantDesc,
              mt.MaintenanceInterval,
              mt.CycleCount,
              MAX(pu.EndHours) as ActualHours
              FROM Maintenance mt
              LEFT JOIN PlantAllocation pa on pa.PlantID = mt.PlantID
              LEFT JOIN PlantList pl on pl.ID = pa.PlantID
              INNER JOIN PlantUtilization pu on pu.PlantAllocationID = pa.ID
              GROUP BY mt.PlantID";
    $result = $this->conn->query($query);
    $key = 0;
    $items = array();
    while($row = $result->fetch_assoc()){   
      if($this->checkMaintenance($row['CycleCount'], $row['MaintenanceInterval'], $row['ActualHours'], $row['PlantID'], $row['PlantDesc'])){    
        $items[$key] = $this->checkMaintenance($row['CycleCount'], $row['MaintenanceInterval'], $row['ActualHours'], $row['PlantID'], $row['PlantDesc']);
        $key++;
      }
    }   
    return $items;                 
  }
  
  public function checkItems($items,$plantID) {
    foreach ($items as $key=>$value) {
      if($value['plantID']==$plantID){
        return $value['due'];
        break;
      }    	
    }
    return false;
  }    
  
  public function getAlocatedSite($plantID,$date) {
    $query = "SELECT               
              sl.SiteName
              FROM PlantAllocation pa
              LEFT JOIN SiteList sl on pa.SiteID = sl.ID
              AND pa.StartDate <= '$date' AND pa.EndDate >= '$date'
              WHERE pa.PlantID = '$plantID'";
              
    $result = $this->conn->query($query);
    $row = $result->fetch_assoc();
    return $row['SiteName'];              
  }
  
  private function checkMaintenance($cycle,$interval,$hours,$plantID,$plantDesc) {
     if($this->getResult($cycle,$interval,$hours)<$this->notifyAdvance){
      return array(
        'due' => $this->getResult($cycle,$interval,$hours),
        'plantID' => $plantID,
        'plantDesc' => $plantDesc,
        'actualHours' => $hours
      );                                                                
    } else {
      return false;
    }  
  }
  
  private function getResult($cycle,$interval,$hours) {
    return $this->getNextMaintenance($cycle,$interval)-$hours;
  }
  
  private function getNextMaintenance($cycle,$interval) {
    return ($cycle+1)*$interval;
  }
  
  private function getMaxHours($plantID) {
    $query = "SELECT 
              MAX(pu.EndHours) as maxHours
              FROM PlantUtilization pu
              LEFT JOIN PlantAllocation pa on pu.PlantAllocationID = pa.ID
              WHERE pa.PlantID = '$plantID' ";
    $result = $this->conn->query($query);
    $row = $result->fetch_assoc();
    return $row['maxHours'];              
  }
  
  public function processMaintenance($plantID,$comments) {
    //cleanup
    $pID = htmlspecialchars($plantID);
    $cmnt = htmlspecialchars($comments);
    $actualHours = $this->getMaxHours($plantID);
    $date = date("Y-m-d");
    
    //add data into the log
    $insert = "INSERT INTO `MaintenanceLog`(`PlantID`, `LastMaintenance`, `DateFor`, `Comment`) VALUES ('$pID','$actualHours','$date','$cmnt')";
    $result = $this->conn->query($insert);
    
    //update data in maintenance
    $update = "UPDATE Maintenance SET CycleCount = CycleCount+1 WHERE PlantID='$pID'";    
    $result = $this->conn->query($update);    
    return true;
  }

}

?>