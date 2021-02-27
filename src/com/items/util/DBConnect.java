package com.items.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnect {
	
	public static  Connection DBconnection()
		{
			Connection con = null;
				try
					{
						Class.forName("com.mysql.jdbc.Driver");
						con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/items","root", "");
	
						//For testing
						System.out.print("Successfully connected");
					}
					catch(Exception e)
						{
							e.printStackTrace();
						}
					return con;
		}

	
    public String insertItem(String code, String name, String price, String desc) {
		
		String output ="";
		
		Connection con;
		
		try {
			 con = DBConnect.DBconnection();
			 
			 if(con == null) {
				 
				 return "Error in while connecting to database";
			 }else {
				 
				//create a prepared statement
				  String query = "insert into item (ItemID,ItemCode,ItemName, ItemPrice, ItemDesc)"
				  		+ " values(?,?,?,?,?)";
				  
				  PreparedStatement preparedstmt = con.prepareStatement(query);
				  
				  //binding values
				  preparedstmt.setInt(1, 0);
				  preparedstmt.setString(2, code);
				  preparedstmt.setString(3, name);
				  preparedstmt.setDouble(4, Double.parseDouble(price));
				  preparedstmt.setString(5, desc);
				  
				//execute the statement
				  preparedstmt.execute();
				  con.close();
				  output = "Inserted successfully";
			 }			 
			
		} catch (Exception e) {
			// TODO: handle exception
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}
		
		// TODO Auto-generated method stub
		return output;
	}


    public String readItems()

{
	
	String output = "";
	
	try
	{
		Connection con = DBConnect.DBconnection();
	
	if (con == null)
	{
		return "Error while connecting to the database for reading.";
	}
		// Prepare the html table to be displayed
		output = "<table border='1'><tr><th>Item Code</th>"
		+"<th>Item Name</th><th>Item Price</th>"
		+ "<th>Item Description</th>"
		+ "<th>Update</th><th>Remove</th></tr>";
		
		String query = "select * from item";
		
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
	
		// iterate through the rows in the result set
		while (rs.next())
			{
				String itemID = Integer.toString(rs.getInt("ItemID"));
				String itemCode = rs.getString("ItemCode");
				String itemName = rs.getString("ItemName");
				String itemPrice = Double.toString(rs.getDouble("ItemPrice"));
				String itemDesc = rs.getString("ItemDesc");
				
				// Add a row into the html table
				
				output += "<tr><td>" + itemCode + "</td>";
				output += "<td>" + itemName + "</td>";
				output += "<td>" + itemPrice + "</td>";
				
				output += "<td>" + itemDesc + "</td>";
				// buttons
				output += "<td><a><input name='btnUpdate' " + " type='button' value='Update'></a></td>"
				
				+ "<td><form method='post' action= 'AddItems.jsp'>"					
				+ "<input name='btnRemove' "
				+ " type='submit' value='Remove'>"													
				+ "<input name='ItemID' type='hidden' "
				+ " value='" + itemID + "'>" 
				
				+ "</form></td></tr>";
				
				
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

    
    public String DeleteItems(String itemid) {
    	
    	String output = "";
    	
    	try
		{
			Connection con = DBConnect.DBconnection();			
			String query =" delete from  item where ItemID='"+itemid+"' ";
			
			Statement st = con.createStatement();
			int r = st.executeUpdate(query);
			
			if(r>0) {
				
				output = "Successfully deleted...";
			}			
		
		}catch(Exception e){
			e.printStackTrace();
		}
    	
    	return output;
    	
    }











	
}
