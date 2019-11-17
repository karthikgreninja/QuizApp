<?php
	$TId = $_GET["String1"];
	$Batch = $_GET["String2"];
	$Course = $_GET["String3"];
	$DB_User = "root";
	$DB_Pass = "";
	$DB_Host = "localhost";
	$DB_Name = "quizappdb";
	$con = mysqli_connect($DB_Host,$DB_User,$DB_Pass,$DB_Name);
	$sql = "SELECT * from student_attempts_test inner join student_takes_course where student_takes_course.RollNo = student_attempts_test.Rno and student_attempts_test.TId = '$TId' and student_takes_course.CId = '$Course' and BatchId = '$Batch';";
	$result = mysqli_query($con,$sql);
	while($e = mysqli_fetch_assoc($result)){
		$output[]=$e;
	}
	echo json_encode($output);
	mysqli_close($con);
?>
