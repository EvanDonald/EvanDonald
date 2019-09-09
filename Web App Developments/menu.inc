
<?php include('cart.inc'); ?>

<div id='menu'>
	<p>
		<a href='index.html'>Home</a>	<a href='products.html'>Products</a>	<a href='contact.html'>Contacts</a>
		
		<?php
			if(!empty($cart)) {
				print "<a href='order.html'><img src='cart.png' id='cartlink'></a>";
			}
		?>

		<hr>
	</p>
</div>