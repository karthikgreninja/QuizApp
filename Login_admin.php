<?php
require "init.php";
$rollno = $_POST["login_roll"];
$password = $_POST["login_pass"];
$sql_query = "select * from admin where LoginId like '$rollno' and Password like '$password';";
$result = mysqli_query($con,$sql_query);
if(mysqli_num_rows($result) > 0)
{
	$row = mysqli_fetch_assoc($result);
	echo "Login success......Welcome Admin ";
}
else
{
	echo "Login failed.....Try again";
}




?>
