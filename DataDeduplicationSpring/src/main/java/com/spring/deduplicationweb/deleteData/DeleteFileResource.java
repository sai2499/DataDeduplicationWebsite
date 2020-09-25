package com.spring.deduplicationweb.deleteData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.deduplicationweb.connection.ConnectionDB;

@Component
public class DeleteFileResource
{
	@Autowired
	public ConnectionDB con;	

	public boolean deleteFileData(int fileId) throws Exception
	{
		Map<String,Integer> mapCountFile = new HashMap<>();		
		System.out.println(fileId);
		Integer[] shaId = retrieveShaId(fileId); 
		String[] sha256 = retrieveSha(shaId);                
		PreparedStatement deleteHashTable=con.getConnect().prepareStatement("delete from hashTable where userFileId=?");
		PreparedStatement deleteUserFileTable = con.getConnect().prepareStatement("delete from userfile where userFileId=? ");
		deleteUserFileTable.setInt(1, fileId);
		deleteUserFileTable.addBatch();
		PreparedStatement deleteShaTable = con.getConnect().prepareStatement("delete from shaTable where sha256Value=?");
		PreparedStatement updateCount = con.getConnect().prepareStatement("update shaTable set shacount=? where sha256Value=?");
		PreparedStatement deleteFiledetailsTable = con.getConnect().prepareStatement("delete from fileDetails where userFileId=?");
		deleteFiledetailsTable.setInt(1, fileId);
		deleteFiledetailsTable.addBatch();
		PreparedStatement pstmtShaCount = con.getConnect().prepareStatement("select * from shaTable");
		ResultSet rs = pstmtShaCount.executeQuery();
		int count = 0;
		while (rs.next())
		{
			mapCountFile.put(rs.getString(1), rs.getInt(2));
		}
		System.out.println("Number of sha's:"+sha256.length);
		for (int i = 0; i < sha256.length; i++)
		{
			if (mapCountFile.containsKey(sha256[i]))
			{
				count = mapCountFile.get(sha256[i]);
				if (count <= 1)
				{
					deleteShaTable.setString(1, sha256[i]);
					deleteShaTable.addBatch();
				}
				else
				{
					count--;
					updateCount.setInt(1, count);
					updateCount.setString(2, sha256[i]);
					updateCount.addBatch();
				}
			}
			else
			{
				System.out.print("nothing found");
			}
		}
		deleteHashTable.setInt(1,fileId);
		deleteHashTable.addBatch();
		int[] deleteShaTableExe=deleteShaTable.executeBatch();
		int[] updateCountExe=updateCount.executeBatch();
		int[] deleteFileDetailsTableExe=deleteFiledetailsTable.executeBatch();
		int[] deleteUserFileTableExe=deleteUserFileTable.executeBatch();
		int[] deleteHashTableExe=deleteHashTable.executeBatch(); 
		con.getConnect().close();
		deleteHashTable.close();
		deleteShaTable.close();
		updateCount.close();
		deleteFiledetailsTable.close();
		deleteUserFileTable.close();
		return true;
	}

	public Integer[] retrieveShaId(int fileId) throws Exception
	{
		ArrayList<Integer> arr=new ArrayList<Integer>();
		PreparedStatement pstmt=con.getConnect().prepareStatement("select shaId from fileDetails where userFileId=?");
		pstmt.setInt(1,fileId);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next())
		{
			arr.add(rs.getInt(1));
		}
		Integer[] shaArr= new Integer[arr.size()];
		shaArr=arr.toArray(shaArr);
		return shaArr;
	}

	public String[] retrieveSha(Integer[] shaid) throws Exception
	{
		ArrayList<String> arrSha=new ArrayList<String>();		
		PreparedStatement pstmt=con.getConnect().prepareStatement("select sha256 from hashtable where shaid=?");
		for(int i=0;i<shaid.length;i++)
		{
			pstmt.setInt(1,shaid[i]);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				arrSha.add(rs.getString(1));
			}
		}
		String[] sha256arr=new String[arrSha.size()];
		sha256arr=arrSha.toArray(sha256arr);
		con.getConnect().close();
		pstmt.close();		
		return sha256arr;
	}

}
