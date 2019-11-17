<?php
	$ID = $_GET["String1"];
	$DB_User = "root";
	$DB_Pass = "";
	$DB_Host = "localhost";
	$DB_Name = "ontologyappdb";
	$con = mysqli_connect($DB_Host,$DB_User,$DB_Pass,$DB_Name);
	$sql = "select * from ideas where ID like '$ID'";
	mysqli_query($con,$sql);
	if(mysqli_affected_rows($con) > 0){
		echo "Yes";
	}
	else
	{
		echo "No";
	}
	mysqli_close($con);
?>
