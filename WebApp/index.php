
<?php
	$lat1 = 7.2565982;  // set origin latitude
	$lon1 = 80.5944206; // and longitude
	$lat2 = 7.2677092; // set destination latitude
	$lon2 = 80.5611016; // and longitude

	$json = file_get_contents('https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins='.strval($lat1).','.strval($lon1).'&destinations='.strval($lat2).'%2C'.strval($lon2).'&key=AIzaSyAyOE0-MuGvLhmtsiStp72Hkvvp66FP49g');

	$obj = json_decode($json,true);
	$distance = $obj['rows'][0]['elements'][0]['distance']['value']; // got distance to a variable in meters

	echo $distance;
?>

