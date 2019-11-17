<?php
require "init.php";
$rollno = $_GET["String1"];
$sql_query = "update student set LoggedStatus = 'Yes' where RollNo like '$rollno' and LoggedStatus = 'No';";
$result = mysqli_query($con,$sql_query);
if(mysqli_affected_rows($con) >0)
{
	echo "Login success......Welcome ".$rollno;
}
else
{
	echo "Already logged in!!!";
}





?>
