package com.bulkmail.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.commons.lang.StringEscapeUtils;

import com.bulkmail.bean.LoginBean;
import com.bulkmail.bean.MailBean;
import com.bulkmail.bean.SenderIdBean;

public class SqlDao extends DaoFactory{

	@Override
	public LoginBean login(LoginBean bean) {
		  
		 Connection con=null;
		 ResultSet rs=null;
		  try { 
			  con=DBConnection.dbConnection(); 
		  PreparedStatement ps = con.prepareStatement("select id,Name,Email,Password,Contact,Status,role,if(role=0,1,if(validityDate>date(now()),1,0)) as is_active from Login where Email=? and Password=?  and status=1");
		  ps.setString(1,bean.getEmail()); 
		  ps.setString(2,bean.getPassword());
		 
		  rs=ps.executeQuery();
		  if(rs.next()) {
			  bean=new LoginBean(); // set any feild 
			  bean.setId(rs.getInt("id"));
			  bean.setName(rs.getString("Name"));
		  bean.setEmail(rs.getString("Email"));
		 bean.setPassword(rs.getString("Password"));
		 bean.setContact(rs.getString("Contact"));
		  bean.setStatus(rs.getInt("Status")); 
		  bean.setRole(rs.getInt("role"));
		  bean.setIs_active(rs.getInt("is_active"));
		bean.setLoggedIn(true);
		  }
			else
			{
				bean=new LoginBean();
				bean.setLoggedIn(false);
				
			}
		  } 
		  
		  catch (Exception e) 
		  {
			  e.printStackTrace();
			  }
		  return bean; 
		  }

	@Override
	public int executeQuery(String query) {
		Connection conn=null;
		Statement stmt=null;
				int i=0;
		try {
			System.out.println("Execute query "+query);
			conn=DBConnection.dbConnection();
			stmt=conn.createStatement();
			i=stmt.executeUpdate(query);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return i;
	}

	@Override
	public ResultSet getAllTemplate() {
		 Connection con=null;
		 ResultSet rs=null;
		 Statement stmt=null;
		 String query="";
		  try { 
			  con=DBConnection.dbConnection(); 
			  stmt=con.createStatement();
			  query="select id,getusername(`user_id`) as username,`tempname`,`templates`,`template_date` from template order by id desc";
			  rs=stmt.executeQuery(query);
			  
			  
		  }
		  catch (Exception e) {
				// TODO: handle exception
			}
		return rs;
	}

	@Override
	public ResultSet getAllUserTemplate(int userid) {
		 Connection con=null;
		 ResultSet rs=null;
		 Statement stmt=null;
		 String query="";
		  try { 
			  con=DBConnection.dbConnection(); 
			  stmt=con.createStatement();
			  query="select * from template where user_id="+userid+" order by id desc";
			  rs=stmt.executeQuery(query);
			  
			  
		  }
		  catch (Exception e) {
				// TODO: handle exception
			}
		return rs;
	}

	@Override
	public ArrayList<MailBean> getMailForProcess() {
		 Connection con=null;
		 ResultSet rs=null;
		 Statement stmt=null;
		 String query="";
		 ArrayList<MailBean> Ar=new ArrayList<MailBean>();
		 MailBean mailBean=new MailBean();
		  try { 
			  con=DBConnection.dbConnection(); 
			  stmt=con.createStatement();
			  query="select id,`subject`,`file`,`userid`,`tempid`,senderid from mail where status=0 and scheduledate<=now()";
			  rs=stmt.executeQuery(query);
			  while(rs.next())
			  {
				  mailBean=new MailBean();
				  mailBean.setId(rs.getInt("id"));
				  mailBean.setSubject(rs.getString("subject"));
				  mailBean.setUserid(rs.getString("userid"));
				  mailBean.setTempid(rs.getString("tempid"));
				  mailBean.setFile(rs.getString("file"));
				  mailBean.setSendermailid(rs.getInt("senderid"));
				  Ar.add(mailBean);
			  }
			  
		  }
		  catch (Exception e) {
				// TODO: handle exception
			}
		return Ar;
	}

	@Override
	public String getTemplate(String id) {
		 Connection con=null;
		 ResultSet rs=null;
		 Statement stmt=null;
		 String query="";
		 String templates="";
		  try { 
			  con=DBConnection.dbConnection(); 
			  stmt=con.createStatement();
			//  query="select templates from template where id="+id+";";
			  query="select tempname from template where id="+id+";";
			  rs=stmt.executeQuery(query);
			  if(rs.next())
			  {
				  templates=rs.getString("tempname");
			  }
			  
		  }
		  catch (Exception e) {
				e.printStackTrace();
			}
		return templates;
	}

	@Override
	public boolean checkTemplate(String temp, int userid) {
		 Connection con=null;
		 ResultSet rs=null;
		 Statement stmt=null;
		 String query="";
		 boolean status=false;
		  try { 
			  con=DBConnection.dbConnection(); 
			  stmt=con.createStatement();
			  query="select id from template where user_id="+userid+" and tempname='"+temp+"';";
			  System.out.println(query);
			  rs=stmt.executeQuery(query);
			  if(rs.next())
			  {
				  status=true;
			  }
			  
		  }
		  catch (Exception e) {
				// TODO: handle exception
			}
		return status;
	}

	@Override
	public ResultSet getMailData(int id,int role) {
		 Connection con=null;
		 ResultSet rs=null;
		 Statement stmt=null;
		 String query="";
		  try { 
			  con=DBConnection.dbConnection(); 
			  stmt=con.createStatement();
			  if(role==0)
			  {
			  query="select id,mailName,gettempname(tempid) as tempname,getusername(userid) as username,totaldata,validdata,invaliddata,status from mail order by id desc";
			  }
			  else
			  {
				  query="select id,mailName,gettempname(tempid) as tempname,getusername(userid) as username,totaldata,validdata,invaliddata,status from mail where userid="+id+" order by id desc";
			  }
			  System.out.println("Mail Data "+query);
			  rs=stmt.executeQuery(query);
			  
			  
		  }
		  catch (Exception e) {
				// TODO: handle exception
			}
		return rs;
	}

	@Override
	public ResultSet getCount(int id) {
		Connection con=null;
		 ResultSet rs=null;
		 Statement stmt=null;
		 String query="";
		  try { 
			  con=DBConnection.dbConnection(); 
			  stmt=con.createStatement();
			  query="SELECT IF(SUM(`status`=0) IS NULL,'0',SUM(`status`=0)) AS sent,IF(SUM(`status`=1) IS NULL,'0',SUM(`status`=1)) AS notdelivered,IF(SUM(`isopen`=1) IS NULL,'0',SUM(`isopen`=1)) AS open FROM `mail_report` WHERE mailid="+id;
			  rs=stmt.executeQuery(query);
		  }
		  catch (Exception e) {
			// TODO: handle exception
		}
		  return rs;
	}

	@Override
	public int insertIntoMail(String email, String mailid) {
		Connection con=null;
		PreparedStatement ps=null;
		int i=0;
		ResultSet rs = null;
		try {
			con= DBConnection.dbConnection();
		ps = con.prepareStatement("INSERT INTO `mail_report`(`mailid`,`email`,`sendtime`) values(?,?,now())",PreparedStatement.RETURN_GENERATED_KEYS);

		ps.setString(1, mailid);
		ps.setString(2, email);
		

		 ps.executeUpdate();
		  rs = ps.getGeneratedKeys();
		if (rs.next()) {
		i= rs.getInt(1);
		}
		 
		  System.out.println(" Insert MailReport "+i );
		 
		    }
		catch (Exception e1) {

		i=0;
		   }
		return i;
	}

	@Override
	public ResultSet getMailStatus(String id) {
		Connection con=null;
		 ResultSet rs=null;
		 Statement stmt=null;
		 String query="";
		  try { 
			  con=DBConnection.dbConnection(); 
			  stmt=con.createStatement();
			  query="SELECT `email`, IF(`status`=0,'Sent','NotDelivered') AS `s`,IF(`isopen`=0,'No','Yes') AS o FROM `mail_report` WHERE mailid="+id;
			  rs=stmt.executeQuery(query);
		  }
		  catch (Exception e) {
			// TODO: handle exception
		}
		  return rs;
	}

	@Override
	public boolean checkEmailId(String email) {

		  
		 Connection con=null;
		 ResultSet rs=null;
		 boolean status=false;
		  try { 
			  con=DBConnection.dbConnection(); 
		  PreparedStatement ps = con.prepareStatement("select id,Name,Email,Password,Contact,Status,role from Login where Email=? ");
		  ps.setString(1,email); 
		 
		  rs=ps.executeQuery();
		  if(rs.next()) {
			  status=true;
		  }
		  } 
		  
		  catch (Exception e) 
		  {
			  e.printStackTrace();
			  }
		  return status; 
		  
	}

	@Override
	public boolean checkSenderEmailId(String email) {
		  
		 Connection con=null;
		 ResultSet rs=null;
		 boolean status=false;
		  try { 
			  con=DBConnection.dbConnection(); 
		  PreparedStatement ps = con.prepareStatement("select id from tbl_sendermail where Email=? ");
		  ps.setString(1,email); 
		 
		  rs=ps.executeQuery();
		  if(rs.next()) {
			  status=true;
		  }
		  } 
		  
		  catch (Exception e) 
		  {
			  e.printStackTrace();
			  }
		  return status; 
		  
	
	}

	@Override
	public ArrayList<SenderIdBean> getSenderMail() {
		 Connection con=null;
		 ResultSet rs=null;
		 Statement stmt=null;
		 String query="";
		 ArrayList<SenderIdBean> Ar=new ArrayList<SenderIdBean>();
		 SenderIdBean senderIdBean=new SenderIdBean();
		 try {
			 con=DBConnection.dbConnection(); 
			  stmt=con.createStatement();
			  query="select id,`mailid`,`password`,`username` from tbl_sendermail order by id desc;";
			  System.out.println(query);
			  rs=stmt.executeQuery(query);
			  while(rs.next())
			  {
				  senderIdBean=new SenderIdBean();
				  senderIdBean.setId(rs.getInt("id"));
				  senderIdBean.setName(rs.getString("username"));
				  senderIdBean.setSender_email(rs.getString("mailid"));
				  senderIdBean.setSender_pass(rs.getString("password"));
				  Ar.add(senderIdBean);
			  }
		} catch (Exception e) {
			// TODO: handle exception
		}
		 
		return Ar;
	}

	@Override
	public ArrayList<LoginBean> getAllUserList() {

		 Connection con=null;
		 ResultSet rs=null;
		 Statement stmt=null;
		 String query="";
		 ArrayList<LoginBean> Ar=new ArrayList<LoginBean>();
		 LoginBean loginBean=new LoginBean();
		 try {
			 con=DBConnection.dbConnection(); 
			  stmt=con.createStatement();
			  query="select id,Name,Email,Password,Contact,Status,role from Login where status=1 order by id desc;";
			  System.out.println(query);
			  rs=stmt.executeQuery(query);
			  while(rs.next())
			  {
				  loginBean=new LoginBean();
				  loginBean.setId(rs.getInt("id"));
				  loginBean.setName(rs.getString("Name"));
				  Ar.add(loginBean);
			  }
		} catch (Exception e) {
			// TODO: handle exception
		}
		 
		return Ar;
	
	}

	@Override
	public ArrayList<SenderIdBean> getActiveSenderMail() {

		 Connection con=null;
		 ResultSet rs=null;
		 Statement stmt=null;
		 String query="";
		 ArrayList<SenderIdBean> Ar=new ArrayList<SenderIdBean>();
		 SenderIdBean senderIdBean=new SenderIdBean();
		 try {
			 con=DBConnection.dbConnection(); 
			  stmt=con.createStatement();
			  query="select id,`mailid`,`password`,`username` from tbl_sendermail where status=1 order by id desc;";
			  System.out.println(query);
			  rs=stmt.executeQuery(query);
			  while(rs.next())
			  {
				  senderIdBean=new SenderIdBean();
				  senderIdBean.setId(rs.getInt("id"));
				  senderIdBean.setName(rs.getString("username"));
				  senderIdBean.setSender_email(rs.getString("mailid"));
				  senderIdBean.setSender_pass(rs.getString("password"));
				  Ar.add(senderIdBean);
			  }
		} catch (Exception e) {
			// TODO: handle exception
		}
		 
		return Ar;
	
	}

	@Override
	public boolean checkAssignEmailId(String sendermailid, String userid) {
		  
		 Connection con=null;
		 ResultSet rs=null;
		 boolean status=false;
		  try { 
			  con=DBConnection.dbConnection(); 
		  PreparedStatement ps = con.prepareStatement("SELECT id FROM `assign_sendermail` WHERE `user_id`=? AND `assign_id`=? ");
		  ps.setString(1,userid); 
		  ps.setString(2,sendermailid); 
		 
		  rs=ps.executeQuery();
		  if(rs.next()) {
			  status=true;
		  }
		  } 
		  
		  catch (Exception e) 
		  {
			  e.printStackTrace();
			  }
		  return status; 
		  
	
	}

	@Override
	public ArrayList<SenderIdBean> getAssignUserSenderMail() {


		 Connection con=null;
		 ResultSet rs=null;
		 Statement stmt=null;
		 String query="";
		 ArrayList<SenderIdBean> Ar=new ArrayList<SenderIdBean>();
		 SenderIdBean senderIdBean=new SenderIdBean();
		 try {
			 con=DBConnection.dbConnection(); 
			  stmt=con.createStatement();
			  query="SELECT asd.id,l.Name,ts.mailid FROM `assign_sendermail`  asd ,`Login` l,`tbl_sendermail` ts WHERE asd.status=1 AND asd.user_id=l.id AND asd.assign_id=ts.id order by asd.id desc;";
			  System.out.println(query);
			  rs=stmt.executeQuery(query);
			  while(rs.next())
			  {
				  senderIdBean=new SenderIdBean();
				  senderIdBean.setAssignid(rs.getInt("id"));
				  senderIdBean.setUsername(rs.getString("Name"));
				  senderIdBean.setSender_email(rs.getString("mailid"));
				  Ar.add(senderIdBean);
			  }
		} catch (Exception e) {
			// TODO: handle exception
		}
		 
		return Ar;
	
	}

	@Override
	public ArrayList<SenderIdBean> getAssignUserSender(int id) {



		 Connection con=null;
		 ResultSet rs=null;
		 Statement stmt=null;
		 String query="";
		 ArrayList<SenderIdBean> Ar=new ArrayList<SenderIdBean>();
		 SenderIdBean senderIdBean=new SenderIdBean();
		 try {
			 con=DBConnection.dbConnection(); 
			  stmt=con.createStatement();
			  query="SELECT asd.id,l.Name,ts.mailid,ts.id as m_id FROM `assign_sendermail`  asd ,`Login` l,`tbl_sendermail` ts WHERE asd.status=1 AND asd.user_id=l.id AND asd.assign_id=ts.id and asd.user_id="+id+" order by asd.id desc;";
			  System.out.println(query);
			  rs=stmt.executeQuery(query);
			  while(rs.next())
			  {
				  senderIdBean=new SenderIdBean();
				  senderIdBean.setId(rs.getInt("m_id"));
				  senderIdBean.setAssignid(rs.getInt("id"));
				  senderIdBean.setUsername(rs.getString("Name"));
				  senderIdBean.setSender_email(rs.getString("mailid"));
				  Ar.add(senderIdBean);
			  }
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		return Ar;
	
	
	}

	@Override
	public SenderIdBean getSendeIdDetails(int id) {


		 Connection con=null;
		 ResultSet rs=null;
		 Statement stmt=null;
		 String query="";
		 ArrayList<SenderIdBean> Ar=new ArrayList<SenderIdBean>();
		 SenderIdBean senderIdBean=new SenderIdBean();
		 try {
			 con=DBConnection.dbConnection(); 
			  stmt=con.createStatement();
			  query="select id,`mailid`,`password`,`username` from tbl_sendermail where id="+id+" order by id desc;";
			  System.out.println(query);
			  rs=stmt.executeQuery(query);
			  while(rs.next())
			  {
				  senderIdBean=new SenderIdBean();
				  senderIdBean.setId(rs.getInt("id"));
				  senderIdBean.setName(rs.getString("username"));
				  senderIdBean.setSender_email(rs.getString("mailid"));
				  senderIdBean.setSender_pass(rs.getString("password"));
			  }
		} catch (Exception e) {
			// TODO: handle exception
		}
		 
		return senderIdBean;
	
	
	}

	
}
