package com.spring.deduplicationweb.login;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.deduplicationweb.connection.ConnectionDB;

@Component
public class LoginCheck
{
	@Autowired
	public ConnectionDB con;
	
	public int checklog(String user,String pass) throws Exception
	{
		PreparedStatement stmt=con.getConnect().prepareStatement("SELECT userId FROM userTable WHERE userName =? and passwords =?");
		stmt.setString(1,user);
		stmt.setString(2,pass);
		ResultSet rs=stmt.executeQuery();
		if(rs.next())
		{
			return rs.getInt(1);
		}
		else
		{
			return 0;
		}
	}
	
}
