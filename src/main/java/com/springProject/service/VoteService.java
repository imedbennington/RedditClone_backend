package com.springProject.service;

import org.apache.el.stream.Optional;
import org.springframework.stereotype.Service;

import com.springProject.dto.VoteDto;
import com.springProject.exception.PostNotFoundException;
import com.springProject.exception.SpringRedditException;
import com.springProject.model.Post;
import com.springProject.model.Vote;
import com.springProject.repository.PostRepository;
import com.springProject.repository.VoteRepository;
import static com.springProject.model.VoteType.UPVOTE;

import java.util.List;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VoteService {
	private final PostRepository  postRepository;
	private final VoteRepository voteRepository;
	private final AuthService authService;
	@Transactional
    public void vote(VoteDto voteDto) {
        Post post = postRepository.findById(voteDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException("Post Not Found with ID - " + voteDto.getPostId()));
        java.util.Optional<Vote> voteByPostAndUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());
        if (voteByPostAndUser.isPresent() &&
                voteByPostAndUser.get().getVoteType()
                        .equals(voteDto.getVoteType())) {
            throw new SpringRedditException("You have already "
                    + voteDto.getVoteType() + "'d for this post");
        }
        if (UPVOTE.equals(voteDto.getVoteType())) {
            post.setVoteCount(post.getVoteCount() + 1);
        } else {
            post.setVoteCount(post.getVoteCount() - 1);
        }
        voteRepository.save(mapToVote(voteDto, post));
        postRepository.save(post);
    }
	private Vote mapToVote(VoteDto voteDto, Post post) {
        return Vote.builder()
                .voteType(voteDto.getVoteType())
                .post(post)
                .user(authService.getCurrentUser())
                .build();
    }
}
