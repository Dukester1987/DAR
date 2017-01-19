<?php
  $link["plantutilization"] = array('linkdesc' => 'Plant Utilization');
  $link["labourutilization"] = array('linkdesc' => 'Labour Utilization');
  $link["productutilization"] = array('linkdesc' => 'Production');
  $link["Sales"] = array('linkdesc' => "Sales");
  $link["Stock"] = array('linkdesc' => "Stock",'badge' => 'not implemented');
  
  $query = "SELECT `ID`,`SiteName` FROM `SiteList`";
  $result = $conn->query($query);
  
  $site = isSet($_GET["site"])?$_GET["site"]:0;

  switch($site){
    case 0:
      $addQry = "";
      break;
    default:
      $addQry = "AND ".$jj."SiteID = $site";
      break;
  }
  
  $page = $_GET['page'];
  $longLink = "&page=$page&dateFrom=$dateFrom&dateTo=$dateTo";  
  while($row = $result->fetch_assoc()){
    if($row['ID']==$_GET['site']){
      $actualSiteName = $row['SiteName'];
      break;
    } else {
      $actualSiteName = "All sites";
    }
  }
  
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=720">
    <title>eDAR Reporting v1.1.2</title>
    <link rel="stylesheet" href="css/bootstrap.css" type="text/css" />
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
    <link rel="stylesheet" href="css/datepicker.css" type="text/css" />
    <script src="js/jquery-3.1.1.min.js"></script>
    <script src="js/bootstrap-datepicker.js"></script>
    <script src="js/bootstrap.js"></script>   
    <script>
      $(function(){
       $('.dfrom').datepicker();
      });
      $(function(){
       $('.dto').datepicker();
      });      
    </script>     
  </head>
  <body>
     <div class="Container">
     <div class="row">
       <nav class="navbar navbar-default">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#mynavbar-colapse-1">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>  
          </button>
          <a class="navbar-brand" href="#">eDAR v1.1.1</a>
        </div>
        <div class="collapse navbar-collapse" id="mynavbar-colapse-1">
          <ul class="nav navbar-nav">
            <?php
            foreach ($link as $key=>$value) {                    
            	?>
              <li <?=$_GET["page"]==$key?"class=\"active\"":"";?>><a href="?page=<?=$key."&site=$site&dateFrom=$dateFrom&dateTo=$dateTo";?>"><?=$value["linkdesc"];?>
              <?=$value["badge"]!=""?"<span class=\"badge\">".$value["badge"]."</span>":"";?>
              </a></li>
              <?php
            }      
            ?>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li>
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Select site <i>(<?=$actualSiteName;?>)</i><span class="caret"></span></a>
                <ul class="dropdown-menu">
                  <li <?=$_GET['site']==0?"class=\"active\"":"";?>><a href="?site=0<?=$longLink;?>">all sites</a></li>
                  <hr>
                  <?php
                    $result = $conn->query($query);
                    while($rows = $result->fetch_assoc()){
                    ?>
                    <li <?=$_GET['site']==$rows["ID"]?"class=\"active\"":"";?>><a href="?site=<?=$rows["ID"].$longLink;?>"><?=$rows["SiteName"];?></a></li>
                    <?php  
                    }                
                  ?>
                </ul>
            </li>
          </ul>          
        </div>
       </nav>