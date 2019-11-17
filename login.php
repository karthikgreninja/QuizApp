<?php
require "init.php";
$rollno = $_POST["login_roll"];
$password = $_POST["login_pass"];
$sql_query = "select RollNo from student where RollNo like '$rollno' and Password like '$password';";
$result = mysqli_query($con,$sql_query);
if(mysqli_num_rows($result) > 0)
{
	$row = mysqli_fetch_assoc($result);
	$roll = $row["RollNo"];
	echo "Login success......Welcome ".$roll;
}
else
{
	echo "Login failed.....Try again";
}




?>
