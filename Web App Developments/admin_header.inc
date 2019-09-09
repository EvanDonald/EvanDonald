
<?php 


	function redirect($url) 
	{
		header('Location: '.$url);
		die("<html>
			<meta http-equiv='refresh' content='0; $url'>
			<body onload=\"location.replace('$url');\">
			<a href='$url'>$url</a>
			</body></html>");
	}
	
	require('admin.inc');

	if(!(isadmin()))
		redirect('login.html');
	else
	{
		include('head.inc');
		
	}
?>
