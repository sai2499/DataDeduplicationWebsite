package com.spring.deduplicationweb.UpdateFile;

import com.spring.deduplicationweb.FileDetails.FileDetails;
import com.spring.deduplicationweb.connection.ConnectionDB;

import com.spring.deduplicationweb.uploadFile.CreateChunk;
import com.spring.deduplicationweb.uploadFile.UploadController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class updateFileResource
{
    @Autowired
    public ConnectionDB con;

    @Autowired
    public UploadController up;

    @Autowired
    public CreateChunk d;

    public List<FileDetails> fileList=new ArrayList<>();

    public void update(int userId,String fileName) throws Exception
    {
        int versionCount=0;
        int fileId=retrieveFileId(userId,fileName);
        int versionNo=retrieveVersionNo(fileId);
        String newFileLocation="D:/Project/ProjectFiles/Update/"+fileName;
        if(checkFileName(userId,fileName))
        {
            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MM-yyyy|HH:mm:ss");
            String formattedDate = myDateObj.format(myFormatObj);
            String[] arr=fileName.split("\\.");
            String newFileName=arr[0]+"_"+formattedDate+"."+arr[1];
            versionCount=versionNo+1;
            PreparedStatement insertPstmt=con.getConnect().prepareStatement("insert into userFile (userId,fileName,versionNo,versionOf) values (?,?,?,?)");
            insertPstmt.setInt(1,userId);
            insertPstmt.setString(2,newFileName);
            insertPstmt.setInt(3,versionCount);
            insertPstmt.setInt(4,fileId);
            insertPstmt.executeUpdate();
            int versionId=retrieveVersionId(fileId,versionCount);
            d.createChunks(versionId,newFileLocation);
            up.insertVersionFileTable(versionId);
            LengthOfOriginalFile(versionId,fileName);
            String[] shaValue=retrieveShaValue(versionId);
            LengthOfChunkFile(shaValue);
        }
        else
        {
            System.out.println("No file found");
        }
    }
    public boolean checkFileName(int userId,String fileName) throws Exception
    {
        PreparedStatement pstmt=con.getConnect().prepareStatement("select * from userFile where userId=? and fileName=?");
        pstmt.setInt(1,userId);
        pstmt.setString(2,fileName);
        ResultSet rs=pstmt.executeQuery();
        if(rs.next())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public int retrieveFileId(int userId,String fileName) throws Exception
    {
        int userFileId=0;
        PreparedStatement pstmt=con.getConnect().prepareStatement("select userFileId from userFile where userId=? and fileName=?");
        pstmt.setInt(1,userId);
        pstmt.setString(2,fileName);
        ResultSet rs=pstmt.executeQuery();
        while(rs.next())
        {
            userFileId=rs.getInt(1);
        }
        return userFileId;
    }
    public int retrieveVersionNo(int fileId) throws Exception
    {
        PreparedStatement pstmt=con.getConnect().prepareStatement("select MAX(versionNo) from userFile where versionOf=?");
        pstmt.setInt(1,fileId);
        ResultSet rs=pstmt.executeQuery();
        int versionNo=0;
        while(rs.next())
        {
            versionNo=rs.getInt(1);
        }
        return versionNo;
    }
    public int retrieveVersionId(int fileId,int versionNo) throws Exception
    {
        PreparedStatement pstmt=con.getConnect().prepareStatement("select userFileId from userFile where versionOf=? and versionNo=?");
        pstmt.setInt(1,fileId);
        pstmt.setInt(2,versionNo);
        ResultSet rs=pstmt.executeQuery();
        int versionId=0;
        while(rs.next())
        {
            versionId=rs.getInt(1);
        }
        return versionId;
    }
    public List<FileDetails> retrieveVersion(int id) throws Exception
    {
        PreparedStatement pstmt=con.getConnect().prepareStatement("select * from userFile where userId=? and versionNo>1");
        pstmt.setInt(1,id);
        ResultSet rs=pstmt.executeQuery();
        fileList.clear();
        while(rs.next())
        {
            fileList.add(new FileDetails(rs.getInt(1),rs.getString(3),new Date(),rs.getLong(7)));
        }
        return fileList;
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
