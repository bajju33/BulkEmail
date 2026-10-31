package com.bulkmail.model;

import java.util.ArrayList;

import com.bulkmail.bean.MailBean;
import com.bulkmail.dao.DaoFactory;

public class ProcessMailMethod {
public static void main(String[] args) {
	DaoFactory daoFactory=DaoFactory.getDao("");
	while(true)
	{
		try {
			ArrayList<MailBean> Ar=daoFactory.getMailForProcess();
			if(!Ar.isEmpty())
			{
				for(MailBean mailbean:Ar)
				{
					daoFactory.executeQuery("update mail set status=1 where id="+mailbean.getId()+";");
					ThreadForProcessMail threadForProcessMail=new ThreadForProcessMail("");
					threadForProcessMail.start(mailbean);
				}
			}
			Thread.sleep(1000);
			System.out.println("Mail process");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
}
