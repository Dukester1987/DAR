  <?php
switch ($_GET["page"]){
  case "plantutilization":
    $req = "inc/plantUtil.php";
    break;
  case "labourutilization":
    $req = "inc/labourUtil.php";
    break;    
  case "productutilization":
    $req = "inc/prodUtil.php";
    break;
  case "Sales":
    $req = "inc/sales.php";
    break;    
  default:
    $req = "inc/plantUtil.php";
    $_GET["page"]="plantutilization";
    break;  
}

require_once "init.php";  
require "header.php";  
require $req;
require "footer.php";

$conn->close();

?>