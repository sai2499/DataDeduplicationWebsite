package com.spring.deduplicationweb.login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins="http://localhost:4200")
@RestController
public class LoginResource 
{
	@Autowired
	public LoginService logservice;	
	
	@GetMapping("/data/{user}/{pass}/filepage")
	public int CheckUserAuth(@PathVariable String user,@PathVariable String pass) throws Exception
	{
		return logservice.checkUser(user,pass);
	}
}