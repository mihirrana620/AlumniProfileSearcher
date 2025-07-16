package com.assignment.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.assignment.entity.AlumniProfile;

@Repository("alumniProfileDao")
public interface AlumniProfileDao extends JpaRepository<AlumniProfile, Long>{

	
	
}