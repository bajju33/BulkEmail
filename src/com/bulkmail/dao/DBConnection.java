package com.bulkmail.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import com.bulkmail.constant.Constant;

public class DBConnection{
static Connection con=null;
public static Connection dbConnection()
{
	try {
	Class.forName("com.mysql.jdbc.Driver");
	
	if (con == null) {
		con = DriverManager.getConnection(Constant.getConfig("DATABASE_URL"),Constant.getConfig("DATABASE_USERNAME"),Constant.getConfig("DATABASE_PASSWORD"));
	} else if (con.isClosed()) {
		con = DriverManager.getConnection(Constant.getConfig("DATABASE_URL"),Constant.getConfig("DATABASE_USERNAME"),Constant.getConfig("DATABASE_PASSWORD"));
	}
} 
catch (Exception e) {
	e.printStackTrace();
}
return con;
}
}