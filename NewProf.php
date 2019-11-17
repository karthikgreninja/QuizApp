<?php
	$ProfId = $_GET["String1"];
	$Fname = $_GET["String2"];
	$Mname = $_GET["String3"];
	$Lname = $_GET["String4"];
	$Pass = $_GET["String5"];
	$Dept = $_GET["String6"];
	$DB_User = "root";
	$DB_Pass = "";
	$DB_Host = "localhost";
	$DB_Name = "quizappdb";
	$con = mysqli_connect($DB_Host,$DB_User,$DB_Pass,$DB_Name);
	$sql = "INSERT INTO professor (PId, Fname, Mname, Lname, Password, DeptName) VALUES ('$ProfId','$Fname','$Mname','$Lname','$Pass','$Dept')";
	mysqli_query($con,$sql);
	if(mysqli_affected_rows($con) >0)
	{
		echo "Success";
	}
	else
	{
		echo "Failed.....Check the values entered";
	}
	mysqli_close($con);

?>
