package com.springProject.service;

import java.util.List;

import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.springProject.repository.SubredditRepository;
import jakarta.transaction.Transactional;
import com.springProject.dto.SubRedditDto;
import com.springProject.exception.SpringRedditException;
import com.springProject.model.Subreddit;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.springProject.mapping.SubredditMapper;
@Service
@AllArgsConstructor
@Slf4j
public class SubredditService {
	private final SubredditRepository subredditRepository;
	private final SubredditMapper subRedditMapper;
	/*public void save (SubRedditDto subredditDto) {
		Subreddit subreddit = mapSubredditDto(subredditDto);
		subredditRepository.save(subreddit);
		}*/
	@Transactional
	public SubRedditDto save (SubRedditDto subredditdto) {
		Subreddit save = subredditRepository.save(subRedditMapper.mapDtoToSubreddit(subredditdto));
		subredditdto.setId(save.getId());
		return subredditdto;
	}
	//@Transactional(readOnly = true)
	/*public List<Subreddit> getAll() {
		return  subredditRepository.findAll()
		.stream()
		.map(this::mapToDto)
		.collect(Collectors.toList());
	}*/
	public List<SubRedditDto> getAll() {
        return subredditRepository.findAll()
                .stream()
                .map(subRedditMapper::mapSubredditToDto)
                .collect(Collectors.toList());
    }
	public SubRedditDto getSubreddit(Long id) {
        Subreddit subreddit = subredditRepository.findById(id)
                .orElseThrow(() -> new SpringRedditException("No subreddit found with ID - " + id));
        return subRedditMapper.mapSubredditToDto(subreddit);
    }
	/*
	private Subreddit mapSubredditDto(SubRedditDto subredditDto) {
		return Subreddit.builder().name(subredditDto.getName())
				.description(subredditDto.getDescription())
				.build();
		}
	private Subreddit mapToDto(Subreddit subreddit) {
		return Subreddit.builder().name(subreddit.getName())
				.id(subreddit.getId())
				.numberofPost(subreddit.getPosts().size())
				.build();
	}*/
}
