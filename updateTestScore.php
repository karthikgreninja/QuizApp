<?php
	$RollNo = $_GET["String1"];
	$CId = $_GET["String2"];
	$TId = $_GET["String3"];
	$Score = $_GET["String4"];
	$DB_User = "root";
	$DB_Pass = "";
	$DB_Host = "localhost";
	$DB_Name = "quizappdb";
	$con = mysqli_connect($DB_Host,$DB_User,$DB_Pass,$DB_Name);
	$sql = "INSERT INTO student_attempts_test (Rno,CId,TId,TestScore) VALUES ('$RollNo','$CId','$TId','$Score')";
	mysqli_query($con,$sql);
	mysqli_close($con);
?>
