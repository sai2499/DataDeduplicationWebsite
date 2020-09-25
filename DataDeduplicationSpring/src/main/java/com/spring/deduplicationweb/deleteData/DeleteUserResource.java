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
public class DeleteUserResource
{
	@Autowired
	public ConnectionDB con;

	public boolean DeleteUserData(int userId) throws Exception
	{
		int count=0;
		Map<String,Integer> mapCountUser= new HashMap<>();
		Integer[] allFileId =retrieveAllFileId(userId);
		String[] allShaValue = retrieveAllShaValue(userId);
		PreparedStatement deleteFromUserTable = con.getConnect().prepareStatement("delete from userTable where userId=?");
		PreparedStatement deleteFromUserFileTable = con.getConnect().prepareStatement("delete from userFile where userId=?");
		PreparedStatement deleteShaTable = con.getConnect().prepareStatement("delete from shaTable where sha256Value=?");
		PreparedStatement deleteFileDetailsTable = con.getConnect().prepareStatement("delete from fileDetails where userFileId=?");
		PreparedStatement deleteHashTable = con.getConnect().prepareStatement("delete from hashTable where userFileId=?");
		PreparedStatement updateCount = con.getConnect().prepareStatement("update shaTable set shacount = ? where sha256Value = ?");
		PreparedStatement retreiveShaValue = con.getConnect().prepareStatement("select * from shaTable");
		deleteFromUserTable.setInt(1,userId);
		deleteFromUserFileTable.setInt(1,userId);
		deleteFromUserTable.addBatch();
		deleteFromUserFileTable.addBatch();

		ResultSet rs = retreiveShaValue.executeQuery();

		while(rs.next())
		{
			mapCountUser.put(rs.getString(1),rs.getInt(2));
		}

		for(int i = 0 ; i < allShaValue.length ; i++)
		{
			count =  mapCountUser.get(allShaValue[i]);
			System.out.println(allShaValue[i] + "::" + count);
			if (count == 1)
			{
				deleteShaTable.setString(1, allShaValue[i]);
				deleteShaTable.addBatch();
			}
			else
			{
				count--;
				updateCount.setInt(1, count);
				updateCount.setString(2, allShaValue[i]);
				mapCountUser.put(allShaValue[i] , mapCountUser.get(allShaValue[i])-1);
				updateCount.addBatch();
			}
		}

		for(int i=0;i < allFileId.length;i++)
		{
			deleteHashTable.setInt(1 , allFileId[i]);
			deleteFileDetailsTable.setInt(1 , allFileId[i]);
			deleteHashTable.addBatch();
			deleteFileDetailsTable.addBatch();
		}

		int[] deleteShaTableExe = deleteShaTable.executeBatch();
		int[] updateCountExe = updateCount.executeBatch();
		int[] deleteFileDetailsTableExe = deleteFileDetailsTable.executeBatch();
		int[] deleteHashTableExe = deleteHashTable.executeBatch();
		int[] deleteFromUserFileTableExe = deleteFromUserFileTable.executeBatch();
		int[] deleteFromUserTableExe = deleteFromUserTable.executeBatch();
		con.getConnect().close();
		deleteShaTable.close();
		updateCount.close();
		deleteFileDetailsTable.close();
		deleteHashTable.close();
		deleteFromUserFileTable.close();
		deleteFromUserTable.close();
		return true;
	}
	
	public Integer[] retrieveAllFileId(int userId) throws Exception
    {        
        ArrayList<Integer> allFileIdArr=new ArrayList<>();
        PreparedStatement pstmt=con.getConnect().prepareStatement("select userFileId from userFile where userId=?");
        pstmt.setInt(1,userId);
        ResultSet rs=pstmt.executeQuery();
        while(rs.next())
        {
            allFileIdArr.add(rs.getInt(1));
        }
        Integer[] allFileId=new Integer[allFileIdArr.size()];
        allFileId=allFileIdArr.toArray(allFileId);
        con.getConnect().close();
        pstmt.close();
        return allFileId;
    }
    public String[] retrieveAllShaValue(int userId) throws Exception
    {
        Integer[] AllFileId = retrieveAllFileId(userId);
        ArrayList<String> allShaValuesArr = new ArrayList<>();
        PreparedStatement pstmt = con.getConnect().prepareStatement("select sha256 from hashTable where userFileId=?");

        for(int i=0 ; i < AllFileId.length;i++)
        {
            System.out.println("FILE IDS : " + AllFileId[i]);

            pstmt.setInt(1,AllFileId[i]);//1,2
            ResultSet rs=pstmt.executeQuery();

            while(rs.next())
            {
                allShaValuesArr.add(rs.getString(1));
            }
        }
        String[] allShaValues = new String[allShaValuesArr.size()];
        allShaValues = allShaValuesArr.toArray(allShaValues);
        con.getConnect().close();
        pstmt.close();
        return allShaValues;
    }

}
