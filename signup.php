<?php
 
require "init.php";
$shop_name = $_POST["shop_name"];
$owner = $_POST["owner"];
$password = $_POST["password"];
$openingtime = $_POST["openingtime"];
$closingtime = $_POST["closingtime"];
$latitude = $_POST["latitude"];
$longitude = $_POST["longitude"];


//$sql_query = "insert into locations (Shop_Name, Owner, Password, openingtime, closingtime) values('$shop_name', '$owner', '$password', $openingtime, $closingtime);";

$sql_query = "insert into locations (Shop_Name, Owner, Password, openingtime, closingtime, Latitude, Longitude) values('$shop_name', '$owner', '$password', $openingtime, $closingtime, $latitude, $longitude);";

 
if(mysqli_query($con,$sql_query)){
    //echo "<h3>Data Insertion Success...</h3>";
} else{
    //echo "Data insertion error...".mysqli_error($con);
}
 
?>