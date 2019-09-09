
/*
Name: Evan Donald
Date: January 23, 2019
Complete through lab 12
Purpose: scripts
*/

function submithandler() 
{
	var e = document.getElementById('fname');
	if(e != null) 
	{
		if(e.value == '') 
		{
			var m = document.getElementById('emptyfname');
			if(m != null) 
				m.style.display='block';
			return false;
		}
		else
		{
			var m = document.getElementById('emptyfname');
			if(m != null) 
				m.style.display='none';
		}
	}
	
	var e = document.getElementById('lname');
	if(e != null) 
	{
		if(e.value == '') 
		{
			var m = document.getElementById('emptylname');
			if(m != null) 
				m.style.display='block';
			return false;
		}
		else
		{
			var m = document.getElementById('emptylname');
			if(m != null) 
				m.style.display='none';
		}
	}
	
	var e = document.getElementById('email');
	var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	if(!re.test(e.value)) 
	{
		var m = document.getElementById('emptyemail');
		if(m != null) 
			m.style.display = 'block';
		return false;
	}
	
	var e = document.getElementById('street');
	if(e != null) 
	{
		if(e.value == '') 
		{
			var m = document.getElementById('emptystreet');
			if(m != null) 
				m.style.display='block';
			return false;
		}
		else
		{
			var m = document.getElementById('emptystreet');
			if(m != null) 
				m.style.display='none';
		}
	}
	
	var e = document.getElementById('city');
	if(e != null) 
	{
		if(e.value == '') 
		{
			var m = document.getElementById('emptycity');
			if(m != null) 
				m.style.display='block';
			return false;
		}
		else
		{
			var m = document.getElementById('emptycity');
			if(m != null) 
				m.style.display='none';
		}
	}
	
	var e = document.getElementById('state');
	if(e != null) 
	{
		if(e.value == '') 
		{
			var m = document.getElementById('emptystate');
			if(m != null) 
				m.style.display='block';
			return false;
		}
		else
		{
			var m = document.getElementById('emptystate');
			if(m != null) 
				m.style.display='none';
		}
	}
	
	var e = document.getElementById('zip');
	if(e != null) 
	{
		if(e.value == '') 
		{
			var m = document.getElementById('emptyzip');
			if(m != null) 
				m.style.display='block';
			return false;
		}
		else
		{
			var m = document.getElementById('emptyzip');
			if(m != null) 
				m.style.display='none';
		}
	}
	
	var e = document.getElementById('cardnum');
	if(e != null) 
	{
		if(e.value == '') 
		{
			var m = document.getElementById('emptycardnum');
			if(m != null) 
				m.style.display='block';
			return false;
		}
		else
		{
			var m = document.getElementById('emptycardnum');
			if(m != null) 
				m.style.display='none';
		}
	}
	
	var ex = document.getElementById('expmonth');
	if(ex != null) 
	{
		if(ex.value == '' || ex.value < 1 || ex.value > 12) 
		{
			var m = document.getElementById('emptymonth');
			if(m != null) 
				m.style.display='block';
			return false;
		}
		else
		{
			var m = document.getElementById('emptymonth');
			if(m != null) 
				m.style.display='none';
		}
	}
	
	var ex = document.getElementById('expyear');
	if(ex != null) 
	{
		if(ex.value == '' || ex.value < 2019 || ex.value > 2100) 
		{
			var m = document.getElementById('emptyyear');
			if(m != null) 
				m.style.display='block';
			return false;
		}
		else
		{
			var m = document.getElementById('emptyyear');
			if(m != null) 
				m.style.display='none';
		}
	}
	
	var e = document.getElementById('seccode');
	if(e != null) 
	{
		if(e.value == '') 
		{
			var m = document.getElementById('emptycode');
			if(m != null) 
				m.style.display='block';
			return false;
		}
		else
		{
			var m = document.getElementById('emptycode');
			if(m != null) 
				m.style.display='none';
		}
	}
	
	
	
	
	
	
	
	
	
	return true;

	
}