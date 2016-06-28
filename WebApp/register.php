<?php
 
require "init.php";
$category = $_POST["category"];
$name = $_POST["name"];
$shop_name = $_POST["shop_name"];
$price = $_POST["price"];

//$sql_query = "insert into $category (Name, Price, Shop_Name) values('$name',$price,'$shop_name');";

$sql_query = "insert into $category (Name, Price, Shop_Name) values('$name', $price, '$shop_name');";

 
if(mysqli_query($con,$sql_query)){
    //echo "<h3>Data Insertion Success...</h3>";
} else{
    //echo "Data insertion error...".mysqli_error($con);
}
 
?>