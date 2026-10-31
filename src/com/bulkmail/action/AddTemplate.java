package com.bulkmail.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bulkmail.bean.LoginBean;
import com.bulkmail.dao.DaoFactory;

/**
 * Servlet implementation class AddTemplate
 */
public class AddTemplate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTemplate() {
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
		// TODO Auto-generated method stub
		try {
			HttpSession session=request.getSession();
			String template="";
			String tempname="";
			template=request.getParameter("template").replace("&lt;", " <").replace("&gt;", ">").replace("&nbsp", " ");
			template=template.replace(";", "");
			tempname=request.getParameter("tempname");
			DaoFactory daoFactory=DaoFactory.getDao("");
			LoginBean bean=(LoginBean)session.getAttribute("login"); 
			
		
			boolean check1=daoFactory.checkTemplate(tempname, bean.getId());

			if(check1==true) 
			{
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write("TemplateName Already Exists");
			}
				
			else  {
				daoFactory.executeQuery("INSERT INTO `template`(`user_id`,`tempname`,`templates`,`template_date`) value ("+bean.getId()+",'"+tempname+"','"+template+"',now())");
					response.setContentType("text/html;charset=UTF-8");
				    response.getWriter().write("Template Create Successfully");
				  }
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
