package com.example.demo.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Process;
import com.example.demo.domain.Tag;
import com.example.demo.domain.User;
import com.example.demo.repo.ProcessRepository;
import com.example.demo.repo.TagRepository;
import com.example.demo.repo.UserRepository;
import com.example.demo.service.TagService;

@RestController()
public class ProcessResource {

	@Autowired
	ProcessRepository processRepository;
	
		
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TagService tagService;
	
	
	// -------- PROCESS 
	
	@GetMapping("/processes")
	public ResponseEntity<List<Process>> listProcesses() {
		List<Process> list = processRepository.findAll();
		return new ResponseEntity<List<Process>>(list, HttpStatus.OK);
		
	}
	
	@GetMapping("/processes/tags/{tag}")
	public List<Process> listProcessByTag(@PathVariable("tag") String tag) {
		Tag t = tagService.findByTag(tag);
		List<Process> list = new ArrayList<Process>();
		if (  t != null ) {
			list = processRepository.findAllByTags( t );
		}
		return list;
	}
	
	@DeleteMapping("/processes/{id}")
	public void deleteProcess(@PathVariable("id") Long id) {
		Process p =	processRepository.getOne(id);
		if ( p != null ) {
			p.getTags().clear();
			processRepository.delete(p);
		}
	}
	
	@PutMapping("/processes/{id}/tags/{tagId}")
	public Process tagProcess(@PathVariable("id") Long id, @PathVariable("tagId") Long tagId) {
		Process p = tagService.tag(id, tagId);
		return p;
	}
	
	// --------- TAGS
	
	@GetMapping("/tags")
	public List<Tag> listTags() {
		return tagService.listTags();
	}
	
	
	@DeleteMapping("/tags/{id}")
	public void deleteTag(@PathVariable("id") Long id) {
		tagService.deleteTag(id);
	}
	
	@GetMapping("/tags/{user}")
	public List<Tag> listTagByUser(@PathVariable("user") String user) {
		return tagService.listTagsByUser( user );
	}
	
	@PostMapping("/tags")
	public Tag createTag(@RequestBody CreateTagRequest createTagRequest) {
		User u = userRepository.findByUsername( createTagRequest.getUsername() );
		Tag t = null;
		if ( u != null ) {
			t = tagService.createTag(createTagRequest.getTag(), u);	
		}
		return t;
	}
	
	
	//------- USER
	
	
	@GetMapping("/users")
	public List<User> listUsers() {
		List<User> list = userRepository.findAll();
		return list;
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable("id") Long id) {
		User u = userRepository.getOne(id);
		if ( u != null ) {
			List<Tag> tags = tagService.listTagsByUser( u.getUsername() );
			for( Tag t : tags) {
				tagService.deleteTag(t.getId());
				
			}
			userRepository.delete(u);
		}
	}
	
	
}
