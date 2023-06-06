package com.springProject.service;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Service;

import com.springProject.dto.CommentsDto;
import com.springProject.exception.PostNotFoundException;
import com.springProject.exception.SpringRedditException;
import com.springProject.exception.UsernameNotFoundException;
import com.springProject.mapping.CommentMapper;
import com.springProject.model.Comment;
import com.springProject.model.NotificationEmail;
import com.springProject.model.Post;
import com.springProject.model.User;
import com.springProject.repository.CommentRepository;
import com.springProject.repository.PostRepository;
import com.springProject.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentService {
	private final CommentRepository commentRepository;
	private final CommentMapper commentMapper;
	private final AuthService authService;
	private final PostRepository postRepository;
	private final UserRepository userRepository;
	private final mailService MailService;
	private static final String POST_URL = "";
	public void save(CommentsDto commentsDto) {
        Post post = postRepository.findById(commentsDto.getPostId())
        		.orElseThrow(() -> new PostNotFoundException(commentsDto.getPostId().toString()));;
        Comment comment = commentMapper.map(commentsDto, post, authService.getCurrentUser());
        commentRepository.save(comment);
        String message = mailContentBuilder.build(post.getUser().getUsername() + " posted a comment on your post." + POST_URL);
        sendCommentNotification(message, post.getUser());
    }
	private void sendCommentNotification(String message, User user) {
		MailService.sendMail(new NotificationEmail(user.getUsername() + " Commented on your post", user.getEmail(), message));
	}
	public List<CommentsDto> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId.toString()));
        return commentRepository.findByPost(post)
                .stream()
                .map(commentMapper::mapToDto).toList();
    }
	public List<CommentsDto> getAllCommentsForUser(String userName) {
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException(userName));
        return commentRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .toList();
    }
	public boolean containsSwearWords(String comment) {
        if (comment.contains("shit")) {
            throw new SpringRedditException("Comments contains unacceptable language");
        }
        return false;
    }
}
