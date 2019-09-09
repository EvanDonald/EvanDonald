<?php
$db=new mysqli('localhost', 'edonald', '300714', 'edonald');
if($db->connect_error) die("DB Fail: ".$db->connect_error);
?>