<?php
	$CourseId = $_GET["String1"];
	$DB_User = "root";
	$DB_Pass = "";
	$DB_Host = "localhost";
	$DB_Name = "quizappdb";
	$con = mysqli_connect($DB_Host,$DB_User,$DB_Pass,$DB_Name);
	$sql = "DELETE FROM course WHERE CId like '$CourseId'";
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
