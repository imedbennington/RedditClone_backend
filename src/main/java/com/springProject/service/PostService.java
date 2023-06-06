package com.springProject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.springProject.dto.PostRequest;
import com.springProject.exception.PostNotFoundException;
import com.springProject.exception.SubredditNotFoundException;
import com.springProject.exception.UsernameNotFoundException;
import com.springProject.mapping.PostMapper;
import com.springProject.model.Post;
import com.springProject.model.Subreddit;
import com.springProject.model.User;
import com.springProject.repository.PostRepository;
import com.springProject.repository.SubredditRepository;
import com.springProject.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.springProject.dto.*;
@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {
	private final PostRepository postRepository;
    private final SubredditRepository subredditRepository;
    private final PostMapper postMapper;
    private final AuthService authService;
    private final UserRepository userRepository;
	public void save(PostRequest postRequest) {
        Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName())
                .orElseThrow();
        postRepository.save(postMapper.map(postRequest, subreddit, authService.getCurrentUser()));
    }
	@Transactional
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());
    }
	@Transactional
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow();
        return postMapper.mapToDto(post);
    }
	@Transactional
    public List<PostResponse> getPostsBySubreddit(Long subredditId) {
        Subreddit subreddit = subredditRepository.findById(subredditId)
                .orElseThrow(() -> new SubredditNotFoundException(subredditId.toString()));
        List<Post> posts = postRepository.findAllBySubreddit(subreddit);
        return posts.stream().map(postMapper::mapToDto).collect(Collectors.toList());
    }
	@Transactional
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
