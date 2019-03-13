package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Process;
import com.example.demo.domain.Tag;
import com.example.demo.domain.User;
import com.example.demo.repo.ProcessRepository;
import com.example.demo.repo.TagRepository;
import com.example.demo.repo.UserRepository;

@Transactional
@Service
public class TagService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ProcessRepository processRepository;

	@Autowired
	TagRepository tagRepository;

	
	@Deprecated
	public List<Tag> listTags() {
		List<Tag> list = tagRepository.findAll();
		return list;
	}
	
	public Tag getTag(Long id) {
		return tagRepository.getOne( id );
	}
	
	// should be byTag AND USER
	public Tag findByTag(String tag) {
		return tagRepository.findByTag( tag );
	}
	
	public List<Tag> listTagsByUser(String username) {
		List<Tag> tags = new ArrayList<>();
		User u = userRepository.findByUsername( username );
		if ( u != null ) {
			tags = tagRepository.findByUser( u );
		}
		return tags;
	}
	
	public void deleteTag(Long id) {
		Tag t = tagRepository.getOne(id);
		if (t != null) {
			List<Process> processes = processRepository.findAllByTags(t);
			for (Process p : processes) {
				p.getTags().remove(t);
			}
			t.getProcesses().clear();
			tagRepository.delete(t);
		}
	}

	public Tag createTag(String tag, User user) {
		Tag t = tagRepository.findByTagAndUser( tag, user );
		if ( t == null ) {
			t = new Tag();
			t.setTag( tag );
			t.setUser( user );
			t = tagRepository.save( t );
		}
		return t;
	}
	
	public Process tag(Long processId, Long tagId) {
		Tag t = this.getTag(tagId);
		Process p = processRepository.getOne(processId);
		
		p.getTags().add(t);
		p = processRepository.save(p);
		
		return p;
	}
	

}
