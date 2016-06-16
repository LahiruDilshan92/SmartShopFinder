<?php

require "init.php";
$user_name = $_POST["user_name"];

$sql_query = "show tables;";

$result = mysqli_query($con, $sql_query);

if(mysqli_num_rows($result)>0){
	while($row = mysqli_fetch_assoc($result)){
		$pass[] = $row;
	}
	echo "Pass = ".$pass."";
} else{
	echo "No Info is available";
}

print(json_encode($output));

?>