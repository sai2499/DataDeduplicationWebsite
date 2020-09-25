package com.spring.deduplicationweb.downloadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:4200")
@RestController
public class downloadResource
{
    @Autowired
    public downloadService downService;

    @GetMapping("/data/user/{id}/download")
    public String downloadFile(@PathVariable String id) throws Exception
    {
        return downService.download(Integer.parseInt(id));
    }
}
