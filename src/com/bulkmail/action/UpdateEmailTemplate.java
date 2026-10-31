package com.bulkmail.action;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.bulkmail.bean.LoginBean;
import com.bulkmail.constant.Constant;
import com.bulkmail.dao.DaoFactory;

/**
 * Servlet implementation class UpdateEmailTemplate
 */
public class UpdateEmailTemplate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateEmailTemplate() {
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
	//	System.out.println(request.getParameter("htmldata"));
		try {
			 PrintWriter out = response.getWriter();
			HttpSession session=request.getSession();
			JSONObject jsonObject=new JSONObject(request.getParameter("htmldata"));
			//System.out.println(jsonObject.toString());
			//System.out.println(jsonObject.get("html"));
			
			String tempname=request.getParameter("tempname");
			System.out.println(tempname);
			LoginBean bean=(LoginBean)session.getAttribute("login"); 
			
			DaoFactory daoFactory=DaoFactory.getDao("");
			
			File file=new File(Constant.getConfig("TempPath")+""+tempname+".txt");
			file.delete();
				BufferedWriter bw=new BufferedWriter(new FileWriter(Constant.getConfig("TempPath")+""+tempname+".txt"));
				bw.write(jsonObject.toString());
				bw.close();
				
				response.setContentType("application/json");
				JSONObject obj = new JSONObject();
		        obj.put("message", "Template Create Successfully");
		        out.print(obj.toString());
				  
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
