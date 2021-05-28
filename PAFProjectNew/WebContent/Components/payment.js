$(document).ready(function()
	{ 
		if ($("#alertSuccess").text().trim() == "") 
	{ 
		$("#alertSuccess").hide(); 
	} 
		$("#alertError").hide(); 
	}); 
// SAVE ============================================
		$(document).on("click", "#btnSave", function(event) 
				{ 
// Clear alerts---------------------
					$("#alertSuccess").text(""); 
					$("#alertSuccess").hide(); 
					$("#alertError").text(""); 
					$("#alertError").hide(); 
// Form validation-------------------
				var status = validatePaymentForm(); 
					if (status != true) 
				{ 
						$("#alertError").text(status); 
						$("#alertError").show(); 
					return; 
				} 
					
				
				var type = ($("#hidPaymentIDSave").val() == "") ? "POST" : "PUT";
						
				$.ajax(
					{
						url : "PaymentAPI",
						type : type,
						data : $("#formPayment").serialize(),
						dataType : "text",
						complete : function(response, status)
					 {
							onPaymentSaveComplete(response.responseText, status);
					 }
					});
					
					
					
// If valid------------------------
					$("#formPayment").submit(); 
				}); 
		
function onPaymentSaveComplete(response, status)
	{
		if (status == "success")
		 {
			var resultSet = JSON.parse(response);
			if (resultSet.status.trim() == "success")
		 {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		 } else if (resultSet.status.trim() == "error")
			 
		 {
			 $("#alertError").text(resultSet.data);
			 $("#alertError").show();
		 }
			
		 } else if (status == "error")
			 
		 {
			 $("#alertError").text("Error while saving.");
			 $("#alertError").show();
		 } else
			 
		 {
			 $("#alertError").text("Unknown error while saving..");
			 $("#alertError").show();
		 }
		
		 	$("#hidItemIDSave").val("");
		 	$("#formItem")[0].reset();
	}	


$(document).on("click", ".btnRemove", function(event)
		{
		 $.ajax(
		 {
		 url : "PaymentAPI",
		 type : "DELETE",
		 data : "id=" + $(this).data("id"),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 onItemDeleteComplete(response.responseText, status);
		 }
		 });
});

function onItemDeleteComplete(response, status)
{
		if (status == "success")
		{
				var resultSet = JSON.parse(response);
				if (resultSet.status.trim() == "success")
			{
					$("#alertSuccess").text("Successfully deleted.");
					$("#alertSuccess").show();
					$("#divItemsGrid").html(resultSet.data);
			} else if (resultSet.status.trim() == "error")
				{
					$("#alertError").text(resultSet.data);
					$("#alertError").show();
				}
		} else if (status == "error")
			{
				$("#alertError").text("Error while deleting.");
				$("#alertError").show();
			} else
				{
					$("#alertError").text("Unknown error while deleting..");
					$("#alertError").show();
				}
}

		
		
		
		
		
		
		
// UPDATE==========================================
					$(document).on("click", ".btnUpdate", function(event) 
				{ 
 $("#hidPaymentIDSave").val($(this).closest("tr").find('#hidPaymentIDUpdate').val()); 
 $("#fname").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#lname").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#email").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#contact").val($(this).closest("tr").find('td:eq(3)').text()); 
 $("#cardNo").val($(this).closest("tr").find('td:eq(4)').text()); 
 $("#cardType").val($(this).closest("tr").find('td:eq(5)').text()); 
 $("#expire").val($(this).closest("tr").find('td:eq(6)').text()); 
 $("#cardCode").val($(this).closest("tr").find('td:eq(7)').text());
 
}); 
// CLIENT-MODEL================================================================
function validatePaymentForm() 
{ 
// CODE
if ($("#fname").val().trim() == "") 
 { 
 return "Insert First name."; 
 } 
// NAME
if ($("#lname").val().trim() == "") 
 { 
 return "Insert last name."; 
 } // PRICE-------------------------------
if ($("#email").val().trim() == "") 
{ 
return "Insert Email Address."; 
} 
//is numerical value
//var tmpPrice = $("#itemPrice").val().trim(); 
//if (!$.isNumeric(tmpPrice)) 
//{ 
//return "Insert a numerical value for Item Price."; 
//} 
//convert to decimal price
//$("#itemPrice").val(parseFloat(tmpPrice).toFixed(2)); 
//DESCRIPTION------------------------
//if ($("#itemDesc").val().trim() == "") 
//{ 
//return "Insert Item Description."; 
//} 
return true; 
}