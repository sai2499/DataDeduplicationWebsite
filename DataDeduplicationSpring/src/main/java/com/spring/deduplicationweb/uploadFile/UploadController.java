package com.spring.deduplicationweb.uploadFile;

import com.spring.deduplicationweb.connection.ConnectionDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Component
public class UploadController
{
    @Autowired
    public ConnectionDB con;

    @Autowired
    public CreateChunk chucks;

    public String fileLocation;

    public void insertIntoUserFile(int userId,String fileName) throws Exception
    {
        PreparedStatement pstmt=con.getConnect().prepareStatement("insert into userFile (userId,fileName,versionNo) values(?,?,?)");
        pstmt.setInt(1,userId);
        pstmt.setString(2,fileName);
        pstmt.setInt(3,1);
        pstmt.executeUpdate();
        int fileId=retrieveFileId(fileName);
        PreparedStatement updatePtmt=con.getConnect().prepareStatement("update userFile set versionOf=? where userFileId=? ");
        updatePtmt.setInt(1,fileId);
        updatePtmt.setInt(2,fileId);
        updatePtmt.executeUpdate();
        LengthOfOriginalFile(fileId,fileName);
        upload(fileId,fileName);
        insertIntoFileTable(fileId,fileName);
        String[] shaValue=retrieveShaValue(fileId);
        LengthOfChunkFile(shaValue);
    }

    public void upload(int fileId,String fileName) throws Exception
    {
        int n = 1;
        for(int i =0; i<n; i++)
        {
            fileLocation = "D:/Project/ProjectFiles/Files"+fileName;
            chucks.createChunks(fileId,fileLocation);
        }
    }

    public void insertIntoFileTable(int FileId,String fileName ) throws Exception
    {
        int fileId=retrieveFileId(fileName);
        Integer[] sha256Id=retrieveShaId(fileId);
        PreparedStatement pstmt = con.getConnect().prepareStatement("insert into fileDetails values(?,?)");
        for(int i=0;i<sha256Id.length;i++)
        {
            pstmt.setInt(1,FileId);
            pstmt.setInt(2,sha256Id[i]);
            pstmt.addBatch();
        }
        int[] records=pstmt.executeBatch();
    }

    public void insertVersionFileTable(int fileId) throws Exception
    {
        Integer[] sha256Id=retrieveShaId(fileId);
        PreparedStatement pstmt = con.getConnect().prepareStatement("insert into fileDetails values(?,?)");
        for(int i=0;i<sha256Id.length;i++)
        {
            pstmt.setInt(1,fileId);
            pstmt.setInt(2,sha256Id[i]);
            pstmt.addBatch();
        }
        int[] records=pstmt.executeBatch();
    }

    public int retrieveFileId(String fileName) throws Exception
    {
        int fileId = 0;
        PreparedStatement pstmt = con.getConnect().prepareStatement("select userFileId from userFile where fileName=?");
        pstmt.setString(1,fileName);
        ResultSet rs=pstmt.executeQuery();
        while(rs.next())
        {
            fileId=rs.getInt(1);
        }
        return fileId;
    }

    public Integer[] retrieveShaId(int fileId) throws Exception
    {
        ArrayList<Integer> arr=new ArrayList<Integer>();
        PreparedStatement pstmt = con.getConnect().prepareStatement("select shaId from hashTable where userFileId=? ");
        pstmt.setInt(1,fileId);
        ResultSet rs=pstmt.executeQuery();
        while(rs.next())
        {
            arr.add(rs.getInt(1));
        }
        Integer[] arrId = new Integer[arr.size()];
        arrId = arr.toArray(arrId);
        return arrId;
    }
    public void LengthOfOriginalFile(int fileId, String FileName) throws Exception
    {
        String locationFile="D:/Project/ProjectFiles/Files"+FileName;
        File fin= new File(locationFile);
        long filelen=0;
        if(fin.exists())
        {
            filelen=fin.length()/1024;
        }
        PreparedStatement pstmt=con.getConnect().prepareStatement("update userFile set fileSize=? where userFileId=?");
        pstmt.setLong(1,filelen+1);
        pstmt.setInt(2,fileId);
        pstmt.executeUpdate();
    }
    public void LengthOfChunkFile(String[] sha256list) throws Exception
    {
        PreparedStatement pstmt=con.getConnect().prepareStatement("update hashTable set chunkSize=? where sha256=?");
        String fileLocation="";
        long filelen=0;
        for(int i=0;i<sha256list.length;i++)
        {
            fileLocation="D:/Project/ProjectFiles/chunks/"+sha256list[i];
            File fin=new File(fileLocation);
            if(fin.exists())
            {
                filelen=fin.length()/1024;
                pstmt.setLong(1,filelen+1);
                pstmt.setString(2,sha256list[i]);
                pstmt.addBatch();
            }
            else
            {
                System.out.println("File not found");
                break;
            }
        }
        int[] arr=pstmt.executeBatch();
    }
    public String[] retrieveShaValue(int fileId) throws Exception
    {
        ArrayList<String> arr=new ArrayList<String>();
        PreparedStatement pstmt=con.getConnect().prepareStatement("select sha256 from hashTable where userFileId=?");
        pstmt.setInt(1,fileId);
        ResultSet rs=pstmt.executeQuery();
        while(rs.next())
        {
            arr.add(rs.getString(1));
        }
        String[] testing = new String[arr.size()];
        testing=arr.toArray(testing);
        return testing;
    }

}
