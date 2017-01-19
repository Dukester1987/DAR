  <?php

class Budget {
  public $dw;
  public $conn;

  public function __construct($conn) {    
    $this->conn = $conn;
    $this->dw = new DateWrapper($this->getHoliday());    
    //echo "initialized<br>";        	
  }

  public function getBudget($date, $siteID=null){
    $date = $this->dw->getBeginingOfTheMonth($date);
    $groupBy="";
    $where = "WHERE DateFor='$date'";
    if(isset($siteID)){ //group it for all sites
      if($siteID!=0){
       $groupBy = "SiteID,";
       $where .= " AND SiteID=$siteID";  
      }     
    }
    $query = sprintf('SELECT %1$s DateFor, SUM(Budget) AS Budget FROM `CompanyBudget` %2$s GROUP BY %1$s DateFor',$groupBy,$where);     
    $result = $this->conn->query($query);
    
    $row = $result->fetch_assoc(); 
    return $row["Budget"];
  
           
  }
  
  private function getHoliday(){
    $query = "SELECT `Holiday` FROM `Holidays`";
    $result = $this->conn->query($query);
    $i=0;
    while($row = $result->fetch_assoc()){
      $holidays[$i] = $row["Holiday"];
      $i++;
    }
    return $holidays;
  }
  
  public function getTotalBudget($date1,$date2,$siteID = null){
    $theMonth = $this->dw->getBeginingOfTheMonth($date1);
    for($i=0;$i<=$this->dw->getAmountOfMonths($date1,$date2);$i++){
      $pricePerDay = $this->dw->getPricePerDay($this->getBudget($theMonth,$siteID),$theMonth);    
      if($i==0 && $i==$this->dw->getAmountOfMonths($date1,$date2)){
        $workingDays = $this->dw->getWorkingDays($date1,$date2);
        $part = "Start & End";    
      }else if($i==0){ // first month
        $workingDays = $this->dw->getWorkingDays($date1,$this->dw->getEndOfTheMonth($date1));      
        $part = "start"; 
      } else if($i==$this->dw->getAmountOfMonths($date1,$date2)){  // last month
        $workingDays = $this->dw->getWorkingDays($this->dw->getBeginingOfTheMonth($date2),$date2);
        $part = "end";
      } else { //middle months            
        $workingDays = $this->dw->getWorkingDays($this->dw->getBeginingOfTheMonth($theMonth),$this->dw->getEndOfTheMonth($theMonth));
        $part = "middle";      
      }
      //echo "Amount of days: $workingDays for part $part Budget for this instance is: ($pricePerDay) ".$workingDays*$pricePerDay." / ".$this->getBudget($theMonth,$siteID)." ($theMonth)<br>";
      $total += $workingDays*$pricePerDay;
      $theMonth = $this->dw->getNextMonth($theMonth);
    }
    return $total;    
  }

}

?>