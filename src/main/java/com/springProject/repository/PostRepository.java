package com.springProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springProject.model.Post;
import com.springProject.model.Subreddit;
import com.springProject.model.User;

public interface PostRepository extends JpaRepository <Post, Long>{
	 List<Post> findAllBySubreddit(Subreddit subreddit);
	 List<Post> findByUser(User user);
}
