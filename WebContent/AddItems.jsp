<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="com.items.util.*" %>
 <%@page import= "java.sql.Connection" %>
  <%@page import= " java.sql.PreparedStatement" %>
   <%@page import= "java.sql.ResultSet" %>

 
 <%
 //Adding item
if (request.getParameter("ItemCode") != null)
{
		DBConnect itemObj = new DBConnect();
		
		
		String stsMsg = itemObj.insertItem(request.getParameter("ItemCode"),
		request.getParameter("ItemName"),
		request.getParameter("ItemPrice"),
		request.getParameter("ItemDesc"));
		
		session.setAttribute("statusMsg", stsMsg);		
		
}
 
 //Deleting items
 if(request.getParameter("ItemID") != null){
	 
	 DBConnect deleteitem = new DBConnect();
	 String stsMsg = deleteitem.DeleteItems(request.getParameter("ItemID"));
	 session.setAttribute("statusMsg", stsMsg);
 } 
 /*
if(request.getParameter("ItemID") != null && request.getParameter("ItemCode") != null){
	 
	 DBConnect update = new DBConnect();	 
	 out.print(update.readSelectedItem(Integer.parseInt(request.getParameter("ItemID"))));
	 
	 String stsMsg = itemObj.UpdateItem(Integer.parseInt(request.getParameter("ItemID") ,
													    request.getParameter("ItemID"),
														request.getParameter("ItemName"),
														request.getParameter("ItemPrice"),
														request.getParameter("ItemDesc"));
														
														session.setAttribute("statusMsg", stsMsg);		
	 
 }*/
 
 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Items Management</title>
</head>
<body>

	<form method="post" action="AddItems.jsp">
	
		Item code: <input name="ItemCode" type="text"><br>
		Item name: <input name="ItemName" type="text"><br>
		Item price: <input name="ItemPrice" type="text"><br>
		Item description: <input name="ItemDesc" type="text"><br>
		<input name="action" type="hidden" value="Add">
		<input name="btnSubmit" type="submit" value="Save">
	</form>

<%



	    out.print(session.getAttribute("statusMsg"));
		DBConnect itemObj = new DBConnect();
        out.print(itemObj.readItems());
        
     
%>
</body>
</html>