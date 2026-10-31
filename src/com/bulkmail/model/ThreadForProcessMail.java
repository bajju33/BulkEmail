package com.bulkmail.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.json.JSONObject;

import com.bulkmail.bean.MailBean;
import com.bulkmail.bean.SenderIdBean;
import com.bulkmail.constant.Constant;
import com.bulkmail.dao.DaoFactory;

public class ThreadForProcessMail implements Runnable{

	private Thread t;
	private String ThreadName="";
	private DaoFactory daoFactory=DaoFactory.getDao("");
	private MailBean mailBean=new MailBean();
	static int d=0;
	public ThreadForProcessMail(String name)
	{
		this.ThreadName=name;
	}
	
	public void run() {
		try {
			System.out.println("mail statrt");
			String msgbody="";
			String imgtag="";
			int totalcount=0;
			int validcount=0;
			int invalidcount=0;
			  String regex = "^(.+)@(.+)$";	
			  Pattern pattern = Pattern.compile(regex);
			String folder=Constant.getConfig("Folder")+""+mailBean.getFile();
			String temp=daoFactory.getTemplate(mailBean.getTempid());
			System.out.println("temp name "+temp);
			BufferedReader br=new BufferedReader(new FileReader(folder));
			String line="";
			br.readLine();
			String mailtemp=getMailTemp(temp);
			String mailt="";
			String mail="";
			mailt=mailtemp;
			int counter=0;
			String mailid="";
			int reportid=0;
			int varcount=0;
			
			SenderIdBean senderIdBean=daoFactory.getSendeIdDetails(mailBean.getSendermailid());
			
			while((line=br.readLine())!=null)
			{
				//mailtemp=temp;
				totalcount++;
				
				mailtemp=mailt;
				
			String data[]=line.split(",");	
			mail=data[0];
			
			Matcher matcher = pattern.matcher(mail); 
			if(matcher.matches()==true )
			{
				
				
			//	varcount=getDymanicVarPart(mailtemp);
				
				
				
				for(int k=1;k<data.length;k++)
				{
					mailtemp=mailtemp.replace("{var"+k+"}", data[k]);
				}
				
				
			
			
			//System.out.println("MailBody "+mailtemp);
		//	mailid=Sendmail(mail,"<html><body>"+mailtemp+"<img src=\"https://www.google-analytics.com/collect?v=1&tid=UA-203137377-1&cid="+d+"&aip=1&t=event&ec=email&ea=open&el="+d+"&dp=%2Femail%2Fnewsletter&dt=My%20Newsletter\"></body></html>", mailBean.getSubject());
		
			reportid=daoFactory.insertIntoMail(mail, ""+mailBean.getId());
			imgtag="<img src=\"http://122.176.21.146:8080/BulkEmail/MailRecord?message_id="+reportid+"  width=\"\"0\"\" height=\"\"0\"\"style=\"\"width: 0px; height: 0px; border:0px;</html>";
		
			mailtemp=mailtemp.replace("</html>", "");
			msgbody=mailtemp+imgtag;
			/*
			 * mailid=Sendmail(mail,msgbody, mailBean.getSubject());
			 * mailid=mailid.replace("<", ""); mailid=mailid.replace(">", "");
			 * mailid=mailid.substring(0, mailid.lastIndexOf("@"));
			 * daoFactory.executeQuery("update  `mail_report` set messageid='"
			 * +mailid+"' where id="+reportid+";");
			 */
			
			ThreadForSendMail threadForSendMail=new ThreadForSendMail("");
			try {
				threadForSendMail.start(""+reportid, msgbody, mailBean.getSubject(), mail, senderIdBean);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			//threadForSendMail.Sendmail(mail, msgbody, mailBean.getSubject());
			
			//daoFactory.executeQuery("INSERT INTO `mail_report`(`mailid`,`email`,`sendtime`,messageid) VALUE ("+mailBean.getId()+",'"+mail+"',now(),'"+mailid+"')");
			validcount++;
				
			}
			else
			{
				invalidcount++;
			}
			
			counter++;
			if(counter==100)
			{
				System.out.println("process in sleep mood");
				daoFactory.executeQuery("update mail set `totaldata`="+totalcount+",`validdata`="+validcount+",`invaliddata`="+invalidcount+" where id="+mailBean.getId()+";");
				Thread.sleep(1000);
				counter=0;
			
			}
			}
			daoFactory.executeQuery("update mail set status=2, `totaldata`="+totalcount+",`validdata`="+validcount+",`invaliddata`="+invalidcount+" where id="+mailBean.getId()+";");
			
		} catch (Exception e) {
		e.printStackTrace();
		}
		
	}

	public void start(MailBean mail)
	{
		this.mailBean=mail;
		t=new Thread(this,ThreadName);
		t.start();
	}
	
	
	
	public String  Sendmail(String email,String body,String subject)
	{
		  String mailid="";
		try {
			String USER_NAME =Constant.getConfig("Sender");  // GMail user name (just the part before "@gmail.com")
		     String PASSWORD = Constant.getConfig("SenderPass"); // GMail password

	        String from = USER_NAME;
	        String pass = PASSWORD;
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
	        	message.setFrom(new InternetAddress(from, "SuDrives"));
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
	            
	            System.out.println("<"+mailid+"@localhost>");
	            System.out.println(message.getMessageID());
	            System.out.println("Sent mail "+body);
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
	 
	 public int getDymanicVarPart(String string)
		{
		 int count = 0;
        Pattern pattern = Pattern.compile("\\{[^}]+\\}"); // Match anything inside {}
        Matcher matcher = pattern.matcher(string);

        while (matcher.find()) {
            count++;
        }
        
        return count;

		}
	 public static String getRandomString() {
			StringBuilder builder = new StringBuilder();
			Random random = new Random();
		
			for (int i = 0; i <8; i++) {
				String abc = System.currentTimeMillis()+"";
				int n = random.nextInt(5);
				builder.append(abc.charAt(n));

			}
			return builder.toString();
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
	 
	 
	 public static String getMailTemp(String tempname)
	 {
		 String temp="";
		 String line="";
		 try {
			BufferedReader br=new BufferedReader(new FileReader(Constant.getConfig("TempPath")+""+tempname+".txt"));
			while((line=br.readLine())!=null)
			{
				JSONObject jsonObject=new JSONObject(line);
				temp=jsonObject.getString("html");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return temp;
	 }
	
}
