package com.spring.deduplicationweb.signup;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.springframework.stereotype.Component;
import com.spring.deduplicationweb.connection.ConnectionDB;

@Component
public class SignUpQuery 
{
	public int InsertQuery(String user,String pass,String emailId) throws Exception
	{
		ConnectionDB con = new ConnectionDB();
		PreparedStatement stmt=con.getConnect().prepareStatement("Insert into userTable(userName,passwords,emailId) values(?,?,?)");
		stmt.setString(1,user);
		stmt.setString(2,pass);
		stmt.setString(3,emailId);
		stmt.executeUpdate();
		int id=retrieveId(user);
		return id;
	}
	public int retrieveId(String user) throws Exception
	{
		ConnectionDB con = new ConnectionDB();
		PreparedStatement stmt=con.getConnect().prepareStatement("select userId from userTable where userName=?");
		stmt.setString(1,user);
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
