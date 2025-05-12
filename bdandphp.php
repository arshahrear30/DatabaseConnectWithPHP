<?php

//প্রথমে server এর Hosting  এর C pannel এ Database এ যাবো ।সেখানে Manage Database এ যাবো 
//তারপর  Add User To Database এখানে New User/New Database/DBUSERID & DBPassword set করবো । তারপর Database 
//create করবো ।  এরপর phpmyadmin এ যাবো ।

//mysqli_connect(); এর কাজ database(phpmyadmin) এর সাথে php file এর connection ঘটানো
// mysqli_connect(); এর ভিতর 4 টা part থাকবে । 1st part DB(database) localhost 
//mysqli_connect("localhost","DBUSERID","DBPassword","DBNameinphpmyadmin");

mysqli_connect("localhost","zftsszne_shahrear","arshahrear30@gmail.com","zftsszne_my_database");

if(mysqli_connect_errno()){
	//mysqli_connect_errno এটার মানে , যদি mysql এর সাথে connect না হয় তাহলে show করবে Couldn't connect to database এবং<br> মানে newline
	echo "Couldn't connect to database!<br>".mysqli_connect_error();
	//.mysqli_connect_error() এটার মানে , কোডে কোথায় error আছে সেটা দেখাও ।
}else{
	echo"Connected with database";
}


?>
