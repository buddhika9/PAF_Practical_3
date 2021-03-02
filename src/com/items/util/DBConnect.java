package com.items.util;

import java.sql.Array;
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
				 
				
				  String query = "insert into item (ItemID,ItemCode,ItemName, ItemPrice, ItemDesc)"
				  		+ " values(?,?,?,?,?)";
				//create a prepared statement
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
		//create a prepared statement
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
				
				
				
				// update buttons
				output += "<td><form method='post' action='AddItems.jsp'>"						
						+ "<input name='ItemID' type='hidden' "
						+ " value='" + itemID + "'>" 
						+ "<input name='btnUpdate' type='submit' value='Update'>"
						+"</form></td>"
						
					
					
				//delete button
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
    	
    	if (con == null)
    	{
    		return "Error while connecting to the database for reading.";
    	}
    	
			String query =" delete from  item where ItemID= ? ";
			//create a prepared statement
			 PreparedStatement preparedstmt = con.prepareStatement(query);
			 
			 preparedstmt.setInt(1, Integer.parseInt(itemid));
				//execute the statement
			 preparedstmt.execute();
			 con.close();
			 output = "Deleted successfully";
			
		
		}catch(Exception e){
			output = "Error while deleting the item details...";
			e.printStackTrace();
		}
    	
    	return output;
    	
    }

   
    public String UpdateItem(int itemid ,String code, String name, String price, String desc) {

    	String output = "";
    	
    	try
    	{
    		Connection con = DBConnect.DBconnection();
    	
    	if (con == null)
    	{
    		return "Error while connecting to the database for reading.";
    	}	
			
			String querry = "Update item set ItemCode = ? , ItemName = ? , ItemPrice = ? , ItemDesc = ?  "+"Where ItemID = ?";
    	   
			//create a prepared statement
			PreparedStatement preparedstmt = con.prepareStatement(querry);
			  
			//binding values
			  preparedstmt.setString(1,code );
			  preparedstmt.setString(2, name);
			  preparedstmt.setDouble(3, Double.parseDouble(price));
			  preparedstmt.setString(4, desc);
			  preparedstmt.setInt(5,  itemid);
			  
			//execute the statement
			  preparedstmt.execute();
			
			  con.close();
			  output = "Updated successfully..";
			 
		
		}catch(Exception e){
			output = "Error in updating details...";
			e.printStackTrace();
		}
    	
    	
    	return output;
    }


    public String readSelectedItem(int id)
	{
		//For testing System.out.print(id);
		String output = "";
		try
		{
			Connection con = DBConnect.DBconnection();
			
			if (con == null)
			{
				return "Error while connecting to the database for reading.";
			}
			String query = "select * from item where ItemID = ? ";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1, id);
			
			ResultSet rs = preparedStmt.executeQuery();
			// iterate through the rows in the result set
			
			while (rs.next())
			{
				String itemID = Integer.toString(rs.getInt("ItemID"));
				String itemCode = rs.getString("ItemCode");
				String itemName = rs.getString("ItemName");
				String itemPrice = Double.toString(rs.getDouble("ItemPrice"));
				String itemDesc = rs.getString("ItemDesc");
				
				output += "<form method=post action='AddItems.jsp'>"
					    + "	Item code: <input name='ItemCode' type='text' value = '"+itemCode+"' ><br>"
						+ " Item name: <input name='ItemName' type='text' value = '"+itemName+"' ><br>"
						+ " Item price: <input name='ItemPrice' type='text'  value = '"+itemPrice+"'><br>"
						+ " Item description: <input name='ItemDesc' type='text'  value = '"+itemDesc+"'><br>"
						+ " <input name='ItemID' type='hidden' value='" + itemID + "'>"
						+ " <input name='btnSubmit' type='submit' value= 'Update' >"
						+ " </form>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
	}
	catch (Exception e)
	{
		output = "Error while reading the one item.";
		System.err.println(e.getMessage());
	}
	return output;
	}
    	
   






	
}
