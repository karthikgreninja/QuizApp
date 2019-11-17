<?php
	$RollNo = $_GET["String1"];
	$QId = $_GET["String2"];
	$Response = $_GET["String3"];
	$DB_User = "root";
	$DB_Pass = "";
	$DB_Host = "localhost";
	$DB_Name = "quizappdb";
	$con = mysqli_connect($DB_Host,$DB_User,$DB_Pass,$DB_Name);
	$sql = "UPDATE student_response SET Response ='$Response' WHERE Rno = '$RollNo' AND QId = '$QId'";
	mysqli_query($con,$sql);
	mysqli_close($con);
?>
