
<?php 
	
	session_start();
	if(!isset($_SESSION['user'])) $_SESSION['user'] = null;
	

	if ((!empty($_POST['user'])) && (strtolower($_POST['user']) == "admin"))
	{
		if ((!empty($_POST['pass'])) && ($_POST['pass']) == "123qwe")
		{
			$_SESSION['user'] = "admin";
		}
		else
		{
			$_SESSION['user'] = null;
		}
	}
	else
	{
		$_SESSION['user'] = null;
	}
	
	if(!empty($_REQUEST['logoff'])) $_SESSION['user']=null;

	
	function isadmin()
	{
		if (!empty($_SESSION['user']))
			return true;
		else 
			return false;
	}
	
?>