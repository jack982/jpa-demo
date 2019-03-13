package com.example.demo.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {

	@Id
	private Long id;
	private String username;
	
	/*
	@OneToMany(
        mappedBy = "user",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
	private Set<Tag> tags;
	 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	/*
	public void addTag(Tag tag) {
        tags.add(tag);
        tag.setUser(this);
    }
 
    public void removeTag(Tag tag) {
        tags.remove(tag);
        tag.setUser(null);
    }
    */
	
	
	
}
