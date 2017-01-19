<?php

//column setup
$column = array(
  'ID' => array(
    'dbcolumn' => 'ID',
    'canSort' => false
  ),
  'Plant Number' => array(
    'dbcolumn' => 'PlantID',
    'canSort' => true
  ),
  'Plant Description' => array(
    'dbcolumn' => 'PlantDesc',
    'canSort' => true
  ),
  'Start Hours' => array(
    'dbcolumn' => 'StartHours',
    'canSort' => true,
    'align' => 'text-right'
  ),
  'End Hours' => array(
    'dbcolumn' => 'EndHours',
    'canSort' => true,
    'align' => 'text-right'
  ),   
  'Total Hours' => array(
    'dbcolumn' => 'totalHours',
    'canSort' => true,
    'align' => 'text-right'
  ),    
  'Fuel' => array(
    'dbcolumn' => 'Fuel',
    'canSort' => true,
    'align' => 'text-right'
  ),
  'Fuel consumption' => array(
    'dbcolumn' => 'fuelConsumption',
    'canSort' => true,
    'align' => 'text-right'
  ),
  'flag' => array(
    'dbcolumn' => 'flag',
    'canSort' => false,
    'align' => 'text-right'
  )           
);

$m = new Maintenance($conn);

// Maintenance control
if(isset($_POST['mPlantID']) && !empty($_POST['mPlantID'])){
  //process maintenance
  if($m->processMaintenance($_POST['mPlantID'],$_POST['mComments'])){
    $displaySuccess = "Maintenance cycle finished for: ".$_POST['mPlantID'];    
  }
}

$items = $m->getMaintenanceData();

if(!empty($_GET['orderby'])){
  $orderby = $_GET['orderby'];
  $sort = $_GET['sort'];      
} else {
  $orderby = "PlantID";
  $sort = "ASC";
}

$sql = "SELECT 
pa.PlantID,
pl.PlantDesc,
pa.SiteID,
MIN(pu.StartHours) as StartHours,
MAX(pu.EndHours) as EndHours,
MAX(pu.EndHours) - MIN(pu.StartHours)  as totalHours,
SUM(pu.Fuel) / (MAX(pu.EndHours) - MIN(pu.StartHours)) as fuelConsumption, 
sum(pu.Fuel) as Fuel
FROM `PlantAllocation` pa
INNER JOIN PlantUtilization pu on pa.ID = pu.PlantAllocationID AND pu.DateFor BETWEEN '$dateFrom' AND '$dateTo'
AND pu.StartHours<>0
left JOIN PlantList pl on PlantID = pl.ID
WHERE pa.StartDate<='$dateTo' $addQry
GROUP BY pa.PlantID,
pl.PlantDesc
ORDER BY $orderby $sort
";
  
  $result = $conn->query($sql);
  
?>
     <Div class="well">
     <div class="row"> 
     <div class="col-xs-12">    
     <form method="post" class="Form-inline">
      <div class="form-group">
        <label for="datefrom">from:</label>
        <input id="datefrom" class="dfrom" data-date-format="yyyy-mm-dd" type="text" name="date1" value="<?=$dateFrom;?>"> 
       </div>
       <div class="form-group">
        <label for="dateto">To:</label> 
        <input id="dateto" class="dto" type="text" data-date-format="yyyy-mm-dd" name="date2" value="<?=$dateTo;?>">
       </div>
       <div class="form-group">
        <input type="hidden" name="sent" value="ok"> <input class="btn btn-primary" type="submit" value="apply filter">
       </div>
      </form>
      </div>
      </div>
     </div>
     <?
      if(!empty($displaySuccess)){
     ?>
     <div class="alert alert-success">
      <button class="close" type="button" data-dismiss="alert">&times;</button>
      <?=$displaySuccess;?>
     </div>
     <?
      }
     ?>
     <div class="col-xs-12">
     <script type="text/javascript">
      $('document').ready(function(){
        $('.displayTooltip').tooltip();
      });
     </script>      
    <table class="table table-condensed table-hover table-striped">
    <thead>
    <tr>
      <?php
      foreach ($column as $key=>$value) {
        $sublink = "&sort=ASC";
        $caret = "";
        if($_GET["orderby"]==$value['dbcolumn']){
          if($_GET['sort']=="ASC"){
            $caret = "<span class=\"dropup\"><span class=\"caret\"></span></span>";
            $sublink = "&sort=DESC";
          } elseif($_GET['sort']=="DESC"){
            $caret = "<span class=\"caret\"></span>";
            $sublink = "&sort=ASC";
          }
        }
        ?>
        <th class="<?=$value["align"];?>"><?=$value['canSort']?"<a href=\"?page=".$_GET['page']."&site=".$_GET['site']."&orderby=".$value['dbcolumn'].$sublink."&dateFrom=$dateFrom&dateTo=$dateTo\">":"";?>
        <?=$key.$caret;?>
        <?=$value['canSort']?"</a>":"";?></th>
        <?      	
      }
      ?>
    </tr>      
    </thead>
  <tbody>    
    <?php                          
      if($result->num_rows > 0){
      $i=0;
      while($row = $result->fetch_assoc()){
      

      $MachineHours = $row["EndHours"]-$row["StartHours"]<0?0:$row["EndHours"]-$row["StartHours"];      
      $highlight = "";
      $image = "";
      if($row["Fuel"]>0 && $MachineHours == 0){
        $highlight = "class=\"danger displayTooltip\"";
        $tooltip = "Check Hours";
        $fuelConsumption = "Check Hours";
      } else if($row["Fuel"]==0 && $MachineHours == 0){      
        $fuelConsumption = "0";
      } else {
        $fuelConsumption = round($row["Fuel"]/$MachineHours,2)."   l/h";      
      }   
      
      $due = $m->checkItems($items,$row['PlantID']);
      
      if($due) {    
        if($due<0){
          $highlight = "class=\"danger\"";
          $total = $due*-1;
          $tooltip = "Maintenance overdue by $total hours!";
          $image = "<a href=\"#\" data-toggle=\"modal\" data-target=\"#modal-$i\"><img data-toggle=\"tooltip\" title=\"$tooltip\" class=\"displayTooltip\" src=\"res/Repair16.png\"></a>";
        } else {
          $highlight = "class=\"warning\"";
          $tooltip = "Maintenance due in $due hours!";
          $image = "<a href=\"#\" data-toggle=\"modal\" data-target=\"#modal-$i\"><img data-toggle=\"tooltip\" title=\"$tooltip\" class=\"displayTooltip\" src=\"res/Wrench16.png\"></a>";
        }
        ?>
          <div class="modal" id="modal-<?=$i;?>">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <button class="close" type="button" data-dismiss="modal">&times;</button>
                  <h3>Process the mainenance for plant <?=$row['PlantID'];?></h3>
                </div>
                <div class="modal-body">
                  <form role="form" method="POST">
                  <div class="form-group">
                    <label for="name">Maintenance details:</label>
                    <textarea name="mComments" class="form-control" id="name" rows="5"></textarea>
                    <br>
                    <input type="hidden" name="mPlantID" value="<?=$row['PlantID'];?>">
                    <input type="hidden" name="mDone" value="true">                     
                    <input type="submit" class="btn btn-primary" value="Finish maintenance">                    
                  </div>
                  </form>
                </div>
              </div>
            </div>
          </div>
        <?      
      }      
      
      $totalFuel += $row["Fuel"];
      $totalHours += $MachineHours; 
      $totalFuelConsumption += $fuelConsumption;      
       
    ?>

  <tr <?=$highlight;?> title="<?=$tooltip;?>" data-toggle="tooltip">
    <th scope="row"><?=$i;?></td>
    <td><?=$row["PlantID"];?></td>
    <td><?=$row["PlantDesc"];?></td>
    <td class="text-right"><?=$row["StartHours"]==""?0:number_format($row["StartHours"],2);?></td>
    <td class="text-right"><?=$row["EndHours"]==""?0:number_format($row["EndHours"],2);?></td>
    <td class="text-right"><?=$row["totalHours"]==""?0:number_format($row["totalHours"],2);?>h</td>
    <td class="text-right"><?=$row["Fuel"]==""?0:number_format($row["Fuel"],2);?>l</td>
    <td class="text-right"><?=$row["fuelConsumption"]==""?0:number_format($row["fuelConsumption"],2);?> l/h</td>
    <td class="text-right"><?=$image;?></td>
  </tr>
      
      <?php
      $i++;
      }
   ?>
   <tr>
    <th colspan="5">TOTAL</th>
    <th class="text-right"><?=number_format($totalHours);?>h</th>
    <th class="text-right"><?=number_format($totalFuel);?>l</th>
    <th class="text-right"><?=number_format($totalFuelConsumption);?> <small>liter/hour</small></th>
    <th></th>
   </tr>
   <?php
    } else {
    ?>
      <tr>
        <td colspan="7">No results</td>
      </tr>
    <?php
    }  
    ?>
    </tbody>
    </table>  
     </div>