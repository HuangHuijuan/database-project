<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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


							<br>
							<br>
							<form class="form" onsubmit="return false">
								<input class="textbox" type="text" id="username" name="username" size="20px"
									value="StaffNo" onfocus="this.value = '';" style="width:288px;position:absolute;top:170px"
									onblur="if (this.value == '') {this.value = 'StaffNo';}"><br>
								<input class="textbox" type="password" id="pwd" name="pwd" size="20px"
									value="password" onfocus="this.value = '';" style="width:288px;position:absolute;top:225px"
									onblur="if (this.value == '') {this.value = 'password';}"><br>
								<input class="textbox" type="password" id="pwd_1" name="pwd_1" size="20px"
									value="password" onfocus="this.value = '';" style="width:288px;position:absolute;top:280px"
									onblur="if (this.value == '') {this.value = 'password';}">
								<input type="submit" value="Create Account"
									style="width:325px;position:absolute;top:340px" onclick="check()">
							</form>
							<form name="form2" class="form" method="post" action="login.jsp">
								<input type="submit" value="Cancel"
									style="width:325px;position:absolute;top:395px">
							</form>
					</div>
					</div>
				</div>            
	   </div>	
       <!----end-tabs--->
	   
</div>	
</div>
<script language="javascript">
function check(){
   
   var username=document.getElementById("username").value;   
   var p1=document.getElementById("pwd").value;
   var p2=document.getElementById("pwd_1").value;
   if(username=="StaffNo"){
       alert("Please enter your staffNo!");
       return false;
       }
   if(p1=="password"){
       alert("Please enter your password!");
       return false;
       }
   if(p2=="password"){
       alert("Please confirm your password!");
       return false;
       }
   if(p1!=p2){
       alert("Passwords don't match!");
       return false;
       }
       
       var xmlhttp;
			
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");

		}

		xmlhttp.open("POST", "servlet/registerServlet" , true);
		xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded"); 
		xmlhttp.onreadystatechange = function() {
		//alert(xmlhttp.readyState+" " + xmlhttp.status);
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {		
			var data = xmlhttp.responseText;
			if(data=="Register successfully!"){
			         alert( data);
                     window.location.href="login.jsp";
            }else{ 
				alert( data);
				}
			}
		};

		xmlhttp.send("username=" + username
				+ "&&pwd=" + p1 + "&&pwd_1=" + p2);
				
	}

</script>


</body>
</html>