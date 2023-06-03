package com.springProject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springProject.dto.PostRequest;
import com.springProject.service.PostService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {
	 private final PostService postService;
	 @PostMapping
	    public ResponseEntity<Void> createPost(@RequestBody PostRequest postRequest) {
	        postService.save(postRequest);
	        return new ResponseEntity<>(HttpStatus.CREATED);
	    }
}
