package com.bulkmail.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.bulkmail.bean.LoginBean;
import com.bulkmail.bean.MailBean;
import com.bulkmail.constant.Constant;
import com.bulkmail.dao.DaoFactory;


/**
 * Servlet implementation class MailSchedule
 */
public class MailSchedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MailSchedule() {
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
		int i=0;
		HttpSession session=request.getSession();
		LoginBean bean=(LoginBean)session.getAttribute("login"); 
DaoFactory daoFactory=DaoFactory.getDao("");
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

MailBean mailbean=new MailBean();

		File uploadedFile = null;
		String fileName="";

		String folder=Constant.getConfig("Folder");
		FileItem fitem=null;
		System.out.println(isMultipart);
		System.out.println(folder);
		if (isMultipart) {
		FileItemFactory item = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(item);
		try
		{
		List items = upload.parseRequest(request);
		Iterator iterator = items.iterator();

		while (iterator.hasNext()) {

		fitem = (FileItem) iterator.next();

		if (!fitem.isFormField()) {
		                            String name=fitem.getName();
		                          

		fileName = getUniqueString()+""+fitem.getName();
		// System.out.println("File Name="+fileName);


		//System.out.println("In Condition");
		File path=new File(folder);
		if (!path.exists()) {
		path.mkdirs();
		}


		System.out.println("Location :"+folder +  fileName);
		uploadedFile = new File(folder +  fileName);
		fitem.write(uploadedFile);
		//leadBean.setCard(fileName);
		mailbean.setFile(fileName);                                                   
		}
		else{
		String htmltag=fitem.getFieldName();


		    if(htmltag.equalsIgnoreCase("emailName"))
		    {
		mailbean.setMailName(fitem.getString());
		    }   
		else if(htmltag.equalsIgnoreCase("subject"))
		   {
			   mailbean.setSubject(fitem.getString());
		   }
		    
		   else if(htmltag.equalsIgnoreCase("templateid"))
		   {
			   mailbean.setTempid(fitem.getString());
		   }
		   else if(htmltag.equalsIgnoreCase("ValidityDate"))
		   {
			   mailbean.setScheduledate(fitem.getString().replace(",", ""));
		   }
		   else if(htmltag.equalsIgnoreCase("sendermailid"))
		   {
			   mailbean.setSendermailid(Integer.parseInt(fitem.getString()));
		   }
		    
		}


		}
		System.out.println("Hotel name "+mailbean.getScheduledate());


		boolean check1=false;

		if(check1==true) 
		{
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write("This MailName already exists");
		}
			
		else  {
			daoFactory.executeQuery("INSERT INTO mail(`mailName`,`subject`,`file`,`userid`,`tempid`,`scheduledate`,senderid) value ('"+mailbean.getMailName()+"','"+mailbean.getSubject()+"','"+mailbean.getFile()+"',"+bean.getId()+","+mailbean.getTempid()+",'"+mailbean.getScheduledate()+"',"+mailbean.getSendermailid()+")");
				response.setContentType("text/html;charset=UTF-8");
			    response.getWriter().write("Mail Successfully Schedule");
			  }
		} catch (Exception e) {
		e.printStackTrace();
		}
		}
		} catch (Exception e) {
		e.printStackTrace();
		}
}

	public static String getUniqueString() {
		 int k =5;
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		 Calendar c = Calendar.getInstance();
		 StringBuilder builder = new StringBuilder();
		 String abc = "ABCDEFGHIJKLMNOPQRSTUVWZYZ1234567890"
		   + sdf.format(c.getTime());
		 Random random = new Random();
		 for (int i = 0; i < k; i++) {
		  int n = random.nextInt(36);
		  builder.append(abc.charAt(n));

		 }
		 return builder.toString();
		}
}
