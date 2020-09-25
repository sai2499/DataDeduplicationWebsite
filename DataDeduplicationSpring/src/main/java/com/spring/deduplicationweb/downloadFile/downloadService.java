package com.spring.deduplicationweb.downloadFile;

import com.spring.deduplicationweb.connection.ConnectionDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Service
public class downloadService
{
    @Autowired
    public ConnectionDB con;

    public String download(int fileId) throws Exception
    {
        Integer[] shaId=retrieveShaId(fileId);
        String[] sha256=retrieveSha(shaId);
        String fileNameTry=getFileName(fileId);
        String[] changeName=fileNameTry.split("[_.]");
        int len=changeName.length-1;
        String fileName = changeName[0]+"(v"+getVersionNo(fileId)+")"+"."+changeName[len];
        createOriginal(sha256,fileName);
        return "File downloaded";
    }
    public String[] retrieveSha(Integer[] shaId) throws Exception
    {
        ArrayList<String> arrSha=new ArrayList<String>();
        PreparedStatement pstmt=con.getConnect().prepareStatement("select sha256 from hashtable where shaid=?");
        for(int i=0;i<shaId.length;i++)
        {
            pstmt.setInt(1,shaId[i]);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next())
            {
                arrSha.add(rs.getString(1));
            }
        }
        String[] sha256arr=new String[arrSha.size()];
        sha256arr=arrSha.toArray(sha256arr);
        return sha256arr;
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

    public String getFileName(int fileid) throws Exception
    {
        String filename="";
        PreparedStatement pstmt=con.getConnect().prepareStatement("select fileName from userFile where userfileId=?");
        pstmt.setInt(1,fileid);
        ResultSet rs=pstmt.executeQuery();
        while(rs.next())
        {
            filename=rs.getString(1);
        }
        return filename;
    }

    public void createOriginal(String[] sha256,String fileName) throws Exception
    {
        String str;
        String str1=" ";
        for(int i=0;i<sha256.length;i++)
        {
            String chuckName="D:/Project/ProjectFiles/chunks/"+sha256[i];
            File file=new File(chuckName);
            BufferedReader br=new BufferedReader(new FileReader(file));
            while((str=br.readLine())!=null)
            {
                str1=str1+str;
            }
        }
        String finalName="D:/Project/ProjectFiles/downloads/"+fileName;
        FileWriter fw=new FileWriter(finalName);
        fw.write(str1);
        fw.close();
    }
    public int getVersionNo(int fileId) throws Exception
    {
        int versionno=0;
        PreparedStatement pstmt = con.getConnect().prepareStatement("select versionNo from userfile where userFileId=?");
        pstmt.setInt(1,fileId);
        ResultSet rs=pstmt.executeQuery();
        while(rs.next())
        {
            versionno=rs.getInt(1);
        }
        return versionno;
    }
}
