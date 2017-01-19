<?php

//column setup
$column = array(
  'ID' => array(
    'dbcolumn' => 'ID',
    'canSort' => false
  ),
  'Product Name' => array(
    'dbcolumn' => 'ProductName',
    'canSort' => true
  ),
  'Direction' => array(
    'dbcolumn' => 'Direction',
    'canSort' => true
  ),
  'Qty' => array(
    'dbcolumn' => 'Amount',
    'canSort' => true,
    'align' => 'text-right'
  ),    
  'Income' => array(
    'dbcolumn' => 'PriceExGST',
    'canSort' => true,
    'align' => 'text-right'
  )    
);

setlocale(LC_MONETARY, 'en_US');

if(!empty($_GET['orderby'])){
  $orderby = $_GET['orderby'];
  $sort = $_GET['sort'];      
} else {
  $orderby = "Direction";
  $sort = "ASC";
}

$sql = "SELECT
p.ProductName,
s.Direction,
sum(s.Amount) as Amount,
sum(s.`PriceIncGST`) as PriceIncGST,
sum(s.`PriceExGST`) as PriceExGST
FROM `Sales` s 
LEFT JOIN Products p on p.ID = s.ProductID
WHERE s.DateFor BETWEEN '$dateFrom' AND '$dateTo'
$addQry
GROUP BY p.ProductName, s.Direction
ORDER BY $orderby $sort";  
  
$result = $conn->query($sql);
$b = new Budget($conn); 
$budget = $b->getTotalBudget($dateFrom,$dateTo,$_GET["site"]);

//time setup
$today = $b->dw->getToday();
$yesterday = $b->dw->adjustDate($today,-1);
$lastWorkingDay = $b->dw->getLastWorkingDay($yesterday);
$beginingOfTheMonth = $b->dw->getBeginingOfTheMonth($today);

//$lastWorkingDay = $b->dw->dayOfWeek($yesterday);
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
       <a class="btn btn-primary" href="?dateFrom=<?=$lastWorkingDay;?>&dateTo=<?=$lastWorkingDay;?>&page=<?=$_GET['page'];?>&site=<?=$_GET['site'];?>">Previous working day</a>
       <a class="btn btn-primary" href="?dateFrom=<?=$beginingOfTheMonth;?>&dateTo=<?=$today;?>&page=<?=$_GET['page'];?>&site=<?=$_GET['site'];?>">Month</a>
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
      $totAmount += $row["Amount"];
      $price1 += $row["PriceIncGST"];
      $price2 += $row["PriceExGST"]; 
    ?>

  <tr <?=$err?"class=\"danger\"":"";?>>
    <th scope="row"><?=$i;?></td>
    <td><?=$row["ProductName"];?></td>
    <td><?=$row["Direction"];?></td>
    <td class="text-right"><?=number_format($row["Amount"],2, '.', ',');?></td>
    <td class="text-right"><?=money_format('%.2n',$row["PriceExGST"]);?></td>
  </tr>
      
      <?php
      $i++;
      }
   ?>
   <tr>
    <th colspan="3">TOTAL</th>
    <th class="text-right"><?=number_format($totAmount, 2, '.', ',');?></th>
    <th class="text-right"><?=money_format('%.2n',$price2);?></th>  
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
     <div class="col-xs-4">
      <table class="table table-condensed table-hover table-striped">
        <thead>
          <tr>
            <th class="text-left">Total income</th>
            <th class="text-left">Budget</th>
            <th class="text-left">Variaton</th>
            <th></th>
          </tr>
        </thead>
        <tbody>        
          <tr class="<?=($price2-$budget>0)?"success":"danger";?>">
            <td class="text-left"><?=money_format('%.2n',$price2);?></td>
            <td class="text-left"><?=money_format('%.2n',$budget);?></td>
            <td class="text-left"><?=money_format('%.2n',$price2-$budget);?></td>
            <td class="justify"><img src="res/<?=($price2-$budget>0)?"OK":"No-entry";?>.png"></td>
          </tr>
        </tbody>
      </table>      
     </div>
     