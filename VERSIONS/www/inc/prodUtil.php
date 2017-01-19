<?php
$column = array(
  'ID' => array(
    'dbcolumn' => 'ID',
    'canSort' => false
  ),
  'Product Name' => array(
    'dbcolumn' => 'ProductName',
    'canSort' => true
  ),
  'Qty' => array(
    'dbcolumn' => 'Qty',
    'canSort' => true,
    'align' => 'text-right'
  )
);

if(!empty($_GET['orderby'])){
  $orderby = $_GET['orderby'];
  $sort = $_GET['sort'];      
} else {
  $orderby = "ProductName";
  $sort = "DESC";
}

if($_POST['Sellable']=="on"){
 $aditionalSelection = "AND ProductVal > 0";
 $checked = "checked";
 $active = "active";
}

$sql = "SELECT 
p.ProductName,
SUM(pu.Qty) as Qty,
tt.Description
FROM `ProductAllocation` pa
INNER JOIN Products p on p.ID = pa.ProductID
INNER JOIN ProductUtilization pu on pu.ProductAllocationID = pa.ID
AND pu.DateFor >= '$dateFrom' AND pu.DateFor <= '$dateTo'
LEFT JOIN TransactionTypes tt on pu.TransactionType = tt.ID
WHERE `StartDate`<= '$dateTo' $addQry $aditionalSelection
GROUP BY p.ProductName
ORDER BY $orderby $sort";
  
  $result = $conn->query($sql);
  
//time setup
$b = new Budget($conn); 
$today = $b->dw->getToday();
$yesterday = $b->dw->adjustDate($today,-1);
$lastWorkingDay = $b->dw->getLastWorkingDay($yesterday);
$beginingOfTheMonth = $b->dw->getBeginingOfTheMonth($today);  
  
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
       <div class="btn-group" data-toggle="buttons">
        <label class="btn btn-primary <?=$active;?>">
          <input type="checkbox" autocomplete="off" name="Sellable" <?=$checked;?>>Display sellable products</input>
        </label>
       </div>       
       <div class="form-group">
        <input type="hidden" name="sent" value="ok"> <input class="btn btn-primary" type="submit" value="apply filter">
       </div>
       <a class="btn btn-primary" href="?dateFrom=<?=$lastWorkingDay;?>&dateTo=<?=$lastWorkingDay;?>&page=<?=$_GET['page'];?>&site=<?=$_GET['site'];?>">Previous working day</a>
       <a class="btn btn-primary" href="?dateFrom=<?=$beginingOfTheMonth;?>&dateTo=<?=$today;?>&page=<?=$_GET['page'];?>&site=<?=$_GET['site'];?>">Month</a>       
      </form>
      </div>
      </div>
     </div>
     <div class="col-xs-12">
    <table class="table table-condensed table-hover table-striped">
    <thead>
    <th colspan="3" class="Success text-center text-uppercase">Production</th>
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
        if($row["Description"]=="Production"){
        $totQty += $row["Qty"]; 
      ?>
  
    <tr <?=$err?"class=\"danger\"":"";?>>
      <th scope="row"><?=$i;?></td>
      <td><?=$row["ProductName"];?></td>
      <td class="text-right"><?=number_format($row["Qty"],2);?>t</td>
    </tr>
        
        <?php
        $i++;
        }
      }
   ?>
   <tr>
    <th colspan="2">TOTAL</th>
    <th class="text-right"><?=number_format($totQty,2);?>t</th>
   </tr>
   <thead>
   <tr>
   <th colspan="3" class="Success text-center text-uppercase">Used in production</th>
   </tr>
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
   <?php
    $i=1;
    $result = $conn->query($sql);
    $totQty = 0;
    while($row = $result->fetch_assoc()){
      if($row["Description"]!="Production"){
              $totQty += $row["Qty"]; 
      ?>
  
    <tr <?=$err?"class=\"danger\"":"";?>>
      <th scope="row"><?=$i;?></td>
      <td><?=$row["ProductName"];?></td>
      <td class="text-right"><?=number_format($row["Qty"],2);?>t</td>
    </tr>
        
        <?php
        $i++;
      }
    }
    ?>
   <tr>
    <th colspan="2" >TOTAL</th>
    <th class="text-right"><?=number_format($totQty,2);?>t</th>    
   </tr>
    <?
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