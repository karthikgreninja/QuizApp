<?php
	$TestId = $_GET["String1"];
	$DB_User = "root";
	$DB_Pass = "";
	$DB_Host = "localhost";
	$DB_Name = "quizappdb";
	$con = mysqli_connect($DB_Host,$DB_User,$DB_Pass,$DB_Name);
	$sql = "select TDuration from test , testtype where test.TType = testtype.TType and TId = '$TestId';";
	$result = mysqli_query($con,$sql);
	while($e = mysqli_fetch_assoc($result)){
		$output[]=$e;
	}
	echo json_encode($output);
	mysqli_close($con);
?>
