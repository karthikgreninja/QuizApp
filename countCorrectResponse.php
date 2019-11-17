<?php
	$RollNo = $_GET["String1"];
	$TId = $_GET["String2"];
	$DB_User = "root";
	$DB_Pass = "";
	$DB_Host = "localhost";
	$DB_Name = "quiz";
	$con = mysqli_connect($DB_Host,$DB_User,$DB_Pass,$DB_Name);
	$sql = "SELECT count(*) from test natural join question inner join student_response on question.QId=student_response.QId and question.Answer=student_response.Response where RNo = '$RollNo' and TId = '$TId';";
	$result = mysqli_query($con,$sql);
	while($e = mysqli_fetch_assoc($result)){
		$output[]=$e;
	}
	echo json_encode($output);
	mysqli_close($con);
?>
