package com.spring.deduplicationweb.uploadFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

@RestController
@CrossOrigin("http://localhost:4200")
public class uploadFileService
{
    @Autowired
    public UploadController upload;

    @RequestMapping(value="/upload",method = RequestMethod.POST,consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public String fileUpload(@RequestParam("file") MultipartFile file,@RequestParam("id") String id) throws Exception
    {
        File convertFile= new File("D:/Project/ProjectFiles/Files"+file.getOriginalFilename());
        convertFile.createNewFile();
        try(FileOutputStream fOut=new FileOutputStream(convertFile))
        {
            fOut.write(file.getBytes());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        upload.insertIntoUserFile(Integer.parseInt(id),file.getOriginalFilename());
        return "File has been uploaded successfully";
    }
}
