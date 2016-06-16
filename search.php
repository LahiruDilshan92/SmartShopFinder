<?php

require "init.php";

$name = $_POST["name"];
$price = $_POST["price"];
$category = $_POST["category"];
$time = $_POST["time"];
$lat1 = $_POST["lat1"];
$lon1 = $_POST["lon1"];

//$sql_query = "select Locations.Latitude, Locations.Longitude from locations where Shop_Name = (select Shop_Name from $category where Price < $price and Name = '$name') and openingtime < $time and closingtime > $time;";
$sql_query = "select Locations.Latitude, Locations.Longitude from Locations inner join(select shop_name from $category where price < $price and name = '$name') as shops on shops.shop_name = locations.shop_name and openingtime < $time and closingtime > $time;";
//$sql_query = "select Locations.Latitude, Locations.Longitude from locations;";

$result = mysqli_query($con, $sql_query);
$rows[] = array();

$latitude = 0;
$longitude = 0; 
$distance = 0;

$min = 1000000;
$latans = 0;
$lonans = 0;

if(mysqli_num_rows($result)>0){
	while($row = mysqli_fetch_assoc($result)){
		$latitude = strval($row['Latitude']);
		$longitude = strval($row['Longitude']);

		$json = file_get_contents('https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins='.strval($lat1).','.strval($lon1).'&destinations='.$latitude.'%2C'.$longitude.'&key=AIzaSyAyOE0-MuGvLhmtsiStp72Hkvvp66FP49g');
		//sleep(10);
		$obj = json_decode($json,true);
		$distance2 = $obj['rows'][0]['elements'][0]['distance']['value']; // got distance to a variable in meters

		if($distance2 < $min){
			$min = $distance2;
			$latans = $latitude;
			$lonans = $longitude;
		}
		//echo $row['Latitude'].":".$row['Longitude']." ";
		$rows[] = $row;
		//var_dump($json);
	}
	echo $latans." ".$lonans;
	//echo "Successful";
} else{
	echo "No info";
}

?>