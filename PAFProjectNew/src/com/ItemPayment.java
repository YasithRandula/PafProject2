package com;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
//For REST Service
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Payment;
		@Path("/Payment")
			public class ItemPayment
{
			 Payment paymentObj = new Payment();
			 
			 
		
		//Get Method
			@GET
			@Path("/")
			@Produces(MediaType.TEXT_HTML)
				public String readcus()
					{
						return paymentObj.readcus();
					}
		
		
		//Post Method
			@POST
			@Path("/")
			@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
			@Produces(MediaType.TEXT_PLAIN)
				public String insertPayment(
				
						@FormParam("fname") String fname,
						@FormParam("lname") String lname,
						@FormParam("email") String email,
						@FormParam("contact") String contact,
						@FormParam("cardNo") String cardNo,
						@FormParam("cardType") String cardType,
						@FormParam("expire") String expire,
						@FormParam("cardCode") String cardCode )
									
									
						{
							String output = paymentObj.insertPayment(fname, lname, email, contact, cardNo, cardType, expire, cardCode);
							return output;
						}
		
		//Put Method
			@PUT
			@Path("/")
			@Consumes(MediaType.APPLICATION_JSON)
			@Produces(MediaType.TEXT_PLAIN)
				public String updatePayment(String paymentData)
		{
		//Convert the input string to a JSON object
				
				JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject();
		 
		//Read the values from the JSON object
				
				String id = paymentObject.get("id").getAsString();
				String fname = paymentObject.get("fname").getAsString();
				String lname = paymentObject.get("lname").getAsString();
				String email = paymentObject.get("email").getAsString();
				String contact = paymentObject.get("contact").getAsString();
				String cardNo = paymentObject.get("cardNo").getAsString();
				String cardType = paymentObject.get("cardType").getAsString();
				String expire = paymentObject.get("expire").getAsString();
				String cardCode = paymentObject.get("cardCode").getAsString();
		 
				String output = paymentObj.updatePayment(id,fname, lname, email, contact, cardNo,cardType,expire,cardCode);
		 
				return output;
		 
		}
		
		//Delete Method
			@DELETE
			@Path("/")
			@Consumes(MediaType.APPLICATION_XML)
			@Produces(MediaType.TEXT_PLAIN)
				public String deletePayment(String paymentData)
			{
		//Convert the input string to an XML document
				
				Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());

		//Read the value from the element 
				
				String id = doc.select("id").text();
				String output = paymentObj.deletePayment(id);
				return output;
		}

}
	
		