<?php
	$Roll = $_GET["String1"];
	$DB_User = "root";
	$DB_Pass = "";
	$DB_Host = "localhost";
	$DB_Name = "quizappdb";
	$con = mysqli_connect($DB_Host,$DB_User,$DB_Pass,$DB_Name);
	$sql = "SELECT * from student_takes_course natural join test where RollNo like '$Roll' and Grade = ' ' and TDate = date(now()) and time(TStarttime) <= time(now()) and BatchId = Batch and TId not in (select TId from student_attempts_test where Rno = '$Roll');";
	$result = mysqli_query($con,$sql);
	while($e = mysqli_fetch_assoc($result)){
		$output[]=$e;
	}
	echo json_encode($output);
	mysqli_close($con);
?>
