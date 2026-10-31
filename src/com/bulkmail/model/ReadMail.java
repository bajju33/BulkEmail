package com.bulkmail.model;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;
import javax.mail.search.FlagTerm;

import com.bulkmail.dao.DaoFactory;
import com.sun.mail.pop3.POP3Folder;
import com.sun.mail.pop3.POP3Store;

public class ReadMail {
	
	 public static void receiveEmail(String pop3Host, String storeType,  
			  String user, String password) {  
			  try {  
				  DaoFactory daoFactory=DaoFactory.getDao("");
			   //1) get the session object  
			   Properties properties = new Properties();  
			   properties.put("mail.host", pop3Host);  
			   properties.put("mail.port", "995");
			//   properties.put("mail.pop3.starttls.enable", "true");
			   properties.put("mail.transport.protocol", "imaps");
			      Session emailSession = Session.getDefaultInstance(properties);
			  
			      //create the POP3 store object and connect with the pop server
			      Store store = emailSession.getStore(storeType);

			      store.connect(pop3Host, user, password);

			   //3) create the folder object and open it  
			     
			   Folder emailFolder = store.getFolder("INBOX");  
			   emailFolder.open(Folder.READ_WRITE);  
			 
			   
			   Flags seen = new Flags(Flags.Flag.SEEN);
			    FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
			    Message messages[] = emailFolder.search(unseenFlagTerm);
			   
			   //4) retrieve the messages from the folder in an array and print it  
			//   Message[] messages = emailFolder.getMessages();  
			   String messgeid="";
			    for (int i = 0; i < messages.length; i++) {  
				   
			    Message message = messages[i];  
			    
			    MimeMessage msgdata =(MimeMessage) message;
               
			    message.setFlag(Flags.Flag.SEEN,true);
			    System.out.println("---------------------------------");  
			    System.out.println("Email Number " + (i + 1));  
			    System.out.println("Subject: " + message.getSubject());  
			    
			    if(message.getSubject().contains("(Failure)"))
			    {
			    	System.out.println("Mail is Fail");
			    	
			    	messgeid=  message.getHeader("References")[0];
			    	
			    	
			    	
			    	messgeid=messgeid.replace("<", "");
					messgeid=messgeid.replace(">", "");
					messgeid=messgeid.substring(0, messgeid.lastIndexOf("@"));
			    	
			    	daoFactory.executeQuery("update mail_report set `status`=1 where messageid='"+messgeid+"'");
			    }
			    
			    System.out.println("From: " + message.getFrom()[0]);  
			    System.out.println("Text: " + message.getContent().toString());  
			    System.out.println(msgdata.getMessageID());
			   }  
			 
			  
			   //5) close the store and folder objects  
			 //  emailFolder.close(false);  
			  // store.close();  
			  
			  } catch (NoSuchProviderException e) {e.printStackTrace();}   
			  catch (MessagingException e) {e.printStackTrace();}  
			  catch (IOException e) {e.printStackTrace();}  
			 }  
			  
			 public static void main(String[] args) {  
			  
			  String host = "imap.gmail.com";//change accordingly  
			  String mailStoreType = "imaps";  
			  String username= "infosudrive517@gmail.com";  
			  String password= "SuDrive@123";//change accordingly  
			  while(true)
			   {
			  receiveEmail(host, mailStoreType, username, password);  
			   try {
				   System.out.println("GOing For sleep");
				   
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			   }
			 }  
}
