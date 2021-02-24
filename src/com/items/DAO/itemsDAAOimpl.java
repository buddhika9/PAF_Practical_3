package com.items.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.items.util.*;

import com.items.util.DBConnect;

public class itemsDAAOimpl implements ItemsDAO {

	@Override
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
	
	
	

}
