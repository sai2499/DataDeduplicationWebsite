package com.spring.deduplicationweb.FileDetails;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.deduplicationweb.connection.ConnectionDB;

@Service
public class FileDetailService 
{
	private List<FileDetails> filedets=new ArrayList<>();
	
	@Autowired
	public ConnectionDB con;
	
	public void addFilesToList(int id) throws Exception
	{
		PreparedStatement pstmt=con.getConnect().prepareStatement("select * from userfile where userid=?");
		pstmt.setInt(1, id);
		ResultSet rs=pstmt.executeQuery();
		filedets.clear();
		while(rs.next())
		{
			filedets.add(new FileDetails(rs.getInt(1),rs.getString(3),new Date(),rs.getLong(7)));
		}
	}
		
	public List<FileDetails> userfiles(int id) throws Exception
	{
		if(id==0)
		{
			return null;
		}
		else
		{ 
			addFilesToList(id);			
			return filedets;
		}		
	}
	
	public FileDetails findById(int id) 
	{
		for (FileDetails fileDetails : filedets) 
		{
			if(fileDetails.getId()==id)
			{
				return fileDetails;
			}
		}
		return null;
	}

}
