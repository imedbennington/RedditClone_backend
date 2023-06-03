package com.springProject.service;

import org.springframework.stereotype.Service;

import com.springProject.dto.PostRequest;
import com.springProject.mapping.PostMapper;
import com.springProject.model.Subreddit;
import com.springProject.repository.PostRepository;
import com.springProject.repository.SubredditRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {
	private final PostRepository postRepository;
    private final SubredditRepository subredditRepository;
    private final PostMapper postMapper;
    private final AuthService authService;
	public void save(PostRequest postRequest) {
        Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName())
                .orElseThrow();
        postRepository.save(postMapper.map(postRequest, subreddit, authService.getCurrentUser()));
    }
}
