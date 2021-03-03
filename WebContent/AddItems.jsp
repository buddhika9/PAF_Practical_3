<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="com.items.util.*" %>
 <%@page import= "java.sql.Connection" %>
  <%@page import= " java.sql.PreparedStatement" %>
   <%@page import= "java.sql.ResultSet" %>

 
 <%
 

 //Adding item
  String submit = request.getParameter("btnSubmit");
if (request.getParameter("ItemCode") != null && "Save".equals(submit))
{
		DBConnect itemObj = new DBConnect();
		
		
		String stsMsg = itemObj.insertItem(request.getParameter("ItemCode"),
		request.getParameter("ItemName"),
		request.getParameter("ItemPrice"),
		request.getParameter("ItemDesc"));
		
		session.setAttribute("statusMsg", stsMsg);		
		
}
 

 //Deleting items
  String remove = request.getParameter("btnRemove");
 if(request.getParameter("ItemID") != null && "Remove".equals(remove)){
	 
	 DBConnect deleteitem = new DBConnect();
	 String stsMsg = deleteitem.DeleteItems(request.getParameter("ItemID"));
	 session.setAttribute("statusMsg", stsMsg);

 }
 //updating items
 String updatebtn = request.getParameter("btnUpdate"); //getting the value of the update button in table
 String submitupdate = request.getParameter("btnSubmit");  //getting the value of the update button in form
 
 if("Update".equals(submitupdate)){
	 DBConnect update = new DBConnect();	 
	 String stsMsg = update.UpdateItem(Integer.parseInt(request.getParameter("ItemID")) ,
									    request.getParameter("ItemCode"),
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
<link rel="stylesheet" href="Views/css/bootstrap.min.css">
<title>Items Management</title>
</head>


	   
	  
	 
<%

 //showing the details in form
if("Update".equals(updatebtn)){
	 
	 DBConnect update = new DBConnect();	 
	 out.print(update.readSelectedItem(Integer.parseInt(request.getParameter("ItemID"))));
		 
}else{
	 
	%>

<body>
<div class="container">
         <div class="row">
                 <div class="col">
	<form method="post" action="AddItems.jsp">
	
		Item code: <input name="ItemCode" type="text" class="form-control"><br>
		Item name: <input name="ItemName" type="text" class="form-control"><br>
		Item price: <input name="ItemPrice" type="text" class="form-control"><br>
		Item description: <input name="ItemDesc" type="text" class="form-control"><br>
		
		<input name="btnSubmit" type="submit" value="Save" class="btn btn-primary">
	</form>
	
	<div class ="alert alert-success">
	<% out.print(session.getAttribute("statusMsg")); %>
	</div>
	
<%

		}
	   
		DBConnect itemObj = new DBConnect();
        out.print(itemObj.readItems());
            
%>
 				</div>
 		</div> 	
 </div>

</body>


</html>