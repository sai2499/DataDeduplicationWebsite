package com.spring.deduplicationweb.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpService 
{
	@Autowired
	public SignUpQuery signupquery;
	
	public int insertUser(String user, String pass,String email) throws Exception
	{
		int id=signupquery.InsertQuery(user, pass, email);
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
