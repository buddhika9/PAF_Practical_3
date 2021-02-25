package com.items.Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.items.DAO.itemsDAAOimpl;

/**
 * Servlet implementation class ItemsController
 */
@WebServlet("/ItemsController")
public class ItemsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = (request.getParameter("ItemID"));
		itemsDAAOimpl itemObj = new itemsDAAOimpl();
		boolean output = itemObj.Deleteitems(id);
		
		if(output == true) {
			RequestDispatcher dis = request.getRequestDispatcher("AddItems.jsp");
			dis.forward(request, response);
		}
	
	}
	
	protected void UpdateItems(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {}

}
