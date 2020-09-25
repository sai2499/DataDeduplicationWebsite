package com.spring.deduplicationweb.FileDetails;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class FileDetailResource 
{
	@Autowired
	public FileDetailService filedetails;
	
	@GetMapping("/data/{id}/filepage")
	public List<FileDetails> CheckUserAuth(@PathVariable int id) throws Exception
	{
		return filedetails.userfiles(id);
	}


}
