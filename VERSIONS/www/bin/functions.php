<?php
class TableDesigner {

  private $column;
  private $sql;
  private $styles; 
  private $conn;
  
  public function __construct($column, $sql, $styles, $conn, $summary=true){
    $this->column = $column;
    $this->sql = $sql;
    $this->styles = $styles;
    $this->conn = $conn; 
    
    $this->initTable();
    $this->fillTable();
    if($summary){
      $this->createSummary();
    }
    $this->closeTable();
     
  }
  
  private function initTable(){
    ?>
      <table class="<?=$this->styles;?>">
      <thead>
      <tr>    
    <?
    foreach ($this->column as $key=>$value) {
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
      <th><?=$value['canSort']?"<a href=\"?page=".$_GET['page']."&site=".$_GET['site']."&orderby=".$value['dbcolumn'].$sublink."&dateFrom=$dateFrom&dateTo=$dateTo\">":"";?>
      <?=$key.$caret;?>
      <?=$value['canSort']?"</a>":"";?></th>    
      <?      	      
    }    
    ?>
      </tr>      
      </thead>  
    <?    
  }
  
  private function fillTable(){
    ?>
    <tbody>
    <?
        $result = $this->conn->query($this->$sql);
          if($result->num_rows > 0){
          $i=0;
          while($row = $result->fetch_assoc()){
          $MachineHours = $row["EndHours"]-$row["StartHours"]<0?0:$row["EndHours"]-$row["StartHours"];      
          $err = false;
          if($row["Fuel"]>0 && $MachineHours == 0){
            $err = true;
            $fuelConsumption = "Check Hours";
          } else if($row["Fuel"]==0 && $MachineHours == 0){      
            $fuelConsumption = "0";
          } else {
            $fuelConsumption = round($row["Fuel"]/$MachineHours,2)."   l/h";      
          }   
          
          $totalFuel += $row["Fuel"];
          $totalHours += $MachineHours; 
          $totalFuelConsumption += $fuelConsumption;      
           
        ?>
    
      <tr <?=$err?"class=\"danger\"":"";?>>
        <th scope="row"><?=$i;?></td>
        <td><?=$row["PlantID"];?></td>
        <td><?=$row["PlantDesc"];?></td>
        <td><?=$row["StartHours"]==""?0:number_format($row["StartHours"],2);?></td>
        <td><?=$row["EndHours"]==""?0:number_format($row["EndHours"],2);?></td>
        <td><?=$row["totalHours"]==""?0:number_format($row["totalHours"],2);?>h</td>
        <td><?=$row["Fuel"]==""?0:number_format($row["Fuel"],2);?>l</td>
        <td><?=$row["fuelConsumption"]==""?0:number_format($row["fuelConsumption"],2);?> l/h</td>
      </tr>
          
          <?php
          $i++;
          }    
    ?>
    </tbody>
    <?    
  }
  
    
}

?>