<?php
$start = round(microtime(true) * 1000);
require_once("db.php");
require_once("bin/Maintenance.php");

$m = new Maintenance($conn);
$plants = $m->getMaintenanceData();

echo "MAINTENANCE OVERDUE<BR>";
echo "<Table>";
echo "<TR><TH>Site</TH><TH>Hours</TH><TH>Plant ID</TH><TH>Plant Desc</TH><TH>hours due</TH></TR>";
foreach ($plants as $key=>$value) {
  if($value['due']<0){
    echo "<TR>";
    echo "<TD><i>".$m->getAlocatedSite($value['plantID'],date("Y-m-d"))."</i></td>";
    echo "<TD align=\"right\"><i>".number_format($value['actualHours'],0, '.', ',')."</i></TD>";
    echo "<TD><b>".$value['plantID'] ."</b></TD>";
    echo "<TD>". $value['plantDesc'] ."</TD>";
    echo "<TD align=\"right\">(<b>".  $value['due'] ."h</b>)</TD>";
    echo "</TR>";
  }
}
echo "<TR><TD>&nbsp;</TD></TR>";
echo "<TR><TD colspan=\"5\">MAINTENANCE WITHIN 50 HOURS</TD></TR>";
echo "<TR><TH>Site</TH><TH>Hours</TH><TH>Plant ID</TH><TH>Plant Desc</TH><TH>hours to maintenance</TH></TR>";
foreach ($plants as $key=>$value) {
  if($value['due']>0){
    echo "<TR>";
    echo "<TD><i>".$m->getAlocatedSite($value['plantID'],date("Y-m-d"))."</i></td>";
    echo "<TD align=\"right\"><i>".number_format($value['actualHours'],0, '.', ',')."</i></TD>";
    echo "<TD><b>".$value['plantID'] ."</b></TD>";
    echo "<TD>". $value['plantDesc'] ."</TD>";
    echo "<TD align=\"right\">(<b>".  $value['due'] ."h</b>)</TD>";
    echo "</TR>";        
  }
}
echo "</TABLE>";
$end = round(microtime(true)*1000);
$result = ($end-$start)/1000;
echo "<Table class=\"\"><tr><td>Summary done generate report took $result seconds</td></tr></table>";
//print_r($plants);

?>