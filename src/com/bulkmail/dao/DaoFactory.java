package com.bulkmail.dao;

import java.sql.ResultSet;
import java.util.ArrayList;


import com.bulkmail.bean.LoginBean;
import com.bulkmail.bean.MailBean;
import com.bulkmail.bean.SenderIdBean;

public abstract class DaoFactory {

	public static DaoFactory getDao(String SalesTrack)
	   {
	     return new SqlDao();
	   }
	
	public abstract  LoginBean login(LoginBean b);
public abstract int executeQuery(String query);
public abstract ResultSet getAllTemplate();
public abstract ResultSet getMailData(int id,int role);
public abstract ResultSet getAllUserTemplate(int userid);
public abstract ArrayList<MailBean> getMailForProcess();
public abstract String getTemplate(String id);
public abstract boolean checkTemplate(String temp,int userid);
public abstract ResultSet getCount(int id);
public abstract ResultSet getMailStatus(String id);
public abstract int insertIntoMail(String email,String mailid);

public abstract boolean checkEmailId(String email);

public abstract boolean checkSenderEmailId(String email);

public abstract ArrayList<SenderIdBean> getSenderMail();

public abstract ArrayList<SenderIdBean> getActiveSenderMail();

public abstract ArrayList<SenderIdBean> getAssignUserSenderMail();

public abstract ArrayList<LoginBean> getAllUserList();


public abstract boolean checkAssignEmailId(String sendermailid,String userid);

public abstract ArrayList<SenderIdBean> getAssignUserSender(int id);
public abstract SenderIdBean getSendeIdDetails(int id);
}
