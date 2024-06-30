package com.telusko.Spring_Boot_Rest;

import com.telusko.Spring_Boot_Rest.model.JobPost;
import com.telusko.Spring_Boot_Rest.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class JobRestController {
    @Autowired
    private JobService service;

    @GetMapping("jobPosts")

    public List<JobPost> getAllJobs(){
        return service.returnAllJobPosts();
    }

    @GetMapping("jobPost/{postId}")
    public JobPost getjob(@PathVariable("postId") int postId){
        return service.getJob(postId);
    }

    @PostMapping("jobPost")
    public void addJobPost(@RequestBody JobPost jobPost){
        service.addJobPost(jobPost);
    }
    @PutMapping("jobPost")
    public JobPost updateJob(@RequestBody JobPost jobPost) {
        service.updateJob(jobPost);
        return service.getJob(jobPost.getPostId());
    }




    @DeleteMapping("jobPost/{postId}")
    public String deleteJob(@PathVariable int postId)
    {
        service.deleteJob(postId);
        return "Deleted";
    }


    @GetMapping("load")
    public String loadData() {
        service.load();
        return "success";
    }

    @GetMapping("jobPosts/keyword/{keyword}")
    public List<JobPost> searchByKeyword(@PathVariable("keyword") String keyword){
         return service.search(keyword);

    }
}
