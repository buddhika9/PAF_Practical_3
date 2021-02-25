<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="com.items.util.*" %>
  <%@page import="com.items.DAO.*" %>
 
 <%
if (request.getParameter("ItemCode") != null)
{
		itemsDAAOimpl itemObj = new itemsDAAOimpl();
		
		String stsMsg = itemObj.insertItem(request.getParameter("ItemCode"),
		request.getParameter("ItemName"),
		request.getParameter("ItemPrice"),
		request.getParameter("ItemDesc"));
		
		session.setAttribute("statusMsg", stsMsg);
}
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
		
		<input name="btnSubmit" type="submit" value="Save">
	</form>

<%
		out.print(session.getAttribute("statusMsg"));
		itemsDAAOimpl itemObj = new itemsDAAOimpl();
        out.print(itemObj.readItems());
        
     
%>
</body>
</html>