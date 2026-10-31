package com.bulkmail.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bulkmail.dao.DaoFactory;

/**
 * Servlet implementation class AddUserServlet
 */
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserServlet() {
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
			PrintWriter out=response.getWriter();
			DaoFactory daoFactory=DaoFactory.getDao("");
			String name="";
			String email="";
			String password="";
			String contactno="";
			String validityDate="";
			
			name=request.getParameter("name");
			
			email=request.getParameter("emailid");
			
			password=request.getParameter("password");
			contactno=request.getParameter("contact");
			validityDate=request.getParameter("ValidityDate");
			
			
			boolean checkemail=daoFactory.checkEmailId(email);
			
			if(checkemail==false)
			{
			response.setContentType("text/html;charset=UTF-8");
	    	response.getWriter().write("User SuccessFully Add");
	    	
	    	daoFactory.executeQuery("INSERT INTO `Login`(`Name`,`Password`,`Contact`,`Email`,`role`,`Create_Date`,`validityDate`)VALUE('"+name+"','"+password+"','"+contactno+"','"+email+"',0,now(),'"+validityDate+"');");
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
