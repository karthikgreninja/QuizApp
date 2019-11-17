<?php
	$Course = $_GET["String1"];
	$Batch = $_GET["String2"];
	$DB_User = "root";
	$DB_Pass = "";
	$DB_Host = "localhost";
	$DB_Name = "quizappdb";
	$con = mysqli_connect($DB_Host,$DB_User,$DB_Pass,$DB_Name);
	$sql = "SELECT DISTINCT TId as TID FROM student_attempts_test natural join student_takes_course WHERE CId like '$Course' and BatchId like '$Batch';";
	$result = mysqli_query($con,$sql);
	while($e = mysqli_fetch_assoc($result)){
		$output[]=$e;
	}
	echo json_encode($output);
	mysqli_close($con);
?>
