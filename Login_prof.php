<?php
require "init.php";
$rollno = $_POST["login_roll"];
$password = $_POST["login_pass"];
$sql_query = "select * from professor where PId like '$rollno' and Password like '$password';";
$result = mysqli_query($con,$sql_query);
if(mysqli_num_rows($result) > 0)
{
	$row = mysqli_fetch_assoc($result);
	$roll = $row["Fname"];
	echo "Login success......Welcome professor ".$roll;
}
else
{
	echo "Login failed.....Try again";
}




?>
