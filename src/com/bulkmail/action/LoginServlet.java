package com.bulkmail.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bulkmail.bean.LoginBean;
import com.bulkmail.dao.DaoFactory;

public class LoginServlet extends HttpServlet {
	Connection con=null;
	ResultSet rs=null;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
		PrintWriter out=response.getWriter();
		LoginBean b=new LoginBean();
		b.setEmail(request.getParameter("Email"));
		System.out.println(b.getEmail());
		b.setPassword(request.getParameter("Password"));
		System.out.println(b.getPassword());
		DaoFactory dao=DaoFactory.getDao("");
		
		try{
	LoginBean bean=dao.login(b);
	System.out.println(bean.isLoggedIn());
	  if(bean.isLoggedIn()==true) 
	    {
	    		HttpSession session=request.getSession();
	    		
	    		if(bean.getIs_active()==1)
	    		{
	    	session.setAttribute("login", bean);
	    		//response.sendRedirect("dashboard.jsp");
	    	
	    		
	    		response.setContentType("text/html;charset=UTF-8");
		    	response.getWriter().write("Login SuccessFully");
	    		}
	    		else
	    		{
	    			response.setContentType("text/html;charset=UTF-8");
			    	response.getWriter().write("Account Expired");
	    		}
		}
	  else
	    {
	    //	request.setAttribute("errorMessage", "Invalid Username and Password");
	    	//request.getRequestDispatcher("Login.jsp").forward(request, response);
	    	
	    	response.setContentType("text/html;charset=UTF-8");
	    	response.getWriter().write("Invalid Username and Password");
	    
	    }
		}
		catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

