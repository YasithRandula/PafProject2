 <%@ page import="model.Payment" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
    <% 
    session.setAttribute("statusMsg","");
    System.out.println("Trying to process....");
    
    
    if (request.getParameter("fname") != null) 
    { 
    	 Payment projectObj = new Payment(); 
    	 String stsMsg = ""; 
    	//Insert--------------------------
    	if (request.getParameter("hidPaymentIDSave") == "") 
    	 { 
    	 stsMsg = projectObj.insertPayment(request.getParameter("fname"), 
    	 request.getParameter("lname"), 
    	 request.getParameter("email"), 
    	 request.getParameter("contact"),
    	 request.getParameter("cardNo"),
    	 request.getParameter("cardType"),
    	 request.getParameter("expire"),
    	 request.getParameter("cardCode") );
    	 
    			 
    			 
    	 } 
    	else//Update----------------------
    	 { 
    	 stsMsg = projectObj.updatePayment(request.getParameter("hidPaymentIDSave"), 
    			 request.getParameter("fname"),
    			 request.getParameter("lname"), 
    	    	 request.getParameter("email"), 
    	    	 request.getParameter("contact"),
    	    	 request.getParameter("cardNo"),
    	    	 request.getParameter("cardType"),
    	    	 request.getParameter("expire"),
    	    	 request.getParameter("cardCode") );
    	 } 
    	 session.setAttribute("statusMsg", stsMsg); 
    	} 
    	//Delete-----------------------------
    	if (request.getParameter("hidPaymentIDDelete") != null) 
    	{ 
    	 Payment paymentObj = new Payment();
    	 String stsMsg = 
    	paymentObj.deletePayment(request.getParameter("hidPaymentIDDelete")); 
    	 session.setAttribute("statusMsg", stsMsg); 
    	} %>
    

    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="Views/bootstrap.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/payment.js"></script>
</head>
<body>

	<div class="container">
 		<div class="row">
 		<div class="col-8">
 			
 				<h1 class="m-3">Payment Details</h1>
 
		<form id="formPayment" name="formPayment" method="post" action="index.jsp">
			First Name:
			<input id="fname" name="fname" type="text"
 			class="form-control form-control-sm">
 			
			<br> 
			Last Name:
			<input id="lname" name="lname" type="text"
 			class="form-control form-control-sm">
		
			<br>
			Email:
			<input id="email" name="email" type="text"
			 class="form-control form-control-sm">

			<br> 
			Contact Number:
			<input id="contact" name="contact" type="text"
			class="form-control form-control-sm">
			
			<br> 
			Card Number:
			<input id="cardNo" name="cardNo" type="text"
			class="form-control form-control-sm">
			
			<br> 
			Card Type:
			<input id="cardType" name="cardType" type="text"
			class="form-control form-control-sm">
			
			<br> 
			Expire Date:
			<input id="expire" name="expire" type="text"
			class="form-control form-control-sm">
			
			<br> 
			Card Code:
			<input id="cardCode" name="cardCode" type="text"
			class="form-control form-control-sm">
			
			
			<br>
			<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
			<input type="hidden" id="hidPaymentIDSave" name="hidPaymentIDSave" value="">
			
		</form>
		
		<div id="alertSuccess" class="alert alert-success">
			<%
				out.print(session.getAttribute("statusMsg"));
			%>
		</div>
			
		
		
		<br>
		<div>
		
		<% 
		 Payment paymentObj = new Payment();
		 out.print(paymentObj.readcus());
		 %>
		 
		 </div>
		
 		</div>
 		</div>

 <br>
 <div class="row">
 <div class="col-12" id="colStudents">

 </div>
 </div>
</div>


</body>
</html>