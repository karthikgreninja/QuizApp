<?php
	$QId = $_GET["String1"];
	$QStatement = $_GET["String2"];
	$Option1 = $_GET["String3"];
	$Option2 = $_GET["String4"];
	$Option3 = $_GET["String5"];
	$Option4 = $_GET["String6"];
	$Answer = $_GET["String7"];
	$TId = $_GET["String8"];
	$DB_User = "root";
	$DB_Pass = "";
	$DB_Host = "localhost";
	$DB_Name = "quizappdb";
	$con = mysqli_connect($DB_Host,$DB_User,$DB_Pass,$DB_Name);
	$sql = "INSERT INTO question (QId, QStatement, Option1, Option2, Option3, Option4, Answer, TId) VALUES ('$QId','$QStatement','$Option1','$Option2','$Option3','$Option4','$Answer','$TId')";
	mysqli_query($con,$sql);
	if(mysqli_affected_rows($con) >0)
	{
		echo "Success";
	}
	else
	{
		echo "The questions have already been inserted";
	}
	mysqli_close($con);
?>
