package com.spring.deduplicationweb.deleteData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:4200")
public class DeleteService
{
    @Autowired
    public DeleteFileResource deleteFile;

    @Autowired
    public DeleteUserResource deleteUser;

    @GetMapping("/data/filepage/{id}/deletefile")
    public boolean DeleteFileData(@PathVariable int id) throws Exception
    {
        if(deleteFile.deleteFileData(id))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @GetMapping("/data/user/{id}/deleteuser")
    public boolean DeleteUserData(@PathVariable int id) throws Exception
    {
        if(deleteUser.DeleteUserData(id))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
