package com.example.demo.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Process {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;

	
	@ManyToMany(cascade= CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name = "process_tag",
     joinColumns = @JoinColumn(name = "process_id", referencedColumnName = "id"),
     inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
	@JsonManagedReference 
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Set<Tag> tags;
	
	
	
	public Process() {
		
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	
	
	
	
	
}
