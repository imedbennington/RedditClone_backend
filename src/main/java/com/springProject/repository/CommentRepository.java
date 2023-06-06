package com.springProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springProject.model.Comment;
import com.springProject.model.Post;
import com.springProject.model.User;

public interface CommentRepository extends JpaRepository<Comment, Long>{
	List<Comment> findByPost(Post post);
    List<Comment> findAllByUser(User user);
}
