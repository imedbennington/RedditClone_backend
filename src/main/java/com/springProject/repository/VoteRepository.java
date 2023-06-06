package com.springProject.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.springProject.model.Post;
import com.springProject.model.User;
import com.springProject.model.Vote;

public interface VoteRepository extends JpaRepository <Vote, Long>{
	Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}
