package com.items.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.items.util.*;

import com.items.util.DBConnect;

public class itemsDAAOimpl  {

	public String insertItem(String code, String name, String price, String desc) {
		
		String output ="";
		
		Connection con;
		
		try {
			 con = DBConnect.DBconnection();
			 
			 if(con == null) {
				 
				 return "Error in while connecting to database";
			 }else {
				 
				//create a prepared statement
				  String query = "insert into items (itemID, itemCode, ItemPrice, ItemDesc)"
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
		
		String query = "select * from items";
		
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		// iterate through the rows in the result set
		while (rs.next())
		{
			String itemID = Integer.toString(rs.getInt("itemID"));
			String itemCode = rs.getString("itemCode");
			String itemName = rs.getString("itemName");
			String itemPrice = Double.toString(rs.getDouble("itemPrice"));
			String itemDesc = rs.getString("itemDesc");
			// Add a row into the html table
			output += "<tr><td>" + itemCode + "</td>";
			output += "<td>" + itemName + "</td>";
			output += "<td>" + itemPrice + "</td>";
			
			output += "<td>" + itemDesc + "</td>";
			// buttons
			output += "<td><input name='btnUpdate' "
			+ " type='button' value='Update'></td>"
			+ "<td><form method='post' action='items.jsp'>"
			+ "<input name='btnRemove' "
			+ " type='submit' value='Remove'>"
			+ "<input name='itemID' type='hidden' "
			+ " value='" + itemID + "'>" + "</form></td></tr>";
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
	
	
	3
	

}
