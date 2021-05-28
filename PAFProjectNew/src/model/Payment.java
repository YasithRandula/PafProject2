package model;
import java.sql.*;
public class Payment
	
{ 
	
	//connect to the DB
	
	private static String url = "jdbc:mysql://127.0.0.1:3306/payment";
	private static String userName = "root";
	private static String password = "yasith@1234";
	private static Connection con;
	
		
	
	private static Connection connect() {
		
			
			try
				{
			
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection(url,userName,password);
			
				}
				catch (Exception e)
					{
				e.printStackTrace();
					}
			return con;
		}
		
		
		// Insert Customer Details
	
			public String insertPayment(String Fname, String Lname , String Email , String Contact , String CardNo , String CardType , String Expire , String CardCode)
			{
				String output = "";
				try
			{
				Connection con = connect();
				if (con == null)
				{
					return "Error while connecting to the database for inserting."; 
				}
 
 
 // create a prepared statement
				String query = "insert into cus(`id`,`fname`,`lname`,`email`,`contact`,`cardNo`,`cardType`,`expire`,`cardCode`)"
						+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
					preparedStmt.setInt(1, 0);
					preparedStmt.setString(2, Fname);
					preparedStmt.setString(3, Lname);
					preparedStmt.setString(4, Email);
					preparedStmt.setString(5, Contact);
					preparedStmt.setString(6, CardNo);
					preparedStmt.setString(7, CardType);
					preparedStmt.setString(8, Expire);
					preparedStmt.setString(9, CardCode);
// execute the statement 3
 
					preparedStmt.execute();
					con.close();
					
					String newPayment = readcus();
					 output = "{\"status\":\"success\", \"data\": \"" +
					 newPayment + "\"}";
			}
				catch (Exception e)
			{
					output = "{\"status\":\"error\", \"data\":\"Error while inserting the payment.\"}";
							 System.err.println(e.getMessage());
			}
				return output;
			} 
		
		
				public String readcus()
				{
					String output = "";
					try
				{
				
				
				Connection con = connect();
				if (con == null)
				{
					return "Error while connecting to the database for reading."; 
				}
				
 // Prepare the html table to be displayed
				
				output = "<table border='1'><tr><th>Id</th>" +
						"<th>First Name</th>" +
						"<th>Last Name</th>" +
						"<th>Email</th>" +
						"<th>Contact Number</th>" +
						"<th>Card Number</th>" +
						"<th>Card Type</th>" +
						"<th>Expire Date</th>" +
						"<th>Card Code</th>" +
						"<th>Update</th>" +
						"<th>Delete</th></tr>";

				String query = "SELECT * FROM cus";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
 // iterate through the rows in the result set
				
				while (rs.next())
				{
					String id = Integer.toString(rs.getInt("id"));
					String fname = rs.getString("fname");
					String lname = rs.getString("lname");
					String email = rs.getString("email");
					String contact = rs.getString("contact");
					String cardNo = rs.getString("cardNo");
					String cardType = rs.getString("cardType");
					String expire = rs.getString("expire");
					String cardCode = rs.getString("cardCode");
					
 // Add into the html table
					
					output += "<td>" + id + "</td>";
					output += "<td>" + fname + "</td>";
					output += "<td>" + lname + "</td>";
					output += "<td>" + email + "</td>";
					output += "<td>" + contact + "</td>";
					output += "<td>" + cardNo + "</td>";
					output += "<td>" + cardType + "</td>";
					output += "<td>" + expire + "</td>";
					output += "<td>" + cardCode + "</td>";
 
					
// buttons
					
						    
						    output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						    		 + "<td><form method='post' action='index.jsp'>"+ "<input name='btnDelete' type='submit' value='Delete' class='btn btn-danger'>"
						    		 + "<input name='hidPaymentIDDelete' type='hidden' data-paymentid='" + id 
						    		 + "'>" + "</form></td></tr>";
						    
						    
	}
				con.close(); 
 // Complete the html table
				
			output += "</table>";
		}
			catch (Exception e)
		{
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
			return output;
		}
		
				
				
//put
				
				
	public String updatePayment(String ID,String Fname, String Lname , String Email , String Contact , String CardNo , String CardType , String Expire , String CardCode)

		{
			String output = "";
			try
		{
				
			Connection con = connect();
			if (con == null)
				{
				return "Error while connecting to the database for updating."; 
				}
 // create a prepared statement
			
			String query = "UPDATE cus SET fname=?,lname=?,email=?,contact=? cardNo=? cardType=? expire=? cardCode=? WHERE id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
			
			preparedStmt.setString(1, Fname);
			preparedStmt.setString(2, Lname);
			preparedStmt.setString(3, Email);
			preparedStmt.setString(4, Contact);
			preparedStmt.setString(5, CardNo);
			preparedStmt.setString(6, CardType);
			preparedStmt.setString(7, Expire);
			preparedStmt.setString(8, CardCode);
			preparedStmt.setInt(9, Integer.parseInt(ID));
 // execute the statement
			
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
				}
					catch (Exception e)
				{
						output = "Error while updating the payment.";
						System.err.println(e.getMessage());
				}
				return output;
				}
		
	//Delete
	
	
					public String deletePayment(String id)
						{
							String output = "";
							try
						{
								Connection con = connect();
								if (con == null)
						{
								return "Error while connecting to the database for deleting.";
						}
 // create a prepared statement
					
					String query = "delete from cus where id=?";
					PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
					
					preparedStmt.setInt(1, Integer.parseInt(id));
					
 // execute the statement
					preparedStmt.execute();
					con.close();
					output = "Deleted successfully";
						}
							catch (Exception e)
							{
								output = "Error while deleting the payment.";
								System.err.println(e.getMessage());
							}
				return output;
			} 
} 