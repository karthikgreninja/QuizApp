<?php
	$RollNo = $_GET["String1"];
	$Course = $_GET["String2"];
	$DB_User = "root";
	$DB_Pass = "";
	$DB_Host = "localhost";
	$DB_Name = "quizappdb";
	$con = mysqli_connect($DB_Host,$DB_User,$DB_Pass,$DB_Name);
	$sql = "SELECT * FROM student_attempts_test inner join student_takes_course on student_takes_course.RollNo = student_attempts_test.Rno and student_attempts_test.CId = student_takes_course.CId where student_takes_course.CId = '$Course' and student_takes_course.RollNo = '$RollNo' and TId like '%Q2'";
	$result = mysqli_query($con,$sql);
	if(mysqli_affected_rows($con) >0)
	{
		echo "Success";
	}
	else
	{
		echo "Failed";
	}
	mysqli_close($con);
?>
