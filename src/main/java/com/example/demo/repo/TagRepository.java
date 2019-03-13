package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Tag;
import com.example.demo.domain.User;

public interface TagRepository extends JpaRepository<Tag, Long> {
	
	
	Tag findByTag(String tag);
	
	List<Tag> findByUser(User user);
	
	Tag findByTagAndUser(String tag, User user);

}
