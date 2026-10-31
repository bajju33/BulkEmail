package com.bulkmail.model;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.bulkmail.bean.MailBean;
import com.bulkmail.bean.SenderIdBean;
import com.bulkmail.constant.Constant;
import com.bulkmail.dao.DaoFactory;

public class ThreadForSendMail  implements Runnable{

	private Thread t;
	private String ThreadName="";
	private String ReportId="";
	private String mailbody="";
	private String mailsubject="";
	private String email="";
	private SenderIdBean senderIdBean=new SenderIdBean();
	
	private DaoFactory daoFactory=DaoFactory.getDao("");
	private MailBean mailBean=new MailBean();
	static int d=0;
	public ThreadForSendMail(String name)
	{
		this.ThreadName=name;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//System.out.println(senderIdBean.getSender_email());
	//	System.out.println(senderIdBean.getSender_pass());
		
		String mailid=Sendmail(this.email,this.mailbody,mailsubject,senderIdBean.getSender_email(),senderIdBean.getSender_pass());
		mailid=mailid.replace("<", "");
		mailid=mailid.replace(">", "");
		mailid=mailid.substring(0, mailid.lastIndexOf("@"));
		daoFactory.executeQuery("update  `mail_report` set messageid='"+mailid+"' where id="+this.ReportId+";");
	}
	
	public void start(String reportmailid,String bodymail,String subjectmail,String toemail,SenderIdBean newseSenderIdBean)
	{
		this.ReportId=reportmailid;
		this.mailbody=bodymail;
		this.mailsubject=subjectmail;
		this.email=toemail;
		this.senderIdBean=newseSenderIdBean;
		t=new Thread(this, ThreadName);
		t.start();
	}

	public String  Sendmail(String email,String body,String subject,String from,String pass)
	{
		  String mailid="";
		try {
			String USER_NAME =Constant.getConfig("Sender");  // GMail user name (just the part before "@gmail.com")
		     String PASSWORD = Constant.getConfig("SenderPass"); // GMail password

	        //String from = USER_NAME;
	        //String pass = PASSWORD;
	        String[] to = email.split(",") ; // list of recipient email addresses

	         mailid=sendFromGMail(from, pass, to, subject, body);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mailid;
        
    
	}
	
	 private static String sendFromGMail(String from, String pass, String[] to, String subject, String body) {
	        Properties props = System.getProperties();
	        String host = "smtp.gmail.com";
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.auth.mechanisms", "login");
	        props.put("mail.smtp.host", host);
	        props.put("mail.smtp.user", from);
	        props.put("mail.smtp.password", pass);
	        props.put("mail.smtp.port", "587");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.dsn.notify","SUCCESS,FAILURE ORCPT=rfc2822;"+from);
	        props.put("mail.smtp.dsn.ret","FULL");
	        String mailid="NA";
	        Session session = Session.getDefaultInstance(props);
	        MimeMessage message = new MimeMessage(session);
	        try {
	        	message.setFrom(new InternetAddress(from, "ACS"));
	          //  message.setFrom(new InternetAddress(from));
	            InternetAddress[] toAddress = new InternetAddress[to.length];

	            // To get the array of addresses
	            for( int i = 0; i < to.length; i++ ) {
	                toAddress[i] = new InternetAddress(to[i]);
	            }

	            for( int i = 0; i < toAddress.length; i++) {
	                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
	            }
	            mailid=getEnglish(9)+"."+d+"."+getEnglish(13);
	           // message.setHeader( "Return-Receipt-To ",from);
	          //  message.setHeader("Message-ID","<"+mailid+"@localhost>");
	           // message.setHeader("Disposition-Notification-To", from);
	            //message.setHeader("mailer.Message.ConfirmRead", from);
	           
	            message.setSubject(subject);
	            message.setContent(body, "text/html");
	            Transport transport = session.getTransport("smtp");
	            transport.connect(host, from, pass);
	            transport.sendMessage(message, message.getAllRecipients());
	            transport.close();
	           // mailid=message.getMessageID();
	            mailid=message.getMessageID();
	            
	         //   System.out.println("<"+mailid+"@localhost>");
	          //  System.out.println(message.getMessageID());
	           // System.out.println("Sent mail "+body);
	            d++;
	        }
	        catch (AddressException ae) {
	            ae.printStackTrace();
	        }
	        catch (MessagingException me) {
	            me.printStackTrace();
	        } catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return mailid;
	    }

	 
	 public static String getEnglish(int n)
	 {

		 
		 
		 String AlphaNumericString = "0123456789";

// create StringBuffer size of AlphaNumericString
StringBuilder sb = new StringBuilder(20);

for (int i = 0; i < n; i++) {

// generate a random number between
// 0 to AlphaNumericString variable length
int index
= (int)(AlphaNumericString.length()
 * Math.random());

// add Character one by one in end of sb
sb.append(AlphaNumericString
   .charAt(index));
}

return sb.toString();

	 }
}
