<?php
	$Course = $_GET["String1"];
	$RollNo = $_GET["String2"];
	$Batch = $_GET["String3"];
	$DB_User = "root";
	$DB_Pass = "";
	$DB_Host = "localhost";
	$DB_Name = "quizappdb";
	$con = mysqli_connect($DB_Host,$DB_User,$DB_Pass,$DB_Name);
	$sql = "SELECT * from Question where TId = (select TId from Test where CId = '$Course' and TDate = date(now()) and Batch = '$Batch' and TId not in (select TId from student_attempts_test where Rno = '$RollNo'));";
	$result = mysqli_query($con,$sql);
	while($e = mysqli_fetch_assoc($result)){
		$output[]=$e;
	}
	if(mysqli_num_rows($result) > 0)
	{
		echo json_encode($output);
	}
	else
	{
		echo json_encode(null);
	}
	mysqli_close($con);
?>
