package com.bulkmail.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bulkmail.dao.DaoFactory;

/**
 * Servlet implementation class AddSenderMailServlet
 */
public class AddSenderMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddSenderMailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			DaoFactory daoFactory=DaoFactory.getDao("");
			String name="";
			String email="";
			String password="";
			String contactno="";
			String validityDate="";
			
			name=request.getParameter("mailname");
			
			email=request.getParameter("emailid");
			
			password=request.getParameter("password");
			
			
			boolean checkemail=daoFactory.checkEmailId(email);
			
			if(checkemail==false)
			{
			response.setContentType("text/html;charset=UTF-8");
	    	response.getWriter().write("SenderId SuccessFully Add");
	    	
	    	daoFactory.executeQuery("INSERT INTO `tbl_sendermail`(`mailid`,`password`,`username`)VALUE('"+email+"','"+password+"','"+name+"');");
			}
			else
			{
				response.setContentType("text/html;charset=UTF-8");
		    	response.getWriter().write("EmailId Already Exist");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	}

}
