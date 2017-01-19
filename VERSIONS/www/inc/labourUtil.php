<?php

//column setup
$column = array(
  'ID' => array(
    'dbcolumn' => 'ID',
    'canSort' => false
  ),
  'Labour Name' => array(
    'dbcolumn' => 'LaborName',
    'canSort' => true    
  ),
  'Hours' => array(
    'dbcolumn' => 'Hours',
    'canSort' => true,
    'align' => 'text-right'
  )
);

if(!empty($_GET['orderby'])){
  $orderby = $_GET['orderby'];
  $sort = $_GET['sort'];      
} else {
  $orderby = "LaborName";
  $sort = "ASC";
}

$sql = "SELECT
ll.LaborName,
SUM(lu.Hours) as Hours
FROM LaborAllocation la
INNER JOIN LaborUtilization lu on lu.LaborAllocationID = la.ID
AND lu.DateFor BETWEEN '$dateFrom' AND '$dateTo'
LEFT JOIN LaborList ll on la.LaborID = ll.ID
WHERE la.EndDate>='$dateFrom' AND la.StartDate<=la.EndDate $addQry
GROUP BY ll.LaborName
ORDER BY $orderby $sort";
  
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
     <div class="col-xs-12">
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
      $err = false;
      $i=1;        
      while($row = $result->fetch_assoc()){
      $totQty += $row["Hours"]; 
    ?>

  <tr <?=$err?"class=\"danger\"":"";?>>
    <th scope="row"><?=$i;?></td>
    <td><?=$row["LaborName"];?></td>
    <td class="text-right"><?=$row["Hours"];?>h</td>
  </tr>
      
      <?php
      $i++;
      }
   ?>
   <tr>
    <th colspan="2">TOTAL</th>
    <th class="text-right"><?=$totQty;?>h</th>
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