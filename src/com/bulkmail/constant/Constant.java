package com.bulkmail.constant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class Constant {
	static Properties prop = new Properties();
	
	
	static String FilePath="/home/saurabh/config/BulkMail/config.properties";
//static String FilePath="D:\\config\\BulkMail\\Config.properties";
 static String lastModifieddate=GetLastFileModifiedDate();
	
	static{
	readfile();
	}
	
	
static public String getConfig(String key) 
	{
		
		
		if(!lastModifieddate.equalsIgnoreCase(GetLastFileModifiedDate()))
		{
		readfile();				lastModifieddate=GetLastFileModifiedDate();
		}
	
		String value=prop.getProperty(key, "");
		return value.trim();
	}


	
	
	static private String GetLastFileModifiedDate()
	{
		File file=new File(FilePath);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dt=sdf.format(file.lastModified());
		return dt;
}
	
	
	static private void readfile()
	{
		try {
			BufferedReader br = new BufferedReader(new FileReader(FilePath));
			prop.load(br);
			br.close();
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
}



