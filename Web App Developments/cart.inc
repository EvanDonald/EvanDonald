<?php

	
	
	if(!empty($_COOKIE['cart'])) $cart = unserialize($_COOKIE['cart']);
	if(!is_array($cart)) $cart = array();
	
	
	
	if(!empty($_GET['product'])) 
	{ 
		cart_add(1, $_GET['product']);
	}
	
	cart_update();
	
	setcookie('cart', serialize($cart));
	
	
	function cart_add($qty, $id) 
	{
		global $cart;
		foreach($cart as $i=>$row) 
		{
			if($row['id'] == $id) 
			{
				$cart[$i]['qty'] += $qty;
				$qty = 0; 
			}
		}
		if($qty > 0)
			$cart[] = array('qty'=>$qty, 'id'=>$id);
	}
	
	function get_product($id) 
	{
		global $db;
		$stmt = $db->prepare("select * from products where id=?");
		$stmt->bind_param('i', $id);
		if(!$stmt->execute()) return null;
		if(!$result = $stmt->get_result()) return null;
		if(!$row = $result->fetch_object()) return null;
		return $row;
	}
	
	function cart_show() 
	{
		global $cart;
		print "<table class='cart'>";
		print "<form action='order.html'>";
		foreach($cart as $row) 
		{
			$product = get_product($row['id']);
			if($product == null) continue;
			print "<tr>";
			//print "<td>".$row['qty']."</td>";
			
			print "<td>";
			print "<input type='number' name='qty".$row['id']."'value='".$row['qty']."'>";
			print "</td>";
			
			print "<td>".$product->Name."</td>";
			print "<td>$";
			print number_format($row['qty']*$product->Price,2);
			print "</td>";
			print "</tr>";
		}
		
		print "<tr><td colspan='3'>";
		print "<button type='submit'>Update Quantities</button>";
		print "</td></tr>";
		print "</form>";
		
		print "</table>";
		
	}
	
	function cart_update() 
	{
		global $cart;
		foreach($cart as $i=>$row) 
		{
			$field = 'qty'.$row['id'];
			if(isset($_GET[$field])) 
			{
				if($_GET[$field] > 0) 
				{
					$cart[$i]['qty'] = $_GET[$field];
				}
				else
				{
					unset($cart[$i]);
				}
			}
		}
		
		
	}



?>
