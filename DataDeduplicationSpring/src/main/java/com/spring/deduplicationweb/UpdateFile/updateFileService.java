package com.spring.deduplicationweb.UpdateFile;

import com.spring.deduplicationweb.FileDetails.FileDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class updateFileService
{
    @Autowired
    public updateFileResource updateFile;

    @RequestMapping(value="/update",method = RequestMethod.POST,consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public String updateUpload(@RequestParam("file") MultipartFile file, @RequestParam("id") String id) throws Exception
    {
        File convertFile= new File("D:/Project/ProjectFiles/Update/"+file.getOriginalFilename());
        convertFile.createNewFile();
        try(FileOutputStream fOut=new FileOutputStream(convertFile))
        {
            fOut.write(file.getBytes());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        updateFile.update(Integer.parseInt(id),file.getOriginalFilename());
        return "File has been uploaded successfully";
    }

    @GetMapping("/data/{id}/fileVersionpage")
    public List<FileDetails> retrieveVersionDet(@PathVariable int id) throws Exception
    {
        return updateFile.retrieveVersion(id);
    }
}
