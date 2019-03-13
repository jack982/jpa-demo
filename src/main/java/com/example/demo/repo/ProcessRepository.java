package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Process;
import com.example.demo.domain.Tag;

public interface ProcessRepository extends JpaRepository<Process, Long> {

	public List<Process> findAllByTags(Tag t);
}
