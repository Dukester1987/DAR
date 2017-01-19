<?php
  $server = "localhost";
  $db = "sopsioco_DARTEST";
  $user = "sopsioco_duke";
  $pass = "chapadlo";
  
  $currentDate = date("Y-m-d"); 
  if($_POST["sent"]=="ok"){
    $dateFrom = $_POST["date1"];
    $dateTo = $_POST["date2"];    
  } else {
    if(empty($_GET['dateFrom']) && empty($_GET['dateTo'])){
      $dateFrom = $currentDate;
      $dateTo = $currentDate;   
    } else {
      $dateFrom = $_GET['dateFrom'];
      $dateTo = $_GET['dateTo'];
    }
  }
  
  $conn = new mysqli($server,$user,$pass,$db);
  
  if($conn->connect_error) {
    die("Connection failed");
  }
  ?>