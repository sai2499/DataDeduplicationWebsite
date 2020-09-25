package com.spring.deduplicationweb.Chunks;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.deduplicationweb.connection.ConnectionDB;

@Service
public class ChunkService 
{
	@Autowired
	public ConnectionDB con;
	
	public List<ChunkData> getAllChunk=new ArrayList<>();
	public List<ChunkData> getFileChunk=new ArrayList<>();
	public List<ChunkCount> getChunkCount=new ArrayList<>();

	public List<ChunkData> GetAllChucks(int id) throws Exception
	{
		PreparedStatement pstmt=con.getConnect().prepareStatement("select shaId,rollHash,sha256 from hashTable");
		ResultSet rs=pstmt.executeQuery();
		getAllChunk.clear();
		while(rs.next())
		{
			getAllChunk.add(new ChunkData(rs.getInt(1),String.valueOf(rs.getInt(2)),rs.getString(3),rs.getLong(5)));
		}
		return getAllChunk;
	}

	public List<ChunkData> GetFileChunks(int id) throws Exception
	{
		PreparedStatement pstmt=con.getConnect().prepareStatement("select shaId,rollHash,sha256,chunkSize from hashTable where userFileId=?");
		pstmt.setInt(1,id);
		ResultSet rs=pstmt.executeQuery();
		getFileChunk.clear();
		while(rs.next())
		{
			getFileChunk.add(new ChunkData(rs.getInt(1),String.valueOf(rs.getInt(2)),rs.getString(3),rs.getLong(4)));
		}
		return getFileChunk;
	}

	public List<ChunkCount> GetChunkCount() throws Exception
	{
		PreparedStatement pStmt=con.getConnect().prepareStatement("select * from shaTable");
		ResultSet rs=pStmt.executeQuery();
		getChunkCount.clear();
		while(rs.next())
		{
			getChunkCount.add(new ChunkCount(rs.getString(1),rs.getInt(2)));
		}
		return getChunkCount;
	}
}
