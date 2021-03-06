package com.spring.deduplicationweb.connection;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

import org.springframework.stereotype.Service;
@Service
public class ConnectionDB 
{
	Connection con;
	public Connection getConnect() throws Exception
	{
		FileInputStream fin=new FileInputStream("dbCred.properties");
		Properties pro=new Properties();
		pro.load(fin);
		String JDBC_DRIVER=pro.getProperty("driver");
		String DB_URL=pro.getProperty("url");
		String USER=pro.getProperty("user");
		String PASS=pro.getProperty("pass");
		Class.forName(JDBC_DRIVER);
		con= DriverManager.getConnection(DB_URL,USER,PASS);
		return	con;
	}
}
