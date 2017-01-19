<?php
class DateWrapper {

  public $holidays;
  private $date;
  
  public function __construct($holidays){
    $this->holidays = $holidays;
    date_default_timezone_set ("Australia/Sydney");    
  }
  
  public function getToday() {
    return date("Y-m-d");
  }
  
  public function adjustDate($date,$days=null,$hours=null,$minutes=null,$seconds=null) {
    $date = date("Y-m-d",strtotime($date));
    if(!is_null($days)){
       $date = date("Y-m-d",strtotime("+$days days"));      
    }  
    if(!is_null($hours)){
       $date = date("Y-m-d",strtotime("+$hours hours"));      
    }  
    if(!is_null($minutes)){
       $date = date("Y-m-d",strtotime("+$minutes minutes"));      
    }
    if(!is_null($seconds)){
       $date = date("Y-m-d",strtotime("+$seconds seconds"));      
    }     
    
    return $date;            
  }
  
  
  public function dayOfWeek($date){
    return date("w",strtotime($date));    
  }
  
  public function getLastWorkingDay($date) {
    if($this->dayOfWeek($date)==7){
      return($this->adjustDate($date,-1));
    } else {
      return $date;
    }    
  }
  
  public function getEndOfTheMonth($date) {
    $date = date("Y-m-d",strtotime($date));
    $d = date_parse_from_format("Y-m-d", $date);
    $lastday = date("t",mktime(0,0,0,$d["month"],1,$d["year"])); 
    return date("Y-m-d",mktime(0,0,0,$d["month"],$lastday,$d["year"]));
  }
  
  public function getBeginingOfTheMonth($date) {
    $date = date("Y-m-d",strtotime($date));  
    $d = date_parse_from_format("Y-m-d",$date);
    return date("Y-m-d",mktime(0,0,0,$d["month"],1,$d["year"]));
  }
  
  public function getNextMonth($date){
    $date = date("Y-m-d",strtotime($date));
    $date = strtotime(date("Y-m-d", strtotime($date)) . " +1 month");
    return date("Y-m-d",$date);  
  }
  
  public function getPricePerDay($amount, $date){
    $date = date("Y-m-d",strtotime($date));
    $total = $this->getWorkingDays($this->getBeginingOfTheMonth($date),$this->getEndOfTheMonth($date));
    return $amount/$total;
  }
  
  public function getAmountOfMonths($startDate,$endDate){
    $ts1 = strtotime($startDate);
    $ts2 = strtotime($endDate);
    
    $year1 = date('Y', $ts1);
    $year2 = date('Y', $ts2);
    
    $month1 = date('m', $ts1);
    $month2 = date('m', $ts2);
    
    return (($year2 - $year1) * 12) + ($month2 - $month1);   
  }
  
  public function getWorkingDays($startDate,$endDate){
      // do strtotime calculations just once
      $holidays=$this->holidays;
      $endDate = strtotime($endDate);
      $startDate = strtotime($startDate);
  
  
      //The total number of days between the two dates. We compute the no. of seconds and divide it to 60*60*24
      //We add one to inlude both dates in the interval.
      $days = ($endDate - $startDate) / 86400 + 1;
  
      $no_full_weeks = floor($days / 7);
      $no_remaining_days = fmod($days, 7);
  
      //It will return 1 if it's Monday,.. ,7 for Sunday
      $the_first_day_of_week = date("N", $startDate);
      $the_last_day_of_week = date("N", $endDate);
  
      //---->The two can be equal in leap years when february has 29 days, the equal sign is added here
      //In the first case the whole interval is within a week, in the second case the interval falls in two weeks.
      if ($the_first_day_of_week <= $the_last_day_of_week) {
          if ($the_first_day_of_week <= 6 && 6 <= $the_last_day_of_week) $no_remaining_days--;
          if ($the_first_day_of_week <= 7 && 7 <= $the_last_day_of_week) $no_remaining_days--;
      }
      else {
          // (edit by Tokes to fix an edge case where the start day was a Sunday
          // and the end day was NOT a Saturday)
  
          // the day of the week for start is later than the day of the week for end
          if ($the_first_day_of_week == 7) {
              // if the start date is a Sunday, then we definitely subtract 1 day
              $no_remaining_days--;
  
              if ($the_last_day_of_week == 6) {
                  // if the end date is a Saturday, then we subtract another day
                  $no_remaining_days--;
              }
          }
          else {
              // the start date was a Saturday (or earlier), and the end date was (Mon..Fri)
              // so we skip an entire weekend and subtract 2 days
              $no_remaining_days -= 2;
          }
      }
  
      //The no. of business days is: (number of weeks between the two dates) * (5 working days) + the remainder
  //---->february in none leap years gave a remainder of 0 but still calculated weekends between first and last day, this is one way to fix it
     $workingDays = $no_full_weeks * 5;
      if ($no_remaining_days > 0 )
      {
        $workingDays += $no_remaining_days;
      }
  
      //We subtract the holidays
      foreach($holidays as $holiday){
          $time_stamp=strtotime($holiday);
          //If the holiday doesn't fall in weekend
          if ($startDate <= $time_stamp && $time_stamp <= $endDate && date("N",$time_stamp) != 6 && date("N",$time_stamp) != 7)
              $workingDays--;
      }
  
      return $workingDays;
  }  
  
}

?>