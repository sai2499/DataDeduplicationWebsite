package com.spring.deduplicationweb.login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService 
{
	@Autowired
	public LoginCheck logcheck;	
	public int checkUser(String user,String pass) throws Exception
	{
		int id=logcheck.checklog(user, pass);
		if(id==0)
		{
			return 0;
		}
		else
		{ 
			return id;
		}
		
	}
}
