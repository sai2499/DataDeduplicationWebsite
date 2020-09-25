package com.spring.deduplicationweb.Chunks;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class ChunkResource 
{
	@Autowired
	public ChunkService chuckService;
	
	@GetMapping("/data/{id}/allChunks")
	public List<ChunkData> getAllChunks(@PathVariable int id) throws Exception
	{
		return chuckService.GetAllChucks(id); 
	}

	@GetMapping("/data/{id}/fileChunks")
	public List<ChunkData> getFileChunks(@PathVariable int id) throws Exception
	{
		return chuckService.GetFileChunks(id);
	}

	@GetMapping("/data/ChunkCount")
	public List<ChunkCount> getChunkCount() throws Exception
	{
		return chuckService.GetChunkCount();
	}
}
