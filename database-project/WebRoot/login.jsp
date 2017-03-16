<!--A Design by W3layouts
Author: W3layout
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE HTML>
<html>
<head>
<title>Login Page</title>
<link rel="stylesheet" href="css/style_1.css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<script src="js/jquery.min.js"></script>
<script src="js/login.js"></script>
<script src="js/modernizr.custom.js"></script>
<!---tabs-->
<script type="text/javascript" src="js/JFCore.js"></script>
		<script type="text/javascript">
			(function() {
				JC.init({
					domainKey: ''
				});
				})();
		</script>
</head>
<BODY style="background-image : url(images/bg.jpg);background-repeat : no-repeat;background-size:100% ">
<div class="wrap">
<div class="main">
	
		
		
        <!----start-tabs--->
		<div class="row">
			<div class="grid_12 columns">
				<div class="tab style-1">
				<div class="bg">
			    					<dl>
			 			             	 <dd class="users"><a class="user" href="#tab1" onclick="choose_b()">Branch</a></dd>
			    						 <dd class="messages"><a class="msg" href="#tab2" onclick="choose_s()">Staff </a></dd>
			    					</dl>
		    					
		    						<br><br>
					    				<form class="form" onsubmit="return false">		
												<input type="text" class="textbox" id="username" name="username" value="StaffNo" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'StaffNo';}">
												<input type="password" class="textbox" id="password" name="password" value="Password" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Password';}">
												
												
												<input class="from" type="submit" value="Login" onclick="check()">
										</form>
										<form name="form2" class="form" method="post" action="register.jsp">
										<input type="submit" value="Create Account" style="width:288px;position:absolute;top:408px">
										</form>
										<input type="text" id="usertype" name="usertype" value="" style="display:none;">
										
												
									   
									   
									
				 </div>
				 </div>
		   </div>            
	   </div>	
       <!----end-tabs--->
	   
</div>	
</div>

<script language="javascript">
function choose_b()
{
   document.getElementById("usertype").value="Branch";  
}
function choose_s()
{
   document.getElementById("usertype").value="Staff";  
}
function check(){
   var username=document.getElementById("username").value;
   var password=document.getElementById("password").value;
   var usertype=document.getElementById("usertype").value;
   if(username=="StaffNo"){
    alert("Please enter your username!");
    return false;
   }
      
   if(password=="Password"){
    alert("Please enter your password!");
    return false;
   }
    
   if(usertype==""){
   alert("Please choose a userType!");  
   return false;
   }
   
   var xmlhttp;
			
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");

		}

		xmlhttp.open("POST", "servlet/loginServlet", true);
		xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded"); 
		xmlhttp.onreadystatechange = function() {
		//alert(xmlhttp.readyState+" " + xmlhttp.status);
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {		
			var data = xmlhttp.responseText;
			if(data=="success"){
                     window.location.href="home.jsp";
            }else{ 
				alert( data);
				}
			}
		};

		xmlhttp.send("username=" + username
				+ "&&password=" + password + "&&usertype=" + usertype );
	}
          

</script>
</body>
</html>