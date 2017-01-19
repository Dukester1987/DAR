<?
require_once("DateWrapper.php");
require_once("Budget.php");
require_once("../db.php");

 $b = new Budget($conn);
 
 $date1 = "2016-08-05";
 $date2 = "2016-10-28";

 echo "$".number_format($b->getTotalBudget($date1,$date2,1),2);
   
?>