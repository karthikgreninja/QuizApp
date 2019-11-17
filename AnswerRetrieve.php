<?php
	$RollNo = $_GET["String1"];
	$QId = $_GET["String2"];
	$DB_User = "root";
	$DB_Pass = "";
	$DB_Host = "localhost";
	$DB_Name = "quizappdb";
	$con = mysqli_connect($DB_Host,$DB_User,$DB_Pass,$DB_Name);
	$sql = "SELECT * FROM student_response WHERE Rno = '$RollNo' AND QId = '$QId';";
	$result = mysqli_query($con,$sql);
	while($e = mysqli_fetch_assoc($result)){
		$output[]=$e;
	}
	echo json_encode($output);
	mysqli_close($con);
?>
