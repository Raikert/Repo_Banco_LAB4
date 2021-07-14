<%@page import="com.sun.corba.se.spi.orbutil.fsm.Action"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Banco Deocares & Asociados</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
body {
font-family: Perpetua;
width: 50%;
margin:0px auto;
background-color: #E6B0AA;
}
form {
border: 3px solid #f1f1f1;
background-color: #641E16;
}
input[type=text], input[type=password] {
  width: 100%;
  padding: 12px 20px;
  margin: 10px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
  
  
}

button {
  background-color: #F39C12;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
}

button:hover {
  opacity: 0.8;
}

.cancelbtn {
  width: auto;
  padding: 10px 18px;
  background-color: #f44336;
}

.imgcontainer {
  text-align: center;
  margin: 24px 0 12px 0;
}

img.avatar {
  width: 40%;
  border-radius: 50%;
}

.container {
  padding: 16px;
}

span.psw {
  float: right;
  padding-top: 16px;
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
  span.psw {
     display: block;
     float: none;
  }
  .cancelbtn {
     width: 100%;
  }
}

</style>
</head>

<body>
<center><h1 style="color:#641E16">Bienvenido al Banco Deocares & Asociados</h1></center>




<form action="ServletClientes" method="post">
  <div class="imgcontainer">
    <img src="banco.png" alt="Avatar" class="avatar">
  </div>

  <div class="container">
    <label for="uname" style="color:white"><b>Usuario:</b></label>
    <input type="text" placeholder="Ingrese su Usuario" name="user" required>
	<br>
    <label for="psw" style="color:white" ><b>Contraseña:</b></label>
    <input type="password" placeholder="Ingrese su Contraseña" name="pass" required>
        
    <button type="submit" name="BtLogin">Ingresar</button>
    
  </div>
</form>









</body>

</html>