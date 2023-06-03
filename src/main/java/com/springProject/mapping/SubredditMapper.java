package com.springProject.mapping;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.springProject.model.Post;
import com.springProject.model.Subreddit;
import com.springProject.dto.SubRedditDto;
@Mapper(componentModel = "spring")
public interface SubredditMapper {
	@Mapping(target = "numberOfPosts", expression = "java(mapPosts(subreddit.getPosts()))")
    SubRedditDto mapSubredditToDto(Subreddit subreddit);
	default Integer mapPosts(List<Post> numberOfPosts) {
        return numberOfPosts.size();
}		
	@InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    Subreddit mapDtoToSubreddit(SubRedditDto subredditDto);
}
