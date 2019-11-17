<?php
require "init.php";
$user_roll = $_POST["roll"];
$password = $_POST["pass"];
$sql_query = "insert into student values ('$user_roll','$password')";
if(mysqli_query($con,$sql_query))
{
	//echo "<h3>Insertion success</h3>";
}
else
{
	//echo "Data insertion failed".mysqli_error($con);
}





?>
