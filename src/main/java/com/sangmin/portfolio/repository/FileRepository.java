package com.sangmin.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sangmin.portfolio.model.File;

public interface FileRepository extends JpaRepository<File, Long> {

  
}