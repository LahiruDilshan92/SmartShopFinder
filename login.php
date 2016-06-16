<?php

require "init.php";
$user_name = $_POST["user_name"];
$user_pass = $_POST["user_pass"];

$sql_query = "select * from locations where Owner = '$user_name' and password = '$user_pass';";

$result = mysqli_query($con, $sql_query);

if(mysqli_num_rows($result)>0){
	echo "Successful";
} else{
	echo "Invalid Login";
}

?>