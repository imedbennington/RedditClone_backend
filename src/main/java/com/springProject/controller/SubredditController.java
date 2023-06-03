package com.springProject.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springProject.service.SubredditService;
import com.springProject.dto.SubRedditDto;
import com.springProject.model.Subreddit;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/subreddit")
@AllArgsConstructor
@Slf4j
public class SubredditController {
	private final SubredditService subredditService;
	@PostMapping
    public ResponseEntity<SubRedditDto> createSubreddit(@RequestBody SubRedditDto subredditDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(subredditService.save(subredditDto));
    }
	@GetMapping
	/*public void getAllReddit() {
		subredditService.getAll();
	}*/
	public ResponseEntity<List<SubRedditDto>> getAllSubreddits  (){
		return ResponseEntity
				.status(HttpStatus.OK).body( subredditService.getAll());
	}
}
