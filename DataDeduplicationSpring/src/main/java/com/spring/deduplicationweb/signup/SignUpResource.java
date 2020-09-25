package com.spring.deduplicationweb.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class SignUpResource 
{
	@Autowired
	public SignUpService signupresource;
	
	@GetMapping("/data/{user}/{pass}/{email}/filepage")
	public int InsertUser(@PathVariable String user,@PathVariable String pass,@PathVariable String email) throws Exception
	{
		return signupresource.insertUser(user,pass,email);
	}
	
}
