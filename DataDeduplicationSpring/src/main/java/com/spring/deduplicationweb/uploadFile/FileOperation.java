package com.spring.deduplicationweb.uploadFile;

import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FileOperation
{
	public void createFile(int hash_val,String word, String hash256val) throws IOException
	{
		String filename = "D:/Project/ProjectFiles/chunks/"+hash256val;
		FileWriter fw = new FileWriter(filename);
		fw.write(word);
		fw.close();
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

	public static void deleteFile(String shaValue) throws Exception
	{
		String filename = "D:/Project/ProjectFiles/chunks/"+shaValue;
		boolean file=new File(filename).delete();
	}

}
