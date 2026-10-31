package com.bulkmail.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bulkmail.dao.DaoFactory;

/**
 * Servlet implementation class AssignSenderMailServlet
 */
public class AssignSenderMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AssignSenderMailServlet() {
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
			String username="";
			String senderid="";
			
			username=request.getParameter("username");
			senderid=request.getParameter("sendermailid");
			
			DaoFactory daoFactory=DaoFactory.getDao("");
			
			boolean checksender=daoFactory.checkAssignEmailId(senderid, username);
			
			if(checksender==true)
			{
				response.setContentType("text/html;charset=UTF-8");
		    	response.getWriter().write("EmailId Already Assign");
			}
			else
			{
				daoFactory.executeQuery("INSERT INTO assign_sendermail(user_id,assign_id)VALUE("+username+","+senderid+");");
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().write("SenderMail SuccessFully Assign");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
